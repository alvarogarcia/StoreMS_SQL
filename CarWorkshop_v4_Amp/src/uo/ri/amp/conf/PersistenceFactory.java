package uo.ri.amp.conf;

import uo.ri.amp.persistence.OrdersGateway;
import uo.ri.amp.persistence.ProvidersGateway;
import uo.ri.amp.persistence.impl.OrdersGatewayImpl;
import uo.ri.amp.persistence.impl.ProvidersGatewayImpl;

/**
 * @author �lvaro Garc�a
 * 
 */
public class PersistenceFactory {

	/**
	 * @return Devuelve el gateway de proveedores
	 */
	public static ProvidersGateway getProvidersGateway() {
		return new ProvidersGatewayImpl();
	}

	/**
	 * @return Devuelve el gateway de pedidos
	 */
	public static OrdersGateway getOrdersGateway() {
		return new OrdersGatewayImpl();
	}
}
