package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import entidades.Personal;

public class DTPersonal {

	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsPersonal = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	// Metodo para llenar el RusultSet
	public void LlenarExpedienteDocente(Connection c) {
		try {
			ps = c.prepareStatement("SELECT * FROM PERSONAL ORDER BY asignatura ASC;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsPersonal = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR PERSONAL " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Metodo para almacenar nuevo personal
	public boolean guardarPersonal(Personal p) {
		boolean guardado = false;
		Date date = new Date(Calendar.getInstance().getTime().getTime());

		try {
			c = PoolConexion.getConnection();
			this.LlenarExpedienteDocente(c);
			rsPersonal.moveToInsertRow();
			rsPersonal.updateString("nombre", p.getNombre());
			rsPersonal.updateString("apellido", p.getApellido());
			rsPersonal.updateString("correo", p.getCorreo());
			rsPersonal.updateString("telefono", p.getTelefono());
			rsPersonal.updateInt("estado", 1);
			rsPersonal.updateInt("id_usuario", p.getId_usuario());
			rsPersonal.updateInt("id_coordinacion", p.getId_coordinacion());
			rsPersonal.updateInt("id_departamento", p.getId_departamento());
			rsPersonal.insertRow();
			rsPersonal.moveToCurrentRow();
			guardado = true;
		} catch (Exception e) {
			System.err.println("ERROR AL GUARDAR PERSONAL: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rsPersonal != null) {
					rsPersonal.close();
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
	
	public int getIdPersonal(String correo) {
		int id = 0;
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from personal where correo like ?;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, correo);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id_personal");
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN BUSCAR ID PERSONAL" + e.getMessage());
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
		return id;
	}
	
	public String getCorreo(int id) {
		String correo = "";
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select correo from personal where id_personal = ?;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				correo = rs.getString("correo");
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN BUSCAR CORREO PERSONAL " + e.getMessage());
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
		return correo;
	}
}
