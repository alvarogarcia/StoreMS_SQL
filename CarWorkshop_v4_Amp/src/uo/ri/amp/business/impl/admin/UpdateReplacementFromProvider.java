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
public class UpdateReplacementFromProvider { // El único campo que se puede
												// modificar es el del precio

	Long replacementCode, providerCode;
	Double newPrice;

	/**
	 * Contructor
	 * 
	 * @param replacementCode
	 *            Código del repuesto
	 * @param providerCode
	 *            Código del proveedor
	 * @param newPrice
	 *            Precio
	 */
	public UpdateReplacementFromProvider(Long replacementCode,
			Long providerCode, Double newPrice) {
		this.replacementCode = replacementCode;
		this.providerCode = providerCode;
		this.newPrice = newPrice;
	}

	/**
	 * Crea la conexión, la a factoría de persistencia y a través del gateway
	 * establece la conexión y llama al método de persistencia que actualiza el
	 * repuesto del proveedor
	 */
	public void execute() {
		Connection c = null;

		try {
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
			ProvidersGateway pG = PersistenceFactory.getProvidersGateway();

			pG.setConnection(c);

			pG.updateReplacementFromProvider(replacementCode, providerCode,
					newPrice);
			c.commit();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
	}

}
