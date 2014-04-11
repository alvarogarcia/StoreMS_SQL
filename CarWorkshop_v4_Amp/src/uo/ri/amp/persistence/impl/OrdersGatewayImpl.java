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
import uo.ri.amp.persistence.OrdersGateway;

/**
 * @author Álvaro García
 * 
 */
public class OrdersGatewayImpl implements OrdersGateway {

	private Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;

	}

	@Override
	public List<Map<String, Object>> findOrdersByProvider(Long providerCode) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		ResultSet rs2 = null;
		PreparedStatement pst2 = null;

		ArrayList<Map<String, Object>> orders = new ArrayList<Map<String, Object>>();

		try {
			String sentencia = Conf.get("SQL_FINDBYPROVIDER");
			pst = c.prepareStatement(sentencia);
			pst.setLong(1, providerCode);
			pst.setLong(2, providerCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				Map<String, Object> mapa = new HashMap<String, Object>();

				mapa.put("ID", rs.getLong(1));
				mapa.put("FECHA_CREACION", rs.getString(2));
				mapa.put("FECHA_RECEPCION", rs.getString(3));
				mapa.put("PRECIO", rs.getDouble(4));
				mapa.put("PROVEEDOR", rs.getLong(5));
				mapa.put("ESTADO_RECEPCION", rs.getString(6));

				// Ahora una vez que conozco los detalles del pedido saco todos
				// los repuestos pedidos
				List<Map<String, Object>> itemsPedido = new ArrayList<Map<String, Object>>();
				String sentencia2 = Conf.get("SQL_FINDITEMSPEDIDO");
				pst2 = c.prepareStatement(sentencia2);
				pst2.setLong(1, rs.getLong(1));
				rs2 = pst2.executeQuery();
				while (rs2.next()) {
					Map<String, Object> itemPedido = new HashMap<String, Object>();
					itemPedido.put("PEDIDO", rs2.getLong(1));
					itemPedido.put("REPUESTO", rs2.getLong(2));
					itemPedido.put("CANTIDAD", rs2.getLong(3));
					itemPedido.put("PRECIO", rs2.getDouble(4));

					itemsPedido.add(itemPedido);

				}

				mapa.put("ITEMSPEDIDO", itemsPedido);
				Jdbc.close(rs2, pst2);

				orders.add(mapa);
			}

		} catch (SQLException e) {
			Printer.printException(
					"Excepción SQL en método findOrdersByProvider de la clase "
							+ "OrdersGatewayImpl", e);
		} finally {
			Jdbc.close(rs, pst);
		}

		return orders;

	}

	@Override
	public Map<String, Object> findOrder(Long orderCode) {
		Map<String, Object> pedido = new HashMap<String, Object>();
		ResultSet rs = null;
		PreparedStatement pst = null;
		ResultSet rs2 = null;
		PreparedStatement pst2 = null;

		try {
			String sentencia = Conf.get("SQL_FINDORDER");
			pst = c.prepareStatement(sentencia);
			pst.setLong(1, orderCode);
			rs = pst.executeQuery();
			if (rs.next()) {

				pedido.put("ID", rs.getLong(1));
				pedido.put("FECHA_CREACION", rs.getString(2));
				pedido.put("FECHA_RECEPCION", rs.getString(3));

				pedido.put("PRECIO", rs.getDouble(4));
				pedido.put("PROVEEDOR", rs.getLong(5));

				pedido.put("ESTADO_RECEPCION", rs.getString(6));

				// Ahora una vez que conozco los detalles del pedido saco todos
				// los repuestos pedidos
				List<Map<String, Object>> itemsPedido = new ArrayList<Map<String, Object>>();
				String sentencia2 = Conf.get("SQL_FINDITEMSPEDIDO");
				pst2 = c.prepareStatement(sentencia2);
				pst2.setLong(1, rs.getLong(1));
				rs2 = pst2.executeQuery();
				while (rs2.next()) {
					Map<String, Object> itemPedido = new HashMap<String, Object>();
					itemPedido.put("PEDIDO", rs2.getLong(1));
					itemPedido.put("REPUESTO", rs2.getLong(2));
					itemPedido.put("CANTIDAD", rs2.getLong(3));
					itemPedido.put("PRECIO", rs2.getDouble(4));

					itemsPedido.add(itemPedido);

				}

				pedido.put("ITEMSPEDIDO", itemsPedido);

			}

		} catch (SQLException e) {
			Printer.printException(
					"Excepción SQL en método findOrder de la clase OrdersGatewayImpl",
					e);
		} finally {
			Jdbc.close(rs2, pst2);
			Jdbc.close(rs, pst);
		}

		return pedido;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateOrderReceived(Map<String, Object> pedido) {
		// Pongo el estado del pedido a RECIBIDO
		// Relleno la fecha de recepción del pedido con la fecha/hora actual

		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		ResultSet rs = null;

		try {
			String sentencia = Conf.get("SQL_UPDATEORDERRECEIVED");
			pst = c.prepareStatement(sentencia);
			pst.setString(1, "RECIBIDO"); // como primer parámetro cambio el
											// estado a recibido
			java.sql.Date sqlDate = new java.sql.Date(
					new java.util.Date().getTime());
			pst.setDate(2, sqlDate);
			pst.setLong(3, (Long) pedido.get("ID")); // Actualizo el pedido del
														// código dado
			pst.executeUpdate();

			// Se actualizan los repuestos recibidos con las unidades recibidas
			// se actualizan los precios de los repuestos recibidos con el nuevo
			// precio
			// que resulta de calcular la media ponderada entre las unidades
			// existentes
			// (al precio actual) y las unidades recibidas (al precio del pedido

			// El mapa pedido que se le pasa como parámetro a esta función ya
			// contiene en cada item los elementos actualizados
			// ya que esto se ha realizado en la lógica de la aplicación

			for (Map<String, Object> itemPedido : (List<Map<String, Object>>) pedido
					.get("ITEMSPEDIDO")) {

				sentencia = Conf.get("SQL_UPDATEREPLACEMENTORDERRECEIVED");
				pst2 = c.prepareStatement(sentencia);
				pst2.setLong(1, (Long) itemPedido.get("CANTIDAD"));
				pst2.setDouble(2, (Double) itemPedido.get("PRECIO"));
				pst2.setLong(3, (Long) itemPedido.get("REPUESTO"));

				pst2.executeUpdate();
			}

		} catch (SQLException e) {
			Printer.printException(
					"Excepción SQL en método updateOrderReceived de la clase"
							+ " OrdersGatewayImpl", e);
		} finally {
			Jdbc.close(pst);
			Jdbc.close(rs, pst2);
		}

	}

	/**
	 * Obtiene los precios y las cantidades de cada Item del pedido recibido
	 * antes de que estos sean añadidos. Esto se va a utilizar para poder
	 * actualizar en la lógica los precios usando la media ponderada en función
	 * de la cantidad y precios anteriores y los nuevos.
	 * 
	 * @param pedido
	 *            Contiene toda la información del pedido recibido. De él se
	 *            obtendrán los IDs de los items
	 * @return Devuelve una lista que contiene un mapa por cada item recibido
	 *         con su ID, precio anterior y cantidad anterior.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uo.ri.amp.persistence.OrdersGateway#pricesAndQuantityItemsOrderBefore
	 * (java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> pricesAndQuantityItemsOrderBefore(
			Map<String, Object> pedido) {
		List<Map<String, Object>> resultado = new ArrayList<Map<String, Object>>();
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> itemsPedido = (List<Map<String, Object>>) pedido
				.get("ITEMSPEDIDO");
		String sentencia;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			for (Map<String, Object> itemPedido : itemsPedido) {
				// De cada item del pedido compruebo cuantas unidades había
				// antes y a qué precio
				sentencia = Conf.get("SQL_GETREPLACEMENTQUANTITYANDPRICE");

				pst = c.prepareStatement(sentencia);
				pst.setLong(1, (Long) itemPedido.get("REPUESTO"));
				rs = pst.executeQuery();
				if (rs.next()) {
					// Sacamos el precio y lo metemos en un hashmap
					Map<String, Object> datosItemBf = new HashMap<String, Object>();
					datosItemBf.put("REPUESTO", itemPedido.get("REPUESTO"));
					datosItemBf.put("CANTIDAD", rs.getLong(1));
					datosItemBf.put("PRECIO", rs.getDouble(2));
					resultado.add(datosItemBf);
				}
			}

		} catch (SQLException e) {
			Printer.printException(
					"ERROR SQL en método pricesAndQuantityItemsOrderBefore de "
							+ "la clase OrdersGatewayImpl", e);
		} finally {
			Jdbc.close(rs, pst);

		}

		return resultado;
	}

}
