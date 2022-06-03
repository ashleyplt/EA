package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Actividad;
import vistas.VW_Actividad;

public class DTVActividad {
	//Atributos :D
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsActividades = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void llenarActividades() {
		try{
			ps = c.prepareStatement("SELECT * FROM public.vw_actividades", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsActividades = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR ACTIVIDADES: "+ e.getMessage());
			e.printStackTrace();
		}

	}

	public ArrayList<VW_Actividad> listarActividades(){
		ArrayList<VW_Actividad> listaActividades = new ArrayList<VW_Actividad>();

		String sql = "SELECT * FROM public.vw_actividades;";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_Actividad vActividad = new VW_Actividad();
				
				vActividad.setId_actividad(rs.getInt("id_actividades"));
				vActividad.setNombre(rs.getString("actividad"));
				vActividad.setDescripcion(rs.getString("descripcion"));
				vActividad.setTipo(rs.getString("tipo"));
				vActividad.setModalidad(rs.getString("modalidad"));
				vActividad.setEvaluacion(rs.getString("evaluacion"));
				vActividad.setCant_horas(rs.getInt("cantHora"));
				vActividad.setCreador(rs.getString("creador"));
				vActividad.setEstado(rs.getInt("estado"));

				listaActividades.add(vActividad);
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Actividades: Error en listar actividades " + e.getMessage());
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
				System.err.println("DT_Actividad: Error en listar actividades " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return listaActividades;
	}
	public ArrayList<VW_Actividad> listarHistorico(){
		ArrayList<VW_Actividad> listaActividades = new ArrayList<VW_Actividad>();

		String sql = "SELECT * FROM public.vw_actividades WHERE estado = 1;";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_Actividad vActividad = new VW_Actividad();
				
				vActividad.setId_actividad(rs.getInt("id_actividades"));
				vActividad.setNombre(rs.getString("actividad"));
				vActividad.setDescripcion(rs.getString("descripcion"));
				vActividad.setTipo(rs.getString("tipo"));
				vActividad.setModalidad(rs.getString("modalidad"));
				vActividad.setEvaluacion(rs.getString("evaluacion"));
				vActividad.setCant_horas(rs.getInt("cantHora"));
				vActividad.setCreador(rs.getString("creador"));
				vActividad.setEstado(rs.getInt("estado"));

				listaActividades.add(vActividad);
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Actividades: Error en listar actividades " + e.getMessage());
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
				System.err.println("DT_Actividad: Error en listar actividades " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return listaActividades;
	}
	
	public VW_Actividad getActividad(int idactividad)
	{
		VW_Actividad a = new VW_Actividad();
		String sql = "Select * from public.vw_actividades where id_actividades = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idactividad);
			rs = ps.executeQuery();
			if(rs.next())
			{
				
				a.setId_actividad(rs.getInt("id_actividades"));
				a.setNombre(rs.getString("actividad"));
				a.setDescripcion(rs.getString("descripcion"));
				a.setTipo(rs.getString("tipo"));
				a.setModalidad(rs.getString("modalidad"));
				a.setEvaluacion(rs.getString("evaluacion"));
				a.setCant_horas(rs.getInt("cantHora"));
				a.setCreador(rs.getString("creador"));
				a.setEstado(rs.getInt("estado"));

			}
		} 
		catch (SQLException e) 
		{
			System.err.println("DATOS: error getActividad(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsActividades != null)
				{
					rsActividades.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTActividad: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return a;
	}
}