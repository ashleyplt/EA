package entidades;

import java.sql.Date;

public class Usuario {

	private int id;
	private String usuario;
	private String pwd;
	private Date fecha_creacion;
	private Date ultima_fechaingreso;
	private int estado;
	private String imagen;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Date getUltima_fechaingreso() {
		return ultima_fechaingreso;
	}
	public void setUltima_fechaingreso(Date ultima_fechaingreso) {
		this.ultima_fechaingreso = ultima_fechaingreso;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
	
}
