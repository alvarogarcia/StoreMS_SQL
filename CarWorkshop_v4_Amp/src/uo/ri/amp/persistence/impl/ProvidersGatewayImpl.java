package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.console.Printer;
import alb.util.jdbc.Jdbc;

import uo.ri.amp.conf.Conf;
import uo.ri.amp.conf.ServicesFactory;
import uo.ri.amp.persistence.ProvidersGateway;

/**
 * @author Álvaro García
 * 
 */
public class ProvidersGatewayImpl implements ProvidersGateway {

	private Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;
	}

	@Override
	public void addProvider(String providerName) {
		PreparedStatement pst = null;

		try {
			String sentencia = Conf.get("SQL_ADDPROVIDER");
			pst = c.prepareStatement(sentencia);
			pst.setString(1, providerName);
			pst.executeUpdate();

		} catch (SQLException e) {
			Printer.printException(
					"Excepción SQL en método addProvider de la clase "
							+ "ProvidersGatewayImpl", e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void deleteProvider(Long providerCode) {
		PreparedStatement pst = null;

		try {
			// En caso de que haya algún pedido para el proveedor, no se va a
			// poder eliminar
			List<Map<String, Object>> pedidos = ServicesFactory
					.getOrdersService().listOrders(providerCode);

			if (pedidos.size() > 0) {
				throw new RuntimeException(
						"ERROR. No se puede eliminar un proveedor si hay "
								+ "pedidos para el mismo");
			}
			String sentencia = Conf.get("SQL_DELETEPROVIDER");
			pst = c.prepareStatement(sentencia);
			pst.setLong(1, providerCode);
			pst.executeUpdate();

		} catch (SQLException e) {
			Printer.printException(
					"Excepción SQL en método DELETEPROVIDER de la clase"
							+ " ProvidersGatewayImpl", e);
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public void updateProviderName(Long providerCode, String providerName) {
		PreparedStatement pst = null;

		try {
			String sentencia = Conf.get("SQL_UPDATEPROVIDERNAME");
			pst = c.prepareStatement(sentencia);
			pst.setString(1, providerName);
			pst.setLong(2, providerCode);
			pst.executeUpdate();

		} catch (SQLException e) {
			Printer.printException(
					"Excepción SQL en método UPDATEPROVIDERNAME de la clase "
							+ "ProviderssGatewayImpl", e);
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public void addReplacementToProvider(Long replacementCode,
			Long providerCode, Double price) {
		PreparedStatement pst = null;

		try {
			String sentencia = Conf.get("SQL_ADDREPLACEMENTTOPROVIDER");
			pst = c.prepareStatement(sentencia);
			pst.setLong(1, replacementCode);
			pst.setLong(2, providerCode);
			pst.setDouble(3, price);
			pst.executeUpdate();

		} catch (SQLException e) {
			Printer.printException(
					"Excepción SQL en método addReplacementToProvider de la "
							+ "clase ProvidersGatewayImpl", e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void deleteReplacementFromProvider(Long replacementCode,
			Long providerCode) {
		PreparedStatement pst = null;

		try {
			// en primer lugar comprueba si hay algún pedido de ese repuesto a
			// ese proveedor
			// ya que en caso de haberlo no se va a poder eliminar.
			List<Map<String, Object>> pedidos = ServicesFactory
					.getOrdersService().listOrders(providerCode);
			// Una vez que ya tengo los pedidos del proveedor, compruebo si
			// alguno de ellos es el
			// del repuesto que se intenta elimianr
			for (Map<String, Object> pedido : pedidos) {
				if (pedido.get("REPUESTO") == replacementCode)
					throw new RuntimeException(
							"ERROR. No se puede eliminar un repuesto de un "
									+ "proveedor para el que hay un pedido");
			}

			String sentencia = Conf.get("SQL_DELETEREPLACEMENTFROMPROVIDER");
			pst = c.prepareStatement(sentencia);
			pst.setLong(1, replacementCode);
			pst.setLong(2, providerCode);
			pst.executeUpdate();

		} catch (SQLException e) {
			Printer.printException(
					"Excepción SQL en método DELETEREPLACEMENTFROMPROVIDER de"
							+ " la clase ProvidersGatewayImpl", e);
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public void updateReplacementFromProvider(Long replacementCode,
			Long providerCode, Double newPrice) {
		PreparedStatement pst = null;

		try {
			String sentencia = Conf.get("SQL_UPDATEREPLACEMENTFROMPROVIDER");
			pst = c.prepareStatement(sentencia);
			pst.setDouble(1, newPrice);
			pst.setLong(2, replacementCode);
			pst.setLong(3, providerCode);
			pst.executeUpdate();

		} catch (SQLException e) {
			Printer.printException(
					"Excepción SQL en método UPDATEREPLACEMENTFROMPROVIDER"
							+ " de la clase ProviderssGatewayImpl", e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public List<Map<String, Object>> checkWhatReplacementToUpdateOrDelete(
			Long replacementCode, Long providerCode, boolean isReplacementCode) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Map<String, Object>> replacements = new ArrayList<Map<String, Object>>();

		try {
			if (isReplacementCode) {
				String sentencia = Conf
						.get("SQL_LISTREPLACEMENTSPROVIDERSBYCODEREPLACEMENT");
				pst = c.prepareStatement(sentencia);
				pst.setLong(1, replacementCode);

				rs = pst.executeQuery();
			} else {
				String sentencia = Conf
						.get("SQL_LISTREPLACEMENTSPROVIDERSBYCODEPROVIDER");
				pst = c.prepareStatement(sentencia);
				pst.setLong(1, providerCode);
				rs = pst.executeQuery();
			}

			while (rs.next()) {
				Map<String, Object> mapa = new HashMap<String, Object>();

				mapa.put("REPUESTO", rs.getLong(1));
				mapa.put("PROVEEDOR", rs.getLong(2));
				mapa.put("PRECIO", rs.getDouble(3));

				replacements.add(mapa);
			}

		} catch (SQLException e) {
			Printer.printException(
					"Excepción SQL en método checkWhatReplacementToUpdateOrDelete"
							+ " de la clase ProviderssGatewayImpl", e);
		} finally {
			Jdbc.close(rs, pst);
		}

		return replacements;
	}

	@Override
	public List<Map<String, Object>> checkWhatProviderToUpdate(
			String nameProviderBeforeUpdating) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Map<String, Object>> providers = new ArrayList<Map<String, Object>>();

		try {

			String sentencia = Conf.get("SQL_LISTPROVIDERSBYNAME");
			pst = c.prepareStatement(sentencia);
			pst.setString(1, nameProviderBeforeUpdating);
			rs = pst.executeQuery();

			while (rs.next()) {
				Map<String, Object> mapa = new HashMap<String, Object>();

				mapa.put("ID", rs.getLong(1));
				mapa.put("NOMBRE", rs.getString(2));

				providers.add(mapa);
			}

		} catch (SQLException e) {
			Printer.printException(
					"Excepción SQL en método checkWhatProviderToUpdate de la "
							+ "clase ProviderssGatewayImpl", e);
		} finally {
			Jdbc.close(rs, pst);
		}

		return providers;
	}

	@Override
	public List<Map<String, Object>> listAllProviders() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Map<String, Object>> listaProveedores = new ArrayList<Map<String, Object>>();
		try {
			String sentencia = Conf.get("SQL_LISTPROVIDERS");
			pst = c.prepareStatement(sentencia);
			rs = pst.executeQuery();
			while (rs.next()) {
				// Creamos un map y metemos en él la información del proveedor
				Map<String, Object> infoProveedor = new HashMap<String, Object>();
				infoProveedor.put("ID", rs.getInt("ID"));
				infoProveedor.put("NOMBRE", rs.getString("NOMBRE"));
				listaProveedores.add(infoProveedor);
			}

		} catch (SQLException e) {
			Printer.printException(
					"Excepción SQL en método listAllProviders de la clase"
							+ " ProviderssGatewayImpl", e);
		} finally {
			Jdbc.close(rs, pst);
		}

		return listaProveedores;
	}

}
