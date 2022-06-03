   package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.*;

public class DTAgenda {
	//Atributos :D
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsAgenda = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void llenarAgenda(Connection c) {
		try{
			ps = c.prepareStatement("SELECT * FROM public.detalle_syllabus;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsAgenda = ps.executeQuery(); 
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR Las agendas: "+ e.getMessage());
			e.printStackTrace();
		}

	}

	public ArrayList<Agenda> ListarAgenda( String nombre){
		String Nombre = nombre; 
		ArrayList<Agenda> listaAg = new ArrayList<Agenda>();

		String sql = "SELECT * FROM public.detalle_syllabus  WHERE edicion ='" +Nombre +"';";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Agenda a = new Agenda();
 
				

				a.setEdicion(rs.getString("edicion"));
				a.setEstado(rs.getInt("estado"));
				a.setFecha_final(rs.getDate("fecha_final"));
				a.setFecha_inicio(rs.getDate("fecha_inicio"));  
				a.setNum_agenda(rs.getInt("num_agenda"));
				a.setId_syllabus(rs.getInt("id_syllabus"));
				a.setId_det_syllabus(rs.getInt("id_det_syllabus"));
				
				listaAg.add(a);
				
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
				System.err.println("Archivos: Error en listar las agendas " + e2.getMessage());
				e2.printStackTrace();
			}
		}

		return listaAg;
	}



	//Metodo para almacenar un archivo
	public boolean gardarAgenda(Agenda ag){
				boolean guardado = false;				
				try{
					c = PoolConexion.getConnection();
					this.llenarAgenda(c);
					rsAgenda.moveToInsertRow();
					rsAgenda.updateString("edicion", ag.getEdicion()); 
					rsAgenda.updateInt("estado", ag.getEstado()); 
					rsAgenda.updateString("fecha_final", ag.getEdicion());
					rsAgenda.updateString("fecha_inicio", ag.getEdicion());
					rsAgenda.updateInt("num_agenda", ag.getNum_agenda());
					rsAgenda.updateInt("id_syllabus", ag.getId_syllabus());
					rsAgenda.insertRow();
					rsAgenda.moveToCurrentRow();
					guardado = true;
					
				}
				catch (Exception e) {
					System.err.println("ERROR AL GUARDAR EL ARCHIVO "+e.getMessage());
					e.printStackTrace();
				}
				finally{
					try {
						if(rsAgenda != null){
							rsAgenda.close();
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
					this.llenarAgenda(c);
					rsAgenda.beforeFirst();
					while(rsAgenda.next())
					{
						if(rsAgenda.getInt("id_archivo") == a.getId_archivo()) 
						{						
							rsAgenda.updateString("nombre", a.getNombre()); 
					
							rsAgenda.updateRow();
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
						if(rsAgenda != null)
						{
							rsAgenda.close();
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
					this.llenarAgenda(c);
					rsAgenda.beforeFirst();
					
					while(rsAgenda.next()) {
						if(rsAgenda.getInt("Id_archivo") == id){
							rsAgenda.updateInt("estado", 3);
							rsAgenda.updateRow();
							
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
						if(rsAgenda != null)
						{
							rsAgenda.close();
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
