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
public class DeleteReplacementFromProvider {

	Long replacementCode, providerCode;

	/**
	 * Constructor
	 * 
	 * @param replacementCode
	 *            Código del repuesto
	 * @param providerCode
	 *            Código del proveedor
	 */
	public DeleteReplacementFromProvider(Long replacementCode, Long providerCode) {
		this.replacementCode = replacementCode;
		this.providerCode = providerCode;
	}

	/**
	 * Crea la conexión y una gateway de proveedores y a través del mismo le
	 * pasa la conexión y llama al método persistente que elimina el repuesto de
	 * un proveedor
	 */
	public void execute() {
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
			ProvidersGateway pG = PersistenceFactory.getProvidersGateway();

			pG.setConnection(c);
			pG.deleteReplacementFromProvider(replacementCode, providerCode);
			c.commit();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}

}
