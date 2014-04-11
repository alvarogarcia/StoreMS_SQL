package uo.ri.amp.business;

import java.util.List;
import java.util.Map;

/**
 * @author Álvaro García
 * 
 */
public interface OrdersService {

	/**
	 * @param providerCode
	 *            Código del proveedor
	 * @return Lista de mapas cada uno de ellos con los pedidos del proveedor
	 *         dado. Ordenados por fecha descendientemente, y primero los
	 *         pendientes
	 */
	public List<Map<String, Object>> listOrders(Long providerCode);

	/**
	 * @param orderCode
	 *            Código del pedido
	 * @return Devuelve un mapa que contiene los datos de un pedido
	 */
	public Map<String, Object> findOrder(Long orderCode);

	/**
	 * Establece un pediod como recibido => actualiza campos fecha_recepción y
	 * estado_pedido
	 * 
	 * @param pedido
	 *            Mapa con los datos del pedido
	 */
	public void updateOrderReceived(Map<String, Object> pedido);

}
