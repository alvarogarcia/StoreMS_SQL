package uo.ri.amp.ui.provider;

import uo.ri.amp.ui.provider.action.ListOrdersByProviderAction;
import uo.ri.amp.ui.provider.action.ReceiveOrderAction;
import alb.util.menu.BaseMenu;

/**
 * @author �lvaro Garc�a
 * 
 */
public class OrdersMenu extends BaseMenu {

	/**
	 * Men� para los mec�nicos
	 */
	public OrdersMenu() {
		menuOptions = new Object[][] {
				{ "Administrador > Gesti�n de Proveedores", null },
				{ "Recibir Pedido", ReceiveOrderAction.class },
				{ "Listar Pedidos por Proveedor",
						ListOrdersByProviderAction.class }, };
	}

	/**
	 * Ejecuta el men� principal para el administrador
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
