package uo.ri.business.impl.admin;


import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

import alb.util.jdbc.Jdbc;

public class AddMechanic {


	private String nombre;
	private String apellidos;

	/**
	 * Constructor
	 * @param nombre
	 * @param apellidos
	 */
	public AddMechanic(String nombre, String apellidos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	/**
	 * Método execute. Conecta con la base de datos y a través del Gateway (al que le
	 * pasa la conexión) guarda el nuevo mecánico en la Base de Datos.
	 */
	public void execute() {
		// Procesar
		Connection c = null;
		//PreparedStatement pst = null;
		//ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			MecanicosGateway mG = PersistenceFactory.getMecanicosGateway();
			
			mG.setConnection(c);
			mG.save(nombre, apellidos);
			
			/*pst = c.prepareStatement(SQL_INSERT_MECHANIC);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);

			pst.executeUpdate();*/

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			//Jdbc.close(rs, pst, c);
			Jdbc.close(c);
		}
	}
}
