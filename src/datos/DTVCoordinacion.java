package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vistas.VW_Coordinacion;


public class DTVCoordinacion {
	
			PoolConexion pc = PoolConexion.getInstance();
			Connection c = null;
			private ResultSet rsCoordinacion = null;
			private ResultSet rs = null;
			private PreparedStatement ps = null;

	 		// Metodo para llenar el RusultSet
			public void LlenarCoordinacion(Connection c) {
				try {
					ps = c.prepareStatement("SELECT * FROM vw_coordinacion;", ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
					rsCoordinacion = ps.executeQuery();
				} catch (Exception e) {
					System.out.println("DATOS: ERROR EN LISTAR LA COORDINACION" + e.getMessage());
					e.printStackTrace();
				}
			}
			
			public ArrayList<VW_Coordinacion> listarCoordinacion() {
				ArrayList<VW_Coordinacion> listarCoordinacion = new ArrayList<>();
				try {
					c = PoolConexion.getConnection();
					ps = c.prepareStatement("select * from vw_coordinacion;", ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
					rs = ps.executeQuery();
					while (rs.next()) {
						VW_Coordinacion cd = new VW_Coordinacion();
						cd.setId(rs.getInt("id"));
						cd.setNombre(rs.getString("nombre"));
						listarCoordinacion.add(cd);
				}
			} catch (Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR LOS DATOS DE COORDINACION" + e.getMessage());
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
			return listarCoordinacion;
		}

}
