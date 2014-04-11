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
public class ReceiveOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {
		Long idPedido = Console.readLong("Código del pedido");

		try {

			Map<String, Object> pedido = ServicesFactory.getOrdersService()
					.findOrder(idPedido);

			// Compruebo que la información es correcta imprimiendo los detalles
			// del pedido.
			Console.print("PEDIDO:\nID: " + pedido.get("ID")
					+ "\nFECHA CREACIÓN " + "" + pedido.get("FECHA_CREACION")
					+ "\nFECHA RECEPCIÓN: " + pedido.get("FECHA_RECEPCION")
					+ "\nPRECIO " + "" + pedido.get("PRECIO") + "\nPROVEEDOR: "
					+ pedido.get("PROVEEDOR") + "\nESTADO RECEPCIÓN"
					+ pedido.get("ESTADO_RECEPCION") + "\n");
			Console.print("ITEMS DEL PEDIDO:\n");

			@SuppressWarnings("unchecked")
			List<Map<String, Object>> itemsPedido = (List<Map<String, Object>>) pedido
					.get("ITEMSPEDIDO");
			for (Map<String, Object> itemPedido : itemsPedido) {
				Console.print("\tPEDIDO_ID: " + itemPedido.get("PEDIDO")
						+ "\n\tREPUESTO: " + itemPedido.get("REPUESTO")
						+ "\n\tCANTIDAD: " + itemPedido.get("CANTIDAD")
						+ "\n\tPRECIO: " + itemPedido.get("PRECIO"));
				Console.print("\n\t------\n");
			}
			Console.print("--------------------\n");

			// Una vez impresos los detalles del pedido, preguntamos al usuario
			// si es el pedido correcto
			// para poder continuar

			String pedidoCorrecto = Console
					.readString("¿Es el pedido correcto? S/N");

			if (pedidoCorrecto.equalsIgnoreCase("S")) {
				ServicesFactory.getOrdersService().updateOrderReceived(pedido);
			} else { // En caso de que el pedido no sea el correcto no hago
						// nada.
				Console.print("Operación anulada");
			}

		} catch (RuntimeException e) {
			Printer.printRuntimeException(e);
		}

	}
}
