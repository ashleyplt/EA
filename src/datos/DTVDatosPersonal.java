package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vistas.VW_Datos_Personal;

public class DTVDatosPersonal {

	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public ArrayList<VW_Datos_Personal> listarDatosPersonal(){
		ArrayList<VW_Datos_Personal> listarDatosPersonal= new ArrayList<VW_Datos_Personal>();

		String sql = "SELECT * FROM public.vw_datos_personal;";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_Datos_Personal dp = new VW_Datos_Personal();
				dp.setId_personal(rs.getInt("id_personal"));
				dp.setNombre(rs.getString("nombre"));
				dp.setUsuario(rs.getString("usuario"));
				dp.setCorreo(rs.getString("correo"));
				dp.setTelefono(rs.getString("telefono"));
				dp.setImagen(rs.getString("imagen"));
				listarDatosPersonal.add(dp);
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DTVDatosPersonal: Error en listar datos " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rs != null)
					rs.close();

				if(ps != null)
					ps.close();

				if(c != null)
					PoolConexion.closeConnection(c);
			} 
			catch (Exception e2) 
			{
				System.err.println("DTVDatosPersonal: Error en listar datos " + e2.getMessage());
				e2.printStackTrace();
			}
		}

		return listarDatosPersonal;
	}
	
	public VW_Datos_Personal getDatosPersonal(String usuario){
		VW_Datos_Personal dp= new VW_Datos_Personal();

		String sql = "SELECT * FROM public.vw_datos_personal where usuario LIKE ?;";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setString(1, usuario);
			rs = ps.executeQuery();
			if(rs.next())
			{
				dp.setId_personal(rs.getInt("id_personal"));
				dp.setNombre(rs.getString("nombre"));
				dp.setUsuario(rs.getString("usuario"));
				dp.setCorreo(rs.getString("correo"));
				dp.setTelefono(rs.getString("telefono"));
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DTVDatosPersonal: Error en listar datos " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rs != null)
					rs.close();

				if(ps != null)
					ps.close();

				if(c != null)
					PoolConexion.closeConnection(c);
			} 
			catch (Exception e2) 
			{
				System.err.println("DTVDatosPersonal: ERROR EN BUSCAR LOS DATOS DE UN USUARIO " + e2.getMessage());
				e2.printStackTrace();
			}
		}

		return dp;
	}
}
