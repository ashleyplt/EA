  package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vistas.*;

public class DTVNotificacion {
	//Atributos :D
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsnotificacion_usuario = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void llenarUsuarios() {
		try{
			ps = c.prepareStatement("SELECT * FROM public.notificacion_usuario;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();// aqui iva el rsnotificacion
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR Las Notificaciones: "+ e.getMessage());
			e.printStackTrace();
		}

	}

	public ArrayList<VW_Notificacion> listarNotificaciones(){
		ArrayList<VW_Notificacion> listaNot = new ArrayList<VW_Notificacion>();

		String sql = "SELECT * FROM public.notificacion_usuario;";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_Notificacion vNot = new VW_Notificacion();
 
				vNot.setId_destinatario(rs.getInt("id_destinatario"));
				vNot.setDestinatario(rs.getString("destinatario"));
				vNot.setId_remitente(rs.getInt("id_remitente"));
				vNot.setRemitente(rs.getString("remitente"));
				vNot.setMensaje(rs.getString("mensaje"));
				vNot.setEstado(rs.getInt("estado")); 
				listaNot.add(vNot);
			}
		} 
		catch (Exception e) 
		{
			System.err.println("Vista de Datos de Usuarios: Error en listar las Notificaciones " + e.getMessage());
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
				System.err.println("Vista de Datos de Usuarios: Error en listar las Notificaciones " + e2.getMessage());
				e2.printStackTrace();
			}
		}

		return listaNot;
	}



}
