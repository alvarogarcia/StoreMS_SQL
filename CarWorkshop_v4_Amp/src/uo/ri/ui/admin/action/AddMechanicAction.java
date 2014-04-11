package uo.ri.ui.admin.action;



import alb.util.console.Console;
import uo.ri.conf.*;

import alb.util.BusinessException;
import alb.util.console.Printer;

import alb.util.menu.Action;

public class AddMechanicAction implements Action {


	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");
		
		try{
		
			//AdminServiceImpl aSI = new AdminServiceImpl();
			ServicesFactory.getAdminService().newMechanic(nombre, apellidos);
		

		// Mostrar resultado
		Printer.print("Nuevo mecánico añadido");
		} catch(RuntimeException e){
			Printer.printRuntimeException(e);
		}
		
	}

}
