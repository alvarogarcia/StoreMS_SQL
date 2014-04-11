package uo.ri.ui.admin;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

public class TiposVehiculoMenu extends BaseMenu {

	/**
	 * Men� para la gesti�n de los distintos tipos de veh�culo
	 */
	public TiposVehiculoMenu() {
		menuOptions = new Object[][] { 
			{"Administrador > Gestión de tipos de vehiculo", null},
			
			{ "Añadir tipo de vehiculo", 				NotYetImplementedAction.class }, 
			{ "Modificar datos de tipo de vehiculo", 	NotYetImplementedAction.class }, 
			{ "Eliminar tipo de vehiculo", 				NotYetImplementedAction.class }, 
			{ "Listar tipos de vehiculo", 				NotYetImplementedAction.class },
		};
	}

}
