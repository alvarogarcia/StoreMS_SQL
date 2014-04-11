package uo.ri.business;

import java.util.List;
import java.util.Map;

import alb.util.BusinessException;

/**
 * @author alvaroastur4
 *
 */
public interface CashService {

	/**
	 * Crea la factura
	 * @param idsAveria
	 * @return
	 * @throws BusinessException
	 */
	public Map<String, Object> createInvoiceFor(List<Long> idsAveria) throws BusinessException;
	
	/**
	 * Encuentra una factura
	 * @param idFactura
	 * @return
	 */
	public Map<String, Object> findInvoice(Long idFactura);
	
	/**
	 * Encuentra los métodos aceptados para la factura
	 * @param idFactura
	 * @return
	 */
	public List<Map<String, Object>> findPayMethodsForInvoice(Long idFactura);
	
	/**
	 * 
	 * @param idFactura
	 * @param pagos
	 */
	public void settleInvoice(Long idFactura, Map<Long, Double> pagos);
	
	/**
	 * Recopila todas las reparaciones efectuadas al vehículo de un cliente.
	 * @param dni
	 * @return
	 */
	public List<Map<String, Object>> findRepairByClient(String dni);
}
