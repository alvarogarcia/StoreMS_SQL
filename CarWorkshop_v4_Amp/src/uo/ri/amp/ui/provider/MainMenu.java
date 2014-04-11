package uo.ri.amp.ui.provider;

import alb.util.menu.BaseMenu;

/**
 * @author �lvaro Garc�a
 *
 */
public class MainMenu extends BaseMenu {

	/**
	 * Men� principal para el administrador
	 */
	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Administrador", null },
			{ "Gesti�n de Proveedores", ProvidersMenu.class },
			{ "Gesti�n de Pedidos", OrdersMenu.class },
		};
	}

	/**
	 * Ejecuta el men� principal para el administrador
	 * @param args
	 */
	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
