package datos;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import entidades.*;

public class DTPlanDeEstudio {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsPlanDeEstudio = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset Plan de estudio
	public void llenarPlanDeEstudio(Connection c)
	{
		String sql = "SELECT * FROM public.plan_de_estudio";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsPlanDeEstudio = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT PLAN DE ESTUDIO: Error en listar Plan de estudio " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean guardarPlanDeEstudio(PlanDeEstudio pde)
	{
		boolean guardado = false;
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarPlanDeEstudio(c);
			//AQUI INICIA EL GUARDAR
			
			rsPlanDeEstudio.moveToInsertRow();
			rsPlanDeEstudio.updateString("nombre", pde.getNombre());
			rsPlanDeEstudio.updateInt("estado", 1);
			rsPlanDeEstudio.updateDate("fecha_creacion", date);
			rsPlanDeEstudio.insertRow();
			rsPlanDeEstudio.moveToCurrentRow();
			guardado = true;
			
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTPlanDeEstudio: Error al guardar Plan de Estudio " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsPlanDeEstudio != null)
				{
					rsPlanDeEstudio.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTPlanDeEstudio: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public boolean modificarPlanDeEstudio(PlanDeEstudio pde)
	{
		boolean modificado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarPlanDeEstudio(c);
			rsPlanDeEstudio.beforeFirst();
			while(rsPlanDeEstudio.next())
			{
				if(rsPlanDeEstudio.getInt("id_plan_estudio") == pde.getId_plan_estudio()) 
				{	
					rsPlanDeEstudio.updateString("nombre", pde.getNombre());
					rsPlanDeEstudio.updateRow();
					modificado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTPLANDEESTUDIO: Error al modificar la plan de estudio " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsPlanDeEstudio != null)
				{
					rsPlanDeEstudio.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTPlanDeEstudio: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return modificado;
	}
	
	public boolean eliminarPlanDeEstudio(int id_plan_estudio, int estado)
	{
		boolean eliminado = false;
		int state = 1;
		if (estado == 1) {
			state = 0;
		}
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarPlanDeEstudio(c);
			rsPlanDeEstudio.beforeFirst();
			while(rsPlanDeEstudio.next())
			{
				if(rsPlanDeEstudio.getInt("id_plan_estudio")==id_plan_estudio) 
				{
					rsPlanDeEstudio.updateInt("estado", state);	
					rsPlanDeEstudio.updateRow();
					
					eliminado = true;
					break;
				}
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTPLANDEESTUDIO: Error al eliminar plan de estudio " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsPlanDeEstudio != null)
				{
					rsPlanDeEstudio.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTPLANDEESTUDIO: Error al cerrar conexión " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return eliminado;
	}
	
	public int getIdPlanEstudio(String nombre)
	{
		int id = 0;
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM plan_de_estudio where nombre LIKE ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, nombre);
			rs = ps.executeQuery();
			if(rs.next())
			{
				id = rs.getInt("id_plan_estudio");
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTPLANDEESTUDIO: ERROR AL BUSCAR EL ID DEL PLAN DE ESTUDIO " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsPlanDeEstudio != null)
				{
					rsPlanDeEstudio.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTPLANDEESTUDIO: Error al cerrar conexión " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return id;
	}
	
	
	public PlanDeEstudio recuperarPlanDeEstudio(int id_plan_estudio)
	{
		PlanDeEstudio pde = new PlanDeEstudio();
		String sql = "SELECT * FROM public.plan_de_estudio where id_plan_estudio = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, id_plan_estudio);
			rs = ps.executeQuery();
			if(rs.next())
			{
				pde.setId_plan_estudio(id_plan_estudio);
				pde.setNombre(rs.getString("nombre"));
				pde.setFecha_creacion(rs.getDate("fecha_creacion"));
				pde.setEstado(rs.getInt("estado"));
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DATOS: error recuperarPlanDeEstudio(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsPlanDeEstudio != null)
				{
					rsPlanDeEstudio.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTPlanDeEstudio: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return pde;
	}
	
	
	public ArrayList<PlanDeEstudio> listarPlanDeEstudio(int vistaCompleta){
		ArrayList<PlanDeEstudio> listarPlanDeEstudio = new ArrayList<PlanDeEstudio>();
		String sql = "";
		
		if(vistaCompleta == 1) {
			 sql = "SELECT * from public.plan_de_estudio";
		}else {
			sql = "SELECT * from public.plan_de_estudio WHERE estado <> 0";
		}
		
		
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next()){
				PlanDeEstudio pde = new PlanDeEstudio();
				pde.setId_plan_estudio(rs.getInt("id_plan_estudio"));
				pde.setNombre(rs.getString("nombre"));
				pde.setFecha_creacion(rs.getDate("fecha_creacion"));
				pde.setEstado(rs.getInt("estado"));
				listarPlanDeEstudio.add(pde);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR PLAN DE ESTUDIO: "+ e.getMessage());
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
		return listarPlanDeEstudio;
	}

	
	

}
