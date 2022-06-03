package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vistas.VW_Coordinacion_Departamento;


public class DTVCoordinacionDepartamentos {
	
			PoolConexion pc = PoolConexion.getInstance();
			Connection c = null;
			private ResultSet rsCoordinacion_Departamentos = null;
			private ResultSet rs = null;
			private PreparedStatement ps = null;

	 		// Metodo para llenar el RusultSet
			public void LlenarExpedienteDocente(Connection c) {
				try {
					ps = c.prepareStatement("SELECT * FROM vw_coordinacion_departamentos ORDER BY asignatura ASC;", ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
					rsCoordinacion_Departamentos = ps.executeQuery();
				} catch (Exception e) {
					System.out.println("DATOS: ERROR EN LISTAR LA COORDINACION Y DEPARTAMENTOS " + e.getMessage());
					e.printStackTrace();
				}
			}
			
			public ArrayList<VW_Coordinacion_Departamento> listarCoordinacionDepartamentos() {
				ArrayList<VW_Coordinacion_Departamento> listarCoordinacionDepartamentos = new ArrayList<>();
				try {
					c = PoolConexion.getConnection();
					ps = c.prepareStatement("select * from vw_coordinacion_departamentos;", ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
					rs = ps.executeQuery();
					while (rs.next()) {
						VW_Coordinacion_Departamento cd = new VW_Coordinacion_Departamento();
						cd.setId(rs.getInt("id"));
						cd.setNombre(rs.getString("nombre"));
						listarCoordinacionDepartamentos.add(cd);
				}
			} catch (Exception e) {
				System.out.println("DATOS: ERROR EN LISTAR LOS DATOS DE COORDINACION Y DEPARTAMENTOS " + e.getMessage());
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
			return listarCoordinacionDepartamentos;
		}

}
