package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Departamento;
import vistas.VW_Personal_Usuario;


public class DTDepartamento {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsDept = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public ArrayList<Departamento> listarDepartamentos()
	{
		ArrayList<Departamento> listd = new ArrayList<>();
		String SQL = ("SELECT * FROM departamento;");
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			rs = ps.executeQuery();
			while(rs.next()){
				Departamento d = new Departamento();
				d.setId(rs.getInt("id_departamento"));
				d.setNombre(rs.getString("nombre"));
				d.setDescripcion(rs.getString("descripcion"));
				d.setId_facultad(rs.getInt("id_facultad"));
				
				listd.add(d);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR AL LISTAR DEPARTAMENTOS "+ e.getMessage());
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
		return listd;
	}
	
	public int getIdDepartamento(String lugar) {
		int id = 0;
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from departamento where nombre like ?;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, lugar);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id_departamento");
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR EN BUSCAR ID DEPARTAMENTO" + e.getMessage());
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
