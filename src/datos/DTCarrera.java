package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entidades.Carrera;



public class DTCarrera {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsCarrera = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset Carrera
		public void llenarCarrera(Connection c)
		{
			String sql = "SELECT * FROM public.carrera";
			try 
			{
				//c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rsCarrera = ps.executeQuery();
			} 
			catch (Exception e) 
			{
				System.err.println("DT CARRERA: Error en listar carrera " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		
		public boolean guardarCarrera(Carrera cr)
		{
			boolean guardado = false;
			try 
			{
				c = PoolConexion.getConnection();
				this.llenarCarrera(c);
				//AQUI INICIA EL GUARDAR
				rsCarrera.moveToInsertRow();
				rsCarrera.updateString("nombre", cr.getNombre());
				rsCarrera.updateInt("estado", cr.getEstado());
				rsCarrera.updateInt("id_facultad", cr.getId_facultad());
				rsCarrera.updateInt("id_departamento", cr.getId_departamento());
				
				rsCarrera.insertRow();
				rsCarrera.moveToCurrentRow();
				guardado = true;
				
				
			} 
			catch (Exception e) 
			{
				System.err.println("DTCARRERA: Error al guardar Carrera " + e.getMessage());
				e.printStackTrace();
			}
			finally 
			{
				try 
				{
					if(rsCarrera != null)
					{
						rsCarrera.close();
					}
					if(c != null)
					{
						c.close();
					}
				} 
				catch (Exception e2) 
				{
					System.err.println("DTCarrera: Error al cerrar conexion " + e2.getMessage());
					e2.printStackTrace();
				}
			}
			
			return guardado;
		}
		
		public boolean modificarCarrera(Carrera Cr)
		{
			boolean modificado = false;
			try 
			{
				c = PoolConexion.getConnection();
				this.llenarCarrera(c);
				rsCarrera.beforeFirst();
				while(rsCarrera.next())
				{
					if(rsCarrera.getInt("id_carrera") == Cr.getId_carrera()) 
					{	
						rsCarrera.updateString("nombre", Cr.getNombre());
						rsCarrera.updateInt("id_facultad", Cr.getId_facultad());
						rsCarrera.updateInt("estado", Cr.getEstado());
						rsCarrera.updateInt("id_facultad", Cr.getId_departamento());
						rsCarrera.updateInt("id_departamento", Cr.getId_departamento());
						rsCarrera.updateRow();
						modificado = true;
						break;
					}	
				}
				
			} 
			catch (Exception e) 
			{
				System.err.println("DTActividades: Error al modificar la Carrera " + e.getMessage());
				e.printStackTrace();
			}
			finally 
			{
				try 
				{
					if(rsCarrera != null)
					{
						rsCarrera.close();
					}
					if(c != null)
					{
						c.close();
					}
				} 
				catch (Exception e2) 
				{
					System.err.println("DTCarrera: Error al cerrar conexion " + e2.getMessage());
					e2.printStackTrace();
				}
			}
			return modificado;
		}
		
		public boolean eliminarCarrera(int id_carrera, int estado)
		{
			boolean eliminado = false;
			int state = 1;
			if (estado == 1) {
				state = 0;
			}
			
			try 
			{
				c = PoolConexion.getConnection();
				this.llenarCarrera(c);
				rsCarrera.beforeFirst();
				while(rsCarrera.next())
				{
					if(rsCarrera.getInt("id_carrera")==id_carrera) 
					{
						rsCarrera.updateInt("estado", state);	
						rsCarrera.updateRow();
						
						eliminado = true;
						break;
					}
				}
				
			} 
			catch (Exception e) 
			{
				System.err.println("DTCARRERA: Error al eliminar carrera " + e.getMessage());
				e.printStackTrace();
			}
			finally 
			{
				try 
				{
					if(rsCarrera != null)
					{
						rsCarrera.close();
					}
					if(c != null)
					{
						c.close();
					}
				} 
				catch (Exception e2) 
				{
					System.err.println("DTCARRERA: Error al cerrar conexión " + e2.getMessage());
					e2.printStackTrace();
				}
			}
			return eliminado;
		}
		
		public int getIdCarrera(String nombre)
		{
			int id = 0;
			String sql = "SELECT * FROM public.carrera where nombre = ? AND estado = 1";
			
			try 
			{
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ps.setString(1, nombre);
				rs = ps.executeQuery();
				if(rs.next())
				{
					id = rs.getInt("id_carrera");
				}
			} 
			catch (Exception e) 
			{
				System.err.println("DATOS: ERROR AL RECUPERAR EL ID DE LA CARRERA: " + e.getMessage());
				e.printStackTrace();
			}
			finally 
			{
				try 
				{
					if(rsCarrera != null)
					{
						rsCarrera.close();
					}
					if(c != null)
					{
						c.close();
					}
				} 
				catch (Exception e2) 
				{
					System.err.println("DTCarrera: Error al cerrar conexion " + e2.getMessage());
					e2.printStackTrace();
				}
			}
			return id;
		}
		
		
		
		public Carrera recuperarCarrera(int id_carrera)
		{
			Carrera Cr = new Carrera();
			String sql = "SELECT * FROM public.carrera where id_carrera = ?";
			
			try 
			{
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				ps.setInt(1, id_carrera);
				rs = ps.executeQuery();
				if(rs.next())
				{
					Cr.setId_carrera(id_carrera);
					Cr.setNombre(rs.getString("nombre"));
					Cr.setId_facultad(rs.getInt("id_facultad"));
					Cr.setEstado(rs.getInt("estado"));
					Cr.setId_departamento(rs.getInt("id_departamento"));
				}
			} 
			catch (Exception e) 
			{
				System.err.println("DATOS: error recuperarCarrera(): " + e.getMessage());
				e.printStackTrace();
			}
			finally 
			{
				try 
				{
					if(rsCarrera != null)
					{
						rsCarrera.close();
					}
					if(c != null)
					{
						c.close();
					}
				} 
				catch (Exception e2) 
				{
					System.err.println("DTCarrera: Error al cerrar conexion " + e2.getMessage());
					e2.printStackTrace();
				}
			}
			return Cr;
		}
		
		public ArrayList<Carrera> listarCarreras()
		{
			ArrayList<Carrera> listarCarreras = new ArrayList<Carrera>();
			
			String sql = "SELECT * FROM public.carrera WHERE estado <> 0";
			try 
			{
				c = PoolConexion.getConnection();
				ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
				rs = ps.executeQuery();
				while(rs.next())
				{
					Carrera cr = new Carrera();
					cr.setId_carrera(rs.getInt("id_carrera"));
					cr.setNombre(rs.getString("nombre"));
					cr.setId_facultad(rs.getInt("id_facultad"));
					cr.setEstado(rs.getInt("estado"));
					cr.setId_departamento(rs.getInt("id_departamento"));
					listarCarreras.add(cr);
					
				}
			} 
			catch (Exception e) 
			{
				System.err.println("DT Carrera: Error en listar Carreras " + e.getMessage());
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
					System.err.println("DT FACULTAD: Error en listar carreras " + e2.getMessage());
					e2.printStackTrace();
				}
			}
			
			return listarCarreras;
		}

}
