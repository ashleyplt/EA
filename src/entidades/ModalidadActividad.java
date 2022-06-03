package entidades;

public class ModalidadActividad {
	private int id_modalidad_actividad;
	private String nombre;
	private String descripcion;
	
	public int getId_modalidad_actividad() {
		return id_modalidad_actividad;
	}
	public void setId(int id_modalidad_actividad) {
		this.id_modalidad_actividad = id_modalidad_actividad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
