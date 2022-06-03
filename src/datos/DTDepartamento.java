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

	// Método para llenar el resultset Departamento
	public void llenarDepartamento(Connection c) {
		String sql = "SELECT * FROM public.departamento";
		try {
			// c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsDept = ps.executeQuery();
		} catch (Exception e) {
			System.err.println("DT Departamento: Error en listar departamento " + e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<Departamento> listarDepartamentos() {
		ArrayList<Departamento> listd = new ArrayList<>();
		String SQL = ("SELECT * FROM departamento;");
		try {
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(SQL);
			rs = ps.executeQuery();
			while (rs.next()) {
				Departamento d = new Departamento();
				d.setId(rs.getInt("id_departamento"));
				d.setNombre(rs.getString("nombre"));
				d.setDescripcion(rs.getString("descripcion"));
				d.setId_facultad(rs.getInt("id_facultad"));

				listd.add(d);
			}
		} catch (Exception e) {
			System.out.println("DATOS: ERROR AL LISTAR DEPARTAMENTOS " + e.getMessage());
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
			ps = c.prepareStatement("select * from departamento where nombre like ?;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
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
	
	public boolean guardarDepartamento(Departamento d)
	{
		boolean guardado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarDepartamento(c);
			//AQUI INICIA EL GUARDAR
			rsDept.moveToInsertRow();
			rsDept.updateString("nombre", d.getNombre());
			rsDept.updateString("descripcion",d.getDescripcion());
			rsDept.updateInt("id_facultad", d.getId_facultad());
			
			rsDept.insertRow();
			rsDept.moveToCurrentRow();
			guardado = true;
			
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTCARRERA: Error al guardar Carrera " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsDept != null)
				{
					rsDept.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTDepartamento: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		
		return guardado;
	}
	
	public boolean modificarDepartamento(Departamento d)
	{
		boolean modificado = false;
		try 
		{
			c = PoolConexion.getConnection();
			this.llenarDepartamento(c);
			rsDept.beforeFirst();
			while(rsDept.next())
			{
				if(rsDept.getInt("id_departamento") == d.getId()) 
				{	
					rsDept.updateString("nombre", d.getNombre());
					rsDept.updateString("descripcion", d.getDescripcion());
					rsDept.updateInt("id_facultad", d.getId_facultad());
					rsDept.updateRow();
					modificado = true;
					break;
				}	
			}
			
		} 
		catch (Exception e) 
		{
			System.err.println("DTActividades: Error al modificar la departamento " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsDept != null)
				{
					rsDept.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTDepartamento: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return modificado;
	}
	
	public Departamento recuperarDepartamento(int id_departamento)
	{
		Departamento d = new Departamento();
		String sql = "SELECT * FROM public.departamento where id_departamento = ?";
		
		try 
		{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, id_departamento);
			rs = ps.executeQuery();
			if(rs.next())
			{
				d.setId(id_departamento);
				d.setNombre(rs.getString("nombre"));
				d.setDescripcion(rs.getString("descripcion"));
				d.setId_facultad(rs.getInt("id_facultad"));
			}
		} 
		catch (Exception e) 
		{
			System.err.println("DATOS: error recuperarDepartamento(): " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rsDept!= null)
				{
					rsDept.close();
				}
				if(c != null)
				{
					c.close();
				}
			} 
			catch (Exception e2) 
			{
				System.err.println("DTDepartamento: Error al cerrar conexion " + e2.getMessage());
				e2.printStackTrace();
			}
		}
		return d;
	}
}
