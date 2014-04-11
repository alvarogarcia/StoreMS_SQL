package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @author Álvaro García
 * 
 */
public interface OrdersGateway {

	/**
	 * Establece la conexión con el Gateway de Pedidos
	 * 
	 * @param c
	 *            Objecto de tipo conexión
	 */
	void setConnection(Connection c);

	/**
	 * Devuelve una lista de mapas, cada uno de ellos con los pedidos dado un
	 * proveedor
	 * 
	 * @param providerCode
	 *            Proveedor para el que se quiere consultar sus pedidos
	 * @return pedidos del proveedor
	 */
	List<Map<String, Object>> findOrdersByProvider(Long providerCode);

	/**
	 * Encuentra un pedido dado un código
	 * 
	 * @param orderCode
	 *            Código del pedido
	 * @return Mapa con los datos del pedido
	 */
	Map<String, Object> findOrder(Long orderCode);

	/**
	 * Actualiza un pedido estableciendo que ya se ha recibido (cambiando campos
	 * fecha y estado_recepcion)
	 * 
	 * @param pedido
	 *            Mapa con los detalles del pedido
	 */
	void updateOrderReceived(Map<String, Object> pedido);

	/**
	 * Obtiene los precios y las cantidades de cada Item del pedido recibido
	 * antes de que estos sean añadidos. Esto se va a utilizar para poder
	 * actualizar en la lógica los precios usando la media ponderada en función
	 * de la cantidad y precios anteriores y los nuevos.
	 * 
	 * @param pedido
	 *            Contiene toda la información del pedido recibido. De él se
	 *            obtendrán los IDs de los items
	 * @return Devuelve una lista que contiene un mapa por cada item recibido
	 *         con su ID, precio anterior y cantidad anterior.
	 */
	List<Map<String, Object>> pricesAndQuantityItemsOrderBefore(
			Map<String, Object> pedido);

}
