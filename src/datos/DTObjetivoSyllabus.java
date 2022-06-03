package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.ObjetivoSyllabus;

public class DTObjetivoSyllabus {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsObjetivo = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarObjetivo(Connection c)
	{
		String sql = "SELECT * FROM public.syllabus_objetivos";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsObjetivo = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT OBJETIVOS SYLLABUS: Error en listar OBJETIVOS DE SYLLABUS " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean guardarObjetivo(ObjetivoSyllabus os)
	{
		boolean guardado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarObjetivo(c);
			//AQUI INICIA EL GUARDAR
			rsObjetivo.moveToInsertRow();
			rsObjetivo.updateInt("id_syllabus", os.getId_syllabus());
			rsObjetivo.updateString("objetivo", os.getObjetivo());
			
			rsObjetivo.insertRow();
			rsObjetivo.moveToCurrentRow();
			guardado = true;
			
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTObjetivoSyllabus: Error al guardar objetivo " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsObjetivo != null)
				{
					rsObjetivo.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTObjetivoSyllabus: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public ArrayList<ObjetivoSyllabus> listarObjetivos(int idSyllabus){
		ArrayList<ObjetivoSyllabus> listarObjetivo = new ArrayList<ObjetivoSyllabus>();
		String sql = "SELECT * FROM SYLLABUS_OBJETIVOS WHERE id_syllabus = ?";
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idSyllabus);
			rs = ps.executeQuery();
			while(rs.next()) {
				ObjetivoSyllabus os = new ObjetivoSyllabus();
				os.setId_objetivo(rs.getInt("id_objetivo"));
				os.setId_syllabus(rs.getInt("id_syllabus"));
				os.setObjetivo(rs.getString("objetivo"));
				listarObjetivo.add(os);
			}
		}
		catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR OBJETIVOS: "+ e.getMessage());
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
		return listarObjetivo;
	}
	
	
	public boolean modificarObjetivo(ObjetivoSyllabus os) {
		boolean modificado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarObjetivo(c);
			rsObjetivo.beforeFirst();
			while(rsObjetivo.next())
			{
				if(rsObjetivo.getInt("id_objetivo") == os.getId_objetivo()) 
				{	
					rsObjetivo.updateString("objetivo", os.getObjetivo());
					rsObjetivo.updateInt("id_syllabus", os.getId_syllabus());
					rsObjetivo.updateRow();
					modificado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTObjetivosSyllabus: Error al modificar el objetivo " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsObjetivo != null)
				{
					rsObjetivo.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTObjetivosSyllabus: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return modificado;
	}
	
	
	public boolean eliminarObjetivo(int idObjetivo) {
		boolean eliminado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarObjetivo(c);
			rsObjetivo.beforeFirst();
			while(rsObjetivo.next())
			{
				if(rsObjetivo.getInt("id_objetivo") == idObjetivo) 
				{	
					rsObjetivo.deleteRow();
					eliminado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTObjetivosSyllabus: Error al eliminar el objetivo " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsObjetivo != null)
				{
					rsObjetivo.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTObjetivosSyllabus: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return eliminado;
	}
}
