package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @author �lvaro Garc�a
 * 
 */
public interface OrdersGateway {

	/**
	 * Establece la conexi�n con el Gateway de Pedidos
	 * 
	 * @param c
	 *            Objecto de tipo conexi�n
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
	 * Encuentra un pedido dado un c�digo
	 * 
	 * @param orderCode
	 *            C�digo del pedido
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
	 * antes de que estos sean a�adidos. Esto se va a utilizar para poder
	 * actualizar en la l�gica los precios usando la media ponderada en funci�n
	 * de la cantidad y precios anteriores y los nuevos.
	 * 
	 * @param pedido
	 *            Contiene toda la informaci�n del pedido recibido. De �l se
	 *            obtendr�n los IDs de los items
	 * @return Devuelve una lista que contiene un mapa por cada item recibido
	 *         con su ID, precio anterior y cantidad anterior.
	 */
	List<Map<String, Object>> pricesAndQuantityItemsOrderBefore(
			Map<String, Object> pedido);

}
