package vistas;

import java.sql.Date;

public class VW_Usuario {
	private int id;
	private String nombre;
	private Date fecha_creacion;
	private Date ultima_fechaingreso;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	
}
