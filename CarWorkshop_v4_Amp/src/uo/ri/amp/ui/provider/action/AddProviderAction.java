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
public class AddProviderAction implements Action {

	@Override
	public void execute() throws BusinessException {
		String providerName = Console.readString("nombre del proveedor");

		try {

			ServicesFactory.getProviderService().addProvider(providerName);

		} catch (RuntimeException e) {
			Printer.printRuntimeException(e);
		}

	}

}
