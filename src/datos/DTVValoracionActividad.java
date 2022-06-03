package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vistas.VW_ValoracionActividad;

public class DTVValoracionActividad {
			//Atributos :D
			PoolConexion pc = PoolConexion.getInstance(); 
			Connection c = null;
			private ResultSet rsValoraciones = null;
			private ResultSet rs = null;
			private PreparedStatement ps = null;

			public void llenarValoracion(Connection c) {
				try{
					ps = c.prepareStatement("SELECT * FROM public.vw_valoracion_actividades;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
					rsValoraciones = ps.executeQuery(); 
				}
				catch (Exception e){
					System.out.println("DATOS: ERROR EN LISTAR LOS ARCHIVOS: "+ e.getMessage());
					e.printStackTrace();
				}

			}

			public ArrayList<VW_ValoracionActividad> listarValoraciones(int id){
				int idActividad = id;
				ArrayList<VW_ValoracionActividad> listaValoraciones = new ArrayList<VW_ValoracionActividad>();

				String sql = "SELECT * FROM public.vw_valoracion_actividades WHERE id_actividades = " + idActividad+ ";";
				try 
				{
					c = PoolConexion.getConnection();
					ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
					rs = ps.executeQuery();
					while(rs.next())
					{
						VW_ValoracionActividad va = new VW_ValoracionActividad();
						
						va.setActividad(rs.getString("actividad"));
						va.setDocente(rs.getString("docente"));
						va.setComentario(rs.getString("comentario"));
						va.setValor(rs.getInt("valor"));
						listaValoraciones.add(va);
						
					}
				} 
				catch (Exception e) 
				{
					System.err.println("Valoracion: Error en listar la valoracion " + e.getMessage());
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
						System.err.println("Archivos: Error en listar la valoracion " + e2.getMessage());
						e2.printStackTrace();
					}
				}

				return listaValoraciones;
			}
}