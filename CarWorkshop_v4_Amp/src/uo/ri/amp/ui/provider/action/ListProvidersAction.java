package uo.ri.amp.ui.provider.action;

import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.ServicesFactory;
import alb.util.BusinessException;
import alb.util.console.Console;
import alb.util.console.Printer;
import alb.util.menu.Action;

public class ListProvidersAction implements Action {

	@Override
	public void execute() throws BusinessException {
		try {
			List<Map<String, Object>> listaProveedores = ServicesFactory
					.getProviderService().listAllProviders();

			Console.print("-------------------------------\nINFORMACIÓN DE LOS " +
					"PROVEEDORES\n-------------------------------\n");
			for (Map<String, Object> prov : listaProveedores) {
				Console.print("ID: " + prov.get("ID") + " NOMBRE: "
						+ prov.get("NOMBRE") + "\n");
			}
			Console.print("--------- FIN INFO ---------\n");
		} catch (RuntimeException e) {
			Printer.printRuntimeException(e);
		}

	}

}
