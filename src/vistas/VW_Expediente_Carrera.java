package vistas;

import java.sql.Date;

public class VW_Expediente_Carrera {
	private int id_plan;
	private String nombre_plan;
	private Date fecha_creacion;
	private int id_asignatura;
	private String codigo;
	private String asignatura;
	private String url;
	private String carrera;
	
	public int getId_plan() {
		return id_plan;
	}
	public void setId_plan(int id_plan) {
		this.id_plan = id_plan;
	}
	public String getNombre_plan() {
		return nombre_plan;
	}
	public void setNombre_plan(String nombre_plan) {
		this.nombre_plan = nombre_plan;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public int getId_asignatura() {
		return id_asignatura;
	}
	public void setId_asignatura(int id_asignatura) {
		this.id_asignatura = id_asignatura;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	
	
}
