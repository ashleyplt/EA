package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vistas.VW_Usuarios;

public class DTVUsuarioSeguridad {
	// Atributos :D
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public ArrayList<VW_Usuarios> listarUsuarios() {
		ArrayList<VW_Usuarios> listaUsuarios = new ArrayList<VW_Usuarios>();

		String sql = "SELECT * FROM public.vw_usuario_seguridad;";
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while (rs.next()) {
				VW_Usuarios vUsuario = new VW_Usuarios();

				vUsuario.setNombre(rs.getString("nombre"));
				vUsuario.setApellido(rs.getString("apellido"));
				vUsuario.setUsuario(rs.getString("usuario"));
				vUsuario.setFecha_creacion(rs.getDate("fechacreación"));
				vUsuario.setUltima_fechaIngreso(rs.getDate("ultimoingreso"));
				vUsuario.setId_rol(rs.getInt("id_rol"));
				vUsuario.setRol(rs.getString("rol"));
				vUsuario.setEstado(rs.getInt("estado"));
				vUsuario.setImagen(rs.getString("imagen"));

				listaUsuarios.add(vUsuario);
			}
		} catch (Exception e) {
			System.err.println("Vista de Datos de Usuarios: Error en listar los usuarios " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();

				if (ps != null)
					ps.close();

				if (c != null)
					PoolConexion.closeConnection(c);
			} catch (Exception e2) {
				System.err.println("Vista de Datos de Usuarios: Error en listar los usuarios " + e2.getMessage());
				e2.printStackTrace();
			}
		}

		return listaUsuarios;
	}

	public ArrayList<VW_Usuarios> listarUsuariosenPerfil(int id) {

		int id_usuario = id;
		ArrayList<VW_Usuarios> listaUsuarios = new ArrayList<VW_Usuarios>();

		String sql = "SELECT * FROM public.vw_usuario_seguridad where id_usuario =" + id_usuario + ";";
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while (rs.next()) {
				VW_Usuarios vUsuario = new VW_Usuarios();

				vUsuario.setNombre(rs.getString("nombre"));
				vUsuario.setApellido(rs.getString("apellido"));
				vUsuario.setUsuario(rs.getString("usuario"));
				vUsuario.setFecha_creacion(rs.getDate("fechaCreacion"));
				vUsuario.setUltima_fechaIngreso(rs.getDate("ultimoIngreso"));
				vUsuario.setId_rol(rs.getInt("id_rol"));
				vUsuario.setRol(rs.getString("rol"));
				vUsuario.setEstado(rs.getInt("estado"));
				vUsuario.setImagen(rs.getString("imagen"));

				listaUsuarios.add(vUsuario);
			}
		} catch (Exception e) {
			System.err.println("Vista de Datos de Usuarios: Error en listar los usuarios " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();

				if (ps != null)
					ps.close();

				if (c != null)
					PoolConexion.closeConnection(c);
			} catch (Exception e2) {
				System.err.println("Vista de Datos de Usuarios: Error en listar los usuarios " + e2.getMessage());
				e2.printStackTrace();
			}
		}

		return listaUsuarios;
	}

	public VW_Usuarios getVistaDeUsuario(String usuario) {

		VW_Usuarios vUsuario = new VW_Usuarios();
		

		String sql = "SELECT * FROM public.vw_usuario_seguridad WHERE usuario LIKE ? AND id_rol = 1;";
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, usuario);
			rs = ps.executeQuery();
			if(rs.next()) {
				vUsuario.setNombre(rs.getString("nombre"));
				vUsuario.setApellido(rs.getString("apellido"));
				vUsuario.setUsuario(rs.getString("usuario"));
				vUsuario.setFecha_creacion(rs.getDate("fechacreacion"));
				vUsuario.setUltima_fechaIngreso(rs.getDate("ultimoingreso"));
				vUsuario.setId_rol(rs.getInt("id_rol"));
				vUsuario.setRol(rs.getString("rol"));
				vUsuario.setEstado(rs.getInt("estado"));
				vUsuario.setImagen(rs.getString("imagen"));

			}else {
				vUsuario = null;
			}
		} catch (Exception e) {
			String mensaje = e.getMessage();
			System.err.println("Vista de Datos de Usuarios: Error en listar los usuarios " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();

				if (ps != null)
					ps.close();

				if (c != null)
					PoolConexion.closeConnection(c);
			} catch (Exception e2) {
				System.err.println("Vista de Datos de Usuarios: Error en listar los usuarios " + e2.getMessage());
				e2.printStackTrace();
			}
		}

		return vUsuario;
	}
}
