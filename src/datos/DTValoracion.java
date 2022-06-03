   package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.*;

public class DTValoracion {
	//Atributos :D
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsValoracion = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void llenarValoracion(Connection c) {
		try{
			ps = c.prepareStatement("SELECT * FROM public.valoracion_docente;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsValoracion = ps.executeQuery(); 
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LOS ARCHIVOS: "+ e.getMessage());
			e.printStackTrace();
		}

	}

	public ArrayList<Valoracion> listarValoracion(  String periodo){
		String Periodo = periodo;
		ArrayList<Valoracion> listaValoracion = new ArrayList<Valoracion>();

		String sql = "SELECT * FROM public.valoracion_docente WHERE periodo ='" + Periodo+ "';";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Valoracion va = new Valoracion();
 

				va.setId_valoracion(rs.getInt("id_valoracion"));
				

				va.setPeriodo(rs.getString("periodo"));
				va.setNombre(rs.getString("nombre"));
				va.setAsignatura(rs.getString("asignatura"));
				va.setSeccion(rs.getString("seccion"));
				
				

				va.setTotal(rs.getInt("total")); 
				va.setTotalr(rs.getInt("totalr")); 
				va.setTotala(rs.getInt("totala"));  
				va.setSdc(rs.getInt("sdc"));
				

				va.setValoracion(rs.getString("valoracion"));
				va.setValoracionC(rs.getString("valoracionc"));
				va.setValoracionCon(rs.getString("valoracioncon"));
				
				va.setValoracionMet(rs.getString("valoracionmet"));
				va.setValoracionT(rs.getString("valoraciont"));
				va.setValoracionP(rs.getString("valoracionp"));
				va.setOtros(rs.getString("otros"));
				
				listaValoracion.add(va);
				
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

		return listaValoracion;
	}



	//Metodo para almacenar un archivo
	public boolean guardarValoracion(Valoracion va){
				boolean guardado = false;				
				try{
					c = PoolConexion.getConnection();
					this.llenarValoracion(c);
					rsValoracion.moveToInsertRow();  
				//	rsValoracion.updateString("nombre_archivo", va.getNombre_archivo());   
					
					rsValoracion.updateString("periodo", va.getPeriodo()); 
					rsValoracion.updateString("nombre", va.getNombre()); 
					rsValoracion.updateString("asignatura", va.getAsignatura()); 
					rsValoracion.updateString("seccion", va.getSeccion()); 

					rsValoracion.updateInt("total", va.getTotal());
					rsValoracion.updateInt("totalr", va.getTotalr());
					rsValoracion.updateInt("totala", va.getTotala());
					rsValoracion.updateInt("sdc", va.getSdc());
					

					rsValoracion.updateString("valoracion", va.getValoracion());
					rsValoracion.updateString("valoracionc", va.getValoracionC()); 
					rsValoracion.updateString("valoracioncon", va.getValoracionCon());

					rsValoracion.updateString("valoracionmet", va.getValoracionMet());
					rsValoracion.updateString("valoraciont", va.getValoracionT());
					rsValoracion.updateString("valoracionp", va.getValoracionP());
					rsValoracion.updateString("otros", va.getOtros());
					
					rsValoracion.insertRow();
					rsValoracion.moveToCurrentRow();
					guardado = true;
					
				}
				catch (Exception e) {
					System.err.println("ERROR AL GUARDAR LA VALORACION "+e.getMessage());
					e.printStackTrace();
				}
				finally{
					try {
						if(rsValoracion != null){
							rsValoracion.close();
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
			
			//Método para editar archivos
			public boolean modificarActividad(Archivo a)
			{
				boolean modificado = false;
				try 
				{
					c = PoolConexion.getConnection();
					this.llenarValoracion(c);
					rsValoracion.beforeFirst();
					while(rsValoracion.next())
					{
						if(rsValoracion.getInt("id_archivo") == a.getId_archivo()) 
						{						
							rsValoracion.updateString("nombre", a.getNombre()); 
					
							rsValoracion.updateRow();
							modificado = true;
							break;
						}	
					}
					
				} 
				catch (Exception e) 
				{
					System.err.println("DTActividades: Error al modificar el archivo " + e.getMessage());
					e.printStackTrace();
				}
				finally 
				{
					try 
					{
						if(rsValoracion != null)
						{
							rsValoracion.close();
						}
						if(c != null)
						{
							c.close();
						}
					} 
					catch (Exception e2) 
					{
						System.err.println("DTArchivo: Error al cerrar conexion " + e2.getMessage());
						e2.printStackTrace();
					}
				}
				return modificado;
			}

			public boolean eliminarArchivo(int estado, int id) {
				// TODO Auto-generated method stub
				boolean deletArachivo = false;
				
				try {
					c = PoolConexion.getConnection();
					this.llenarValoracion(c);
					rsValoracion.beforeFirst();
					
					while(rsValoracion.next()) {
						if(rsValoracion.getInt("Id_archivo") == id){
							rsValoracion.updateInt("estado", 3);
							rsValoracion.updateRow();
							
							deletArachivo = true;
							break;
						}
					}
				}catch(SQLException e){
					System.err.println("DATOS: error eliminar el archivo: " + e.getMessage());
					e.printStackTrace();
					
				}finally {
					try 
					{
						if(rsValoracion != null)
						{
							rsValoracion.close();
						}
						if(c != null)
						{
							c.close();
						}
					} 
					catch (Exception e2) 
					{
						System.err.println("DTArchivo: Error al cerrar conexion " + e2.getMessage());
						e2.printStackTrace();
					}
				}
				return deletArachivo;
			}
			
}
