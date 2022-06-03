package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import entidades.Facultad;

public class DTFacultad {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsFacultad = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarFacultad(Connection c)
	{
		String sql = "SELECT * FROM public.facultad";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsFacultad = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT SYLLABUS: Error en listar facultad " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<Facultad> listarFacultades()
	{
		ArrayList<Facultad> listarFacultades = new ArrayList<Facultad>();
		
		String sql = "SELECT * FROM public.facultad WHERE estado <> 0";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Facultad f = new Facultad();
				f.setId_facultad(rs.getInt("id_facultad"));
				f.setNombre(rs.getString("nombre"));
				f.setEstado(rs.getInt("estado"));
				
				listarFacultades.add(f);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT FACULTAD: Error en listar facultades " + e.getMessage());
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
				System.err.println("DT FACULTAD: Error en listar facultades " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listarFacultades;
	}
	
}
