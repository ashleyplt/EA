package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import entidades.Asignatura;
import entidades.EdicionAsignatura;

public class DTEdicionAsignatura {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsEdicion = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void llenarEdicionAsignatura(Connection c) {
		try {
			ps = c.prepareStatement("select * from edicion_de_asignatura;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsEdicion = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR LAS EDICIONES DE ASIGNATURA" + e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean existeEdicionAsignatura(String nombre) {
		boolean existe = false;
		String SQL = ("SELECT * FROM edicion_de_asignatura WHERE nombre = ?");
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			ps.setString(1, nombre);
			rs = ps.executeQuery();
			if (rs.next()) {
				existe = true;
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR AL VERIFICAR LA EDICIÓN DE ASIGNATURA " + e.getMessage());
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

	// Metodo para almacenar nuevo expediente
	public boolean guardarEdicion(EdicionAsignatura ea) {
		boolean guardado = false;
		Date date = new Date(Calendar.getInstance().getTime().getTime());

		try {
			c = PoolConexion.getConnection();
			this.llenarEdicionAsignatura(c);
			rsEdicion.moveToInsertRow();
			rsEdicion.updateString("Nombre", ea.getNombre());
			rsEdicion.updateDate("fecha_inicio", ea.getFecha_inicio());
			rsEdicion.updateDate("fecha_cierre", ea.getFecha_cierre());
			rsEdicion.insertRow();
			rsEdicion.moveToCurrentRow();
			guardado = true;
		} catch (Exception e) {
			System.err.println("ERROR AL GUARDAR EXPEDIENTE: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rsEdicion != null) {
					rsEdicion.close();
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
}
