package uo.ri.amp.ui.provider;

import uo.ri.amp.ui.provider.action.UpdateReplacementFromProviderByPCodeAction;
import uo.ri.amp.ui.provider.action.UpdateReplacementFromProviderByRCodeAction;
import alb.util.menu.BaseMenu;

/**
 * @author �lvaro Garc�a
 * 
 */
public class UpdateReplacementFromProviderMenu extends BaseMenu {

	/**
	 * Men� para los mec�nicos
	 */
	public UpdateReplacementFromProviderMenu() {
		menuOptions = new Object[][] {
				{
						"Administrador > Gesti�n de Proveedores >" +
						" Actualizar Proveedor",
						null },

				{ "Buscar Por C�digo de proveedor",
						UpdateReplacementFromProviderByPCodeAction.class },
				{ "Buscar Por C�digo de Repuesto",
						UpdateReplacementFromProviderByRCodeAction.class }, };
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
