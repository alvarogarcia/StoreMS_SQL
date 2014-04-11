package uo.ri.business;

import java.util.List;
import java.util.Map;

public interface AdminService {

	
	/**
	 * Crea un nuevo mecánico
	 * 
	 * @param nombre Nombre
	 * @param apellidos Apellidos
	 */
	public void newMechanic(String nombre, String apellidos);

	/**
	 * Borra un mecánico 
	 * 
	 * @param idMecanico id del mecánico a borrar
	 */
	public void deleteMechanic(Long idMecanico);
	
	/**
	 * Actualiza el mecánico
	 * 
	 * @param id id del mecánico
	 * @param nombre Nombre del mecánico a actualizar
	 * @param apellidos Apellidos del mecánico a actualizar
	 */
	public void updateMechanic(Long id, String nombre, String apellidos);
	
	/**
	 * @return
	 */
	public List<Map<String, Object>> findAllMechanics();
}
