package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

import alb.util.jdbc.Jdbc;

public class UpdateMechanic {

	//private static String SQL_UPDATE_MECHANIC = "update TMecanicos "
		//	+ "set nombre = ?, apellidos = ? " + "where id = ?";

	Long id;
	String nombre, apellidos;

	/**
	 * Constructor de la clase que actualiza los mec�nicos
	 * @param id
	 * @param nombre
	 * @param apellidos
	 */
	public UpdateMechanic(Long id, String nombre, String apellidos) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	/**
	 * M�todo execute. Conecta con la base de datos y a trav�s del Gateway (al que le
	 * pasa la conexi�n) actualiza el nombre y los apellidos del mec�nico.
	 */
	public void execute() {
		// Procesar
		Connection c = null;
		

		try {
			c = Jdbc.getConnection();

			MecanicosGateway mG = PersistenceFactory.getMecanicosGateway();

			mG.setConnection(c);
			mG.update(id, nombre, apellidos);

			
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}

}
