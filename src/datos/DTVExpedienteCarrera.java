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
	private ResultSet rsAsignatura = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	// Metodo para llenar el RusultSet
	public void LlenarExpedienteDocente(Connection c) {

		try {
			ps = c.prepareStatement("SELECT * FROM vw_expediente_carrera;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsAsignatura = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LOS EXPEDIENTES EN SU CARRERA " + e.getMessage());
			e.printStackTrace();
		}

	}

	public ArrayList<VW_Expediente_Carrera> listarExpedientesCarrera(String carrera) {
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
	
	public String getCarrera(String expediente) {
		String carrera = "";
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select carrera from vw_expediente_carrera where asignatura like ?;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
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
}
