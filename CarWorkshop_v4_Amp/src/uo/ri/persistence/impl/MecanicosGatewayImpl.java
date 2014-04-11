package uo.ri.persistence.impl;

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

import uo.ri.persistence.MecanicosGateway;

public class MecanicosGatewayImpl implements MecanicosGateway {

	Connection c;

	@Override
	public void setConnection(Connection c) {
		this.c = c;

	}

	@Override
	public void save(String nombre, String apellidos) {
		try {
			PreparedStatement pst = c.prepareStatement("insert into "
					+ "TMecanicos(nombre, apellidos) values (?, ?)");
			pst.setString(1, nombre);
			pst.setString(2, apellidos);

			pst.executeUpdate();
			Jdbc.close(pst);
		} catch (SQLException e) {
			System.err
					.println("ERROR en método save de la clase MecanicosGatewayImpl");
			Printer.printException(
					"ERROR en método save de la clase MecanicosGatewayImpl", e);
		}

	}

	@Override
	public Map<String, Object> findById(Long id) {
		PreparedStatement pst;
		Map<String, Object> mechanic = null;
		try {
			pst = c.prepareStatement("select id, nombre, apellidos from TMecanicos WHERE id = ?");
			ResultSet rs = pst.executeQuery();

			if (rs.next())
				mechanic = load(rs);
			

			Jdbc.close(rs, pst);
		} catch (SQLException e) {
			Printer.printException(
					"ERROR en método findById de la clase MecanicosGatewayImpl",
					e);
		}

		return mechanic;
	}

	private Map<String, Object> load(ResultSet rs) {
		Map<String, Object> mechanic = new HashMap<String, Object>();
		try {
			mechanic.put("id", rs.getLong(1));
			mechanic.put("nombre", rs.getString(2));
			mechanic.put("apellidos", rs.getString(3));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mechanic;
	}

	@Override
	public List<Map<String, Object>> findAll() {
		ArrayList<Map<String, Object>> mechanics = new ArrayList<Map<String, Object>>();
		PreparedStatement pst;
		try {
			pst = c.prepareStatement("select id, nombre, apellidos from TMecanicos");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Map<String, Object> mechanic = load(rs);
				mechanics.add(mechanic);
			}

			Jdbc.close(rs, pst);
		} catch (SQLException e) {
			Printer.printException(
					"ERROR en método findAll de la clase MecanicosGatewayImpl",
					e);
		}

		return mechanics;

	}

	@Override
	public void update(Long id, String nombre, String apellidos) {

		try {
			PreparedStatement pst = c.prepareStatement("update TMecanicos "
					+ "set nombre = ?, apellidos = ? " + "where id = ?");
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setLong(3, id);

			pst.executeUpdate();
			Jdbc.close(pst);
		} catch (SQLException e) {
			Printer.printException(
					"ERROR en método update de la clase MecanicosGatewayImpl",
					e);
		}

	}

	@Override
	public void delete(Long id) {
		PreparedStatement pst;
		try {
			pst = c.prepareStatement("delete from TMecanicos where id = ?");
			pst.setLong(1, id);
			pst.executeUpdate();

			Jdbc.close(pst);
		} catch (SQLException e) {
			Printer.printException(
					"ERROR en método delete de la clase MecanicosGatewayImpl",
					e);
		}

	}

}
