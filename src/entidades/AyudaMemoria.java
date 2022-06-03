package entidades;

import java.sql.Date;
import java.sql.Time;

public class AyudaMemoria {
	private int id_ayuda_memoria; 
	private String asignatura;
	private String periodo_academico;
	private Date fecha_reunion;
	private Time hora_inicio;
	private Time hora_finalizacion;
	private String lugar;
	private int estado;
	
	public int getId_ayuda_memoria() {
		return id_ayuda_memoria;
	}
	public void setId_ayuda_memoria(int id_ayuda_memoria) {
		this.id_ayuda_memoria = id_ayuda_memoria;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public String getPeriodo_academico() {
		return periodo_academico;
	}
	public void setPeriodo_academico(String periodo_academico) {
		this.periodo_academico = periodo_academico;
	}
	public Date getFecha_reunion() {
		return fecha_reunion;
	}
	public void setFecha_reunion(Date fecha_reunion) {
		this.fecha_reunion = fecha_reunion;
	}
	public Time getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(Time hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	public Time getHora_finalizacion() {
		return hora_finalizacion;
	}
	public void setHora_finalizacion(Time hora_finalizacion) {
		this.hora_finalizacion = hora_finalizacion;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}

}
