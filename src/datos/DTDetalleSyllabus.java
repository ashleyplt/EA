package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DTDetalleSyllabus {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsDetSyllabus = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset Syllabus
	public void llenarSyllabus(Connection c)
	{
		String sql = "SELECT * FROM public.detalle_syllabus";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsDetSyllabus = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT DETALLE SYLLABUS: Error en listar el detalle del syllabus " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
}
