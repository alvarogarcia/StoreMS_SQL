package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uo.ri.amp.conf.PersistenceFactory;
import uo.ri.amp.persistence.ProvidersGateway;
import alb.util.jdbc.Jdbc;

/**
 * @author �lvaro Garc�a
 * 
 */
public class CheckWhatReplacementToUpdateOrDelete {

	Long replacementCode, providerCode;
	boolean isReplacementCode;

	/**
	 * Constructor
	 * 
	 * @param replacementCode
	 *            C�digo del repuesto
	 * @param providerCode
	 *            C�digo del proveedor
	 * @param isReplacementCode
	 *            Determina si el filtrado se va a hacer por c�digo de repuesto
	 *            (true) o por c�digo de proveedor (false)
	 */
	public CheckWhatReplacementToUpdateOrDelete(Long replacementCode,
			Long providerCode, boolean isReplacementCode) {
		this.replacementCode = replacementCode;
		this.providerCode = providerCode;
		this.isReplacementCode = isReplacementCode;
	}

	/**
	 * crea la conexi�on y el gateway de proveedores. A trav�s del gateway le
	 * pasa la conexi�n y llama al me�todo persistente que comprueba los
	 * repuestos dado un proveedor
	 * 
	 * @return Lista de mapas, cada uno de ellos con los repuestos y sus
	 *         proveedores, una vez realizado el filtrado
	 */
	public List<Map<String, Object>> execute() {
		List<Map<String, Object>> lista = new ArrayList<Map<String, Object>>();

		Connection c = null;

		try {
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
			ProvidersGateway pG = PersistenceFactory.getProvidersGateway();

			pG.setConnection(c);
			lista = pG.checkWhatReplacementToUpdateOrDelete(replacementCode,
					providerCode, isReplacementCode);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}

		return lista;

	}

}
