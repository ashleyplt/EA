package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vistas.VW_Cargo_Personal;

public class DTVCargoPersonal {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	// permisos-expediente.jsp para select.
	public ArrayList<VW_Cargo_Personal> listarParaPermisos() {
		ArrayList<VW_Cargo_Personal> listaInfo = new ArrayList<>();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM vw_cargo_personal WHERE estado = 1;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);

			rs = ps.executeQuery();

			while (rs.next()) {
				VW_Cargo_Personal info = new VW_Cargo_Personal();

				info.setId_personal(rs.getInt("id_personal"));
				info.setNombre(rs.getString("nombre"));
				info.setApellido(rs.getString("apellido"));
				info.setCorreo(rs.getString("correo"));
				info.setTelefono(rs.getString("telefono"));
				info.setCoordinacion(rs.getString("coordinacion"));
				info.setCarrera(rs.getString("carrera"));
				info.setId_cargo(rs.getInt("id_cargo"));
				info.setCargo(rs.getString("cargo"));
				info.setEstado(rs.getInt("estado"));

				listaInfo.add(info);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LA INFORMACIÓN DE UN PERSONAL  " + e.getMessage());
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
		return listaInfo;
	}

	// permisos-expediente.jsp para select.
	public ArrayList<VW_Cargo_Personal> listarParaDocenteExpediente() {
		ArrayList<VW_Cargo_Personal> listaInfo = new ArrayList<>();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM vw_cargo_personal WHERE id_cargo = 1 AND estado = 1;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);

			rs = ps.executeQuery();

			while (rs.next()) {
				VW_Cargo_Personal info = new VW_Cargo_Personal();

				info.setId_personal(rs.getInt("id_personal"));
				info.setNombre(rs.getString("nombre"));
				info.setApellido(rs.getString("apellido"));
				info.setCorreo(rs.getString("correo"));
				info.setTelefono(rs.getString("telefono"));
				info.setCoordinacion(rs.getString("coordinacion"));
				info.setCarrera(rs.getString("carrera"));
				info.setId_cargo(rs.getInt("id_cargo"));
				info.setCargo(rs.getString("cargo"));
				info.setEstado(rs.getInt("estado"));

				listaInfo.add(info);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LA INFORMACIÓN DE UN PERSONAL  " + e.getMessage());
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
		return listaInfo;
	}

	// SLajaxFiltroDocenteExp
	public ArrayList<VW_Cargo_Personal> filtrarPersonalExpedienteDoc(String coordinacion) {
		ArrayList<VW_Cargo_Personal> listaInfo = new ArrayList<>();

		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM vw_cargo_personal WHERE coordinacion LIKE ? AND estado = 1;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, coordinacion);
			rs = ps.executeQuery();

			while (rs.next()) {
				VW_Cargo_Personal info = new VW_Cargo_Personal();

				info.setId_personal(rs.getInt("id_personal"));
				info.setNombre(rs.getString("nombre"));
				info.setApellido(rs.getString("apellido"));
				info.setCorreo(rs.getString("correo"));
				info.setTelefono(rs.getString("telefono"));
				info.setCoordinacion(rs.getString("coordinacion"));
				info.setCarrera(rs.getString("carrera"));
				info.setCargo(rs.getString("cargo"));
				info.setEstado(rs.getInt("estado"));

				listaInfo.add(info);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN FILTRAR LA INFORMACIÓN DE UN PERSONAL " + e.getMessage());
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
		return listaInfo;
	}

	public ArrayList<VW_Cargo_Personal> filtrarPersonalPermiso(String lugar, String cargo) {
		ArrayList<VW_Cargo_Personal> listaInfo = new ArrayList<>();
		String condicion = "";
		String opcion = " AND ";

		if (!cargo.equals(""))
			condicion += "cargo LIKE '" + cargo + "'";
		if (!cargo.equals("") && !lugar.equals(""))
			condicion += opcion;
		if (!lugar.equals(""))
			condicion += "coordinacion LIKE '" + lugar + "'";
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM vw_cargo_personal WHERE " + condicion + " AND estado = 1;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();

			while (rs.next()) {
				VW_Cargo_Personal info = new VW_Cargo_Personal();

				info.setId_personal(rs.getInt("id_personal"));
				info.setNombre(rs.getString("nombre"));
				info.setApellido(rs.getString("apellido"));
				info.setCorreo(rs.getString("correo"));
				info.setTelefono(rs.getString("telefono"));
				info.setCoordinacion(rs.getString("coordinacion"));
				info.setCarrera(rs.getString("carrera"));
				info.setCargo(rs.getString("cargo"));
				info.setEstado(rs.getInt("estado"));

				listaInfo.add(info);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN FILTRAR LA INFORMACION DE UN PERSONAL  " + e.getMessage());
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
		return listaInfo;
	}

	public ArrayList<VW_Cargo_Personal> buscarCargoPersonal(int id) {
		ArrayList<VW_Cargo_Personal> listaInfo = new ArrayList<>();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM vw_cargo_personal WHERE id_personal = " + id + ";",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();

			while (rs.next()) {
				VW_Cargo_Personal info = new VW_Cargo_Personal();

				info.setId_personal(rs.getInt("id_personal"));
				info.setNombre(rs.getString("nombre"));
				info.setApellido(rs.getString("apellido"));
				info.setCorreo(rs.getString("correo"));
				info.setTelefono(rs.getString("telefono"));
				info.setCoordinacion(rs.getString("coordinacion"));
				info.setCarrera(rs.getString("carrera"));
				info.setCargo(rs.getString("cargo"));
				info.setEstado(rs.getInt("estado"));

				listaInfo.add(info);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LA INFORMACION DE UN PERSONAL  " + e.getMessage());
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
		return listaInfo;
	}
	
	public VW_Cargo_Personal getCoordinadorCarrera(String carrera) {
		VW_Cargo_Personal info = new VW_Cargo_Personal();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM vw_cargo_personal WHERE carrera LIKE ? AND id_cargo = 2 AND estado = 1;",
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, carrera);
			rs = ps.executeQuery();

			while (rs.next()) {
				info.setId_personal(rs.getInt("id_personal"));
				info.setNombre(rs.getString("nombre"));
				info.setApellido(rs.getString("apellido"));
				info.setCorreo(rs.getString("correo"));
				info.setTelefono(rs.getString("telefono"));
				info.setCoordinacion(rs.getString("coordinacion"));
				info.setCarrera(rs.getString("carrera"));
				info.setId_cargo(rs.getInt("id_cargo"));
				info.setCargo(rs.getString("cargo"));
				info.setEstado(rs.getInt("estado"));

			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LA INFORMACION DE UN PERSONAL  " + e.getMessage());
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
		return info;
	}
}
