package uo.ri.ui.admin.action;



import alb.util.console.Console;
import uo.ri.conf.ServicesFactory;

import alb.util.BusinessException;
import alb.util.console.Printer;
import alb.util.menu.Action;

public class UpdateMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		Long id = Console.readLong("Id del mecánico");
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");

		try {
			//AdminServiceImpl aSI = new AdminServiceImpl();
			ServicesFactory.getAdminService().updateMechanic(id, nombre, apellidos);
			
			Printer.print("Mecánico actualizado");

		} catch (RuntimeException e) {
			Printer.printRuntimeException(e);
		}

	}

}
