package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import vistas.VW_CoordinacionCarrera;

public class DTVCoordinacionCarrera {
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsCoordinacionCarrera = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarCoordinacionCarrera() {
		try{
			ps = c.prepareStatement("SELECT * FROM public.vw_coordinacion_carrera;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsCoordinacionCarrera = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR COORDINACION Y CARRERAS : "+ e.getMessage());
			e.printStackTrace();
		}

	}
	
	public ArrayList<VW_CoordinacionCarrera> listarCoordinacionCarreras(int vistaCompleta){
		ArrayList<VW_CoordinacionCarrera> listarCoordinacionCarreras = new ArrayList<VW_CoordinacionCarrera>();

		String sql = "";
		if(vistaCompleta == 1) {
			sql = "SELECT * FROM public.vw_coordinacion_carrera;";
		}else{
			sql = "SELECT * FROM public.vw_coordinacion_carrera WHERE estado <> 0;";
		}
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				
				VW_CoordinacionCarrera vCoordinacionCarrera = new VW_CoordinacionCarrera();
				
				vCoordinacionCarrera.setId_coordinacion(rs.getInt("id_coordinacion"));
				vCoordinacionCarrera.setCoordinacion(rs.getString("coordinacion"));
				vCoordinacionCarrera.setTelefono(rs.getString("telefono"));
				vCoordinacionCarrera.setCarrera(rs.getString("carrera"));
				vCoordinacionCarrera.setEstado(rs.getInt("estado"));
				
				listarCoordinacionCarreras.add(vCoordinacionCarrera);
				
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Coordinacion Carrera: Error en listar Coordinacion y Carrera " + e.getMessage());
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
				System.err.println("DTVCoordinacionCarreras: Error en listar coordinacion y carreras " + e2.getMessage());
				e2.printStackTrace();
			}
		}

		return listarCoordinacionCarreras;
	}
}
	


