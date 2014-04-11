package uo.ri.amp.conf;

import uo.ri.amp.business.OrdersService;
import uo.ri.amp.business.ProviderService;
import uo.ri.amp.business.impl.OrdersServiceImpl;
import uo.ri.amp.business.impl.ProviderServiceImpl;

/**
 * @author �lvaro Garc�a
 * 
 */
public class ServicesFactory {

	/**
	 * Factor�a que devuelve un servicio de Proveedor
	 * 
	 * @return servicio de proveedor
	 */
	public static ProviderService getProviderService() {
		return new ProviderServiceImpl();

	}

	/**
	 * Factor�a que devuelve un servicio de Pedidos
	 * 
	 * @return servicio de pedidos
	 */
	public static OrdersService getOrdersService() {
		return new OrdersServiceImpl();
	}

}
