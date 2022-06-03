package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vistas.VW_DepartamentoFacultad;

public class DTVDepartamentoFacultad {
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsDepartamentoFacultad = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarDepartamentoFacultad() {
		try{
			ps = c.prepareStatement("SELECT * FROM public.vw_departamento_facultad;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsDepartamentoFacultad = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR DEPARTAMENTOS Y FACULTADES : "+ e.getMessage());
			e.printStackTrace();
		}

	}
	
	public ArrayList<VW_DepartamentoFacultad> listarDepartamentoFacultades(){
		ArrayList<VW_DepartamentoFacultad> listarDepartamentosFacultades = new ArrayList<VW_DepartamentoFacultad>();

		String sql = "SELECT * FROM public.vw_departamento_facultad;";
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			while(rs.next())
			{
				VW_DepartamentoFacultad vDepartamentoFacultad = new VW_DepartamentoFacultad();
				
				vDepartamentoFacultad.setId_departamento(rs.getInt("id_departamento"));
				vDepartamentoFacultad.setDepartamento(rs.getString("departamento"));
				vDepartamentoFacultad.setFacultad(rs.getString("facultad"));
				vDepartamentoFacultad.setDescripcion(rs.getString("descripcion"));
				
				listarDepartamentosFacultades.add(vDepartamentoFacultad);
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DT Departamento: Error en listar Departamentos y facultades " + e.getMessage());
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
				System.err.println("DTVDepartamentoFacultad: Error en listar Departamentos y facultades " + e2.getMessage());
				e2.printStackTrace();
			}
		}

		return listarDepartamentosFacultades;
	}
}
