package entidades;

import java.sql.Date;

public class Agenda {

	
	
int id_det_syllabus;
	private Date fecha_inicio;
	private Date fecha_final;
	int estado;
	String edicion ;
	int num_agenda;
	int id_syllabus;
	public int getId_det_syllabus() {
		return id_det_syllabus;
	}
	public void setId_det_syllabus(int id_det_syllabus) {
		this.id_det_syllabus = id_det_syllabus;
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
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getEdicion() {
		return edicion;
	}
	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}
	public int getNum_agenda() {
		return num_agenda;
	}
	public void setNum_agenda(int num_agenda) {
		this.num_agenda = num_agenda;
	}
	public int getId_syllabus() {
		return id_syllabus;
	}
	public void setId_syllabus(int id_syllabus) {
		this.id_syllabus = id_syllabus;
	}
	
	
	
	
	
}
