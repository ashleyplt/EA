package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.ValoracionActividad;
import vistas.VW_ValoracionActividad;

public class DTValoracionActividad {

	//Atributos :D
		PoolConexion pc = PoolConexion.getInstance(); 
		Connection c = null;
		private ResultSet rsValoraciones = null;
		private ResultSet rs = null;
		private PreparedStatement ps = null;

		public void llenarValoracion(Connection c) {
			try{
				ps = c.prepareStatement("SELECT * FROM public.valoraciones;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rsValoraciones = ps.executeQuery(); 
			}
			catch (Exception e){
				System.out.println("DATOS: ERROR EN LISTAR LOS ARCHIVOS: "+ e.getMessage());
				e.printStackTrace();
			}

		}

		public ArrayList<ValoracionActividad> listarValoraciones(int id){
			int idActividad = id;
			ArrayList<ValoracionActividad> listaValoraciones = new ArrayList<ValoracionActividad>();

			String sql = "SELECT * FROM public.valoraciones WHERE id_actividades =" + idActividad+ ";";
			try 
			{
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rs = ps.executeQuery();
				while(rs.next())
				{
					ValoracionActividad va = new ValoracionActividad();
					va.setId_valoracionesA(rs.getInt("id_valoracionesA"));
					va.setId_valoracionesA(rs.getInt("id_actividades"));
					va.setId_usuario(rs.getInt("id_usuario"));
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
		
		public int obtenerValoracion (int id){
			int idActividad = id;
			ValoracionActividad va = new ValoracionActividad();
			int total = 0;
			int cont=0;
			String sql = "SELECT * FROM public.valoraciones WHERE id_actividades = ?;";
			try 
			{
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ps.setInt(1, idActividad);
				rs = ps.executeQuery();
				while(rs.next())
				{
					va.setValor(rs.getInt("valor"));
					total += va.getValor();
					cont++;
				}
				total = total / cont;
				total = Math.round(total);
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

			return total;
		}
		
		public VW_ValoracionActividad obtenerDatosValoracion (int id){
			int idActividad = id;
			VW_ValoracionActividad va = new VW_ValoracionActividad();
			int total = 0;
			int cont=0;
			String sql = "SELECT * FROM public.vw_valoracion_actividades WHERE id_actividades = ?;";
			try 
			{
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ps.setInt(1, idActividad);
				rs = ps.executeQuery();
				if(rs.next())
				{
					va.setImagen(rs.getString("imagen"));
					va.setDocente(rs.getString("docente"));
					va.setComentario(rs.getString("comentario"));
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

			return va;
		}
		public ArrayList<VW_ValoracionActividad> obtenerDatosValoracionlist (int id){
			int idActividad = id;
			ArrayList<VW_ValoracionActividad> listaValoraciones = new ArrayList<VW_ValoracionActividad>();
			int total = 0;
			int cont=0;
			String sql = "SELECT * FROM public.vw_valoracion_actividades WHERE id_actividades = ?;";
			try 
			{
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ps.setInt(1, idActividad);
				rs = ps.executeQuery();
				while(rs.next())
				{
					VW_ValoracionActividad va = new VW_ValoracionActividad();
					va.setValor(rs.getInt("valor"));
					va.setDocente(rs.getString("docente"));
					va.setComentario(rs.getString("comentario"));
					va.setImagen(rs.getString("imagen"));
					
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

		//Metodo para almacenar un archivo
		public boolean guardarValoracion(ValoracionActividad va){
					boolean guardado = false;				
					try{
						c = PoolConexion.getConnection();
						this.llenarValoracion(c);
						rsValoraciones.moveToInsertRow();  
					//	rsValoracion.updateString("nombre_archivo", va.getNombre_archivo());   
						
						rsValoraciones.updateInt("id_actividades", va.getId_actividades()); 
						rsValoraciones.updateInt("id_usuario", va.getId_usuario()); 
						rsValoraciones.updateString("comentario", va.getComentario()); 
						rsValoraciones.updateInt("valor", va.getValor()); 

						rsValoraciones.insertRow();
						rsValoraciones.moveToCurrentRow();
						guardado = true;
						
					}
					catch (Exception e) {
						System.err.println("ERROR AL GUARDAR LA VALORACION "+e.getMessage());
						e.printStackTrace();
					}
					finally{
						try {
							if(rsValoraciones != null){
								rsValoraciones.close();
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