package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Cargo;

public class DTCargo {

	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsCargo = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	// Metodo para llenar el RusultSet
	public void LlenarExpedienteDocente(Connection c) {
		try {
			ps = c.prepareStatement("SELECT * FROM CARGO;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsCargo = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR CARGO " + e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<Cargo> listarCargo() {
		ArrayList<Cargo> listarCargo = new ArrayList<>();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from cargo;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while (rs.next()) {
				Cargo cargo = new Cargo();

				cargo.setId_cargo(rs.getInt("id_cargo"));
				cargo.setNombre(rs.getString("nombre"));
				cargo.setDescripcion(rs.getString("descripcion"));
				cargo.setEstado(rs.getInt("estado"));
				listarCargo.add(cargo);

			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR CARGO" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (c != null) {
					PoolConexion.closeConnection(c);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return listarCargo;
	}

}
