package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.TipoEvaluacionActividad;

public class DTTEActividad {
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsTEActividad = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void LlenarTActividad(Connection c){

		try{
			ps = c.prepareStatement("Select * from tipo_evaluacion_actividad;" , ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			 
			rsTEActividad = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LOS TIPOS DE Evaluacion de ACTIVIDADES "+ e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<TipoEvaluacionActividad> listarTEActividad(){
		ArrayList<TipoEvaluacionActividad> listaTEActividad = new ArrayList<TipoEvaluacionActividad>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("Select * from tipo_evaluacion_actividad;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();

			while(rs.next()){
				TipoEvaluacionActividad teActividad = new TipoEvaluacionActividad();

				teActividad.setId(rs.getInt("id_tipo_evaluacion_act"));
				teActividad.setDescripcion(rs.getString("descripcion"));
				
				listaTEActividad.add(teActividad);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LOS TIPOS DE EVALUACI�N DE ACTIVIDADES "+ e.getMessage());
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
		return listaTEActividad;
	}
	//M�todo para poner El tipo de actividad por defecto
			public TipoEvaluacionActividad getTEActividad(int idTEActividad)
			{
				TipoEvaluacionActividad a = new TipoEvaluacionActividad();
				String sql = "Select * from public.tipo_evaluacion_actividad where id_tipo_evaluacion_act = ?";
				
				try 
				{
					c = PoolConexion.getConnection();
					ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
					ps.setInt(1, idTEActividad);
					rs = ps.executeQuery();
					if(rs.next())
					{
						a.setDescripcion(rs.getString("descripcion"));
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
						if(rsTEActividad != null)
						{
							rsTEActividad.close();
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