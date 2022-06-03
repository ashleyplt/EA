package vistas;
import java.sql.Date;

public class VW_Datos_Syllabus {
	private int id_syllabus;
	private int id_expediente_asignatura;
	private String asignatura;
	private int creditos;
	private String cod_asignatura;
	private String edicion;
	private String carrera;
	private String facultad;
	private String departamento;
	private Date fecha_entrega;
	private String coordinador;
	private int estado;
	
	public int getId_syllabus() {
		return id_syllabus;
	}
	public void setId_syllabus(int id_syllabus) {
		this.id_syllabus = id_syllabus;
	}
	public int getId_expediente_asignatura() {
		return id_expediente_asignatura;
	}
	public void setId_expediente_asignatura(int id_expediente_asignatura) {
		this.id_expediente_asignatura = id_expediente_asignatura;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	public String getCod_asignatura() {
		return cod_asignatura;
	}
	public void setCod_asignatura(String cod_asignatura) {
		this.cod_asignatura = cod_asignatura;
	}
	public String getEdicion() {
		return edicion;
	}
	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public String getFacultad() {
		return facultad;
	}
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public Date getFecha_entrega() {
		return fecha_entrega;
	}
	public void setFecha_entrega(Date fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}
	public String getCoordinador() {
		return coordinador;
	}
	public void setCoordinador(String coordinador) {
		this.coordinador = coordinador;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
}
