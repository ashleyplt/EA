package entidades;

import java.sql.Date;

public class UsuarioRol {
	private int id_usuario_rol;
	private Date fecha_creacion;
	private int id_rol;
	private int id_usuario;
	
	public int getId_usuario_rol() {
		return id_usuario_rol;
	}
	public void setId_usuario_rol(int id_usuario_rol) {
		this.id_usuario_rol = id_usuario_rol;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	
}
