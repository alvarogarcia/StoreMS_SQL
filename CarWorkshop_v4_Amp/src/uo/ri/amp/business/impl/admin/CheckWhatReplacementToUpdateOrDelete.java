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
 * @author Álvaro García
 * 
 */
public class CheckWhatReplacementToUpdateOrDelete {

	Long replacementCode, providerCode;
	boolean isReplacementCode;

	/**
	 * Constructor
	 * 
	 * @param replacementCode
	 *            Código del repuesto
	 * @param providerCode
	 *            Código del proveedor
	 * @param isReplacementCode
	 *            Determina si el filtrado se va a hacer por código de repuesto
	 *            (true) o por código de proveedor (false)
	 */
	public CheckWhatReplacementToUpdateOrDelete(Long replacementCode,
			Long providerCode, boolean isReplacementCode) {
		this.replacementCode = replacementCode;
		this.providerCode = providerCode;
		this.isReplacementCode = isReplacementCode;
	}

	/**
	 * crea la conexiñon y el gateway de proveedores. A través del gateway le
	 * pasa la conexión y llama al me´todo persistente que comprueba los
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
