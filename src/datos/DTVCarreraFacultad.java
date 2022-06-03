package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vistas.VW_CarreraFacultad;

public class DTVCarreraFacultad {
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsCarreraFacultad = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarCarreraFacultad() {
		try{
			ps = c.prepareStatement("SELECT * FROM public.vw_carrera_facultad;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsCarreraFacultad = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR CARRERAS Y FACULTADES : "+ e.getMessage());
			e.printStackTrace();
		}

	}
	
	public ArrayList<VW_CarreraFacultad> listarCarrerasFacultades(int vistaCompleta){
		ArrayList<VW_CarreraFacultad> listarCarrerasFacultades = new ArrayList<VW_CarreraFacultad>();
		String sql = "";
		if(vistaCompleta == 1) {
			 sql = "SELECT * FROM public.vw_carrera_facultad;";
		}else {
		sql = "SELECT * FROM public.vw_carrera_facultad WHERE estado <> 0;";
		}
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_CarreraFacultad vCarreraFacultad = new VW_CarreraFacultad();
				
				vCarreraFacultad.setId_carrera(rs.getInt("id_carrera"));
				vCarreraFacultad.setCarrera(rs.getString("carrera"));
				vCarreraFacultad.setFacultad(rs.getString("facultad"));
				vCarreraFacultad.setDepartamento(rs.getString("departamento"));
				vCarreraFacultad.setEstado(rs.getInt("estado"));
				listarCarrerasFacultades.add(vCarreraFacultad);
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Actividades: Error en listar Carreras y facultades " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rs != null)
					rs.close();

				if(ps != null)
					ps.close();

				if(c != null)
					PoolConexion.closeConnection(c);
			} 
			catch (Exception e2) 
			{
				System.err.println("DTVCarreraFacultad: Error en listar carreras y facultades " + e2.getMessage());
				e2.printStackTrace();
			}
		}

		return listarCarrerasFacultades;
	}
}
