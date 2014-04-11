package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MecanicosGateway;

import alb.util.jdbc.Jdbc;

public class DeleteMechanic {


	Long idMecanico;

	/**
	 * Elimina un mecánico a través del Gateway.
	 * @param idMecanico id del mecánico a eliminar
	 */
	public DeleteMechanic(Long idMecanico) {
		this.idMecanico = idMecanico;
	}

	public void execute() {
		Connection c = null;

		try {
			c = Jdbc.getConnection();

			MecanicosGateway mG = PersistenceFactory.getMecanicosGateway();

			mG.setConnection(c);
			mG.delete(idMecanico);



		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}

}
