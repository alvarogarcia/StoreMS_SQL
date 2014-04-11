package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;

import uo.ri.amp.persistence.ProvidersGateway;
import uo.ri.amp.conf.PersistenceFactory;

/**
 * @author �lvaro Garc�a
 * 
 */
public class UpdateProvider {

	Long providerCode;
	String providerName;

	/**
	 * Constructor
	 * 
	 * @param providerCode
	 *            C�digo del proveedor
	 * @param providerName
	 *            Nombre del proveedor
	 */
	public UpdateProvider(Long providerCode, String providerName) {
		this.providerCode = providerCode;
		this.providerName = providerName;
	}

	/**
	 * Crea la conexi�n con la base de datos y un gateway de proveedores a
	 * trav�s del cu�l le pasa la conexi�n y llama al m�todo persitente que
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
