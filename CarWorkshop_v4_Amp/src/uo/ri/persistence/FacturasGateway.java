package uo.ri.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import alb.util.BusinessException;

public interface FacturasGateway {

	/**
	 * Establece la conexión para el Dateway
	 * @param connection Conexión ya establecida
	 */
	void setConnection(Connection connection);
	
	/**
	 * Devuelve el número de identificación de la última factura
	 * @return número de identificación de la última factura
	 * @throws SQLException
	 */
	Long getLastInvoiceNumber() throws SQLException;
	
	/**
	 * Encuentra una fatura buscando por id
	 * @param id id de la factura a devolver
	 * @return Mapa que contiene todos los datos de la factura
	 * @throws BusinessException
	 */
	Map<String, Object> findById(Long id) throws BusinessException;
	
	/**
	 * @return Devuelve todas las facturas
	 */
	List<Map<String, Object>> findAll();
	
	/**
	 * Guarda una factura
	 * @param sv Datos de la factura a  guardar
	 * @return ID de la factura guardada
	 * @throws SQLException
	 */
	Long save(Map<String, Object> sv) throws SQLException;
	
	/**
	 * Borra una factura
	 * @param id id de la factura a eliminar
	 */
	void delete(Long id);
	
	/**
	 * Actualiza una factura
	 * @param up Datos de la factura a actualizar
	 * @throws SQLException
	 */
	void update(Map<String, Object> up) throws SQLException;
	
	/**
	 * Encuentra una factura
	 * @param num ID de la factura a encontrar
	 * @return Mapa con los datos de la factura
	 */
	Map<String, Object> findByNumber(Long num);

	/**
	 * @param idAveria id de la avería de la que se quiere consultar la mano de obra
	 * @return importe de la mano de obra
	 * @throws SQLException
	 * @throws BusinessException
	 */
	double consultaManoObra(Long idAveria) throws SQLException, BusinessException;

	/**
	 * Importe de los repuestos
	 * @param idAveria id de la avería de la que se quiere consultar el importe de los repuestos
	 * @return Importe de los repuestos
	 * @throws SQLException
	 */
	double consultaImporteRepuestos(Long idAveria) throws SQLException;

}
