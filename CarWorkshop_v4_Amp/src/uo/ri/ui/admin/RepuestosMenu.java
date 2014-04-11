package uo.ri.ui.admin;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

public class RepuestosMenu extends BaseMenu {

	/**
	 * Men� para la gesti�n de repuestos
	 */
	public RepuestosMenu() {
		menuOptions = new Object[][] { 
			{"Administrador > Gestión de repuestos", null},
			
			{ "Añadir repuesto", 				NotYetImplementedAction.class }, 
			{ "Modificar datos de repuesto", 	NotYetImplementedAction.class }, 
			{ "Eliminar repuesto", 				NotYetImplementedAction.class }, 
			{ "Listar repuestos", 				NotYetImplementedAction.class },
		};
	}

}
