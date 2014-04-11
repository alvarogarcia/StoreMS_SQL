package uo.ri.business.impl.cash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.FacturasGateway;

import alb.util.BusinessException;
import alb.util.date.DateUtil;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;

public class CreateInvoiceFor {


	private static final String SQL_UPDATE_IMPORTE_AVERIA = "update TAverias set importe = ? where id = ?";

	
	List<Long> idsAveria;

	Map<String, Object> invoice = new HashMap<String, Object>();

	private Connection connection;

	FacturasGateway fG;

	/**Constructor de la clase CreateInvoiceFor
	 * @param idsAveria
	 */
	public CreateInvoiceFor(List<Long> idsAveria) {
		this.idsAveria = idsAveria;
	}

	public Map<String, Object> execute() throws BusinessException {

		try {
			connection = Jdbc.getConnection();

			connection.setAutoCommit(false);

			fG = PersistenceFactory.getFacturasGateway();

			fG.setConnection(connection);

			verificarAveriasTerminadas(idsAveria);

			long numeroFactura = generarNuevoNumeroFactura();
			Date fechaFactura = DateUtil.today();
			double totalFactura = calcularImportesAverias(idsAveria);
			double iva = porcentajeIva(totalFactura, fechaFactura);
			double importe = totalFactura * (1 + iva / 100);
			importe = Round.twoCents(importe);

			invoice.put("numeroFactura", numeroFactura);
			invoice.put("fechaFactura", fechaFactura);
			invoice.put("totalFactura", totalFactura);
			invoice.put("iva", iva);
			invoice.put("importe", importe);

			// long idFactura = crearFactura(numeroFactura, fechaFactura, iva,
			// importe);
			long idFactura = crearFactura(invoice);
			vincularAveriasConFactura(idFactura, idsAveria);
			cambiarEstadoAverias(idsAveria, "FACTURADA");

			// mostrarFactura(numeroFactura, fechaFactura, totalFactura, iva,
			// importe);

			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
			}
			;
			throw new RuntimeException(e);
		} catch (BusinessException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
			}
			;
			throw e;
		} finally {
			Jdbc.close(connection);

		}
		return invoice;

	}

	/**
	 * Verifica las Averías terminadas
	 * @param idsAveria
	 * @throws SQLException
	 * @throws BusinessException
	 */
	private void verificarAveriasTerminadas(List<Long> idsAveria)
			throws SQLException, BusinessException {

		try {
			for (Long idAveria : idsAveria) {
				String status = (String) fG.findById(idAveria).get("STATUS");

				if (!"TERMINADA".equalsIgnoreCase(status)) {
					throw new BusinessException("No está terminada la avería "
							+ idAveria);
				}
			}
		} finally {
			// Jdbc.close(rs, pst);
		}

	}

	/**
	 * Cambia el estado de una lista de averías
	 * @param idsAveria Lista de averías a las que se le va a cambiar el estado
	 * @param status Estado 
	 * @throws SQLException
	 */
	private void cambiarEstadoAverias(List<Long> idsAveria, String status)
			throws SQLException {

		for (Long idAveria : idsAveria) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("STATUS", status);
			mapa.put("ID", idAveria);
			fG.update(mapa);
		}
	}

	/**
	 * Vincula una lista de averías con una factura
	 * @param idFactura id de la factura a con las que se van a vincular las averías
	 * @param idsAveria lista de averías que van a tener como factura la pasada como parámetro anterior
	 * @throws SQLException
	 */
	private void vincularAveriasConFactura(long idFactura, List<Long> idsAveria)
			throws SQLException {

		for (Long idAveria : idsAveria) {
			Map<String, Object> mapa = new HashMap<String, Object>();
			mapa.put("factura_id", idFactura);
			mapa.put("ID", idAveria);
			fG.update(mapa);
		}

	}

	/**
	 * Crea una factura
	 * @param iv Mapa que contiene todos los elementos que se necesitan para crear la factura.
	 * @return
	 * @throws SQLException
	 */
	private long crearFactura(Map<String, Object> iv) throws SQLException {
		return fG.save(iv);

	}

	

	/** 
	 * Genera el nuevo número para la nueva factura, que será el número de la última factura + 1
	 *  ya que van secuencialmente
	 * @return Número de la última factura + 1
	 * @throws SQLException
	 */
	private Long generarNuevoNumeroFactura() throws SQLException {
		return fG.getLastInvoiceNumber() + 1;
	}

	/**
	 * Devuelve 18 o 21 según el porcentaje de IVA que se deba aplicar según la fecha de la factura
	 * @param totalFactura Total de la factura SIN IVA
	 * @param fechaFactura Fecha de la factura para poder calcular que valor debe de tomar el IVA a aplicar
	 * @return
	 */
	private double porcentajeIva(double totalFactura, Date fechaFactura) {
		return DateUtil.fromString("1/7/2012").before(fechaFactura) ? 21.0
				: 18.0;
	}

	/**
	 * Calcula el de cada avería y lo mete en la base de datos
	 * @param idsAveria Lista de averías de las que se va a calcular el importe
	 * @return IMporte total de la factura SIN IVA
	 * @throws BusinessException
	 * @throws SQLException
	 */
	protected double calcularImportesAverias(List<Long> idsAveria)
			throws BusinessException, SQLException {

		double totalFactura = 0.0;
		for (Long idAveria : idsAveria) {
			double importeManoObra = consultaImporteManoObra(idAveria);
			double importeRepuestos = consultaImporteRepuestos(idAveria);
			double totalAveria = importeManoObra + importeRepuestos;

			actualizarImporteAveria(idAveria, totalAveria);

			totalFactura += totalAveria;
		}
		return totalFactura;
	}

	/**
	 * Consulta cuanto supone la mano de obra a través del Gateway
	 * @param idAveria
	 * @return
	 * @throws SQLException
	 * @throws BusinessException
	 */
	private double consultaImporteManoObra(Long idAveria) throws SQLException,
			BusinessException {
		return fG.consultaManoObra(idAveria);
	}

	private void actualizarImporteAveria(Long idAveria, double totalAveria)
			throws SQLException {
		PreparedStatement pst = null;

		try {
			pst = connection.prepareStatement(SQL_UPDATE_IMPORTE_AVERIA);
			pst.setDouble(1, totalAveria);
			pst.setLong(2, idAveria);
			pst.executeUpdate();
		} finally {
			Jdbc.close(pst);
		}
	}

	/**
	 * Consulta el importe de los repuestos
	 * @param idAveria
	 * @return
	 * @throws SQLException
	 */
	private double consultaImporteRepuestos(Long idAveria) throws SQLException {
		return fG.consultaImporteRepuestos(idAveria);
	}

}
