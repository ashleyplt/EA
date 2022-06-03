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
	private ResultSet rsUsuario = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void LlenarUsuario(Connection c){

		try{
			ps = c.prepareStatement("select * from vw_cargo_personal", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsUsuario = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LA INFORMACIÓN DEL PERSONAL "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public ArrayList<VW_Cargo_Personal> buscarCargoPersonal(int id){
		ArrayList<VW_Cargo_Personal> listaInfo = new ArrayList<>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM vw_cargo_personal WHERE id_personal = "+id+";", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			
			while(rs.next()){
				VW_Cargo_Personal info = new VW_Cargo_Personal();
				 
				info.setId_personal(rs.getInt("id_personal"));
				info.setNombre(rs.getString("nombre")); 
				info.setApellido(rs.getString("apellido"));
				info.setCorreo(rs.getString("correo"));
				info.setTelefono(rs.getString("telefono"));
				info.setLugar(rs.getString("lugar"));
				info.setCargo(rs.getString("cargo"));
				
				listaInfo.add(info);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LA INFORMACION DE UN PERSONAL  "+ e.getMessage());
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
		return listaInfo;
	}
}
