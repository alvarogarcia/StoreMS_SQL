package uo.ri.ui.foreman;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

public class MainMenu extends BaseMenu {

	/**
	 * Menu principal
	 */
	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Jefe de Taller", null },
			{ "Recepción en taller", RecepcionMenu.class }, 
			{ "Gestión de clientes", ClientesMenu.class },
			{ "Gestión de vehículos", VehiculosMenu.class },
			{ "Revisar historial de un cliente", NotYetImplementedAction.class }, 
		};
	}

	/**
	 * Ejecuta el men� principal
	 * @param args
	 */
	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
