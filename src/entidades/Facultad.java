package entidades;

public class Facultad {
	private int Id_facultad;
	private String nombre;
	private int estado;
	public int getId_facultad() {
		return Id_facultad;
	}
	public void setId_facultad(int id_facultad) {
		Id_facultad = id_facultad;
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
	
}
