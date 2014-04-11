package uo.ri.ui.foreman;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

public class VehiculosMenu extends BaseMenu {

	/**
	 * Men� de los veh�culos
	 */
	public VehiculosMenu() {
		menuOptions = new Object[][] { 
			{ "Jefe de Taller > Gestión de Vehículos", null },

			{ "Añadir vehículo", NotYetImplementedAction.class }, 
			{ "Modificar datos de vehículo", NotYetImplementedAction.class }, 
			{ "Eliminar vehículo", NotYetImplementedAction.class }, 
			{ "Listar vehículo", NotYetImplementedAction.class }, 
		};
	}

}
