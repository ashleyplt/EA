package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Material;

public class DTMaterial {

	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsMaterial = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void LlenarRecurso(Connection c){

		try{
			ps = c.prepareStatement("select * from materiales", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsMaterial = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LOS MATERIALES "+ e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<Material> listarMateriales(){
		ArrayList<Material> listaMateriales = new ArrayList<Material>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from materiales", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Material material = new Material();
				
				material.setId_material(rs.getInt("id_materiales"));
				material.setNombre(rs.getString("nombre"));
				material.setDescripcion(rs.getString("descripcion"));
				material.setUrl(rs.getString("url"));
				
				listaMateriales.add(material);
				
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LOS MATERIALES "+ e.getMessage());
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
		return listaMateriales;
	}
	
	public boolean agregarMaterial(Material m) {
		boolean guardado = false;
		
		try{
			c = PoolConexion.getConnection();
			this.LlenarRecurso(c);;
			rsMaterial.moveToInsertRow();
			rsMaterial.updateString("nombre", m.getNombre());
			rsMaterial.updateString("descripcion", m.getDescripcion());
			rsMaterial.updateString("url", m.getUrl());
			rsMaterial.insertRow();
			rsMaterial.moveToCurrentRow();
			guardado = true;
		}
		catch (Exception e) {
			System.err.println("ERROR AL GUARDAR El Material "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rsMaterial != null){
					rsMaterial.close();
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
	
	public ArrayList<Material> listarUltimoMaterial(){
		ArrayList<Material> listaMateriales = new ArrayList<Material>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from materiales ORDER BY id_materiales DESC LIMIT 1;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Material material = new Material();
				
				material.setId_material(rs.getInt("id_materiales"));
				material.setNombre(rs.getString("nombre"));
				material.setDescripcion(rs.getString("descripcion"));
				material.setUrl(rs.getString("url"));
				
				listaMateriales.add(material);
				
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LOS MATERIALES "+ e.getMessage());
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
		return listaMateriales;
	}
	
	
	
}