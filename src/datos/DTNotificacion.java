package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Actividad;

public class DTNotificacion {
	//Atributos :D
		PoolConexion pc = PoolConexion.getInstance(); 
		Connection c = null;
		private ResultSet rsNotificacion = null;
		private ResultSet rs = null;
		private PreparedStatement ps = null;
		
		public void llenarNotificacion(Connection c) {
			try{
				ps = c.prepareStatement("SELECT * FROM notificacion", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rsNotificacion = ps.executeQuery();
			}
			catch (Exception e){
				System.out.println("DATOS: ERROR EN LISTAR LAS NOTIFICACIONES: "+ e.getMessage());
				e.printStackTrace();
			}
			
		}
		 //Metodo para almacenar una notificacion
		public boolean guardarNotificacion(Actividad ev){
					boolean guardado = false;
					
					try{
						c = PoolConexion.getConnection();
						this.llenarNotificacion(c);
						rsNotificacion.moveToInsertRow();
						rsNotificacion.updateInt("id_remitente", ev.getId_tipo_actividad());
						rsNotificacion.updateInt("id_destinatario", ev.getId_modalidad_actividad());
						rsNotificacion.updateString("mensaje", ev.getNombre()); 

						rsNotificacion.updateInt("etado", ev.getEstado());
						 rsNotificacion.insertRow();
						rsNotificacion.moveToCurrentRow();
						guardado = true;
					}
					catch (Exception e) {
						System.err.println("ERROR AL GUARDAR LA NOTIFICACION "+e.getMessage());
						e.printStackTrace();
					}
					finally{
						try {
							if(rsNotificacion != null){
								rsNotificacion.close();
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
				
				//Método para editar actividades
				public boolean modificarActividad(Actividad a)
				{
					boolean modificado = false;
					try 
					{
						c = PoolConexion.getConnection();
						this.llenarNotificacion(c);
						rsNotificacion.beforeFirst();
						while(rsNotificacion.next())
						{
							if(rsNotificacion.getInt("id_actividad") == a.getId_actividad()) 
							{						
								rsNotificacion.updateInt("id_remitente", a.getId_tipo_actividad());
								rsNotificacion.updateInt("id_destinatario", a.getId_tipo_actividad());

								rsNotificacion.updateString("mensaje", a.getNombre()); 
								rsNotificacion.updateRow();
								modificado = true;
								break;
							}	
						}
						
					} 
					catch (Exception e) 
					{
						System.err.println("DTActividades: Error al modificar la Notificacion" + e.getMessage());
						e.printStackTrace();
					}
					finally 
					{
						try 
						{
							if(rsNotificacion != null)
							{
								rsNotificacion.close();
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
				 

}
