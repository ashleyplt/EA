package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.TipoActividad;

public class DTTipoActividad {
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsTActividad = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void LlenarTActividad(Connection c){

		try{
			ps = c.prepareStatement("Select * from tipo_actividad;" , ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			 
			rsTActividad = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LOS TIPOS DE ACTIVIDADES "+ e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<TipoActividad> listarTActividad(){
		ArrayList<TipoActividad> listaTActividad = new ArrayList<TipoActividad>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from tipo_actividad;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();

			while(rs.next()){
				TipoActividad tActividad = new TipoActividad();

				tActividad.setId(rs.getInt("id_tipo_actividad"));
				tActividad.setNombre(rs.getString("nombre"));
				tActividad.setDescripcion(rs.getString("descripcion"));
				
				listaTActividad.add(tActividad);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LOS TIPOS DE ACTIVIDADES "+ e.getMessage());
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
		return listaTActividad;
	}
	//Método para poner El tipo de actividad por defecto
	public TipoActividad getTActividad(int idTActividad)
	{
		TipoActividad a = new TipoActividad();
		String sql = "Select * from public.tipo_actividad where id_tipo_actividad = ?;";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idTActividad);
			rs = ps.executeQuery();
			if(rs.next())
			{
				a.setNombre(rs.getString("nombre"));
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
				if(rs != null)
				{
					rs.close();
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