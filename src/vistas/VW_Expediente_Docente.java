package vistas;

import java.sql.Date;

public class VW_Expediente_Docente {
	private String edicion;
	private int id_asignatura;
	private String cod_asignatura;
	private String asignatura;
	private String descripcion;
	private String url_expediente;
	private Date fecha_creacion;
	private int id_docente;
	private String docente;
	
	public String getEdicion() {
		return edicion;
	}
	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}
	public int getId_asignatura() {
		return id_asignatura;
	}
	public void setId_asignatura(int id_asignatura) {
		this.id_asignatura = id_asignatura;
	}
	public String getCod_asignatura() {
		return cod_asignatura;
	}
	public void setCod_asignatura(String cod_asignatura) {
		this.cod_asignatura = cod_asignatura;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUrl_expediente() {
		return url_expediente;
	}
	public void setUrl_expediente(String url_expediente) {
		this.url_expediente = url_expediente;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public int getId_docente() {
		return id_docente;
	}
	public void setId_docente(int id_docente) {
		this.id_docente = id_docente;
	}
	public String getDocente() {
		return docente;
	}
	public void setDocente(String docente) {
		this.docente = docente;
	}
	

	
}
