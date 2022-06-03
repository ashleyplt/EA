package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import entidades.CargoPersonal;


public class DTCargoPersonal {

	PoolConexion cp = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsCp = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void llenarCargoPersonal(Connection c) {
		try {
			ps = c.prepareStatement("select * from cargo_personal;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsCp = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR CARGO PERSONAL" + e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<CargoPersonal> listarCargoPersonal() {
		ArrayList<CargoPersonal> listarCargoPersonal = new ArrayList<>();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from cargo_personal;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while (rs.next()) {
				CargoPersonal cp = new CargoPersonal();

				cp.setId_cargo_personal(rs.getInt("id_usuario_rol"));
				cp.setEstado(rs.getInt("estado"));
				cp.setId_cargo(rs.getInt("id_cargo"));
				cp.setId_personal(rs.getInt("id_personal"));
				listarCargoPersonal.add(cp);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR CARGOS PERSONALES" + e.getMessage());
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
		return listarCargoPersonal;
	}

	// Metodo para almacenar nuevo personal
	public boolean guardarCargoPersonal(CargoPersonal cp) {
		boolean guardado = false;

		try {
			c = PoolConexion.getConnection();
			this.llenarCargoPersonal(c);
			rsCp.moveToInsertRow();
			rsCp.updateInt("estado", cp.getEstado());
			rsCp.updateInt("id_cargo", cp.getId_cargo());
			rsCp.updateInt("id_personal", cp.getId_personal());
			rsCp.insertRow();
			rsCp.moveToCurrentRow();
			guardado = true;
		} catch (Exception e) {
			System.err.println("ERROR AL GUARDAR EXPEDIENTE: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rsCp != null) {
					rsCp.close();
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

}
