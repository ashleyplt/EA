package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vistas.VW_Expediente_Doc;

public class DTVExpedienteDoc {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public ArrayList<VW_Expediente_Doc> listarVWDocentes(String asignatura, String edicion){
		ArrayList<VW_Expediente_Doc> listarVWDocentes = new ArrayList<VW_Expediente_Doc>();
		String sql = "SELECT * FROM VW_EXPEDIENTE_DOC WHERE ASIGNATURA LIKE ? AND EDICION like ? ORDER BY id_docente";
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, asignatura);
			ps.setString(2, edicion);
			rs = ps.executeQuery();
			while(rs.next()) {
				VW_Expediente_Doc vwed = new VW_Expediente_Doc();
				vwed.setId_expediente(rs.getInt("id_expediente"));
				vwed.setId_edicion(rs.getInt("id_edicion"));
				vwed.setEdicion(rs.getString("edicion"));
				vwed.setId_asignatura(rs.getInt("id_asignatura"));
				vwed.setAsignatura(rs.getString("asignatura"));
				vwed.setId_docente(rs.getInt("id_docente"));
				vwed.setDocente(rs.getString("docente"));
				
				listarVWDocentes.add(vwed);
			}
		}
		catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR VWDocentes: "+ e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(ps != null){
					ps.close();
				}
				if(c != null){
					PoolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listarVWDocentes;
	}
}
