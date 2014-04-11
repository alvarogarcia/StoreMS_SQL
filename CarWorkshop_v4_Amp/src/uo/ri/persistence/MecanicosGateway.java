package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface MecanicosGateway {

	/**
	 * Establece la conexi�n
	 * @param c Conexi�n
	 */
	void setConnection(Connection c);

	/**
	 * Guarda un mec�nico nuevo
	 * @param nombre Nombre del mec�nico a guardar
	 * @param apellidos Apellidos del mec�nico a guardar
 	 */
	void save(String nombre, String apellidos);
	
	/**
	 * Encuentra un mec�nico por ID
	 * @param id ID del mec�nico a encontrar
	 * @return datos del mec�nico encontrado (como mapa)
	 */
	Map<String, Object> findById(Long id);
	
	/**
	 * Devuelve todos los mec�nicos
	 * @return Lista que contiene los mapas con los atributos de cada mec�nico
	 */
	List<Map<String, Object>> findAll();
	
	/**
	 * Actualiza un mec�nico
	 * @param id Id del mec�nico a actualizar
	 * @param nombre Nombre nuevo del mec�nico
	 * @param apellidos Apellidos nuevos del mec�nico
	 */
	void update(Long id, String nombre, String apellidos);
	
	/**
	 * Borra un mec�nico
	 * @param id ID del mec�nico a borrar
	 */
	void delete(Long id);

}
