package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.OrdersGateway;
import alb.util.jdbc.Jdbc;

/**
 * @author �lvaro Garc�a
 * 
 */
public class ListOrdersByProvider {

	Long providerCode;

	/**
	 * Constructor
	 * 
	 * @param providerCode
	 *            C�digo del proveedor
	 */
	public ListOrdersByProvider(Long providerCode) {
		this.providerCode = providerCode;
	}

	/**
	 * Crea la conexi�n con la base de datos y un gateway de pedidos a trav�s
	 * del cu�l le pasa la conexi�n y llama al m�todo persistente que encuentra
	 * los pedidos dado un proveedor
	 * 
	 * @return Lista de mapas, cada uno de ellos con un pedido dado el proveedor
	 */
	public List<Map<String, Object>> execute() {
		Connection c = null;
		List<Map<String, Object>> orders;

		try {
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
			OrdersGateway oG = PersistenceFactory.getOrdersGateway();

			oG.setConnection(c);
			orders = oG.findOrdersByProvider(providerCode);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

		return orders;

	}
}
