package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vistas.VW_Expediente_Asignatura;

public class DTVExpedienteAsignatura {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public ArrayList<VW_Expediente_Asignatura> listarExpedientesAsignatura(String asignatura) {
		ArrayList<VW_Expediente_Asignatura> listarExpediente = new ArrayList<>();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from vw_expediente_asignatura where asignatura LIKE ?;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, asignatura);
			rs = ps.executeQuery();
			while (rs.next()) {
				VW_Expediente_Asignatura expediente = new VW_Expediente_Asignatura();
				expediente.setId_expediente(rs.getInt("id_expediente"));
				expediente.setEdicion(rs.getString("edicion"));
				expediente.setId_asignatura(rs.getInt("id_asignatura"));
				expediente.setAsignatura(rs.getString("asignatura"));
				expediente.setCodigo(rs.getString("codigo"));
				
				listarExpediente.add(expediente);
			}
		} catch (Exception e) {
			String msj = e.getMessage();
			System.out.println("DATOS: ERROR EN LISTAR LOS EXPEDIENTES DE LA ASIGNATURA " + e.getMessage());
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
