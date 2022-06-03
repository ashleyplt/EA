package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.AyudaMemoriaAcuerdo;


public class DTAyudaMemoriaAcuerdo {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsAyudaMemoriaAcuerdo = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarAyudaMemoriaAcuerdo(Connection c)
	{
		String sql = "SELECT * FROM public.ayuda_memoria_acuerdos";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsAyudaMemoriaAcuerdo = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Ayuda Memoria Acuerdo: Error en listar OBJETIVOS DE SYLLABUS " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean guardarAyudaMemoriaAcuerdo(AyudaMemoriaAcuerdo amac)
	{
		boolean guardado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarAyudaMemoriaAcuerdo(c);
			//AQUI INICIA EL GUARDAR
			rsAyudaMemoriaAcuerdo.moveToInsertRow();
			rsAyudaMemoriaAcuerdo.updateInt("id_ayuda_memoria", amac.getId_ayuda_memoria());
			rsAyudaMemoriaAcuerdo.updateString("acuerdo", amac.getAcuerdo());
			
			rsAyudaMemoriaAcuerdo.insertRow();
			rsAyudaMemoriaAcuerdo.moveToCurrentRow();
			
			guardado = true;
			
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTObjetivoSyllabus: Error al guardar acuerdo " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsAyudaMemoriaAcuerdo != null)
				{
					rsAyudaMemoriaAcuerdo.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTAyudaMemoriaAcuerdo: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public ArrayList<AyudaMemoriaAcuerdo> listarAcuerdos(int idAyudaMemoria){
		ArrayList<AyudaMemoriaAcuerdo> listarAcuerdos = new ArrayList<AyudaMemoriaAcuerdo>();
		String sql = "SELECT * FROM AYUDA_MEMORIA_ACUERDOS WHERE id_ayuda_memoria = ?";
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idAyudaMemoria);
			rs = ps.executeQuery();
			while(rs.next()) {
				AyudaMemoriaAcuerdo amac = new AyudaMemoriaAcuerdo();
				amac.setId_ayuda_memoria_acuerdo(rs.getInt("id_ayuda_memoria_acuerdo"));
				amac.setId_ayuda_memoria(rs.getInt("id_ayuda_memoria"));
				amac.setAcuerdo(rs.getString("acuerdo"));
				
				listarAcuerdos.add(amac);
			}
		}
		catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR Acuerdos: "+ e.getMessage());
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
		return listarAcuerdos;
	}
	
	public boolean modificarAyudaMemoriaAcuerdo(AyudaMemoriaAcuerdo amac) {
		boolean modificado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarAyudaMemoriaAcuerdo(c);
			rsAyudaMemoriaAcuerdo.beforeFirst();
			while(rsAyudaMemoriaAcuerdo.next())
			{
				if(rsAyudaMemoriaAcuerdo.getInt("id_ayuda_memoria_acuerdo") == amac.getId_ayuda_memoria_acuerdo()) 
				{	
					rsAyudaMemoriaAcuerdo.updateInt("id_ayuda_memoria", amac.getId_ayuda_memoria());
					rsAyudaMemoriaAcuerdo.updateString("acuerdo", amac.getAcuerdo());
					rsAyudaMemoriaAcuerdo.updateRow();
					modificado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTAyudaMemoriaAcuerdos: Error al modificar el acuerdo " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsAyudaMemoriaAcuerdo != null)
				{
					rsAyudaMemoriaAcuerdo.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTAyudaMemoriaAcuerdos: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return modificado;
	}
	
	public boolean eliminarAyudaMemoriaAcuerdo(int idacuerdo) {
		boolean eliminado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarAyudaMemoriaAcuerdo(c);
			rsAyudaMemoriaAcuerdo.beforeFirst();
			while(rsAyudaMemoriaAcuerdo.next())
			{
				if(rsAyudaMemoriaAcuerdo.getInt("id_ayuda_memoria_acuerdo") == idacuerdo) 
				{	
					rsAyudaMemoriaAcuerdo.deleteRow();
					eliminado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTAyudaMemoriaAcuerdo: Error al eliminar el acuerdo " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsAyudaMemoriaAcuerdo != null)
				{
					rsAyudaMemoriaAcuerdo.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTAyudaMemoriaAcuerdo: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return eliminado;
	}
	
	
}
