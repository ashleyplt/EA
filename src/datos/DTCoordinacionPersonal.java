package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Coordinacion;
import entidades.CoordinacionPersonal;

public class DTCoordinacionPersonal {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsCoordinacion = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarCoordinacion(Connection c)
	{
		String sql = "SELECT * FROM public.coordinacion_personal";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsCoordinacion = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT Coordinacion: Error en listar coordinacion personal" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean guardarCoordinacionPersonal(CoordinacionPersonal cor)
	{
		boolean guardado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarCoordinacion(c);
			//AQUI INICIA EL GUARDAR
			rsCoordinacion.moveToInsertRow();	
			rsCoordinacion.updateInt("id_coordinacion", cor.getId_coordinacion());
			rsCoordinacion.updateInt("id_personal", cor.getId_personal());
			
			rsCoordinacion.insertRow();
			rsCoordinacion.moveToCurrentRow();
			
			guardado = true;
			
			
		} 
		catch (Exception e) 
		{
			String msj = e.getMessage();
			System.err.println("DTCoordinacion: Error al guardar Coordinacion personal " + e.getMessage());
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
	
	public boolean modificarCoordinacionPersonal(CoordinacionPersonal cpp)
	{
		boolean modificado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarCoordinacion(c);
			rsCoordinacion.beforeFirst();
			while(rsCoordinacion.next())
			{
				if(rsCoordinacion.getInt("id_coordinacion_personal") == cpp.getId_coordinacion_personal()) 
				{	
					rsCoordinacion.updateInt("id_coordinacion", cpp.getId_coordinacion());
					rsCoordinacion.updateInt("id_personal", cpp.getId_personal());
					
					rsCoordinacion.updateRow();
					modificado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTActividades: Error al modificar coordinacion personal" + e.getMessage());
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
	/*
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
	}*/
}
