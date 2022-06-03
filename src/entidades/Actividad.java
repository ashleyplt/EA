package entidades;

public class Actividad {
	//Declaraci�n de Variables
	private int id_actividad;
	private String nombre;
	private String descripcion;
	private int id_tipo_actividad;
	private int id_modalidad_actividad;
	private int id_evaluacion_actividad;
	private int cant_horas;
	private int valoracion;
	private int id_usuario;
	private int estado;
	
	//Gethers and Setters
	public int getId_actividad() {
		return id_actividad;
	}
	public void setId_actividad(int id_actividad) {
		this.id_actividad = id_actividad;
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
	public int getId_tipo_actividad() {
		return id_tipo_actividad;
	}
	public void setId_tipo_actividad(int id_tipo_actividad) {
		this.id_tipo_actividad = id_tipo_actividad;
	}
	public int getId_modalidad_actividad() {
		return id_modalidad_actividad;
	}
	public void setId_modalidad_actividad(int id_modalidad_actividad) {
		this.id_modalidad_actividad = id_modalidad_actividad;
	}
	public int getId_evaluacion_actividad() {
		return id_evaluacion_actividad;
	}
	public void setId_evaluacion_actividad(int id_evaluacion_actividad) {
		this.id_evaluacion_actividad = id_evaluacion_actividad;
	}
	public int getCant_horas() {
		return cant_horas;
	}
	public void setCant_horas(int cant_horas) {
		this.cant_horas = cant_horas;
	}
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
}