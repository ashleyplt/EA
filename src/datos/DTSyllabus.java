package datos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import entidades.Syllabus;
import entidades.*;

public class DTSyllabus {
	PoolConexion pc = PoolConexion.getInstance();
	Connection c = null;
	private ResultSet rsSyllabus = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	//Método para llenar el resultset Syllabus
	public void llenarSyllabus(Connection c)
	{
		String sql = "SELECT * FROM public.syllabus";
		try 
		{
			//c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsSyllabus = ps.executeQuery();
		} 
		catch (Exception e) 
		{
			System.err.println("DT SYLLABUS: Error en listar syllabus " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public boolean guardarSyllabus(Syllabus s)
	{
		boolean guardado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarSyllabus(c);
			//AQUI INICIA EL GUARDAR
			rsSyllabus.moveToInsertRow();
			rsSyllabus.updateInt("id_expediente_asignatura", s.getId_expediente_asignatura());
			rsSyllabus.updateDate("fecha_creacion", sqlDate);
			rsSyllabus.updateInt("id_carrera", s.getId_carrera());
			rsSyllabus.updateInt("estado", 1);
			
			rsSyllabus.insertRow();
			rsSyllabus.moveToCurrentRow();
			guardado = true;
			
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTSYLLABUS: Error al guardar Syllabus " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsSyllabus != null)
				{
					rsSyllabus.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTSyllabus: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public boolean modificarSyllabus(Syllabus s) {
		boolean modificado = false;
		Date utilDate = new Date();
		java.sql.Date sqlDate =  new java.sql.Date(utilDate.getTime());
		try {
			c = PoolConexion.getConnection();
			this.llenarSyllabus(c);
			rsSyllabus.beforeFirst();
			while(rsSyllabus.next()) {
				if(rsSyllabus.getInt("id_syllabus")==s.getId_syllabus()) {
					rsSyllabus.updateInt("id_carrera", s.getId_carrera());
					rsSyllabus.updateDate("fecha_modificacion", sqlDate);
					rsSyllabus.updateInt("estado",2);
					rsSyllabus.updateRow();
				    modificado = true;
				    break;
				}
			}
		}catch(Exception e2) {
			System.err.println("DTSyllabus: Error al cerrar conexion " + e2.getMessage());
			e2.printStackTrace();
		}finally {
			try {
				if(rsSyllabus != null) {
					rsSyllabus.close();
				}
				if(c !=null) {
					c.close();
				}
			}catch(Exception e) {
				
			}
		}
		
		return modificado;
	}
	
	
	public Syllabus buscarSyllabus(int idsyllabus) {
		Syllabus s = new Syllabus();
		String sql = "SELECT * FROM SYLLABUS WHERE ID_SYLLABUS = ? AND ESTADO <> 0";
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idsyllabus);
			rs = ps.executeQuery();
			if(rs.next())
			{
				s.setId_syllabus(rs.getInt("id_syllabus"));
				s.setId_expediente_asignatura(rs.getInt("id_expediente_asignatura"));
				s.setFecha_creacion(rs.getDate("fecha_creacion"));
				s.setFecha_entrega(rs.getDate("fecha_entrega"));
				s.setFecha_modificacion(rs.getDate("fecha_modificacion"));
				s.setId_carrera(rs.getInt("id_carrera"));
				s.setEstado(rs.getInt("estado"));
			}
			
		}catch(Exception e){
			System.err.println("DATOS: error recuperarUltimoSyllabus(): " + e.getMessage());
			e.printStackTrace();
		}finally{
			try 
			{
				if(rsSyllabus != null)
				{
					rsSyllabus.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTSyllabus: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return s;
				
	}
	
	
	public Syllabus buscarSyllabusporExp(int idexpediente) {
		Syllabus s = new Syllabus();
		String sql = "SELECT * FROM SYLLABUS WHERE ID_EXPEDIENTE_ASIGNATURA = ? AND ESTADO <> 0";
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, idexpediente);
			rs = ps.executeQuery();
			if(rs.next())
			{
				s.setId_syllabus(rs.getInt("id_syllabus"));
				s.setId_expediente_asignatura(rs.getInt("id_expediente_asignatura"));
				s.setFecha_creacion(rs.getDate("fecha_creacion"));
				s.setFecha_entrega(rs.getDate("fecha_entrega"));
				s.setFecha_modificacion(rs.getDate("fecha_modificacion"));
				s.setId_carrera(rs.getInt("id_carrera"));
				s.setEstado(rs.getInt("estado"));
			}
			
		}catch(Exception e){
			System.err.println("DATOS: error recuperarUltimoSyllabus(): " + e.getMessage());
			e.printStackTrace();
		}finally{
			try 
			{
				if(rsSyllabus != null)
				{
					rsSyllabus.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTSyllabus: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return s;
				
	}
	
	public Syllabus ultimoSyllabusCreado() {
		Syllabus ultimoSyllabus  = new Syllabus();
		String sql = "SELECT * FROM SYLLABUS ORDER BY ID_SYLLABUS DESC LIMIT 1";
		
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rs = ps.executeQuery();
			if(rs.next())
			{
				ultimoSyllabus.setId_syllabus(rs.getInt("id_syllabus"));
				ultimoSyllabus.setId_expediente_asignatura(rs.getInt("id_expediente_asignatura"));
				ultimoSyllabus.setFecha_creacion(rs.getDate("fecha_creacion"));
				ultimoSyllabus.setFecha_entrega(rs.getDate("fecha_entrega"));
				ultimoSyllabus.setFecha_modificacion(rs.getDate("fecha_modificacion"));
				ultimoSyllabus.setId_carrera(rs.getInt("id_carrera"));
				ultimoSyllabus.setEstado(rs.getInt("estado"));
			}
			
		}catch(Exception e){
			System.err.println("DATOS: error recuperarUltimoSyllabus(): " + e.getMessage());
			e.printStackTrace();
		}finally{
			try 
			{
				if(rsSyllabus != null)
				{
					rsSyllabus.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTSyllabus: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return ultimoSyllabus;
	}
	
	public ArrayList<Syllabus> listarSyllabus(int id_expediente, int id_syllabus){
		ArrayList<Syllabus> listarSyllabus = new ArrayList<Syllabus>();
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("select * from syllabus WHERE id_expediente_asignatura = ? OR id_syllabus = ? AND estado <> 0", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, id_expediente);
			ps.setInt(2,id_syllabus);
			rs = ps.executeQuery();
			while(rs.next()){
				Syllabus syllabus = new Syllabus();
				syllabus.setId_syllabus(rs.getInt("id_syllabus")); 
				syllabus.setId_expediente_asignatura(rs.getInt("id_expediente_asignatura")); 
				syllabus.setFecha_creacion(rs.getDate("fecha_creacion"));
				syllabus.setFecha_modificacion(rs.getDate("fecha_modificacion"));
				syllabus.setEstado(rs.getInt("estado"));
				syllabus.setId_carrera(rs.getInt("id_carrera"));
				listarSyllabus.add(syllabus);
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR SYLLABUS: "+ e.getMessage());
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
		return listarSyllabus;
	}
	
	public boolean eliminarSyllabus(int id_syllabus) {
		boolean eliminado = false;
		try {
			c = PoolConexion.getConnection();
			this.llenarSyllabus(c);
			rsSyllabus.beforeFirst();
			while(rsSyllabus.next()) {
				if(rsSyllabus.getInt("id_syllabus")==id_syllabus) {
					rsSyllabus.updateInt("estado", 0);
					rsSyllabus.updateRow();
					eliminado = true;
					break;
				}
			}
			
		}catch (Exception e) {
			System.err.println("DTSyllabus: Error al eliminar syllabus" + e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(rsSyllabus !=null) {
					rsSyllabus.close();
				}
				if(c != null) {
					c.close();
				}
			}catch (Exception e2){
				System.err.println("DTSyllabus: Error al cerrar conexión " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return eliminado;
	}
	

	
	
}
