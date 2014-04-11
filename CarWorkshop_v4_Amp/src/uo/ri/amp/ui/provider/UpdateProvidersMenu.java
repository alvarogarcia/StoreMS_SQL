package uo.ri.amp.ui.provider;

import uo.ri.amp.ui.provider.action.UpdateProviderNameAction;
import uo.ri.amp.ui.provider.action.UpdateProviderNameByNameAction;
import alb.util.menu.BaseMenu;

/**
 * @author �lvaro Garc�a
 * 
 */
public class UpdateProvidersMenu extends BaseMenu {

	/**
	 * Men� para los mec�nicos
	 */
	public UpdateProvidersMenu() {
		menuOptions = new Object[][] {
				{
						"Administrador > Gesti�n de Proveedores > " +
						"Actualizar Proveedor",
						null },

				// En caso de que quiera buscar por nombre tendr� que mostrar
				// todos los casos encontrados.
				// Por c�digo no hace falta.
				{ "Buscar Por C�digo de Proveedor",
						UpdateProviderNameAction.class },
				{ "Buscar Por Nombre de Proveedor",
						UpdateProviderNameByNameAction.class }, };
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
