package uo.ri.amp.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.amp.business.OrdersService;
import uo.ri.amp.business.impl.admin.FindOrderByCode;
import uo.ri.amp.business.impl.admin.ListOrdersByProvider;
import uo.ri.amp.business.impl.admin.UpdateOrderReceived;

/**
 * @author Álvaro García
 * 
 */
public class OrdersServiceImpl implements OrdersService {

	@Override
	public List<Map<String, Object>> listOrders(Long providerCode) {
		return new ListOrdersByProvider(providerCode).execute();
	}

	@Override
	public Map<String, Object> findOrder(Long orderCode) {
		return new FindOrderByCode(orderCode).execute();
	}

	@Override
	public void updateOrderReceived(Map<String, Object> pedido) {
		new UpdateOrderReceived(pedido).execute();

	}

}
