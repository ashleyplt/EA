package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import entidades.PermisosAsignatura;
import entidades.Personal;
import vistas.VW_Permisos_Expediente;

public class DTPermisosAsignatura {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsPermiso = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void llenarPermisosAsignatura(Connection c) {
		try {
			ps = c.prepareStatement("select * from permisos_asignatura;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsPermiso = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LOS PERMISOS DE ASIGNATURA" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean guardarPermiso(PermisosAsignatura pa) {
		boolean guardado = false;
		
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		String fecha = date.toString();
		String fecha_inicio = pa.getFecha_inicio().toString();
		
		if(fecha_inicio.equals(fecha)) {
			pa.setEstado(1);
		} else pa.setEstado(0);

		try {
			c = PoolConexion.getConnection();
			this.llenarPermisosAsignatura(c);
			rsPermiso.moveToInsertRow();
			rsPermiso.updateDate("fecha_inicio", pa.getFecha_inicio());
			rsPermiso.updateDate("fecha_final", pa.getFecha_final());
			rsPermiso.updateInt("estado", pa.getEstado());
			rsPermiso.updateInt("id_asignatura", pa.getId_asignatura());
			rsPermiso.updateInt("id_personal", pa.getId_personal());
			rsPermiso.updateInt("id_tipo_permiso", pa.getTipo_permiso());
			
			rsPermiso.insertRow();
			rsPermiso.moveToCurrentRow();
			guardado = true;
		} catch (Exception e) {
			System.err.println("ERROR AL GUARDAR PERMISO DE ASIGNATURA: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rsPermiso != null) {
					rsPermiso.close();
				}
				if (c != null) {
					PoolConexion.closeConnection(c);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return guardado;
	}
	
	public boolean modificarPermiso(PermisosAsignatura pa)
	{
		boolean modificado = false;
		
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		if(pa.getFecha_inicio().equals(date.toString())) {
			pa.setEstado(1);
		} else pa.setEstado(0);
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarPermisosAsignatura(c);
			rsPermiso.beforeFirst();
			while(rsPermiso.next())
			{
				if(rsPermiso.getInt("id_permisos_asign") == pa.getId())
				{	
					rsPermiso.updateDate("fecha_inicio", pa.getFecha_inicio());
					rsPermiso.updateDate("fecha_final", pa.getFecha_final());
					rsPermiso.updateInt("id_tipo_permiso", pa.getTipo_permiso());
					rsPermiso.updateInt("estado", pa.getEstado());
					rsPermiso.updateRow();
					modificado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("ERROR AL MODIFICAR PERMISO DE ASIGNATURA: " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsPermiso != null)
				{
					rsPermiso.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return modificado;
	}
	
	public boolean quitarPermiso(PermisosAsignatura pa)
	{
		boolean modificado = false;
		Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarPermisosAsignatura(c);
			rsPermiso.beforeFirst();
			while(rsPermiso.next())
			{
				if(rsPermiso.getInt("id_permisos_asign") == pa.getId())
				{	
					rsPermiso.updateDate("fecha_final", date);
					rsPermiso.updateInt("estado", 0);
					rsPermiso.updateRow();
					modificado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("ERROR AL QUITAR PERMISO DE ASIGNATURA: " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsPermiso != null)
				{
					rsPermiso.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return modificado;
	}
	
	public boolean modificarPermisoEstado(PermisosAsignatura pa)
	{
		boolean modificado = false;
		try 
		{
			this.llenarPermisosAsignatura(c);
			rsPermiso.beforeFirst();
			while(rsPermiso.next())
			{
				if(rsPermiso.getInt("id_permisos_asign") == pa.getId())
				{	
					rsPermiso.updateInt("estado", pa.getEstado());
					rsPermiso.updateRow();
					modificado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("ERROR AL MODIFICAR ESTADO DE PERMISO DE ASIGNATURA: " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsPermiso != null)
				{
					rsPermiso.close();
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return modificado;
	}
	
	public void actualizarEstadoPersonal(int idpersonal) {
		try {
			Date date = new Date(Calendar.getInstance().getTime().getTime());
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from permisos_asignatura where id_personal = ?;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1,  idpersonal);
			rs = ps.executeQuery();
			while (rs.next()) {
				PermisosAsignatura pe = new PermisosAsignatura();
				
				pe.setId(rs.getInt("id_permisos_asign"));
				pe.setFecha_inicio(rs.getDate("fecha_inicio"));
				pe.setFecha_final(rs.getDate("fecha_final"));
				
				//Actualizar el estado según fecha inicio y final
				DTPermisosAsignatura dtpa = new DTPermisosAsignatura();
				if(pe.getFecha_inicio().toString().equals(date.toString())) {
					pe.setEstado(1);
				}
				if(pe.getFecha_final().toString().equals(date.toString())) {
					pe.setEstado(0);
				}
				if(pe.getFecha_inicio().before(date) && pe.getFecha_inicio().after(date)) {
					pe.setEstado(1);
				}
				this.modificarPermisoEstado(pe);

			}
		} catch (Exception e) {
			String mensaje = "Mensaje" + e.getMessage();
			System.out.println("DATOS: ERROR EN LISTAR LOS PERMISOS EXPEDIENTES " + e.getMessage());
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
	}
	
	public void actualizarEstadoCoordinador(int id) {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		try {
				c = PoolConexion.getConnection();
				ps = c.prepareStatement("select * from permisos_asignatura where id_asignatura = ?;", ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				while (rs.next()) {
					PermisosAsignatura pe = new PermisosAsignatura();
					
					pe.setId(rs.getInt("id_permisos_asign"));
					pe.setFecha_inicio(rs.getDate("fecha_inicio"));
					pe.setFecha_final(rs.getDate("fecha_final"));
					
					//Actualizar el estado según fecha inicio y final
					DTPermisosAsignatura dtpa = new DTPermisosAsignatura();
					if(pe.getFecha_inicio().toString().equals(date.toString())) {
						pe.setEstado(1);
					}
					if(pe.getFecha_final().toString().equals(date.toString()) || pe.getFecha_final().before(date)) {
						pe.setEstado(0);
					}
					if(pe.getFecha_inicio().before(date) && pe.getFecha_inicio().after(date)) {
						pe.setEstado(1);
					}
					this.modificarPermisoEstado(pe);

				}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LOS PERMISOS EXPEDIENTES " + e.getMessage());
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
						
	}
}
