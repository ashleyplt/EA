package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vistas.VW_Expediente_Docente;

public class DTVExpedienteDocente {

	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsAsignatura = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	// Metodo para llenar el RusultSet
	public void LlenarExpedienteDocente(Connection c) {

		try {
			ps = c.prepareStatement("SELECT * FROM vw_expediente_docente ORDER BY asignatura ASC;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsAsignatura = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LOS EXPEDIENTES " + e.getMessage());
			e.printStackTrace();
		}

	}

	public ArrayList<VW_Expediente_Docente> listarExpedienteSegunDocente(int id_docente) {
		ArrayList<VW_Expediente_Docente> listarExpediente = new ArrayList<>();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from vw_expediente_docente where id_docente = " + id_docente + ";",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while (rs.next()) {
				VW_Expediente_Docente expediente = new VW_Expediente_Docente();
				expediente.setEdicion(rs.getString("edicion"));
				expediente.setId_asignatura(rs.getInt("id_asignatura"));
				expediente.setCod_asignatura(rs.getString("cod_asignatura"));
				expediente.setAsignatura(rs.getString("asignatura"));
				expediente.setDescripcion(rs.getString("descripcion"));
				expediente.setUrl_expediente(rs.getString("url_expediente"));
				expediente.setDescripcion(rs.getString("descripcion"));
				expediente.setFecha_creacion(rs.getDate("fecha_creacion"));
				expediente.setId_docente(rs.getInt("id_docente"));
				expediente.setDocente(rs.getString("docente"));
				listarExpediente.add(expediente);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LOS EXPEDIENTES " + e.getMessage());
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
		return listarExpediente;
	}

	public ArrayList<VW_Expediente_Docente> listarExpediente() {
		ArrayList<VW_Expediente_Docente> listarExpediente = new ArrayList<>();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from vw_expediente_docente;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while (rs.next()) {
				VW_Expediente_Docente expediente = new VW_Expediente_Docente();
				expediente.setEdicion(rs.getString("edicion"));
				expediente.setId_asignatura(rs.getInt("id_asignatura"));
				expediente.setCod_asignatura(rs.getString("cod_asignatura"));
				expediente.setAsignatura(rs.getString("asignatura"));
				expediente.setDescripcion(rs.getString("descripcion"));
				expediente.setUrl_expediente(rs.getString("url_expediente"));
				expediente.setDescripcion(rs.getString("descripcion"));
				expediente.setFecha_creacion(rs.getDate("fecha_creacion"));
				expediente.setId_docente(rs.getInt("id_docente"));
				expediente.setDocente(rs.getString("docente"));
				listarExpediente.add(expediente);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LOS EXPEDIENTES " + e.getMessage());
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
		return listarExpediente;
	}

}
