package vistas;

import java.sql.Date;

public class VW_Permisos_Expediente {
	private int id_permiso;
	private String codigo;
	private String asignatura;
	private String url_expediente;
	private int id_personal;
	private String docente;
	private String tipo_permiso;
	private Date fecha_inicio;
	private Date fecha_final;
	
	
	public int getId_permiso() {
		return id_permiso;
	}
	public void setId_permiso(int id_permiso) {
		this.id_permiso = id_permiso;
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
	public String getUrl_expediente() {
		return url_expediente;
	}
	public void setUrl_expediente(String url_expediente) {
		this.url_expediente = url_expediente;
	}
	public int getId_personal() {
		return id_personal;
	}
	public void setId_personal(int id_personal) {
		this.id_personal = id_personal;
	}
	public String getDocente() {
		return docente;
	}
	public void setDocente(String docente) {
		this.docente = docente;
	}
	public String getTipo_permiso() {
		return tipo_permiso;
	}
	public void setTipo_permiso(String tipo_permiso) {
		this.tipo_permiso = tipo_permiso;
	}
	public Date getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public Date getFecha_final() {
		return fecha_final;
	}
	public void setFecha_final(Date fecha_final) {
		this.fecha_final = fecha_final;
	}
	
	
}
