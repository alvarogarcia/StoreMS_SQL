package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.OrdersGateway;

import alb.util.jdbc.Jdbc;

/**
 * @author Álvaro García
 * 
 */
public class FindOrderByCode {

	Long orderCode;

	/**
	 * Constructor
	 * 
	 * @param orderCode
	 *            Código del pedido
	 */
	public FindOrderByCode(Long orderCode) {
		this.orderCode = orderCode;
	}

	/**
	 * Crea la conexión y un gateway de pedidos, a través del cuál le pasa la
	 * conexión y llama al métod persistnte que encuentra el pedido dado su
	 * código de indentificación
	 * 
	 * @return Mapa con los datos del pedido
	 */
	public Map<String, Object> execute() {
		Connection c = null;

		Map<String, Object> pedido = new HashMap<String, Object>();

		try {
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
			OrdersGateway oG = PersistenceFactory.getOrdersGateway();

			oG.setConnection(c);
			pedido = oG.findOrder(orderCode);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

		return pedido;

	}

}
