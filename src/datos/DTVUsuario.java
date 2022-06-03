package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vistas.VW_Usuario;


public class DTVUsuario {
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsUsuario = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	// Metodo para llenar el ResultSet
	public void llenarVCarrera(Connection c){
		
		try{
			ps = c.prepareStatement("Select * from vw_usuario", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsUsuario = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR USUARIO "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	//Metodo para visualizar usuarios registrados y activos
	public ArrayList<VW_Usuario> listarUsuarios(){
		ArrayList<VW_Usuario> listarUsuarios = new ArrayList<VW_Usuario>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from vw_usuario", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next()){
				VW_Usuario usuario = new VW_Usuario();
				usuario.setId(rs.getInt("id")); 
				usuario.setNombre(rs.getString("usuario")); 
				usuario.setFecha_creacion(rs.getDate("fecha_creacion"));
				usuario.setUltima_fechaingreso(rs.getDate("ultima_fechaingreso")); 
				listarUsuarios.add(usuario);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR USUARIOS "+ e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				if(rs != null){
					rs.close();
				}
				if(ps != null){
					ps.close();
				}
				if(c != null){
					PoolConexion.closeConnection(c);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return listarUsuarios;
	}
	
	
			
			
}
