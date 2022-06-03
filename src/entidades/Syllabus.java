package entidades;
import java.sql.Date;

public class Syllabus {
	
	private int id_syllabus;
	private int id_expediente_asignatura;
	private Date fecha_creacion;
	private Date fecha_modificacion;
	private Date fecha_entrega;
	private int estado;
	private int id_carrera;
	
	public int getId_syllabus() {
		return id_syllabus;
	}
	public void setId_syllabus(int id_syllabus) {
		this.id_syllabus = id_syllabus;
	}
	
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Date getFecha_modificacion() {
		return fecha_modificacion;
	}
	public void setFecha_modificacion(Date fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getId_expediente_asignatura() {
		return id_expediente_asignatura;
	}
	public void setId_expediente_asignatura(int id_expediente_asignatura) {
		this.id_expediente_asignatura = id_expediente_asignatura;
	}
	public Date getFecha_entrega() {
		return fecha_entrega;
	}
	public void setFecha_entrega(Date fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}
	public int getId_carrera() {
		return id_carrera;
	}
	public void setId_carrera(int id_carrera) {
		this.id_carrera = id_carrera;
	}
}
