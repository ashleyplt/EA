package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vistas.VW_Expedientes;

public class DTVExpedientes {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsVwExpedientes = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	// Método para llenar el resultset de la vista de Expedientes
		public void llenarDepartamento(Connection c) {
			String sql = "SELECT * FROM public.vw_expedientes";
			try {
				// c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
						ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rsVwExpedientes = ps.executeQuery();
			} catch (Exception e) {
				System.err.println("DTV Expedientes: Error en listar Expedientes " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		public ArrayList<VW_Expedientes> listarExpediente() {
			ArrayList<VW_Expedientes> listarExpediente = new ArrayList<>();
			try {
				c = PoolConexion.getConnection();
				String sql = "SELECT * FROM vw_expedientes;";
				ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rs = ps.executeQuery();
				while (rs.next()) {
					VW_Expedientes expediente = new VW_Expedientes();
					
					expediente.setId_expediente_asignatura(rs.getInt("id_expediente_asignatura"));
					expediente.setAsignatura(rs.getString("asignatura"));
					expediente.setEdicion(rs.getString("edicion"));
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
