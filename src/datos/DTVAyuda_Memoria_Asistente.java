package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.AyudaMemoriaAsistentes;
import vistas.VW_AyudaMemoriaAsistentes;

public class DTVAyuda_Memoria_Asistente {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsVWAyudaMemoriaAsistentes = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	

	public ArrayList<VW_AyudaMemoriaAsistentes> listarVWAsistentes(int idAyudaMemoria){
		ArrayList<VW_AyudaMemoriaAsistentes> listarVWAsistente = new ArrayList<VW_AyudaMemoriaAsistentes>();
		String sql = "SELECT * FROM VW_AYUDA_MEMORIA_ASISTENTES WHERE id_ayuda_memoria = ?";
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idAyudaMemoria);
			rs = ps.executeQuery();
			while(rs.next()) {
				VW_AyudaMemoriaAsistentes vwamas = new VW_AyudaMemoriaAsistentes();
				vwamas.setId_ayuda_memoria_asistentes(rs.getInt("id_ayuda_memoria_asistentes"));
				vwamas.setId_ayuda_memoria(rs.getInt("id_ayuda_memoria"));
				vwamas.setId_personal(rs.getInt("id_personal"));
				vwamas.setPersonal(rs.getString("personal"));
				
				listarVWAsistente.add(vwamas);
			}
		}
		catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR VWAsistentes: "+ e.getMessage());
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
		return listarVWAsistente;
	}
	
}
