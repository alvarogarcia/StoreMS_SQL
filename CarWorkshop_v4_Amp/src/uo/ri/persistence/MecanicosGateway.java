package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface MecanicosGateway {

	/**
	 * Establece la conexión
	 * @param c Conexión
	 */
	void setConnection(Connection c);

	/**
	 * Guarda un mecánico nuevo
	 * @param nombre Nombre del mecánico a guardar
	 * @param apellidos Apellidos del mecánico a guardar
 	 */
	void save(String nombre, String apellidos);
	
	/**
	 * Encuentra un mecánico por ID
	 * @param id ID del mecánico a encontrar
	 * @return datos del mecánico encontrado (como mapa)
	 */
	Map<String, Object> findById(Long id);
	
	/**
	 * Devuelve todos los mecánicos
	 * @return Lista que contiene los mapas con los atributos de cada mecánico
	 */
	List<Map<String, Object>> findAll();
	
	/**
	 * Actualiza un mecánico
	 * @param id Id del mecánico a actualizar
	 * @param nombre Nombre nuevo del mecánico
	 * @param apellidos Apellidos nuevos del mecánico
	 */
	void update(Long id, String nombre, String apellidos);
	
	/**
	 * Borra un mecánico
	 * @param id ID del mecánico a borrar
	 */
	void delete(Long id);

}
