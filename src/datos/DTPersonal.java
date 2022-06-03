package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import entidades.Coordinacion;
import entidades.Personal;

public class DTPersonal {

	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsPersonal = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	// Metodo para llenar el RusultSet
	public void llenarPersonal(Connection c) {
		try {
			ps = c.prepareStatement("SELECT * FROM PERSONAL;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsPersonal = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR PERSONAL " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Metodo para almacenar nuevo personal
	public boolean guardarPersonal(Personal p) {
		boolean guardado = false;
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		String departamento = null;
		
		try {
			c = PoolConexion.getConnection();
			this.llenarPersonal(c);
			rsPersonal.moveToInsertRow();
			rsPersonal.updateString("nombre", p.getNombre());
			rsPersonal.updateString("apellido", p.getApellido());
			rsPersonal.updateString("correo", p.getCorreo());
			rsPersonal.updateString("telefono", p.getTelefono());
			rsPersonal.updateInt("estado", 1);
			rsPersonal.updateInt("id_usuario", p.getId_usuario());
			
			rsPersonal.insertRow();
			rsPersonal.moveToCurrentRow();
			guardado = true;
		} catch (Exception e) {
			System.err.println("ERROR AL GUARDAR PERSONAL: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rsPersonal != null) {
					rsPersonal.close();
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
	
	public boolean modificarPersonal(Personal p)
	{
		boolean modificado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarPersonal(c);
			rsPersonal.beforeFirst();
			while(rsPersonal.next())
			{
				if(rsPersonal.getInt("id_personal") == p.getId_personal())
				{	
					rsPersonal.updateString("nombre", p.getNombre());
					rsPersonal.updateString("apellido", p.getApellido());
					rsPersonal.updateString("correo", p.getCorreo());
					rsPersonal.updateString("telefono", p.getTelefono());
					rsPersonal.updateInt("estado", 1);
					rsPersonal.updateInt("id_usuario", p.getId_usuario());
					
					rsPersonal.updateRow();
					modificado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTPersonal: Error al modificar el personal " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsPersonal != null)
				{
					rsPersonal.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTPersonal: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return modificado;
	}
	
	public int getIdPersonal(String correo) {
		int id = 0;
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from personal where correo like ?;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, correo);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id_personal");
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN BUSCAR ID PERSONAL" + e.getMessage());
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
		return id;
	}
	
	public String getCorreo(int id) {
		String correo = "";
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select correo from personal where id_personal = ?;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				correo = rs.getString("correo");
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN BUSCAR CORREO PERSONAL " + e.getMessage());
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
		return correo;
	}
	
	public Personal getPersona(int idpersonal) {
		Personal p = new Personal();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from personal where id_personal = ?;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idpersonal);
			rs = ps.executeQuery();
			while (rs.next()) {
				p.setApellido(rs.getString("apellido"));
				p.setCorreo(rs.getString("correo"));
				p.setEstado(rs.getInt("estado"));
				p.setId_usuario(rs.getInt("id_usuario"));
				p.setNombre(rs.getString("nombre"));
				p.setTelefono(rs.getString("telefono"));
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN BUSCAR PERSONAL POR SU ID" + e.getMessage());
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
		return p;
	}

	
	public boolean existeCorreo(String correo) {
		boolean existe = false;
		String SQL = ("SELECT * FROM personal WHERE correo = ?");
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			ps.setString(1, correo);
			rs = ps.executeQuery();
			if (rs.next()) {
				existe = true;
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR AL VERIFICAR CORREO" + e.getMessage());
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
	
	public int getIdUsuario(int idpersonal) {
		int id = 0;
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from personal where id_personal = ?;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idpersonal);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id_usuario");
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN BUSCAR ID USUARIO" + e.getMessage());
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
		return id;
	}
}
