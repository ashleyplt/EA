package entidades;

import java.sql.Date;

public class PermisosAsignatura {
	private int id;
	private Date fecha_inicio;
	private Date fecha_final;
	private int estado;
	private int id_asignatura;
	private int id_personal;
	private int tipo_permiso;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getId_asignatura() {
		return id_asignatura;
	}
	public void setId_asignatura(int id_asignatura) {
		this.id_asignatura = id_asignatura;
	}
	public int getId_personal() {
		return id_personal;
	}
	public void setId_personal(int id_personal) {
		this.id_personal = id_personal;
	}
	public int getTipo_permiso() {
		return tipo_permiso;
	}
	public void setTipo_permiso(int tipo_permiso) {
		this.tipo_permiso = tipo_permiso;
	}
	
	
}
