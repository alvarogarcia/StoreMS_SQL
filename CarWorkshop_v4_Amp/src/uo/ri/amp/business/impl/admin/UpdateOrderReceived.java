package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.OrdersGateway;
import alb.util.jdbc.Jdbc;

/**
 * @author Álvaro García
 * 
 */
public class UpdateOrderReceived {

	Map<String, Object> pedido;

	/**
	 * Constructor
	 * 
	 * @param pedido
	 *            Mapa con los datos del pedido
	 */
	public UpdateOrderReceived(Map<String, Object> pedido) {
		this.pedido = pedido;
	}

	/**
	 * Crea una conexión con la base de datos y un gateway de pedidos a través
	 * del cuál le pasa la conexión y llama al método persistente que obtiene
	 * los datos del repuesto anteriores a la llegada del pedido y tras hacer
	 * los cálculos llama al método persistente que actualiza las tablas.
	 */
	public void execute() {
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
			OrdersGateway oG = PersistenceFactory.getOrdersGateway();

			oG.setConnection(c);
			List<Map<String, Object>> pricesAndQuantitiesBf = oG
					.pricesAndQuantityItemsOrderBefore(pedido);
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> itemsPedido = (List<Map<String, Object>>) pedido
					.get("ITEMSPEDIDO");
			for (Map<String, Object> itemRecibido : itemsPedido) {
				Long idRepuesto = (Long) itemRecibido.get("REPUESTO");
				Long cantidadPedido = (Long) itemRecibido.get("CANTIDAD");
				Double precioPedido = (Double) itemRecibido.get("PRECIO");

				// Busco el elemento con los precios y cantidades anteriores de
				// los itemsd el pedido en la lista de mapas
				// "pricesAndQuantitiesBf"
				for (Map<String, Object> itemAnterior : pricesAndQuantitiesBf) {
					if (itemAnterior.get("REPUESTO") == idRepuesto) {
						// Hacemos los cálculos de los nuevos precios
						Long cantidadAnterior = (Long) itemAnterior
								.get("CANTIDAD");
						Double precioAnterior = (Double) itemAnterior
								.get("PRECIO");
						itemRecibido.put("CANTIDAD", cantidadAnterior
								+ cantidadPedido);
						itemRecibido
								.put("PRECIO",
										(Double) (((cantidadAnterior * 
												precioAnterior) + (cantidadPedido
														* precioPedido)) / (
																cantidadAnterior +
																cantidadPedido)));
						break;
					}
				}
				// Ahora pedido es una lista de mapas en el que el mapa
				// ITEMSPEDIDO contiene a su vez una lista de mapas, con cada
				// uno de los items de pedido
				// que ya contiene el precio y las cantidad actualizada, para
				// que se almacene en la base de datos.
			}
			oG.updateOrderReceived(pedido);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}

}
