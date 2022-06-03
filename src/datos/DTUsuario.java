package datos;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;


import entidades.Usuario;

public class DTUsuario {

	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsUsuario = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void LlenarUsuario(Connection c){

		try{
			ps = c.prepareStatement("select * from usuario", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsUsuario = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LOS USUARIOS "+ e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<Usuario> listarUsuario(){
		ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from  usuario", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next()){
				Usuario usuario = new Usuario();
				
				usuario.setId(rs.getInt("id"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setPwd(rs.getString("pwd"));
				usuario.setFecha_creacion(rs.getDate("fecha_creacion"));
				usuario.setUltima_fechaingreso(rs.getDate("ultima_fechaingreso"));
				usuario.setEstado(rs.getInt("estado"));
				
				listaUsuario.add(usuario);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LOS USUARIO "+ e.getMessage());
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
		return listaUsuario;
	}
	public boolean dtverificarLogin(String nombre, String password)
	{
		boolean existe=false;
		String SQL = ("SELECT * FROM public.usuario WHERE \"usuario\"=? AND pwd=?");
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			ps.setString(1, nombre);
			ps.setString(2, password); 
			rs = ps.executeQuery();
			if(rs.next()){
				existe=true;
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR AL VERIFICAR EL LOGIN "+ e.getMessage());
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
	
		return existe;
	}
	
	//Metodo para almacenar nuevo usuario
			public boolean guardarUsuario(Usuario u){
				boolean guardado = false;
				Date date = new Date(Calendar.getInstance().getTime().getTime());
				
				try{
					c = PoolConexion.getConnection();
					this.LlenarUsuario(c);
					rsUsuario.moveToInsertRow();
					rsUsuario.updateString("usuario", u.getUsuario());
					rsUsuario.updateString("pwd", u.getPwd());
					rsUsuario.updateDate("fecha_creacion", date);
					rsUsuario.updateInt("estado", 1);
					rsUsuario.insertRow();
					rsUsuario.moveToCurrentRow();
					guardado = true;
				}
				catch (Exception e) {
					System.err.println("ERROR AL GUARDAR USUARIO: "+e.getMessage());
					e.printStackTrace();
				}
				finally{
					try {
						if(rsUsuario != null){
							rsUsuario.close();
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
		
			public int buscaridUsuario(String usuario)
			{
				int id = 0;
				String SQL = ("SELECT id_usuario FROM usuario WHERE usuario = ?");
				try{
					c = PoolConexion.getConnection();
					ps = c.prepareStatement(SQL);
					ps.setString(1, usuario); 
					rs = ps.executeQuery();
					if(rs.next()){
						id = rs.getInt("id_usuario");
					}
				}
				catch (Exception e){
					System.out.println("DATOS: ERROR AL BUSCAR EL ID USUARIO "+ e.getMessage());
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
}
