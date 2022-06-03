package vistas;

import java.sql.Date;

//Vista de las tablitas att: Javier, el gordito más sexy del mundo
public class VW_Usuarios {
	
	private int id_usuario;
	private int id_personal;
	private int id_usuario_rol;
	private int id_rol;
	
	private String nombre;
	private String apellido;
	private String usuario;
	private Date fecha_creacion;
	private Date ultima_fechaIngreso;
	private String rol;
	private String imagen;
	private int estado;
	
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public int getId_personal() {
		return id_personal;
	}
	public void setId_personal(int id_personal) {
		this.id_personal = id_personal;
	}
	public int getId_usuario_rol() {
		return id_usuario_rol;
	}
	public void setId_usuario_rol(int id_usuario_rol) {
		this.id_usuario_rol = id_usuario_rol;
	}
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Date getUltima_fechaIngreso() {
		return ultima_fechaIngreso;
	}
	public void setUltima_fechaIngreso(Date ultima_fechaIngreso) {
		this.ultima_fechaIngreso = ultima_fechaIngreso;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
}
