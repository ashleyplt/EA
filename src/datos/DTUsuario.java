package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import entidades.PermisosAsignatura;
import entidades.Usuario;

public class DTUsuario {

	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsUsuario = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void llenarUsuario(Connection c) {

		try {
			ps = c.prepareStatement("select * from usuario", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsUsuario = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LOS USUARIOS " + e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<Usuario> listarUsuario() {
		ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from usuario", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();

			while (rs.next()) {
				Usuario usuario = new Usuario();

				usuario.setId(rs.getInt("id"));
				usuario.setUsuario(rs.getString("usuario"));
				usuario.setPwd(rs.getString("pwd"));
				usuario.setFecha_creacion(rs.getDate("fecha_creacion"));
				usuario.setUltima_fechaingreso(rs.getDate("ultima_fechaingreso"));
				usuario.setEstado(rs.getInt("estado"));

				listaUsuario.add(usuario);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LOS USUARIO " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (c != null) {
					PoolConexion.closeConnection(c);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return listaUsuario;
	}

	public boolean dtverificarLogin(String nombre, String password) {
		boolean existe = false;
		String SQL = ("SELECT * FROM public.usuario WHERE \"usuario\"=? AND pwd=? AND estado = 1");
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			ps.setString(1, nombre);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				existe = true;
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR AL VERIFICAR EL LOGIN " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (c != null) {
					PoolConexion.closeConnection(c);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return existe;
	}
	
	public boolean existeNombreUsuario(String usuario) {
		boolean existe = false;
		String SQL = ("SELECT * FROM usuario WHERE usuario = ?");
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			ps.setString(1, usuario);
			rs = ps.executeQuery();
			if (rs.next()) {
				existe = true;
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR AL VERIFICAR NOMBRE DE USUARIO" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (c != null) {
					PoolConexion.closeConnection(c);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return existe;
	}

	// Metodo para almacenar nuevo usuario
	public boolean guardarUsuario(Usuario u) {
		boolean guardado = false;
		Date date = new Date(Calendar.getInstance().getTime().getTime());

		try {
			c = PoolConexion.getConnection();
			this.llenarUsuario(c);
			rsUsuario.moveToInsertRow();
			rsUsuario.updateString("usuario", u.getUsuario());
			rsUsuario.updateString("pwd", u.getPwd());
			rsUsuario.updateDate("fecha_creacion", date);
			rsUsuario.updateInt("estado", 0);
			rsUsuario.insertRow();
			rsUsuario.moveToCurrentRow();
			guardado = true;
		} catch (Exception e) {
			System.err.println("ERROR AL GUARDAR USUARIO: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rsUsuario != null) {
					rsUsuario.close();
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

	public int buscaridUsuario(String usuario) {
		int id = 0;
		String SQL = ("SELECT id_usuario FROM usuario WHERE usuario = ?");
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			ps.setString(1, usuario);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id_usuario");
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR AL BUSCAR EL ID USUARIO " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (c != null) {
					PoolConexion.closeConnection(c);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;
	}
	
	public int obteneridUsuario(String correo) {
		int id = 0;
		String SQL = ("SELECT id_usuario FROM personal WHERE correo = ?");
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			ps.setString(1, correo);
			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id_usuario");
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR AL OBTENER EL ID USUARIO " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (c != null) {
					PoolConexion.closeConnection(c);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;
	}
	
	public int dtretornarid(String nombre, String password)
	{
		int id_usuario = 0;
		String SQL = ("SELECT * FROM public.usuario WHERE \"usuario\"=? AND pwd=?");
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			ps.setString(1, nombre);
			ps.setString(2, password);
			rs = ps.executeQuery();
 
			if(rs.next()){
				id_usuario = rs.getInt("id_usuario");
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
	
		return id_usuario;
	}
	public boolean modificarNombreUsuario(Usuario u)
	{
		boolean modificado = false;
		//String prueba = "MauACZino";
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarUsuario(c);
			rsUsuario.beforeFirst();
			while(rsUsuario.next())
			{
				if(rsUsuario.getInt("id_usuario") == u.getId()) 
				{						
					rsUsuario.updateString("usuario", u.getUsuario());
					rsUsuario.updateRow();
					modificado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTUsuario: Error al modificar la Actividad " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsUsuario != null)
				{
					rsUsuario.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTUsuario: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return modificado;
	}
	
	public boolean modificarPassword(int id_usuario, String password)
	{
		boolean modificado = false;
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarUsuario(c);
			rsUsuario.beforeFirst();
			while(rsUsuario.next())
			{
				if(rsUsuario.getInt("id_usuario") == id_usuario) 
				{		
					
					rsUsuario.updateString("pwd", password);
					rsUsuario.updateRow();
					modificado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTUsuario: Error al modificar la Actividad " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsUsuario != null)
				{
					rsUsuario.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTUsuario: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return modificado;
	}
	
	//Cambiar contraseña
		public boolean modificarPasswordUsuario(Usuario u)
		{
			boolean modificado = false;
			try 
			{
				c = PoolConexion.getConnection();
				this.llenarUsuario(c);
				rsUsuario.beforeFirst();
				while(rsUsuario.next())
				{
					if(rsUsuario.getInt("id_usuario") == u.getId()) 
					{						
						rsUsuario.updateString("pwd", u.getPwd());
						rsUsuario.updateRow();
						modificado = true;
						break;
					}	
				}
				
			} 
			catch (Exception e) 
			{
				System.err.println("DTUsuario: Error al modificar la Actividad " + e.getMessage());
				e.printStackTrace();
			}
			finally 
			{
				try 
				{
					if(rsUsuario != null)
					{
						rsUsuario.close();
					}
					if(c != null)
					{
						c.close();
					}
				} 
				catch (Exception e2) 
				{
					System.err.println("DTUsuario: Error al cerrar conexion " + e2.getMessage());
					e2.printStackTrace();
				}
			}
			return modificado;
		}
	
	public boolean verificarPwd(Usuario user) {
		boolean result = false;
		String SQL = ("SELECT * FROM public.usuario WHERE \"id_usuario\"=? AND pwd=?");
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getPwd());
			rs = ps.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR AL VERIFICAR EL Usuario " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (c != null) {
					PoolConexion.closeConnection(c);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean activarCuenta(int id) {
		 boolean actualizado = false;
		 try 
			{
				c = PoolConexion.getConnection();
				this.llenarUsuario(c);
				rsUsuario.beforeFirst();
				while(rsUsuario.next())
				{
					if(rsUsuario.getInt("id_usuario") == id) 
					{						
						rsUsuario.updateInt("estado", 1);
						rsUsuario.updateRow();
						actualizado = true;
						break;
					}	
				}
				
			} 
			catch (Exception e) 
			{
				System.err.println("DTUsuario: Error al modificar estado de usuario" + e.getMessage());
				e.printStackTrace();
			}
			finally 
			{
				try 
				{
					if(rsUsuario != null)
					{
						rsUsuario.close();
					}
					if(c != null)
					{
						c.close();
					}
				} 
				catch (Exception e2) 
				{
					System.err.println("DTUsuario: Error al cerrar conexion " + e2.getMessage());
					e2.printStackTrace();
				}
			}

			return actualizado;
		}
	
	public boolean modificarFechaIngreso(int id)
	{
		boolean modificado = false;
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarUsuario(c);
			rsUsuario.beforeFirst();
			while(rsUsuario.next())
			{
				if(rsUsuario.getInt("id_usuario") == id)
				{	
					rsUsuario.updateDate("ultima_fechaingreso", date);
					rsUsuario.updateRow();
					modificado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("ERROR AL MODIFICAR LA ÚLTIMA FECHA DE INGRESO DEL USUARIO: " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsUsuario != null)
				{
					rsUsuario.close();
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return modificado;
	}
	// Metodo para guardar la foto del Usuario
				public boolean guardarFotoUser(int idUser, String urlFoto)
				{	
					boolean actualizado = false;		
					try
					{
						c = PoolConexion.getConnection();
						this.llenarUsuario(c);	
						rsUsuario.beforeFirst();
						while(rsUsuario.next())
						{
							if(rsUsuario.getInt(1)==idUser)
							{							
								rsUsuario.updateString("imagen", urlFoto);
								rsUsuario.updateRow();
								actualizado = true;
								break;
							}
						}
					}
					catch (Exception e) 
					{
						System.err.println("ERROR AL GUARDAR FOTO "+e.getMessage());
						e.printStackTrace();
					}
					finally
					{
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
					return actualizado;
				}
	public String getImagenUsuario(int id) {
		String img = "";
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from usuario where id_usuario = ?;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				img = rs.getString("imagen");
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN BUSCAR URL DE LA IMAGEN USUARIO" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (c != null) {
					PoolConexion.closeConnection(c);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return img;
	}
}
