package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;

import uo.ri.amp.persistence.ProvidersGateway;
import uo.ri.amp.conf.PersistenceFactory;

/**
 * @author Álvaro García
 * 
 */
public class UpdateProvider {

	Long providerCode;
	String providerName;

	/**
	 * Constructor
	 * 
	 * @param providerCode
	 *            Código del proveedor
	 * @param providerName
	 *            Nombre del proveedor
	 */
	public UpdateProvider(Long providerCode, String providerName) {
		this.providerCode = providerCode;
		this.providerName = providerName;
	}

	/**
	 * Crea la conexión con la base de datos y un gateway de proveedores a
	 * través del cuál le pasa la conexión y llama al método persitente que
	 * actualiza el nombre del proveedor
	 */
	public void execute() {
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
			ProvidersGateway pG = PersistenceFactory.getProvidersGateway();

			pG.setConnection(c);

			pG.updateProviderName(providerCode, providerName);
			c.commit();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}

}
