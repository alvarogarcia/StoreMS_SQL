package uo.ri.amp.ui.provider;

import uo.ri.amp.ui.provider.action.AddProviderAction;
import uo.ri.amp.ui.provider.action.AddReplacementToProviderAction;
import uo.ri.amp.ui.provider.action.DeleteProviderAction;
import uo.ri.amp.ui.provider.action.DeleteReplacementToProviderAction;
import uo.ri.amp.ui.provider.action.ListProvidersAction;
import alb.util.menu.BaseMenu;

/**
 * @author �lvaro Garc�a
 * 
 */
public class ProvidersMenu extends BaseMenu {

	/**
	 * Men� para los mec�nicos
	 */
	public ProvidersMenu() {
		menuOptions = new Object[][] {
				{ "Administrador > Gesti�n de Proveedores", null },

				{ "A�adir proveedor", AddProviderAction.class },
				{ "Modificar datos proveedor", UpdateProvidersMenu.class },
				{ "Eliminar proveedor", DeleteProviderAction.class },
				{ "Listar todos los proveedores", ListProvidersAction.class },
				{ "A�adir repuesto al Proveedor",
						AddReplacementToProviderAction.class },
				{ "Modificar repuesto del Proveedor",
						UpdateReplacementFromProviderMenu.class },
				{ "Eliminar Repuesto del Proveedor",
						DeleteReplacementToProviderAction.class },

		};
	}

	/**
	 * Ejecuta el men� principal para el administrador
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
