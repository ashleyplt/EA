package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.ExpedienteDocente;

public class DTExpedienteDocente {
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsExpDocente = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void llenarExpedienteDocente(Connection c){

		try{
			ps = c.prepareStatement("select * from expediente_docente", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsExpDocente = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LOS DOCENTES DE LOS EXPEDIENTES "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean guardarAsignatura(ExpedienteDocente ed) {
		boolean guardado = false;
		try {
			c = PoolConexion.getConnection();
			this.llenarExpedienteDocente(c);
			rsExpDocente.moveToInsertRow();
			rsExpDocente.updateInt("estado", 1);
			rsExpDocente.updateInt("id_personal", ed.getId_personal());
			rsExpDocente.updateInt("id_expediente_asignatura", ed.getId_expediente());
			rsExpDocente.insertRow();
			rsExpDocente.moveToCurrentRow();
			guardado = true;
		} catch (Exception e) {
			System.err.println("ERROR AL GUARDAR EXPEDIENTE: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rsExpDocente != null) {
					rsExpDocente.close();
				}
				if (c != null) {
					PoolConexion.closeConnection(c);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return guardado;
	}
	
	public boolean quitarDocente(int id)
	{
		boolean modificado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarExpedienteDocente(c);
			rsExpDocente.beforeFirst();
			while(rsExpDocente.next())
			{
				if(rsExpDocente.getInt("id_expediente_docente") == id)
				{	
					rsExpDocente.updateInt("estado", 0);
					rsExpDocente.updateRow();
					modificado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("ERROR AL QUITAR EL DOCENTE DEL CURSO DE ASIGNATURA: " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsExpDocente != null)
				{
					rsExpDocente.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return modificado;
	}
}
