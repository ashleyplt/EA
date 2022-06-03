package vistas;

public class VW_Roles {
	
	private int id_rol;
	private int id_opciones;
	
	private String nombre;
	private String descripcion;
	private String opciones;
	private int estado;
	
	
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	public int getId_opciones() {
		return id_opciones;
	}
	public void setId_opciones(int id_opciones) {
		this.id_opciones = id_opciones;
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
	public String getOpciones() {
		return opciones;
	}
	public void setOpciones(String opciones) {
		this.opciones = opciones;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}	
}