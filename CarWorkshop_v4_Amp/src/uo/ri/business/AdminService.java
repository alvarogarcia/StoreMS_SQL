package uo.ri.business;

import java.util.List;
import java.util.Map;

public interface AdminService {

	
	/**
	 * Crea un nuevo mec�nico
	 * 
	 * @param nombre Nombre
	 * @param apellidos Apellidos
	 */
	public void newMechanic(String nombre, String apellidos);

	/**
	 * Borra un mec�nico 
	 * 
	 * @param idMecanico id del mec�nico a borrar
	 */
	public void deleteMechanic(Long idMecanico);
	
	/**
	 * Actualiza el mec�nico
	 * 
	 * @param id id del mec�nico
	 * @param nombre Nombre del mec�nico a actualizar
	 * @param apellidos Apellidos del mec�nico a actualizar
	 */
	public void updateMechanic(Long id, String nombre, String apellidos);
	
	/**
	 * @return
	 */
	public List<Map<String, Object>> findAllMechanics();
}
