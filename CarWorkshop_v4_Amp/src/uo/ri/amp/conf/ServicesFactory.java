package uo.ri.amp.conf;

import uo.ri.amp.business.OrdersService;
import uo.ri.amp.business.ProviderService;
import uo.ri.amp.business.impl.OrdersServiceImpl;
import uo.ri.amp.business.impl.ProviderServiceImpl;

/**
 * @author Álvaro García
 * 
 */
public class ServicesFactory {

	/**
	 * Factoría que devuelve un servicio de Proveedor
	 * 
	 * @return servicio de proveedor
	 */
	public static ProviderService getProviderService() {
		return new ProviderServiceImpl();

	}

	/**
	 * Factoría que devuelve un servicio de Pedidos
	 * 
	 * @return servicio de pedidos
	 */
	public static OrdersService getOrdersService() {
		return new OrdersServiceImpl();
	}

}
