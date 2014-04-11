package uo.ri.ui.admin;

import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {

	/**
	 * Men� principal para el administrador
	 */
	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Administrador", null },
			{ "Gestión de mecánicos", 			MecanicosMenu.class }, 
			{ "Gestión de repuestos", 			RepuestosMenu.class },
			{ "Gestión de tipos de vehículo", 	TiposVehiculoMenu.class },
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
