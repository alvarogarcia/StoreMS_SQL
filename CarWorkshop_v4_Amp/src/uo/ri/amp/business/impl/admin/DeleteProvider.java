package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.ProvidersGateway;
import alb.util.jdbc.Jdbc;

/**
 * @author Álvaro García
 * 
 */
public class DeleteProvider {

	Long providerCode;

	/**
	 * Elimina un proveedor
	 * 
	 * @param providerCode
	 *            Código del proveedor a eliminar
	 */
	public DeleteProvider(Long providerCode) {
		this.providerCode = providerCode;
	}

	/**
	 * Crea la conexión y un gateway de proveedores a través del cuál le pasa la
	 * conexión y llama al método persistente que elimina el proveedor de la
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
