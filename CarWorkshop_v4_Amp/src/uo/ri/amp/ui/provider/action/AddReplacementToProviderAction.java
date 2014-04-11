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
public class AddReplacementToProviderAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Long replacementCode = Console.readLong("id del Repuesto");
		Long providerCode = Console.readLong("id del Proveedor");
		Double price = Console.readDouble("Precio del repuesto");

		try {

			ServicesFactory.getProviderService().addReplacementToProvider(
					replacementCode, providerCode, price);

		} catch (RuntimeException e) {
			Printer.printRuntimeException(e);
		}

	}

}
