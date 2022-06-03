package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entidades.PlanCarrera;

public class DTPlanCarrera {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsPlanDeEstudio = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset Plan de estudio
	public void llenarPlanCarrera(Connection c)
	{
		String sql = "SELECT * FROM public.plan_carrera";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsPlanDeEstudio = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT PLAN DE ESTUDIO: ERROR EN LISTA PLAN CARRERA " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean guardarPlanCarrera(PlanCarrera pc)
	{
		boolean guardado = false;
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarPlanCarrera(c);
			
			rsPlanDeEstudio.moveToInsertRow();
			rsPlanDeEstudio.updateInt("estado", 1);
			rsPlanDeEstudio.updateInt("id_plan_estudio", pc.getId_plan());
			rsPlanDeEstudio.updateInt("id_carrera", pc.getId_carrera());
			rsPlanDeEstudio.insertRow();
			rsPlanDeEstudio.moveToCurrentRow();
			guardado = true;
			
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTPlanDeEstudio: ERROR AL GUARDAR EL PLAN CARRERA " + e.getMessage());
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
				System.err.println("DTPlanDeEstudio: Error al cerrar conexión " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
}
