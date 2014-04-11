package uo.ri.amp.business;

import java.util.List;
import java.util.Map;

/**
 * @author �lvaro Garc�a
 * 
 */
public interface OrdersService {

	/**
	 * @param providerCode
	 *            C�digo del proveedor
	 * @return Lista de mapas cada uno de ellos con los pedidos del proveedor
	 *         dado. Ordenados por fecha descendientemente, y primero los
	 *         pendientes
	 */
	public List<Map<String, Object>> listOrders(Long providerCode);

	/**
	 * @param orderCode
	 *            C�digo del pedido
	 * @return Devuelve un mapa que contiene los datos de un pedido
	 */
	public Map<String, Object> findOrder(Long orderCode);

	/**
	 * Establece un pediod como recibido => actualiza campos fecha_recepci�n y
	 * estado_pedido
	 * 
	 * @param pedido
	 *            Mapa con los datos del pedido
	 */
	public void updateOrderReceived(Map<String, Object> pedido);

}
