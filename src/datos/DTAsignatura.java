package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import entidades.Asignatura;
import entidades.Cargo;
import entidades.EdicionAsignatura;

public class DTAsignatura {
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsAsignatura = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void llenarAsignatura(Connection c){

		try{
			ps = c.prepareStatement("select * from asignatura", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsAsignatura = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LAS ASIGNATURAS "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<Asignatura> listarAsignaturas() {
		ArrayList<Asignatura> listarAsignatura = new ArrayList<>();
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from asignatura;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while (rs.next()) {
				Asignatura asignatura = new Asignatura();
				
				asignatura.setId(rs.getInt("id_asignatura"));
				asignatura.setNombre(rs.getString("nombre"));
				asignatura.setCodigo(rs.getString("cod_asignatura"));
				asignatura.setDescripcion(rs.getString("descripcion"));
				asignatura.setFecha_creacion(rs.getDate("fecha_creacion"));
				asignatura.setUrl_expediente(rs.getString("url_expediente"));
				asignatura.setEstado(rs.getInt("estado"));
				
				listarAsignatura.add(asignatura);

			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN LISTAR ASIGNATURAS" + e.getMessage());
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
		return listarAsignatura;
	}

	
	public boolean buscarAsignatura(String codigo)
	{
		boolean existe = false;
		
		String SQL = ("SELECT * FROM asignatura WHERE cod_asignatura = ?");
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			ps.setString(1, codigo); 
			rs = ps.executeQuery();
			if(rs.next()){
				existe=true;
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR AL BUSCAR ASIGNATURA "+ e.getMessage());
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
	
	public boolean existeAsignatura(String nombre, String codigo)
	{
		boolean existe = false;
		
		String SQL = ("SELECT * FROM asignatura WHERE nombre LIKE ? OR cod_asignatura LIKE ?;");
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL); 
			ps.setString(1, nombre); 
			ps.setString(2, codigo);
			rs = ps.executeQuery();
			while(rs.next()){
				existe=true;
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR AL VERIFICAR SI EXISTE ASIGNATURA "+ e.getMessage());
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
	
	public boolean guardarAsignatura(Asignatura a) {
		boolean guardado = false;
		Date date = new Date(Calendar.getInstance().getTime().getTime());

		try {
			c = PoolConexion.getConnection();
			this.llenarAsignatura(c);
			rsAsignatura.moveToInsertRow();
			rsAsignatura.updateString("nombre", a.getNombre());
			rsAsignatura.updateString("cod_asignatura", a.getCodigo());
			rsAsignatura.updateString("descripcion", a.getDescripcion());
			rsAsignatura.updateDate("fecha_creacion", a.getFecha_creacion());
			rsAsignatura.updateString("url_expediente", a.getUrl_expediente());
			rsAsignatura.updateInt("estado", a.getEstado());
			rsAsignatura.insertRow();
			rsAsignatura.moveToCurrentRow();
			guardado = true;
		} catch (Exception e) {
			System.err.println("ERROR AL GUARDAR EXPEDIENTE: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rsAsignatura != null) {
					rsAsignatura.close();
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
