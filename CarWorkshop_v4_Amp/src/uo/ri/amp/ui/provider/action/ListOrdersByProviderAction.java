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
public class ListOrdersByProviderAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Long idProveedor = Console.readLong("id del proveedor");

		try {

			// IMPRIMIR EN PANTALLA LOS PEDIDOS
			List<Map<String, Object>> pedidos = ServicesFactory
					.getOrdersService().listOrders(idProveedor);

			for (Map<String, Object> pedido : pedidos) {
				Console.print("PEDIDO:\nID: " + pedido.get("ID")
						+ "\nFECHA CREACIÓN " + ""
						+ pedido.get("FECHA_CREACION") + "\nFECHA RECEPCIÓN: "
						+ pedido.get("FECHA_RECEPCION") + "\nPRECIO " + ""
						+ pedido.get("PRECIO") + "\nPROVEEDOR: "
						+ pedido.get("PROVEEDOR") + "\nESTADO RECEPCIÓN: "
						+ pedido.get("ESTADO_RECEPCION") + "\n");
				Console.print("ITEMS DEL PEDIDO:\n");

				@SuppressWarnings("unchecked")
				List<Map<String, Object>> itemsPedido = (List<Map<String, Object>>) pedido
						.get("ITEMSPEDIDO");
				if (itemsPedido.size() == 0)
					Console.print("El pedido no incluía ningún item o el código" +
							" del pedido no existe.");
				else
					for (Map<String, Object> itemPedido : itemsPedido) {
						Console.print("\tPEDIDO_ID: "
								+ itemPedido.get("PEDIDO") + "\n\tREPUESTO: "
								+ itemPedido.get("REPUESTO") + "\n\tCANTIDAD: "
								+ itemPedido.get("CANTIDAD") + "\n\tPRECIO: "
								+ itemPedido.get("PRECIO"));
						Console.print("\n\t------\n");
					}
				Console.print("--------------------\n");

			}

		} catch (RuntimeException e) {
			Printer.printRuntimeException(e);
		}

	}

}
