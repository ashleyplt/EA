package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import entidades.PlanDeEstudio;
import entidades.PlanEstudioDet;

public class DTPlanEstudioDet {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsPlanDeEstudio = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset Plan de estudio
	public void llenarPlanDeEstudio(Connection c)
	{
		String sql = "SELECT * FROM public.plan_estudio_det";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsPlanDeEstudio = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT PLAN DE ESTUDIO: ERROR EN LISTAR PLAN DE ESTUDIO DETALLES " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean guardarAsignaturaEnPlan(PlanEstudioDet pde)
	{
		boolean guardado = false;
			
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarPlanDeEstudio(c);
			//AQUI INICIA EL GUARDAR
			
			rsPlanDeEstudio.moveToInsertRow();
			rsPlanDeEstudio.updateInt("estado", 1);
			rsPlanDeEstudio.updateInt("id_plan_estudio", pde.getId_plan_estudio());
			rsPlanDeEstudio.updateInt("id_asignatura", pde.getId_asignatura());
			rsPlanDeEstudio.insertRow();
			rsPlanDeEstudio.moveToCurrentRow();
			guardado = true;
			
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTPlanDeEstudio: ERROR AL GUARDAR LA ASIGNATURA EN EL PLAN DE ESTUDIO " + e.getMessage());
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
	
	public int getId(int idplan, int idasignatura)
	{
		int id = 0;
		
		String SQL = ("SELECT * FROM plan_estudio_det WHERE id_plan_estudio = ? AND id_asignatura = ?;");
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL); 
			ps.setInt(1, idplan);
			ps.setInt(2, idasignatura);
			rsPlanDeEstudio = ps.executeQuery();
			if(rsPlanDeEstudio.next()){
				id = rsPlanDeEstudio.getInt("id_plan_est_det");
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR AL BUSCAR ID DE PLAN ESTUDIO DET "+ e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(rsPlanDeEstudio != null){
					rsPlanDeEstudio.close();
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
		return id;
	}
	
	public boolean modificarEstadoAsignatura(int id, int estado)
	{
		boolean modificado = false;
		if(estado == 1) estado = 0;
		else estado = 1;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarPlanDeEstudio(c);
			rsPlanDeEstudio.beforeFirst();
			while(rsPlanDeEstudio.next())
			{
				if(rsPlanDeEstudio.getInt("id_plan_est_det") == id)
				{	
					rsPlanDeEstudio.updateInt("estado", estado);
					rsPlanDeEstudio.updateRow();
					modificado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("ERROR AL MODIFICAR EL ESTADO DE LA ASIGNATURA EN EL PLAN DE ESTUDIO: " + e.getMessage());
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
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return modificado;
	}
}
