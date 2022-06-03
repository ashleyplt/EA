package entidades;

public class Carrera {
	private int id_carrera;
	private String nombre;
	private int estado;
	private int Id_facultad;
	private int id_departamento;
	
	public int getId_carrera() {
		return id_carrera;
	}
	public void setId_carrera(int id_carrera) {
		this.id_carrera = id_carrera;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getId_facultad() {
		return Id_facultad;
	}
	public void setId_facultad(int Id_facultad) {
		this.Id_facultad = Id_facultad;
	}

	public int getId_departamento() {
		return id_departamento;
	}
	
	public void setId_departamento(int id_departamento) {
		this.id_departamento = id_departamento;
	}
}
