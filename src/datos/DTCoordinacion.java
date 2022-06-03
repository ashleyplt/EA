package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DTCoordinacion {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsCoordinacion = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public int getIdCoordinacion(String lugar) {
		int id = 0;
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from coordinacion where nombre like ?;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, lugar);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id_coordinacion");
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN BUSCAR ID COORDINACIÓN" + e.getMessage());
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
}
