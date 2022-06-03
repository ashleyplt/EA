package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.MaterialActividad;
import vistas.VW_Actividad;
import vistas.VW_MaterialesActividad;

public class DTVMaterialesActividad {
	
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsVMaterialActividad = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void LlenarRecurso(Connection c){

		try{
			ps = c.prepareStatement("select * from vw_material_actividad", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsVMaterialActividad = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LAS ACTIVIDADES CON MATERIALES "+ e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<VW_MaterialesActividad> listarActMateriales(int idActividad){
		ArrayList<VW_MaterialesActividad> listaActMateriales = new ArrayList<VW_MaterialesActividad>();
		
		String sql = "SELECT * FROM public.vw_material_actividad where id_actividades = ?;";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idActividad);			
		    rs = ps.executeQuery();
			while(rs.next())
			{
				VW_MaterialesActividad vMA = new VW_MaterialesActividad();
				
				vMA.setId_actividad(rs.getInt("id_actividades"));
				vMA.setActividad(rs.getString("nombre"));;
				vMA.setMaterial(rs.getString("material"));;
				vMA.setDetalle_material(rs.getString("detalle_material"));;
				vMA.setEnlace(rs.getString("enlace"));;
				

				listaActMateriales.add(vMA);
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Actividades: Error en listar Materiales de las actividades " + e.getMessage());
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
				System.err.println("DT_Actividad: Error en listar Materiales de las actividades " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return listaActMateriales;
	}
}