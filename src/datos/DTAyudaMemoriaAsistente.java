package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.AyudaMemoriaAsistentes;
import vistas.VW_AyudaMemoriaAsistentes;


public class DTAyudaMemoriaAsistente {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsAyudaMemoriaAsistentes = null;
	private ResultSet rsVWAyudaMemoriaAsistentes = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarAyudaMemoriaAsistentes(Connection c)
	{
		String sql = "SELECT * FROM public.ayuda_memoria_asistentes";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsAyudaMemoriaAsistentes = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Ayuda Memoria Asistentes: Error en listar Asistentes ayuda de memoria " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public void llenarVWAyudaMemoriaAsistentes(Connection c)
	{
		String sql = "SELECT * FROM public.vw_ayuda_memoria_asistentes";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsVWAyudaMemoriaAsistentes = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DTV Ayuda Memoria Asistentes: Error en listar Asistentes ayuda de memoria " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public boolean guardarAyudaMemoriaAsistentes(AyudaMemoriaAsistentes amas)
	{
		boolean guardado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarAyudaMemoriaAsistentes(c);
			//AQUI INICIA EL GUARDAR
			rsAyudaMemoriaAsistentes.moveToInsertRow();
			rsAyudaMemoriaAsistentes.updateInt("id_ayuda_memoria", amas.getId_ayuda_memoria());
			rsAyudaMemoriaAsistentes.updateInt("id_personal", amas.getId_personal());
			
			rsAyudaMemoriaAsistentes.insertRow();
			rsAyudaMemoriaAsistentes.moveToCurrentRow();
			
			guardado = true;
			
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTAsistenteSyllabus: Error al guardar asistente " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsAyudaMemoriaAsistentes != null)
				{
					rsAyudaMemoriaAsistentes.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTAyudaMemoriaAsistentes: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public ArrayList<AyudaMemoriaAsistentes> listarAsistentes(int idAyudaMemoria){
		ArrayList<AyudaMemoriaAsistentes> listarAsistente = new ArrayList<AyudaMemoriaAsistentes>();
		String sql = "SELECT * FROM AYUDA_MEMORIA_ASISTENTES WHERE id_ayuda_memoria = ?";
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idAyudaMemoria);
			rs = ps.executeQuery();
			while(rs.next()) {
				AyudaMemoriaAsistentes amas = new AyudaMemoriaAsistentes();
				amas.setId_ayuda_memoria_asistentes(rs.getInt("id_ayuda_asistentes"));
				amas.setId_ayuda_memoria(rs.getInt("id_ayuda_memoria"));
				amas.setId_personal(rs.getInt("id_personal"));
				
				listarAsistente.add(amas);
			}
		}
		catch(Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR Asistentes: "+ e.getMessage());
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
		return listarAsistente;
	}
	
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
				vwamas.setId_ayuda_memoria_asistentes(rs.getInt("id_ayuda_asistentes"));
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
	
	public boolean eliminarAyudaMemoriaAsistente(int idasistente) {
		boolean eliminado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarAyudaMemoriaAsistentes(c);
			rsAyudaMemoriaAsistentes.beforeFirst();
			while(rsAyudaMemoriaAsistentes.next())
			{
				if(rsAyudaMemoriaAsistentes.getInt("id_ayuda_asistentes") == idasistente) 
				{	
					rsAyudaMemoriaAsistentes.deleteRow();
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
				if(rsAyudaMemoriaAsistentes != null)
				{
					rsAyudaMemoriaAsistentes.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTAyudaMemoriaAsistente: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return eliminado;
	}
	
	public boolean yaEsParticipante(int idayudamemoria, int idPersonal) {
		boolean participante = false;
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM AYUDA_MEMORIA_asistentes WHERE ID_AYUDA_MEMORIA= ? AND ID_PERSONAL = ?;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idayudamemoria);
			ps.setInt(2, idPersonal);
			rs = ps.executeQuery();
			while (rs.next()) {
				participante = true;
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN BUSCAR LOS DATOS DEL PERSONAL " + e.getMessage());
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
		return participante;
	}
	
}
