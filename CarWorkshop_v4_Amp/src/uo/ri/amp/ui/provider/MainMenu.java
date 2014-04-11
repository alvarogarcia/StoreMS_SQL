package uo.ri.amp.ui.provider;

import alb.util.menu.BaseMenu;

/**
 * @author Álvaro García
 *
 */
public class MainMenu extends BaseMenu {

	/**
	 * Menú principal para el administrador
	 */
	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Administrador", null },
			{ "Gestión de Proveedores", ProvidersMenu.class },
			{ "Gestión de Pedidos", OrdersMenu.class },
		};
	}

	/**
	 * Ejecuta el menú principal para el administrador
	 * @param args
	 */
	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
