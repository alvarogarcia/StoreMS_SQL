package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import alb.util.BusinessException;
import alb.util.console.Printer;
import alb.util.jdbc.Jdbc;

import uo.ri.persistence.FacturasGateway;

public class FacturasGatewayImpl implements FacturasGateway {
	
	private Connection connection;

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
		
	}

	@Override
	public Long getLastInvoiceNumber() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement("select max(numero) from TFacturas");
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getLong(1); // +1 el siguiente
			} else { // todavía no hay ninguna
				return 0L;
			}
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public Map<String, Object> findById(Long id) throws BusinessException {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Map<String, Object> mapa = new HashMap<String, Object>();
		try {
			pst = connection.prepareStatement("select ID, Descripcion, " +
					"Fecha, importe, status, factura_id, mecanico_id, vehiculo_id from TAverias where id = ?");
			pst.setLong(1, id);
			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException("No existe la averia "
						+ id);
			}
			
			mapa.put("ID", rs.getString("id"));
			mapa.put("DESCRIPCION", rs.getString("Descripcion"));
			mapa.put("FECHA", rs.getString("Fecha"));
			mapa.put("IMPORTE", rs.getString("Importe"));
			mapa.put("STATUS", rs.getString("Status"));
			mapa.put("FACTURA_ID", rs.getString("Factura_id"));
			mapa.put("MECANICO_ID", rs.getString("Mecanico_id"));
			mapa.put("VEHICULO_ID", rs.getString("Vehiculo_id"));
			
		} catch (SQLException e) {
			Printer.printException("Excepción SQL en método findById de la clase FacturasGatewayImpl", e);
		}
		finally{
			Jdbc.close(rs, pst);
		}
		
		return mapa;
		
	}

	@Override
	public List<Map<String, Object>> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long save(Map<String, Object> iv) throws SQLException {
		PreparedStatement pst = null;

		try {
			pst = connection.prepareStatement("insert into TFacturas(numero, fecha, iva, importe, status) "
			+ "	values(?, ?, ?, ?, ?)");
			pst.setLong(1, (Long) iv.get("numeroFactura"));
			pst.setDate(
					2,
					new java.sql.Date(((Date) iv.get("fechaFactura")).getTime()));
			pst.setDouble(3, (Double) iv.get("iva"));
			pst.setDouble(4, (Double) iv.get("importe"));
			pst.setString(5, "SIN_ABONAR");

			pst.executeUpdate();
			return getGeneratedKey((Long) iv.get("numeroFactura")); // Id de la
			// nueva
			// factura
			// generada
			

		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Map<String, Object> mapa) throws SQLException {
		String actualizar = "update TAverias set";
		@SuppressWarnings("rawtypes")
		Iterator it = mapa.entrySet().iterator();
		
		PreparedStatement pst = null;

		while (it.hasNext()) {
		@SuppressWarnings("rawtypes")
		Map.Entry e = (Map.Entry)it.next();
		if(!e.getKey().equals("ID")){
			actualizar += " " + e.getKey().toString() + " = '" +  e.getValue().toString() + "',";
		}
		
		}	
		actualizar = actualizar.substring(0, actualizar.length() - 1);
		actualizar += " WHERE ID = '" + mapa.get("ID") + "'";
		
		try{
		pst = connection.prepareStatement(actualizar);
		pst.executeUpdate();
		}finally{
			Jdbc.close(pst);
		}
	}

	@Override
	public Map<String, Object> findByNumber(Long num) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public double consultaManoObra(Long idAveria) throws SQLException, BusinessException{
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_IMPORTE_MANO_OBRA = "select sum(i.minutos * tv.precioHora / 60) "
				+ "	from TAverias a, TIntervenciones i, TVehiculos v, TTiposVehiculo tv"
				+ "	where i.averia_id = a.id "
				+ "		and a.vehiculo_id = v.id"
				+ "		and v.tipo_id = tv.id"
				+ "		and a.id = ?"
				+ "		and a.status = 'TERMINADA'";

		try {
			pst = connection.prepareStatement(SQL_IMPORTE_MANO_OBRA);
			pst.setLong(1, idAveria);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException(
						"La averia no existe o no se puede facturar");
			}

			return rs.getDouble(1);

		} catch (BusinessException e) {
			throw e;
		} finally {
			Jdbc.close(rs, pst);
		}
	}
	
	public double consultaImporteRepuestos(Long idAveria) throws SQLException{
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_IMPORTE_REPUESTOS = "select sum(s.cantidad * r.precio) "
				+ "	from  TSustituciones s, TRepuestos r "
				+ "	where s.repuesto_id = r.id "
				+ "		and s.intervencion_averia_id = ?";

		try {
			pst = connection.prepareStatement(SQL_IMPORTE_REPUESTOS);
			pst.setLong(1, idAveria);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				return 0.0; // La averia puede no tener repuestos
			}

			return rs.getDouble(1);

		} finally {
			Jdbc.close(rs, pst);
		}
	}
	
	private long getGeneratedKey(long numeroFactura) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL_RECUPERAR_CLAVE_GENERADA = "select id from TFacturas where numero = ?";

		try {
			pst = connection.prepareStatement(SQL_RECUPERAR_CLAVE_GENERADA);
			pst.setLong(1, numeroFactura);
			rs = pst.executeQuery();
			rs.next();

			return rs.getLong(1);

		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
