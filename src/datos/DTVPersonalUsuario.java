package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vistas.VW_Personal_Usuario;

public class DTVPersonalUsuario {
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsUsuario = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void LlenarUsuario(Connection c){

		try{
			ps = c.prepareStatement("select * from vw_personal_usuario", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsUsuario = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR EL ID PERSONAL DEL USUARIO "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<VW_Personal_Usuario> listarIdPersonalUser()
	{
		ArrayList<VW_Personal_Usuario> listpu = new ArrayList<>();
		String SQL = ("SELECT * FROM vw_personal_usuario");
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			rs = ps.executeQuery();
			while(rs.next()){
				VW_Personal_Usuario pu = new VW_Personal_Usuario();
				pu.setId(rs.getInt("id"));
				pu.setUsuario(rs.getString("usuario"));
				listpu.add(pu);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR AL BUSCAR EL ID PERSONAL DEL USUARIO "+ e.getMessage());
			e.printStackTrace();
		}
		finally {
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
		return listpu;
	}
	
	public int buscaridPersonal(String usuario)
	{
		int id = 0;
		String SQL = ("SELECT * FROM vw_personal_usuario WHERE usuario = ?");
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			ps.setString(1, usuario); 
			rs = ps.executeQuery();
			if(rs.next()){
				id = rs.getInt("id");
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR AL BUSCAR EL ID PERSONAL DEL USUARIO "+ e.getMessage());
			e.printStackTrace();
		}
		finally {
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
		return id;
	}
	
	public String buscarNombrePersonal(String usuario)
	{
		String nombre = "";
		String SQL = ("SELECT * FROM vw_personal_usuario WHERE usuario = ?");
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			ps.setString(1, usuario); 
			rs = ps.executeQuery();
			if(rs.next()){
				nombre = rs.getString("personal");
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR AL BUSCAR EL ID PERSONAL DEL USUARIO "+ e.getMessage());
			e.printStackTrace();
		}
		finally {
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
		return nombre;
	}
	
}
