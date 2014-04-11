package uo.ri.amp.ui.provider;

import uo.ri.amp.ui.provider.action.UpdateProviderNameAction;
import uo.ri.amp.ui.provider.action.UpdateProviderNameByNameAction;
import alb.util.menu.BaseMenu;

/**
 * @author Álvaro García
 * 
 */
public class UpdateProvidersMenu extends BaseMenu {

	/**
	 * Menú para los mecánicos
	 */
	public UpdateProvidersMenu() {
		menuOptions = new Object[][] {
				{
						"Administrador > Gestión de Proveedores > " +
						"Actualizar Proveedor",
						null },

				// En caso de que quiera buscar por nombre tendré que mostrar
				// todos los casos encontrados.
				// Por código no hace falta.
				{ "Buscar Por Código de Proveedor",
						UpdateProviderNameAction.class },
				{ "Buscar Por Nombre de Proveedor",
						UpdateProviderNameByNameAction.class }, };
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
