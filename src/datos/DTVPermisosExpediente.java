package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vistas.VW_Permisos_Expediente;

public class DTVPermisosExpediente {
	
		PoolConexion pc = PoolConexion.getInstance();
		Connection c = null;
		private ResultSet rsAsignatura = null;
		private ResultSet rs = null;
		private PreparedStatement ps = null;

		// Metodo para llenar el RusultSet
		public void LlenarExpedienteDocente(Connection c) {

			try {
				ps = c.prepareStatement("SELECT * FROM vw_permisos_expediente ORDER BY asignatura ASC;", ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rsAsignatura = ps.executeQuery();
			} catch (Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR LOS PERMISOS EXPEDIENTES " + e.getMessage());
				e.printStackTrace();
			}

		}
		
		public ArrayList<VW_Permisos_Expediente> listarPermisosExpediente() {
			ArrayList<VW_Permisos_Expediente> listarPermisosExpediente = new ArrayList<>();
			try {
				c = PoolConexion.getConnection();
				ps = c.prepareStatement("select * from vw_permisos_expediente;", ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rs = ps.executeQuery();
				while (rs.next()) {
				VW_Permisos_Expediente pe = new VW_Permisos_Expediente();
					pe.setId_permiso(rs.getInt("id_permiso"));
					pe.setCodigo(rs.getString("codigo"));
					pe.setAsignatura(rs.getString("asignatura"));
					pe.setUrl_expediente(rs.getString("url_expediente"));
					pe.setId_personal(rs.getInt("id_personal"));
					pe.setDocente(rs.getString("docente"));
					pe.setTipo_permiso(rs.getString("tipo_permiso"));
					pe.setFecha_inicio(rs.getDate("fecha_inicio"));
					pe.setFecha_final(rs.getDate("fecha_final"));
					listarPermisosExpediente.add(pe);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LOS PERMISOS EXPEDIENTES " + e.getMessage());
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
		return listarPermisosExpediente;
	}

		public ArrayList<VW_Permisos_Expediente> listarPermisosSegunDocente(int id_personal) {
			ArrayList<VW_Permisos_Expediente> listarPermisosExpediente = new ArrayList<>();
			try {
				c = PoolConexion.getConnection();
				ps = c.prepareStatement("select * from vw_permisos_expediente where id_personal = "+ id_personal +";", ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rs = ps.executeQuery();
				while (rs.next()) {
				VW_Permisos_Expediente pe = new VW_Permisos_Expediente();
					pe.setId_permiso(rs.getInt("id_permiso"));
					pe.setCodigo(rs.getString("codigo"));
					pe.setAsignatura(rs.getString("asignatura"));
					pe.setUrl_expediente(rs.getString("url_expediente"));
					pe.setId_personal(rs.getInt("id_personal"));
					pe.setDocente(rs.getString("docente"));
					pe.setTipo_permiso(rs.getString("tipo_permiso"));
					pe.setFecha_inicio(rs.getDate("fecha_inicio"));
					pe.setFecha_final(rs.getDate("fecha_final"));
					listarPermisosExpediente.add(pe);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LOS PERMISOS EXPEDIENTES " + e.getMessage());
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
		return listarPermisosExpediente;
	}
		
		public ArrayList<VW_Permisos_Expediente> listarPermisosSegunExpediente(String expediente) {
			ArrayList<VW_Permisos_Expediente> listarPermisosExpediente = new ArrayList<>();
			try {
				c = PoolConexion.getConnection();
				ps = c.prepareStatement("select * from vw_permisos_expediente where asignatura like ?;",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ps.setString(1, expediente);
				rs = ps.executeQuery();
				while (rs.next()) {
					VW_Permisos_Expediente pe = new VW_Permisos_Expediente();
					pe.setId_permiso(rs.getInt("id_permiso"));
					pe.setCodigo(rs.getString("codigo"));
					pe.setAsignatura(rs.getString("asignatura"));
					pe.setUrl_expediente(rs.getString("url_expediente"));
					pe.setId_personal(rs.getInt("id_personal"));
					pe.setDocente(rs.getString("docente"));
					pe.setTipo_permiso(rs.getString("tipo_permiso"));
					pe.setFecha_inicio(rs.getDate("fecha_inicio"));
					pe.setFecha_final(rs.getDate("fecha_final"));
					listarPermisosExpediente.add(pe);
				}
			} catch (Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR LOS PERMISOS EXPEDIENTES " + e.getMessage());
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
			return listarPermisosExpediente;
		}
}