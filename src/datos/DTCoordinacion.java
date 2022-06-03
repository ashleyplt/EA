package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Coordinacion;
import vistas.VW_Cargo_Personal;

public class DTCoordinacion {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsCoordinacion = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarCoordinacion(Connection c)
	{
		String sql = "SELECT * FROM public.coordinacion";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsCoordinacion = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Coordinacion: Error en listar coordinacion " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<Coordinacion> listarCoordinacion(){
		ArrayList<Coordinacion> listaInfo = new ArrayList<>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM coordinacion WHERE estado = 1;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Coordinacion info = new Coordinacion();
				 
				info.setId(rs.getInt("id_coordinacion"));
				info.setNombre(rs.getString("nombre"));
				info.setTelefono(rs.getString("telefono"));
				info.setId_carrera(rs.getInt("id_carrera"));
				info.setEstado(rs.getInt("estado"));
				
				listaInfo.add(info);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LAS COORDINACIONES "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
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
		return listaInfo;
	}
	
	public boolean guardarCoordinacion(Coordinacion crd)
	{
		boolean guardado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarCoordinacion(c);
			//AQUI INICIA EL GUARDAR
			rsCoordinacion.moveToInsertRow();
			rsCoordinacion.updateString("nombre", crd.getNombre());
			rsCoordinacion.updateString("telefono",crd.getTelefono());
			rsCoordinacion.updateInt("id_carrera", crd.getId_carrera());
			rsCoordinacion.updateInt("estado", crd.getEstado());
			
			rsCoordinacion.insertRow();
			rsCoordinacion.moveToCurrentRow();
			
			guardado = true;
			
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTCoordinacion: Error al guardar Coordinacion " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsCoordinacion != null)
				{
					rsCoordinacion.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTCoordinacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public boolean modificarCoordinacion(Coordinacion crd)
	{
		boolean modificado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarCoordinacion(c);
			rsCoordinacion.beforeFirst();
			while(rsCoordinacion.next())
			{
				if(rsCoordinacion.getInt("id_coordinacion") == crd.getId()) 
				{	
					rsCoordinacion.updateString("nombre", crd.getNombre());
					rsCoordinacion.updateString("telefono", crd.getTelefono());
					rsCoordinacion.updateInt("id_carrera", crd.getId_carrera());
					rsCoordinacion.updateInt("estado", crd.getEstado());
					
					rsCoordinacion.updateRow();
					modificado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTActividades: Error al modificar la departamento " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsCoordinacion != null)
				{
					rsCoordinacion.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTCoordinacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return modificado;
	}
	
	public boolean eliminarCoordinacion(int id_coordinacion, int estado)
	{
		boolean eliminado = false;
		int state = 1;
		if (estado == 1) {
			state = 0;
		}
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarCoordinacion(c);
			rsCoordinacion.beforeFirst();
			while(rsCoordinacion.next())
			{
				if(rsCoordinacion.getInt("id_coordinacion")==id_coordinacion) 
				{
					rsCoordinacion.updateInt("estado", state);	
					rsCoordinacion.updateRow();
					
					eliminado = true;
					break;
				}
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTCOORDINACION: Error al eliminar coordinacion " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsCoordinacion != null)
				{
					rsCoordinacion.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTCOORDINACION: Error al cerrar conexión " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return eliminado;
	}
	
	
	public Coordinacion recuperarCoordinacion(int id_coordinacion)
	{
		Coordinacion crd = new Coordinacion();
		String sql = "SELECT * FROM public.coordinacion where id_coordinacion = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, id_coordinacion);
			rs = ps.executeQuery();
			if(rs.next())
			{
				crd.setId(id_coordinacion);
				crd.setNombre(rs.getString("nombre"));
				crd.setTelefono(rs.getString("telefono"));
				crd.setId_carrera(rs.getInt("id_carrera"));
				crd.setEstado(rs.getInt("estado"));
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DATOS: error recuperarCoordinacion(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsCoordinacion!= null)
				{
					rsCoordinacion.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTCoordinacion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return crd;
	}
	
	public int getIdCoordinacion(String lugar) {
		int id = 0;
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from coordinacion where nombre like ?;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, lugar);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id_coordinacion");
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN BUSCAR ID COORDINACIÓN" + e.getMessage());
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
		return id;
	}
}
