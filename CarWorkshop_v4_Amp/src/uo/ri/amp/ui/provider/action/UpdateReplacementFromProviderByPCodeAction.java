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
public class UpdateReplacementFromProviderByPCodeAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Long replacementCode = Console.readLong("Codigo del repuesto");
		boolean numeroCorrecto = false;

		try {
			List<Map<String, Object>> listadoRepuestosPorProveedor = ServicesFactory
					.getProviderService().checkWhatReplacementToUpdateOrDelete(
							replacementCode, new Long("0"), true);

			// imprimo la lista de repuestos por proveedor encontrados
			int elemento = 1;
			for (Map<String, Object> repProv : listadoRepuestosPorProveedor) {
				Console.print(elemento + ". ID Repuesto: "
						+ repProv.get("REPUESTO"));
				Console.print("\tIDProveedor: " + repProv.get("PROVEEDOR"));
				Console.println("\tPrecio: " + repProv.get("PRECIO"));
				elemento++;
			}

			int replacementToUpdate = 0;
			while (!numeroCorrecto && listadoRepuestosPorProveedor.size() > 0) {
				replacementToUpdate = Console
						.readInt("Introduzca el elemento a actualizar");
				if (replacementToUpdate > 0
						&& replacementToUpdate <= listadoRepuestosPorProveedor
								.size())
					numeroCorrecto = true;
				else
					Console.println("ERROR. El número debe estar comprendido entre 1 y "
							+ listadoRepuestosPorProveedor.size());
			}

			if (listadoRepuestosPorProveedor.size() > 0) {
				Double newPrice = Console.readDouble("Nuevo precio");

				ServicesFactory.getProviderService()
						.updateReplacementFromProvider(
								replacementCode,
								(Long) listadoRepuestosPorProveedor.get(
										replacementToUpdate - 1).get(
										"PROVEEDOR"), newPrice);
			} else
				Console.println("No se han obtenido resultados con ese código de repuesto");

		} catch (RuntimeException e) {
			Printer.printRuntimeException(e);
		}

	}
}
