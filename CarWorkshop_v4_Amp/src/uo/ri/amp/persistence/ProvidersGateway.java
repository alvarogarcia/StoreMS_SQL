package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface ProvidersGateway {

	/**
	 * Establece la conexión
	 * 
	 * @param c
	 *            Objeto de Tipo Connection para establecer la conexión con el
	 *            Gateway
	 */
	void setConnection(Connection c);

	/**
	 * Añade un proveedor a la base de datos. Sólo se requiere el nombre del
	 * proveedor ya que el ID es generado automáticamente
	 * 
	 * @param providerName
	 *            Nombre del proveedor
	 */
	void addProvider(String providerName);

	/**
	 * Elimina un proveedor
	 * 
	 * @param providerCode
	 *            Código del proveedor a eliminar
	 */
	void deleteProvider(Long providerCode);

	/**
	 * Actualizar el nombre del proveedor
	 * 
	 * @param providerCode
	 *            Código del proveedor del que se quiere actualizar el nombre
	 * @param providerName
	 *            Nombre nuevo del proveedor
	 */
	void updateProviderName(Long providerCode, String providerName);

	/**
	 * Añade un repuesto a un proveedor
	 * 
	 * @param replacementCode
	 *            Código del repuesto
	 * @param providerCode
	 *            Código del proveedor
	 * @param price
	 *            Precio establecido para este repuesto por parte de este
	 *            proveedor.
	 */
	void addReplacementToProvider(Long replacementCode, Long providerCode,
			Double price);

	/**
	 * Elimina un repuesto de un proveedor
	 * 
	 * @param replacementCode
	 *            Código del repuesto a elimina
	 * @param providerCode
	 *            Código del proveedor a eliminar
	 */
	void deleteReplacementFromProvider(Long replacementCode, Long providerCode);

	/**
	 * Actualiza el repuesto de un proveedor
	 * 
	 * @param replacementCode
	 *            Código del repuesto
	 * @param providerCode
	 *            Código del proveedor
	 * @param newPrice
	 *            Nuevo precio
	 */
	void updateReplacementFromProvider(Long replacementCode, Long providerCode,
			Double newPrice);

	/**
	 * Comprueba que repuestos hay en la base de datos dado un código de
	 * repuesto O un ćodigo de proveedor
	 * 
	 * @param replacementCode
	 *            Código del repuesto
	 * @param providerCode
	 *            Código del proveedor
	 * @param isReplacementCode
	 *            Si vale true, la búsqueda se realizará por código de repuesto.
	 *            En caso contrario, por código de proveedor.
	 * @return Devuelve una lista que contiene mapas, cada uno de ellos con los
	 *         detalles de un repuesto
	 */
	List<Map<String, Object>> checkWhatReplacementToUpdateOrDelete(
			Long replacementCode, Long providerCode, boolean isReplacementCode);

	/**
	 * Comprueba que proveedores hay en la base datos dado el nombre que tienen
	 * 
	 * @param nameProviderBeforeUpdating
	 *            Nombre del elemento
	 * @return
	 */
	List<Map<String, Object>> checkWhatProviderToUpdate(
			String nameProviderBeforeUpdating);

	/**
	 * Devuelve una lista con la información de todos los proveedores
	 * 
	 * @return lista con la información de todos los proveedores
	 */
	List<Map<String, Object>> listAllProviders();
}
