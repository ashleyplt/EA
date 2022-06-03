package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Actividad;

public class DTActividad {
	//Atributos :D
		PoolConexion pc = PoolConexion.getInstance(); 
		Connection c = null;
		private ResultSet rsActividades = null;
		private ResultSet rs = null;
		private PreparedStatement ps = null;
		
		public void llenarActividades(Connection c) {
			try{
				ps = c.prepareStatement("SELECT * FROM actividades", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rsActividades = ps.executeQuery();
			}
			catch (Exception e){
				System.out.println("DATOS: ERROR EN LISTAR ACTIVIDADES: "+ e.getMessage());
				e.printStackTrace();
			}
			
		}
		
		public ArrayList<Actividad> listarActividades(){
			ArrayList<Actividad> listaActividades = new ArrayList<Actividad>();
			
			String sql = "SELECT * FROM actividades WHERE estado <> 3";
			try 
			{
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rs = ps.executeQuery();
				while(rs.next())
				{
					Actividad a = new Actividad();
					a.setId_actividad(rs.getInt("id_actividades"));
					a.setNombre(rs.getString("nombre"));
					a.setDescripcion(rs.getString("descripcion"));
					a.setCant_horas(rs.getInt("cant_horas"));
					a.setEstado(rs.getInt("estado"));
					a.setId_tipo_actividad(rs.getInt("id_tipo_actividad"));
					a.setId_modalidad_actividad(rs.getInt("id_modalidad_actividad"));
					a.setId_usuario(rs.getInt("id_usuario"));
					
					listaActividades.add(a);
					
				}
			} 
			catch (Exception e) 
			{
				System.err.println("DT Actividades: Error en listar actividades " + e.getMessage());
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
					System.err.println("DT_Actividad: Error en listar actividades " + e2.getMessage());
					e2.printStackTrace();
				}
			}
			
			return listaActividades;
		}
		
		//Metodo para almacenar una actividad
		public boolean guardarActividad(Actividad ev){
					boolean guardado = false;
					
					try{
						c = PoolConexion.getConnection();
						this.llenarActividades(c);
						rsActividades.moveToInsertRow();
						rsActividades.updateString("nombre", ev.getNombre());
						rsActividades.updateString("descripcion", ev.getDescripcion());
						rsActividades.updateInt("estado", ev.getEstado());
						rsActividades.updateInt("id_tipo_actividad", ev.getId_tipo_actividad());
						rsActividades.updateInt("id_tipo_evaluacion_act", ev.getId_evaluacion_actividad());
						rsActividades.updateInt("id_modalidad_actividad", ev.getId_modalidad_actividad());
						rsActividades.updateInt("cant_horas", ev.getCant_horas());
						rsActividades.updateInt("id_usuario", ev.getId_usuario());
						rsActividades.insertRow();
						rsActividades.moveToCurrentRow();
						guardado = true;
					}
					catch (Exception e) {
						System.err.println("ERROR AL GUARDAR LA ACTIVIDAD "+e.getMessage());
						e.printStackTrace();
					}
					finally{
						try {
							if(rsActividades != null){
								rsActividades.close();
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
				
				//M�todo para editar actividades
				public boolean modificarActividad(Actividad a)
				{
					boolean modificado = false;
					try 
					{
						c = PoolConexion.getConnection();
						this.llenarActividades(c);
						rsActividades.beforeFirst();
						while(rsActividades.next())
						{
							if(rsActividades.getInt("id_actividades") == a.getId_actividad()) 
							{						
								rsActividades.updateString("nombre", a.getNombre());
								rsActividades.updateString("descripcion", a.getDescripcion());
								rsActividades.updateInt("id_tipo_actividad", a.getId_tipo_actividad());
								rsActividades.updateInt("id_modalidad_actividad", a.getId_modalidad_actividad());
								rsActividades.updateInt("id_tipo_evaluacion_act", a.getId_evaluacion_actividad());
								rsActividades.updateInt("id_usuario", a.getId_usuario());
								rsActividades.updateInt("cant_horas", a.getCant_horas());						
								rsActividades.updateRow();
								modificado = true;
								break;
							}	
						}
						
					} 
					catch (Exception e) 
					{
						System.err.println("DTActividades: Error al modificar la Actividad " + e.getMessage());
						e.printStackTrace();
					}
					finally 
					{
						try 
						{
							if(rsActividades != null)
							{
								rsActividades.close();
							}
							if(c != null)
							{
								c.close();
							}
						} 
						catch (Exception e2) 
						{
							System.err.println("DTActividades: Error al cerrar conexion " + e2.getMessage());
							e2.printStackTrace();
						}
					}
					return modificado;
				}
				
				//M�todo para listar los datos en el form
				
				public Actividad getActividad(int idactividad)
				{
					Actividad a = new Actividad();
					String sql = "Select * from public.actividades where id_actividades = ?";
					
					try 
					{
						c = PoolConexion.getConnection();
						ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
						ps.setInt(1, idactividad);
						rs = ps.executeQuery();
						if(rs.next())
						{
							a.setId_actividad(idactividad);
							a.setNombre(rs.getString("nombre"));
							a.setDescripcion(rs.getString("descripcion"));
							a.setId_tipo_actividad(rs.getInt("id_tipo_actividad"));
							a.setId_modalidad_actividad(rs.getInt("id_modalidad_actividad"));
							a.setId_evaluacion_actividad(rs.getInt("id_tipo_evaluacion_act"));
							a.setEstado(rs.getInt("estado"));
							a.setId_usuario(rs.getInt("id_usuario"));
							a.setCant_horas(rs.getInt("cant_horas"));
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
							if(rsActividades != null)
							{
								rsActividades.close();
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
				//M�todo para eliminar las actividades de forma l�gica
				public boolean eliminarActividad(int estado, int id) {
					boolean deletActividad = false;
					int state = 0;
					
					if(estado == 0) {
						state = 1;
						
					}
					try {
						c = PoolConexion.getConnection();
						this.llenarActividades(c);
						rsActividades.beforeFirst();
						
						while(rsActividades.next()) {
							if(rsActividades.getInt("id_actividades") == id){
								rsActividades.updateInt("estado", state);
								rsActividades.updateRow();
								
								deletActividad = true;
								break;
							}
						}
					}catch(SQLException e){
						System.err.println("DATOS: error eliminar la actividad: " + e.getMessage());
						e.printStackTrace();
						
					}finally {
						try 
						{
							if(rsActividades != null)
							{
								rsActividades.close();
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
					return deletActividad;
				}
				
				
				public ArrayList<Actividad> listarUltimaActividad(){
					ArrayList<Actividad> listaActividades = new ArrayList<Actividad>();
					
					String sql = "SELECT * FROM actividades ORDER BY id_actividades DESC LIMIT 1";
					try 
					{
						c = PoolConexion.getConnection();
						ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
						rs = ps.executeQuery();
						while(rs.next())
						{
							Actividad a = new Actividad();
							a.setId_actividad(rs.getInt("id_actividades"));
							a.setNombre(rs.getString("nombre"));
							a.setDescripcion(rs.getString("descripcion"));
							a.setCant_horas(rs.getInt("cant_horas"));
							a.setEstado(rs.getInt("estado"));
							a.setId_tipo_actividad(rs.getInt("id_tipo_actividad"));
							a.setId_modalidad_actividad(rs.getInt("id_modalidad_actividad"));
							a.setId_evaluacion_actividad(rs.getInt("id_tipo_evaluacion_act"));
							a.setId_usuario(rs.getInt("id_usuario"));
							
							listaActividades.add(a);							
						}
					} 
					catch (Exception e) 
					{
						System.err.println("DT Actividades: Error en listar actividades " + e.getMessage());
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
							System.err.println("DT_Actividad: Error en listar actividades " + e2.getMessage());
							e2.printStackTrace();
						}
					}
					
					return listaActividades;
				}
}