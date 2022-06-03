package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Rol;

public class DTRol {

	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsRol = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

		// Metodo para llenar el RusultSet
	public void LlenarRol(Connection c) {
		try {
			ps = c.prepareStatement("SELECT * FROM ROL ORDER BY asignatura ASC;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsRol = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR ROL " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<Rol> listarRol() {
		ArrayList<Rol> listarRol = new ArrayList<>();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from rol;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while (rs.next()) {
				Rol rol = new Rol();

				rol.setId_rol(rs.getInt("id_rol"));
				rol.setNombre(rs.getString("nombre"));
				rol.setDescripcion(rs.getString("descripcion"));
				rol.setEstado(rs.getInt("estado"));
				listarRol.add(rol);
		}
	} catch (Exception e) {
		System.out.println("DATOS: ERROR EN LISTAR ROLES" + e.getMessage());
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
	return listarRol;
}
}
