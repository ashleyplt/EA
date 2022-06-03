package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.AyudaMemoriaAgenda;

public class DTAyudaMemoriaAgenda {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsAyudaMemoriaAgenda = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarAyudaMemoriaAgenda(Connection c)
	{
		String sql = "SELECT * FROM public.ayuda_memoria_agenda";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsAyudaMemoriaAgenda = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Ayuda Memoria Agenda: Error en listar Agenda ayuda de memoria " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean guardarAyudaMemoriaAgenda(AyudaMemoriaAgenda amag)
	{
		boolean guardado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarAyudaMemoriaAgenda(c);
			//AQUI INICIA EL GUARDAR
			rsAyudaMemoriaAgenda.moveToInsertRow();
			rsAyudaMemoriaAgenda.updateInt("id_ayuda_memoria", amag.getId_ayuda_memoria());
			rsAyudaMemoriaAgenda.updateString("agenda", amag.getAgenda());
			
			rsAyudaMemoriaAgenda.insertRow();
			rsAyudaMemoriaAgenda.moveToCurrentRow();
			
			guardado = true;
			
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTAgendaSyllabus: Error al guardar agenda " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsAyudaMemoriaAgenda != null)
				{
					rsAyudaMemoriaAgenda.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTAyudaMemoriaAgenda: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public ArrayList<AyudaMemoriaAgenda> listarAgendas(int idAyudaMemoria){
		ArrayList<AyudaMemoriaAgenda> listarAgenda = new ArrayList<AyudaMemoriaAgenda>();
		String sql = "SELECT * FROM AYUDA_MEMORIA_AGENDA WHERE id_ayuda_memoria = ?";
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idAyudaMemoria);
			rs = ps.executeQuery();
			while(rs.next()) {
				AyudaMemoriaAgenda amag = new AyudaMemoriaAgenda();
				amag.setId_ayuda_memoria_agenda(rs.getInt("id_ayuda_memoria_agenda"));
				amag.setId_ayuda_memoria(rs.getInt("id_ayuda_memoria"));
				amag.setAgenda(rs.getString("agenda"));
				
				listarAgenda.add(amag);
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
		return listarAgenda;
	}
	
	public boolean modificarAyudaMemoriaAgenda(AyudaMemoriaAgenda amag) {
		boolean modificado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarAyudaMemoriaAgenda(c);
			rsAyudaMemoriaAgenda.beforeFirst();
			while(rsAyudaMemoriaAgenda.next())
			{
				if(rsAyudaMemoriaAgenda.getInt("id_ayuda_memoria_agenda") == amag.getId_ayuda_memoria_agenda()) 
				{	
					rsAyudaMemoriaAgenda.updateInt("id_ayuda_memoria", amag.getId_ayuda_memoria());
					rsAyudaMemoriaAgenda.updateString("agenda", amag.getAgenda());
					rsAyudaMemoriaAgenda.updateRow();
					modificado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTAyudaMemoriaAgenda: Error al modificar la agenda " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsAyudaMemoriaAgenda != null)
				{
					rsAyudaMemoriaAgenda.close();
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
	
	
	public boolean eliminarAyudaMemoriaAgenda(int idagenda) {
		boolean eliminado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarAyudaMemoriaAgenda(c);
			rsAyudaMemoriaAgenda.beforeFirst();
			while(rsAyudaMemoriaAgenda.next())
			{
				if(rsAyudaMemoriaAgenda.getInt("id_ayuda_memoria_agenda") == idagenda) 
				{	
					rsAyudaMemoriaAgenda.deleteRow();
					eliminado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTAyudaMemoriaAgenda: Error al eliminar la agenda " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsAyudaMemoriaAgenda != null)
				{
					rsAyudaMemoriaAgenda.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTAyudaMemoriaAgenda: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return eliminado;
	}
	
	
}
