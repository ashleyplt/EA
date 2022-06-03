package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.ModalidadActividad;

public class DTModalidadActividad {
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsMActividad = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void LlenarTActividad(Connection c){

		try{
			ps = c.prepareStatement("Select * from public.modalidad_actividad;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsMActividad = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LA MODALIDAD "+ e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<ModalidadActividad> listarTMActividad(){
		ArrayList<ModalidadActividad> listaMActividad = new ArrayList<ModalidadActividad>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("Select * from public.modalidad_actividad;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();

			while(rs.next()){
				ModalidadActividad mActividad = new ModalidadActividad();

				mActividad.setId(rs.getInt("id_modalidad_actividad"));
				mActividad.setNombre(rs.getString("nombre"));
				mActividad.setDescripcion(rs.getString("descripcion"));
				

				listaMActividad.add(mActividad);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LA MODALIDAD "+ e.getMessage());
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
		return listaMActividad;
	}
	//M�todo para poner El tipo de actividad por defecto
		public ModalidadActividad getMActividad(int idMActividad)
		{
			ModalidadActividad a = new ModalidadActividad();
			String sql = "Select * from public.modalidad_actividad where id_modalidad_actividad = ?;";
			
			try 
			{
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ps.setInt(1, idMActividad);
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