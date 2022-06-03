package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vistas.VW_Roles;

public class DTVRol {

	//Atributos :D
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsRol = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void llenarUsuarios() {
		try{
			ps = c.prepareStatement("SELECT * FROM public.vw_rol_seguridad", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsRol = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LOS ROLES: "+ e.getMessage());
			e.printStackTrace();
		}

	}

	public ArrayList<VW_Roles> listarRoles(){
		ArrayList<VW_Roles> listaRoles = new ArrayList<VW_Roles>();

		String sql = "SELECT * FROM public.vw_rol_seguridad;";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_Roles vRoles = new VW_Roles();

				vRoles.setNombre(rs.getString("Rol"));
				vRoles.setDescripcion(rs.getString("Descripción"));
				vRoles.setOpciones(rs.getString("Opciones"));
				vRoles.setEstado(rs.getInt("Estado"));


				listaRoles.add(vRoles);
			}
		} 
		catch (Exception e) 
		{
			System.err.println("Vista de Datos de Roles: Error en listar los roles " + e.getMessage());
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
				System.err.println("Vista de Datos de Roles: Error en listar los roles " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return listaRoles;
	}
}