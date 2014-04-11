package uo.ri.amp.ui.provider.action;

import uo.ri.amp.conf.ServicesFactory;
import alb.util.BusinessException;
import alb.util.console.Console;
import alb.util.console.Printer;
import alb.util.menu.Action;

/**
 * @author Álvaro García
 * 
 */
public class UpdateProviderNameAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Long idProveedor = Console.readLong("id del proveedor a actualizar");
		String providerName = Console.readString("Nombre nuevo del proveedor");

		try {

			ServicesFactory.getProviderService().updateProvider(idProveedor,
					providerName);

		} catch (RuntimeException e) {
			Printer.printRuntimeException(e);
		}
	}

}
