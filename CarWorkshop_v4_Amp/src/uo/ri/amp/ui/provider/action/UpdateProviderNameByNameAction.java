package uo.ri.amp.ui.provider.action;

import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.ServicesFactory;
import alb.util.BusinessException;
import alb.util.console.Console;
import alb.util.console.Printer;
import alb.util.menu.Action;

/**
 * @author Álvaro García
 * 
 */
public class UpdateProviderNameByNameAction implements Action {

	@Override
	public void execute() throws BusinessException {
		String nameProviderBeforeUpdating = Console.readString("Nombre actual");
		boolean numeroCorrecto = false;

		try {

			List<Map<String, Object>> listadoProveedores = ServicesFactory
					.getProviderService().checkWhatProviderToUpdate(
							nameProviderBeforeUpdating);

			// imprimo la lista de códigos y nombres
			int elemento = 1;
			for (Map<String, Object> proveedor : listadoProveedores) {
				Console.print(elemento + ". ID: " + proveedor.get("ID"));
				Console.println("\tNombre: " + proveedor.get("NOMBRE"));
				elemento++;
			}

			int proveedorDeLaLista = 0;
			while (!numeroCorrecto && listadoProveedores.size() > 0) {
				proveedorDeLaLista = Console
						.readInt("Introduzca el elemento a actualizar");
				if (proveedorDeLaLista > 0
						&& proveedorDeLaLista <= listadoProveedores.size())
					numeroCorrecto = true;
				else
					Console.println("ERROR. El número debe estar comprendido entre 1 y "
							+ listadoProveedores.size());
			}
			if (listadoProveedores.size() > 0) {
				String providerName = Console
						.readString("Nombre nuevo del proveedor");

				ServicesFactory.getProviderService().updateProvider(
						(Long) listadoProveedores.get(proveedorDeLaLista - 1)
								.get("ID"), providerName);
			} else
				Console.println("No se ha encontrado ningún proveedor con ese nombre");
		} catch (RuntimeException e) {
			Printer.printRuntimeException(e);
		}

	}

}
