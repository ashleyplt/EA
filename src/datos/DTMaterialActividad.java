package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.MaterialActividad;

public class DTMaterialActividad {
	
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsMaterialActividad = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void LlenarRecurso(Connection c){

		try{
			ps = c.prepareStatement("select * from material_actividad", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsMaterialActividad = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LAS ACTIVIDADES CON MATERIALES "+ e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<MaterialActividad> listarActMateriales(){
		ArrayList<MaterialActividad> listaActMateriales = new ArrayList<MaterialActividad>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from material_actividad\"", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next()){
				MaterialActividad ActMat = new MaterialActividad();
				
				ActMat.setId_material_actividad(rs.getInt("id_material_actividad"));
				ActMat.setId_material(rs.getInt("id_material"));
				ActMat.setId_actividad(rs.getInt("id_actividad"));
				
				listaActMateriales.add(ActMat);
				
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LAS ACTIVIDADES CON MATERIALES "+ e.getMessage());
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
		return listaActMateriales;
	}
	
	
	
	public ArrayList<MaterialActividad> listarultimoActMateriales(){
		ArrayList<MaterialActividad> listaActMateriales = new ArrayList<MaterialActividad>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from material_actividad\"", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next()){
				MaterialActividad ActMat = new MaterialActividad();
				
				ActMat.setId_material_actividad(rs.getInt("id_material_actividad"));
				ActMat.setId_material(rs.getInt("id_material"));
				ActMat.setId_actividad(rs.getInt("id_actividad"));
				
				listaActMateriales.add(ActMat);
				
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LAS ACTIVIDADES CON MATERIALES "+ e.getMessage());
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
		return listaActMateriales;
	}
	
	public boolean agregarMaterial(MaterialActividad ma) {
		boolean guardado = false;
		
		try{
			c = PoolConexion.getConnection();
			this.LlenarRecurso(c);;
			rsMaterialActividad.moveToInsertRow();
			rsMaterialActividad.updateInt("id_material", ma.getId_material());
			rsMaterialActividad.updateInt("id_actividad", ma.getId_actividad());
			rsMaterialActividad.insertRow();
			rsMaterialActividad.moveToCurrentRow();
			guardado = true;
		}
		catch (Exception e) {
			System.err.println("ERROR AL GUARDAR El Material con la actividad "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rsMaterialActividad != null){
					rsMaterialActividad.close();
				}
				if(c != null){
					PoolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return guardado;
	}

	
	
}
