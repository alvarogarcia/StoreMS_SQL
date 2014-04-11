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
public class DeleteProviderAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Long idProveedor = Console.readLong("id del proveedor");

		try {

			// En caso de que haya algún pedido para el proveedor, no se va a
			// poder eliminar
			ServicesFactory.getProviderService().deleteProvider(idProveedor);

		} catch (RuntimeException e) {
			Printer.printRuntimeException(e);
		}

	}

}
