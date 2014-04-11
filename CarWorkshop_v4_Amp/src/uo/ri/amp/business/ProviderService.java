package uo.ri.amp.business;

import java.util.List;
import java.util.Map;

/**
 * @author �lvaro Garc�a
 * 
 */
public interface ProviderService {

	/**
	 * A�ade un proveedor a la base de datos
	 * 
	 * @param providerName
	 *            Nombre del proveedor a a�adir
	 */
	public void addProvider(String providerName);

	/**
	 * Borra un proveedor de la base de datos
	 * 
	 * @param providerCode
	 *            C�digo del proveedor a eliminar.
	 */
	public void deleteProvider(Long providerCode);

	/**
	 * Actualiza un proveedor
	 * 
	 * @param providerCode
	 *            C�digo del proveedor a actualizar
	 * @param providerName
	 *            Nombre nuevo del proveedor
	 */
	public void updateProvider(Long providerCode, String providerName);

	/**
	 * Devuelve una lista con la informaci�n de todos los proveedores
	 * 
	 * @return lista con la informaci�n de todos los proveedores
	 */
	public List<Map<String, Object>> listAllProviders();

	/**
	 * A�ade un repuesto a un proveedor
	 * 
	 * @param replacementCode
	 *            C�digo del repuesto
	 * @param providerCode
	 *            C�digo del proveedor
	 * @param price
	 *            Precio para el repuesto por parte de ese proveedor
	 */
	public void addReplacementToProvider(Long replacementCode,
			Long providerCode, Double price);

	/**
	 * Elimina un repuesto de un proveedor
	 * 
	 * @param replacementCode
	 *            C�digo del repuesto a eliminar
	 * @param providerCode
	 *            C�digo del proveedor del que se va a eliminar el repuesto
	 */
	public void deleteReplacementFromProvider(Long replacementCode,
			Long providerCode);

	/**
	 * 
	 * @param replacementCode
	 *            C�digo del repuesto
	 * @param providerCode
	 *            C�digo del proveedor
	 * @param isReplacementCode
	 *            Si vale true se comprueba por c�digo de repuesto. En caso
	 *            contrario, por c�digo de proveedor
	 * @return Devuelve una lista con los repuestos de cada proveedor filtrados
	 *         por c�digo de repuesto O por c�digo de proveedor
	 */
	public List<Map<String, Object>> checkWhatReplacementToUpdateOrDelete(
			Long replacementCode, Long providerCode, boolean isReplacementCode);

	/**
	 * @param nameProviderBeforeUpdating
	 *            Nombre del proveedor por el que se va a filtrar
	 * @return Lista de mapas que contiene los datos de cada uno de los
	 *         proveedores cuyo nombre es el pasado por par�metro
	 */
	public List<Map<String, Object>> checkWhatProviderToUpdate(
			String nameProviderBeforeUpdating);

	/**
	 * Actualiza el repuesto de un proveedor. El �nico item que se puede
	 * modificar es el precio.
	 * 
	 * @param replacementCode
	 *            C�digo del repuesto
	 * @param providerCode
	 *            C�digo del proveedor
	 * @param newPrice
	 *            Nuevo precio
	 */
	public void updateReplacementFromProvider(Long replacementCode,
			Long providerCode, Double newPrice);
}
