package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import entidades.UsuarioRol;

public class DTUsuarioRol {

	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsURol = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	// Metodo para llenar el RusultSet
	public void LlenarRol(Connection c) {
		try {
			ps = c.prepareStatement("SELECT * FROM USUARIO_ROL;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsURol = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR USUARIO ROL " + e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<UsuarioRol> listarUsuarioRol() {
		ArrayList<UsuarioRol> listarUsuarioRol = new ArrayList<>();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from rol;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while (rs.next()) {
				UsuarioRol ur = new UsuarioRol();

				ur.setId_usuario_rol(rs.getInt("id_usuario_rol"));
				ur.setFecha_creacion(rs.getDate("fecha_creacion"));
				ur.setId_rol(rs.getInt("id_rol"));
				ur.setId_usuario(rs.getInt("id_usuario"));
				listarUsuarioRol.add(ur);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR USUARIO ROLES" + e.getMessage());
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
		return listarUsuarioRol;
	}

	// Metodo para almacenar nuevo usuario rol
	public boolean guardarUsuarioRol(UsuarioRol ur) {
		boolean guardado = false;
		Date date = new Date(Calendar.getInstance().getTime().getTime());

		try {
			c = PoolConexion.getConnection();
			this.LlenarRol(c);
			rsURol.moveToInsertRow();
			rsURol.updateDate("fecha_creacion", date);
			rsURol.updateInt("id_rol", ur.getId_rol());
			rsURol.updateInt("id_usuario", ur.getId_usuario());
			rsURol.insertRow();
			rsURol.moveToCurrentRow();
			guardado = true;
		} catch (Exception e) {
			System.err.println("ERROR AL USUARIO ROL: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rsURol != null) {
					rsURol.close();
				}
				if (c != null) {
					PoolConexion.closeConnection(c);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return guardado;
	}

}
