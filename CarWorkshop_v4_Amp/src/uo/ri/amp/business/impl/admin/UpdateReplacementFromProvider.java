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
public class UpdateReplacementFromProvider { // El �nico campo que se puede
												// modificar es el del precio

	Long replacementCode, providerCode;
	Double newPrice;

	/**
	 * Contructor
	 * 
	 * @param replacementCode
	 *            C�digo del repuesto
	 * @param providerCode
	 *            C�digo del proveedor
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
	 * Crea la conexi�n, la a factor�a de persistencia y a trav�s del gateway
	 * establece la conexi�n y llama al m�todo de persistencia que actualiza el
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
