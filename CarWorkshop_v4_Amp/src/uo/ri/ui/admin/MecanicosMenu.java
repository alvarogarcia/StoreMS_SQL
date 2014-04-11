package uo.ri.ui.admin;

import uo.ri.ui.admin.action.AddMechanicAction;
import uo.ri.ui.admin.action.DeleteMechanicAction;
import uo.ri.ui.admin.action.ListMechanicsAction;
import uo.ri.ui.admin.action.UpdateMechanicAction;
import alb.util.menu.BaseMenu;

public class MecanicosMenu extends BaseMenu {

	/**
	 * Men� para los mec�nicos
	 */
	public MecanicosMenu() {
		menuOptions = new Object[][] { 
			{"Administrador > Gestión de mecánicos", null},
			
			{ "Añadir mecánico", 				AddMechanicAction.class }, 
			{ "Modificar datos de mecánico", 	UpdateMechanicAction.class }, 
			{ "Eliminar mecánico", 				DeleteMechanicAction.class }, 
			{ "Listar mecánicos", 				ListMechanicsAction.class },
		};
	}

}
