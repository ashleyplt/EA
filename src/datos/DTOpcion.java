package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Opcion;

public class DTOpcion {
	
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsOpciones = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarOpciones(Connection c) {
		try{
			ps = c.prepareStatement("SELECT * FROM opciones", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsOpciones = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LAS OPCIONES: "+ e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	//Método para listar las opciones
	public ArrayList<Opcion> listarOpciones(){
		ArrayList<Opcion> listaOpciones = new ArrayList<Opcion>();
		
		String sql = "SELECT * FROM opciones WHERE estado <> 3";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Opcion o = new Opcion();
				o.setId_opcion(rs.getInt("id_opciones"));
				o.setNombre(rs.getString("nombre"));
				o.setDescripcion(rs.getString("descripcion"));
				o.setEstado(rs.getInt("estado"));
				
				listaOpciones.add(o);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Opciones: Error en listar las opciones " + e.getMessage());
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
				System.err.println("DT_Opcion: Error en listar las Opciones " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return listaOpciones;
	}
	
	//Método para eliminar las opciones de forma lógica
	public boolean eliminarOpcion(int estado, int id) {
		boolean deletOpcion = false;
		int state = 0;
		
		if(estado == 0) {
			state = 1;
			
		}
		try {
			c = PoolConexion.getConnection();
			this.llenarOpciones(c);
			rsOpciones.beforeFirst();
			
			while(rsOpciones.next()) {
				if(rsOpciones.getInt("id") == id){
					rsOpciones.updateInt("estado", state);
					rsOpciones.updateRow();
					
					deletOpcion = true;
					break;
				}
			}
		}catch(SQLException e){
			System.err.println("DATOS: error eliminar la Opcion: " + e.getMessage());
			e.printStackTrace();
			
		}finally {
			try 
			{
				if(rsOpciones != null)
				{
					rsOpciones.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTOpcion: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return deletOpcion;
	}

}
