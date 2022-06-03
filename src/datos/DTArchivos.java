   package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.*;

public class DTArchivos {
	//Atributos :D
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsArchivos = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void llenarArchivo(Connection c) {
		try{
			ps = c.prepareStatement("SELECT * FROM public.archivo;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsArchivos = ps.executeQuery(); 
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LOS ARCHIVOS: "+ e.getMessage());
			e.printStackTrace();
		}

	}

	public ArrayList<Archivo> listarArchivos( int id, String nombre){
		String Nombre = nombre;
		int ID = id;
		ArrayList<Archivo> listaAr = new ArrayList<Archivo>();

		String sql = "SELECT * FROM public.archivo  WHERE (estado <> 3) and (id_carpeta =" + ID +") and (nombre_edicion ='" +Nombre +"');";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Archivo a = new Archivo();
 
				

				a.setNombre(rs.getString("Nombre"));
				a.setNombre_archivo(rs.getString("Nombre_archivo"));
				a.setTipo(rs.getString("tipo"));
				a.setId_archivo(rs.getInt("Id_archivo"));
				a.setId_carpeta(rs.getInt("Id_carpeta"));
				a.setEstado(rs.getInt("estado"));
				
				listaAr.add(a);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("Archivos: Error en listar los archivos " + e.getMessage());
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
				System.err.println("Archivos: Error en listar los archivos " + e2.getMessage());
				e2.printStackTrace();
			}
		}

		return listaAr;
	}



	//Metodo para almacenar un archivo
	public boolean guardarArchivo(Archivo ar){
				boolean guardado = false;				
				try{
					c = PoolConexion.getConnection();
					this.llenarArchivo(c);
					rsArchivos.moveToInsertRow();
					rsArchivos.updateString("nombre", ar.getNombre()); 
					rsArchivos.updateString("nombre_archivo", ar.getNombre_archivo());  
					rsArchivos.updateString("tipo", ar.getTipo());  
					rsArchivos.updateInt("id_carpeta", ar.getId_carpeta()); 
					rsArchivos.updateString("nombre_edicion", ar.getNombre_edicion());  
					rsArchivos.updateInt("estado", ar.getEstado()); 
					rsArchivos.insertRow();
					rsArchivos.moveToCurrentRow();
					guardado = true;
					
				}
				catch (Exception e) {
					System.err.println("ERROR AL GUARDAR EL ARCHIVO "+e.getMessage());
					e.printStackTrace();
				}
				finally{
					try {
						if(rsArchivos != null){
							rsArchivos.close();
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
					this.llenarArchivo(c);
					rsArchivos.beforeFirst();
					while(rsArchivos.next())
					{
						if(rsArchivos.getInt("id_archivo") == a.getId_archivo()) 
						{						
							rsArchivos.updateString("nombre", a.getNombre()); 
					
							rsArchivos.updateRow();
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
						if(rsArchivos != null)
						{
							rsArchivos.close();
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
					this.llenarArchivo(c);
					rsArchivos.beforeFirst();
					
					while(rsArchivos.next()) {
						if(rsArchivos.getInt("Id_archivo") == id){
							rsArchivos.updateInt("estado", 3);
							rsArchivos.updateRow();
							
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
						if(rsArchivos != null)
						{
							rsArchivos.close();
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
