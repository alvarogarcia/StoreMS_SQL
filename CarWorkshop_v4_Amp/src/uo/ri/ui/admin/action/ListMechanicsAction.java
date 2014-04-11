package uo.ri.ui.admin.action;


import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import uo.ri.conf.ServicesFactory;

import alb.util.BusinessException;
import alb.util.console.Printer;
import alb.util.menu.Action;

public class ListMechanicsAction implements Action {

	

	@Override
	public void execute() throws BusinessException {

		Console.println("\nListado de mec�nicos\n");

		try{
			//AdminServiceImpl aSI = new AdminServiceImpl();
			
			List<Map<String, Object>> mechanics = ServicesFactory.getAdminService().findAllMechanics();
			
			for(Map<String, Object> map : mechanics){
				Printer.print("ID: " + map.get("id") + "\tNombre: " + map.get("nombre") + "\tApellidos: " + map.get("apellidos"));
			}
		} catch(RuntimeException e){
			Printer.printRuntimeException(e);
		}
	}
}
