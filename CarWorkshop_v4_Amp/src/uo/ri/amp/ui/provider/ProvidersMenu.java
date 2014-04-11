package uo.ri.amp.ui.provider;

import uo.ri.amp.ui.provider.action.AddProviderAction;
import uo.ri.amp.ui.provider.action.AddReplacementToProviderAction;
import uo.ri.amp.ui.provider.action.DeleteProviderAction;
import uo.ri.amp.ui.provider.action.DeleteReplacementToProviderAction;
import uo.ri.amp.ui.provider.action.ListProvidersAction;
import alb.util.menu.BaseMenu;

/**
 * @author Álvaro García
 * 
 */
public class ProvidersMenu extends BaseMenu {

	/**
	 * Menú para los mecánicos
	 */
	public ProvidersMenu() {
		menuOptions = new Object[][] {
				{ "Administrador > Gestión de Proveedores", null },

				{ "Añadir proveedor", AddProviderAction.class },
				{ "Modificar datos proveedor", UpdateProvidersMenu.class },
				{ "Eliminar proveedor", DeleteProviderAction.class },
				{ "Listar todos los proveedores", ListProvidersAction.class },
				{ "Añadir repuesto al Proveedor",
						AddReplacementToProviderAction.class },
				{ "Modificar repuesto del Proveedor",
						UpdateReplacementFromProviderMenu.class },
				{ "Eliminar Repuesto del Proveedor",
						DeleteReplacementToProviderAction.class },

		};
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
