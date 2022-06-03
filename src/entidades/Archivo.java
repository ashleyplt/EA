package entidades;

public class Archivo {

	int id_archivo ;
	String nombre; 
	String nombre_archivo;
	String nombre_edicion;
	String tipo;
	int id_carpeta;
	int estado ;
	
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getId_archivo() {
		return id_archivo;
	}
	public void setId_archivo(int id_archivo) {
		this.id_archivo = id_archivo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre_archivo() {
		return nombre_archivo;
	}
	public void setNombre_archivo(String nombre_archivo) {
		this.nombre_archivo = nombre_archivo;
	}	
	public String getNombre_edicion() {
		return nombre_edicion;
	}
	public void setNombre_edicion(String nombre_edicion) {
		this.nombre_edicion = nombre_edicion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getId_carpeta() {
		return id_carpeta;
	}
	public void setId_carpeta(int id_carpeta) {
		this.id_carpeta = id_carpeta;
	}
	 
	
	
}
