package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import entidades.AyudaMemoria;
import entidades.Valoracion;

public class DTAyudaMemoria {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsAyudaMemoria = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	
	public void llenarAyudaMemoria(Connection c)
	{
		String sql = "SELECT * FROM public.ayuda_memoria";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsAyudaMemoria = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Ayuda Memoria: Error en listar ayuda de memoria " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<AyudaMemoria> listarAyudasMemorias(String periodo){
		ArrayList<AyudaMemoria> listaAyudaMemoria = new ArrayList<AyudaMemoria>();
		String sql = "SELECT * FROM AYUDA_MEMORIA WHERE PERIODO_ACADEMICO = ? AND ESTADO <>0;";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, periodo);
			rs = ps.executeQuery();
			while(rs.next()) {
					AyudaMemoria am = new AyudaMemoria();
					am.setId_ayuda_memoria(rs.getInt("id_ayuda_memoria"));
					am.setFecha_reunion(rs.getDate("fecha_reunion"));
					am.setHora_inicio(rs.getTime("hora_inicio"));
					am.setHora_finalizacion(rs.getTime("hora_finalizacion"));
					am.setLugar("lugar");
					am.setAsignatura("asignatura");
					am.setPeriodo_academico(rs.getString("periodo_academico"));
					am.setEstado(rs.getInt("estado"));
					
					listaAyudaMemoria.add(am);
			}
		}
		catch(Exception e) 
		{
			System.err.println("Valoracion: Error en listar la ayuda de memoria " + e.getMessage());
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
				System.err.println("Archivos: Error en listar la ayuda memoria " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return listaAyudaMemoria;
	}
	
	public AyudaMemoria ultimaAyudaMemoriaCreada(String asignatura , String periodo) {
		AyudaMemoria ultimaAyudaMemoria  = new AyudaMemoria();
		String sql = "SELECT * FROM AYUDA_MEMORIA WHERE ASIGNATURA LIKE ? AND PERIODO_ACADEMICO LIKE ? ORDER BY ID_AYUDA_MEMORIA DESC LIMIT 1";
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, asignatura);
			ps.setString(2, periodo);
			rs = ps.executeQuery();
			if(rs.next())
			{
				ultimaAyudaMemoria.setId_ayuda_memoria(rs.getInt("id_ayuda_memoria"));
				ultimaAyudaMemoria.setFecha_reunion(rs.getDate("fecha_reunion"));
				ultimaAyudaMemoria.setHora_inicio(rs.getTime("hora_inicio"));
				ultimaAyudaMemoria.setHora_finalizacion(rs.getTime("hora_finalizacion"));
				ultimaAyudaMemoria.setLugar(rs.getString("lugar"));
				ultimaAyudaMemoria.setAsignatura(rs.getString("asignatura"));
				ultimaAyudaMemoria.setPeriodo_academico(rs.getString("periodo_academico"));
				ultimaAyudaMemoria.setEstado(rs.getInt("estado"));
			
			}
			
		}catch(Exception e){
			System.err.println("DATOS: error recuperarUltimoSyllabus(): " + e.getMessage());
			e.printStackTrace();
		}finally{
			try 
			{
				if(rsAyudaMemoria != null)
				{
					rsAyudaMemoria.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTSyllabus: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return ultimaAyudaMemoria;
	}
	
	
	public boolean guardarAyudaMemoria(AyudaMemoria am)
	{
		boolean guardado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarAyudaMemoria(c);
			//AQUI INICIA EL GUARDAR
			rsAyudaMemoria.moveToInsertRow();
			rsAyudaMemoria.updateDate("fecha_reunion", am.getFecha_reunion());
			rsAyudaMemoria.updateTime("hora_inicio", am.getHora_inicio());
			rsAyudaMemoria.updateTime("hora_finalizacion", am.getHora_finalizacion());
			rsAyudaMemoria.updateString("lugar", am.getLugar());
			rsAyudaMemoria.updateString("asignatura", am.getAsignatura());
			rsAyudaMemoria.updateString("periodo_academico", am.getPeriodo_academico());
			rsAyudaMemoria.updateInt("estado", am.getEstado());

			rsAyudaMemoria.insertRow();
			rsAyudaMemoria.moveToCurrentRow();
			guardado = true;
			
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTCARRERA: Error al guardar Ayuda de memoria " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsAyudaMemoria != null)
				{
					rsAyudaMemoria.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DT Ayuda de memoria: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public boolean eliminarAyudaMemoria (int id_ayuda_memoria, int estado) {
		boolean eliminado = false;
		int state = 1;
		if(estado == 1) {
			state = 0;
		}
		try {
			c = PoolConexion.getConnection();
			this.llenarAyudaMemoria(c);
			rsAyudaMemoria.beforeFirst();
			while(rsAyudaMemoria.next()) {
				if(rsAyudaMemoria.getInt("id_ayuda_memoria")==id_ayuda_memoria) {
					rsAyudaMemoria.updateInt("estado", state);
					rsAyudaMemoria.updateRow();
					eliminado = true;
					break;
				}
			}
		}catch(Exception e) {
			System.err.println("DTCAyudaMemoria: Error al eliminar ayuda de memoria " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(rsAyudaMemoria !=null) {
					rsAyudaMemoria.close();
				}
				if(c != null) {
					c.close();
				}
			}catch(Exception e2) {
				System.err.println("DTAyudaMemoria: Error al cerrar conexión " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return eliminado;
	}
}
