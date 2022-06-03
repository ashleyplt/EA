package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.ExpedienteAsignatura;

public class DTExpedienteAsignatura {
	PoolConexion pc = PoolConexion.getInstance(); 
	Connection c = null;
	private ResultSet rsExpediente = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public void llenarExpedienteAsignatura(Connection c){

		try{
			ps = c.prepareStatement("select * from expediente_de_asignatura", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			rsExpediente = ps.executeQuery();
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR EN LISTAR LOS EXPEDIENTES DE ASIGNATURA DE SU TABLA "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	public boolean guardarExpedienteAsignatura(ExpedienteAsignatura ea) {
		boolean guardado = false;
		try {
			c = PoolConexion.getConnection();
			this.llenarExpedienteAsignatura(c);
			rsExpediente.moveToInsertRow();
			rsExpediente.updateInt("estado", ea.getEstado());
			rsExpediente.updateInt("id_asignatura", ea.getId_asignatura());
			rsExpediente.updateInt("id_edicion", ea.getId_edicion());
			rsExpediente.insertRow();
			rsExpediente.moveToCurrentRow();
			guardado = true;
		} catch (Exception e) {
			System.err.println("ERROR AL GUARDAR EL EXPEDIENTE DE ASIGNATURA: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (rsExpediente != null) {
					rsExpediente.close();
				}
				if (c != null) {
					PoolConexion.closeConnection(c);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return guardado;
	}
	
	public int getId(int asignatura, int edicion)
	{
		int id = 0;
		try{
			c = PoolConexion.getConnection();
			ps = c.prepareStatement("SELECT * FROM expediente_de_asignatura WHERE id_asignatura = ? AND id_edicion = ?;;", ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ps.setInt(1, asignatura);
			ps.setInt(2, edicion);
			rs = ps.executeQuery();
			while(rs.next()){
				id=rs.getInt("id_expediente_asignatura");
			}
		}
		catch (Exception e){
			System.out.println("DATOS: ERROR AL BUSCAR ID DEL EXPEDIENTE DE ASIGNATURA "+ e.getMessage());
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
				e.printStackTrace();
			}
		}
		return id;
	}
}
