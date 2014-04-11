package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.OrdersGateway;

import alb.util.jdbc.Jdbc;

/**
 * @author �lvaro Garc�a
 * 
 */
public class FindOrderByCode {

	Long orderCode;

	/**
	 * Constructor
	 * 
	 * @param orderCode
	 *            C�digo del pedido
	 */
	public FindOrderByCode(Long orderCode) {
		this.orderCode = orderCode;
	}

	/**
	 * Crea la conexi�n y un gateway de pedidos, a trav�s del cu�l le pasa la
	 * conexi�n y llama al m�tod persistnte que encuentra el pedido dado su
	 * c�digo de indentificaci�n
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
