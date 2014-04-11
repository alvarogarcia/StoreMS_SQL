package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.ProvidersGateway;
import alb.util.jdbc.Jdbc;

/**
 * @author �lvaro Garc�a
 * 
 */
public class DeleteProvider {

	Long providerCode;

	/**
	 * Elimina un proveedor
	 * 
	 * @param providerCode
	 *            C�digo del proveedor a eliminar
	 */
	public DeleteProvider(Long providerCode) {
		this.providerCode = providerCode;
	}

	/**
	 * Crea la conexi�n y un gateway de proveedores a trav�s del cu�l le pasa la
	 * conexi�n y llama al m�todo persistente que elimina el proveedor de la
	 * base de datos
	 */
	public void execute() {
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
			ProvidersGateway pG = PersistenceFactory.getProvidersGateway();

			pG.setConnection(c);
			pG.deleteProvider(providerCode);
			c.commit();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}

}
