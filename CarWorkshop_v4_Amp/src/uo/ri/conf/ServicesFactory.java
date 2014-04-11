package uo.ri.conf;

import uo.ri.business.AdminService;
import uo.ri.business.CashService;
import uo.ri.business.impl.AdminServiceImpl;
import uo.ri.business.impl.CashServiceImpl;

public class ServicesFactory {

	/** Factor�a que devuelve un servicio de administrador para poder a�adir/modificar/eliminar
	 * @return servicio de administrador
	 */
	public static AdminService getAdminService(){
		return new AdminServiceImpl();
		
	}
	
	/**
	 * Factor�a que devuelve un serivico de caja para poder relizar las facturas
	 * @return servicio de caja
	 */
	public static CashService getCashService(){
		return new CashServiceImpl();
	}
	
	/*public static MechanicServiceImpl getMechanicService(){
		return new MechanicServiceImpl();
	}
	
	public static ForemanServiceImpl getForemanService(){
		return new ForemanServiceImpl();
	}*/
	
}