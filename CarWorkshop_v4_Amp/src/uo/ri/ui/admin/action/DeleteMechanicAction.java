package uo.ri.ui.admin.action;



import alb.util.console.Console;
import uo.ri.conf.ServicesFactory;

import alb.util.BusinessException;
import alb.util.console.Printer;
import alb.util.menu.Action;

public class DeleteMechanicAction implements Action {


	@Override
	public void execute() throws BusinessException {
		Long idMecanico = Console.readLong("Id de mec�nico");

		try {
			//AdminServiceImpl aSI = new AdminServiceImpl();
			ServicesFactory.getAdminService().deleteMechanic(idMecanico);

			Printer.print("se ha eliminado al mecánico");
		} catch (RuntimeException e) {
			Printer.printRuntimeException(e);
		}

	}

}
