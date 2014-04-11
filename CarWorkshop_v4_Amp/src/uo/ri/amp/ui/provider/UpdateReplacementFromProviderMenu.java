package uo.ri.amp.ui.provider;

import uo.ri.amp.ui.provider.action.UpdateReplacementFromProviderByPCodeAction;
import uo.ri.amp.ui.provider.action.UpdateReplacementFromProviderByRCodeAction;
import alb.util.menu.BaseMenu;

/**
 * @author Álvaro García
 * 
 */
public class UpdateReplacementFromProviderMenu extends BaseMenu {

	/**
	 * Menú para los mecánicos
	 */
	public UpdateReplacementFromProviderMenu() {
		menuOptions = new Object[][] {
				{
						"Administrador > Gestión de Proveedores >" +
						" Actualizar Proveedor",
						null },

				{ "Buscar Por Código de proveedor",
						UpdateReplacementFromProviderByPCodeAction.class },
				{ "Buscar Por Código de Repuesto",
						UpdateReplacementFromProviderByRCodeAction.class }, };
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
