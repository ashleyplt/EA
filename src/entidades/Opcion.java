package entidades;

public class Opcion {
	
	private int id_opcion;
	private String nombre;
	private String descripcion;
	private int estado;
	
	public int getId_opcion() {
		return id_opcion;
	}
	public void setId_opcion(int id_opcion) {
		this.id_opcion = id_opcion;
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
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
}
