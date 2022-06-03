package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import entidades.Syllabus;
import entidades.*;
import vistas.*;

public class DTVDatosSyllabus {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsDatosSyllabus = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public void llenarSyllabus(Connection c)
	{
		String sql = "SELECT * FROM public.vw_datos_basicos_syllabus";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsDatosSyllabus = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT SYLLABUS: Error en listar syllabus " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public VW_Datos_Syllabus buscarSyllabus(int idsyllabus) {
		VW_Datos_Syllabus ds = new VW_Datos_Syllabus();
		String sql = "SELECT * FROM VW_DATOS_BASICOS_SYLLABUS WHERE ID_SYLLABUS = ? AND ESTADO <> 0";
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idsyllabus);
			rs=ps.executeQuery();
			if(rs.next()) {
				ds.setId_syllabus(rs.getInt("id_syllabus"));
				ds.setId_expediente_asignatura(rs.getInt("id_expediente_asignatura"));
				ds.setAsignatura(rs.getString("asignatura"));
				ds.setCreditos(rs.getInt("creditos"));
				ds.setCod_asignatura(rs.getString("cod_asignatura"));
				ds.setEdicion(rs.getString("edicion"));
				ds.setCarrera(rs.getString("carrera"));
				ds.setFacultad(rs.getString("facultad"));
				ds.setDepartamento(rs.getString("departamento"));
				ds.setFecha_entrega(rs.getDate("fecha_entrega"));
				ds.setCoordinador(rs.getString("coordinador"));
				ds.setEstado(rs.getInt("estado"));
				
			}
		}catch(Exception e) {
			System.err.println("DATOS: error recuperar Syllabus: " + e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(rsDatosSyllabus != null) {
					rsDatosSyllabus.close();
				}
				if(c != null) {
					c.close();
				}
			}catch(Exception e2) {
				System.err.println("DTVDatosSyllabus: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return ds;
	}
	
}
