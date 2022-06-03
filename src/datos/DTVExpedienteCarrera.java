package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vistas.VW_Expediente_Carrera;

public class DTVExpedienteCarrera {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public ArrayList<VW_Expediente_Carrera> listarExpedientesCarrera(String carrera) {
		ArrayList<VW_Expediente_Carrera> listarExpediente = new ArrayList<>();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from vw_expediente_carrera where carrera = ? and estado = 1;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, carrera);
			rs = ps.executeQuery();
			while (rs.next()) {
				VW_Expediente_Carrera expediente = new VW_Expediente_Carrera();
				expediente.setId_plan(rs.getInt("id_plan_estudio"));
				expediente.setNombre_plan(rs.getString("plan_estudio"));expediente.setFecha_creacion(rs.getDate("fecha_creacion"));
				expediente.setFecha_creacion(rs.getDate("fecha_creacion"));
				expediente.setId_asignatura(rs.getInt("id_asignatura"));
				expediente.setCodigo(rs.getString("cod_asignatura"));
				expediente.setAsignatura(rs.getString("asignatura"));
				expediente.setCreditos(rs.getInt("creditos"));
				expediente.setUrl(rs.getString("url_expediente"));
				expediente.setCarrera(rs.getString("carrera"));
				listarExpediente.add(expediente);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LOS EXPEDIENTES EN SU CARRERA " + e.getMessage());
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
	
	//En solicitud-expediente.jsp para que pueda solicitar permisos el coordinador/a de otros expedientes
	public ArrayList<VW_Expediente_Carrera> listarExpedientesNoCarrera(String carrera) {
		ArrayList<VW_Expediente_Carrera> listarExpediente = new ArrayList<>();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from vw_expediente_carrera where carrera <> ? and estado = 1;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, carrera);
			rs = ps.executeQuery();
			while (rs.next()) {
				VW_Expediente_Carrera expediente = new VW_Expediente_Carrera();
				expediente.setId_plan(rs.getInt("id_plan_estudio"));
				expediente.setNombre_plan(rs.getString("plan_estudio"));expediente.setFecha_creacion(rs.getDate("fecha_creacion"));
				expediente.setFecha_creacion(rs.getDate("fecha_creacion"));
				expediente.setId_asignatura(rs.getInt("id_asignatura"));
				expediente.setCodigo(rs.getString("cod_asignatura"));
				expediente.setAsignatura(rs.getString("asignatura"));
				expediente.setCreditos(rs.getInt("creditos"));
				expediente.setUrl(rs.getString("url_expediente"));
				expediente.setCarrera(rs.getString("carrera"));
				listarExpediente.add(expediente);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LOS EXPEDIENTES QUE NO SON DE LA CARRERA " + e.getMessage());
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
	
	public String getCarrera(String expediente) {
		String carrera = "";
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select carrera from vw_expediente_carrera where asignatura like ? and estado = 1;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, expediente);
			rs = ps.executeQuery();
			while (rs.next()) {
				carrera = rs.getString("carrera");
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN BUSCAR LA CARRERA DEL EXPEDIENTE " + e.getMessage());
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
		return carrera;
	}
	
	public ArrayList<VW_Expediente_Carrera> listarPlanEstudio(String carrera) {
		ArrayList<VW_Expediente_Carrera> listarExpediente = new ArrayList<>();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from vw_expediente_carrera where carrera = ?;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, carrera);
			rs = ps.executeQuery();
			while (rs.next()) {
				VW_Expediente_Carrera expediente = new VW_Expediente_Carrera();
				expediente.setId_plan(rs.getInt("id_plan_estudio"));
				expediente.setNombre_plan(rs.getString("plan_estudio"));expediente.setFecha_creacion(rs.getDate("fecha_creacion"));
				expediente.setFecha_creacion(rs.getDate("fecha_creacion"));
				expediente.setId_asignatura(rs.getInt("id_asignatura"));
				expediente.setCodigo(rs.getString("cod_asignatura"));
				expediente.setAsignatura(rs.getString("asignatura"));
				expediente.setCreditos(rs.getInt("creditos"));
				expediente.setUrl(rs.getString("url_expediente"));
				expediente.setCarrera(rs.getString("carrera"));
				expediente.setEstado(rs.getInt("estado"));
				listarExpediente.add(expediente);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LOS EXPEDIENTES DEL PLAN DE ESTUDIO " + e.getMessage());
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
	
	//SLAsignatura para guardarlo en su respectivo plan
	public int getIdPlanEstudio(String carrera) {
		int id = 0;
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from vw_expediente_carrera where carrera = ?;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, carrera);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id_plan_estudio");
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LOS EXPEDIENTES DEL PLAN DE ESTUDIO " + e.getMessage());
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
	
	public boolean existeExpedienteEnCarrera(String expediente, int campo, String carrera) {
		boolean existe = false;
		String field = "";
		if(campo == 1) field = "asignatura LIKE '"+expediente+"'";
		else field = "id_asignatura = "+expediente;
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from vw_expediente_carrera where carrera LIKE ? and "+field+";", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, carrera);
			rs = ps.executeQuery();
			if (rs.next()) {
				existe = true;
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN VERIFICAR SI LA ASIGNATURA PERTENECE A LA CARRERA DEL COORDINADOR ACTIVO " + e.getMessage());
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
}
