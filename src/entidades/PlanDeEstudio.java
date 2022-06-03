package entidades;
import java.sql.Date;

public class PlanDeEstudio {
	private int id_plan_estudio;
	private String nombre;
	private Date fecha_creacion;
	private int estado;
	
	public int getId_plan_estudio() {
		return id_plan_estudio;
	}
	public void setId_plan_estudio(int id_plan_estudio) {
		this.id_plan_estudio = id_plan_estudio;
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
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
}
