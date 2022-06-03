package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.TipoPermiso;

public class DTTipoPermiso {
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public ArrayList<TipoPermiso> listarTipoPermiso(){
		ArrayList<TipoPermiso> listaTPermiso = new ArrayList<>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from tipo_permiso;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();

			while(rs.next()){
				TipoPermiso tp = new TipoPermiso();

				tp.setId(rs.getInt("id_tipo_permiso"));
				tp.setNombre(rs.getString("nombre"));
				tp.setDescripcion(rs.getString("descripcion"));
				
				listaTPermiso.add(tp);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LOS TIPOS DE PERMISOS "+ e.getMessage());
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
		return listaTPermiso;
	}
}
