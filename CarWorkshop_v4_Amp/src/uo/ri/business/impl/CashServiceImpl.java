package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import alb.util.BusinessException;
import uo.ri.business.CashService;
import uo.ri.business.impl.cash.CreateInvoiceFor;

public class CashServiceImpl implements CashService {

	
	@Override
	public Map<String, Object> createInvoiceFor(List<Long> idsAveria)
			throws BusinessException {
		CreateInvoiceFor cIF = new CreateInvoiceFor(idsAveria);
		Map<String, Object> invoice = cIF.execute();
		return invoice;
	}

	@Override
	public Map<String, Object> findInvoice(Long idFactura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findPayMethodsForInvoice(Long idFactura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void settleInvoice(Long idFactura, Map<Long, Double> pagos) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Map<String, Object>> findRepairByClient(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

}
