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
public class AddReplacementToProvider {

	Long replacementCode, providerCode;
	Double price;

	/**
	 * Constructor
	 * 
	 * @param replacementCode
	 *            Código del repuesto
	 * @param providerCode
	 *            Código del proveedor
	 * @param price
	 *            Precio
	 */
	public AddReplacementToProvider(Long replacementCode, Long providerCode,
			Double price) {
		this.replacementCode = replacementCode;
		this.providerCode = providerCode;
		this.price = price;
	}

	/**
	 * Crea la conexión y a través del gateway que crea establece la conexión y
	 * llama al método persistence que añade el repuesto al proveedor
	 */
	public void execute() {
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
			ProvidersGateway pG = PersistenceFactory.getProvidersGateway();

			pG.setConnection(c);
			pG.addReplacementToProvider(replacementCode, providerCode, price);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}

}
