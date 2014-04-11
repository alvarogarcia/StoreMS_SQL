package uo.ri.amp.ui.provider;

import uo.ri.amp.ui.provider.action.ListOrdersByProviderAction;
import uo.ri.amp.ui.provider.action.ReceiveOrderAction;
import alb.util.menu.BaseMenu;

/**
 * @author Álvaro García
 * 
 */
public class OrdersMenu extends BaseMenu {

	/**
	 * Menú para los mecánicos
	 */
	public OrdersMenu() {
		menuOptions = new Object[][] {
				{ "Administrador > Gestión de Proveedores", null },
				{ "Recibir Pedido", ReceiveOrderAction.class },
				{ "Listar Pedidos por Proveedor",
						ListOrdersByProviderAction.class }, };
	}

	/**
	 * Ejecuta el menú principal para el administrador
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
