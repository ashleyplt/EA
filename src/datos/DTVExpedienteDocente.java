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
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public ArrayList<VW_Expediente_Docente> listarExpedienteSegunDocente(int id_docente) {
		ArrayList<VW_Expediente_Docente> listarExpediente = new ArrayList<>();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from vw_expediente_docente where id_docente = " + id_docente + ";",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while (rs.next()) {
				VW_Expediente_Docente expediente = new VW_Expediente_Docente();
				expediente.setId_expediente(rs.getInt("id_expediente"));
				expediente.setId_edicion(rs.getInt("id_edicion"));
				expediente.setEdicion(rs.getString("edicion"));
				expediente.setId_asignatura(rs.getInt("id_asignatura"));
				expediente.setCod_asignatura(rs.getString("cod_asignatura"));
				expediente.setAsignatura(rs.getString("asignatura"));
				expediente.setDescripcion(rs.getString("descripcion"));
				expediente.setUrl_expediente(rs.getString("url_expediente"));
				expediente.setDescripcion(rs.getString("descripcion"));
				expediente.setId_expdoc(rs.getInt("id_expdoc"));
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
				expediente.setId_expediente(rs.getInt("id_expediente"));
				expediente.setId_edicion(rs.getInt("id_edicion"));
				expediente.setEdicion(rs.getString("edicion"));
				expediente.setId_asignatura(rs.getInt("id_asignatura"));
				expediente.setCod_asignatura(rs.getString("cod_asignatura"));
				expediente.setAsignatura(rs.getString("asignatura"));
				expediente.setDescripcion(rs.getString("descripcion"));
				expediente.setUrl_expediente(rs.getString("url_expediente"));
				expediente.setDescripcion(rs.getString("descripcion"));
				expediente.setId_expdoc(rs.getInt("id_expdoc"));
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

	public boolean existeDocenteExpediente(String exp, int id) {
		boolean existe = false;
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from vw_expediente_docente where asignatura like ? and id_docente = ?;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, exp);
			ps.setInt(2, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				existe = true;
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN BUSCAR LOS DOCENTES DE EXPEDIENTES " + e.getMessage());
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
		return existe;
	}
	
	//plan-estudio.jsp: para buscar si existe un expediente siendo cursado y no dejar que la asignatura se deshabilite del plan de estudio.
	public boolean existeExpedienteActual(int id) {
		boolean existe = false;
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from vw_expediente_docente where id_asignatura = ?;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				existe = true;
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN BUSCAR SI HAY EXPEDIENTES ESTANDO EN CURSOS " + e.getMessage());
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
		return existe;
	}
	
	//Se usa en expediente-asignatura.jsp en caso de que sea coordinador para saber la edición de expediente activa
	public ArrayList<VW_Expediente_Docente> getDocentesEdicionAsignatura(String asignatura) {
		ArrayList<VW_Expediente_Docente> listarExpediente = new ArrayList<>();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from vw_expediente_docente where asignatura LIKE ?;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, asignatura);
			rs = ps.executeQuery();
			while (rs.next()) {
				VW_Expediente_Docente expediente = new VW_Expediente_Docente();
				expediente.setId_expediente(rs.getInt("id_expediente"));
				expediente.setId_edicion(rs.getInt("id_edicion"));
				expediente.setEdicion(rs.getString("edicion"));
				expediente.setId_asignatura(rs.getInt("id_asignatura"));
				expediente.setCod_asignatura(rs.getString("cod_asignatura"));
				expediente.setAsignatura(rs.getString("asignatura"));
				expediente.setDescripcion(rs.getString("descripcion"));
				expediente.setUrl_expediente(rs.getString("url_expediente"));
				expediente.setDescripcion(rs.getString("descripcion"));
				expediente.setId_expdoc(rs.getInt("id_expdoc"));
				expediente.setId_docente(rs.getInt("id_docente"));
				expediente.setDocente(rs.getString("docente"));
				listarExpediente.add(expediente);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LOS EDOCENTES DE UNA EDICIÓN DE EXPEDIENTE " + e.getMessage());
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
