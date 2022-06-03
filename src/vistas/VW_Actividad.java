package vistas;

public class VW_Actividad{

	//Declaracion de atributos
	private int id_actividad;
	private int id_tipo_actividad;
	private int id_modalidad_actividad;
	private int id_usuario;
	private int id_tipo_evaluacion_act;

	private String nombre;
	private String descripcion;
	private String tipo;
	private String modalidad;
	private String evaluacion;
	private String creador;

	private int estado;
	private int cant_horas;


	//Getter and setter
	public int getId_actividad() {
		return id_actividad;
	}
	public void setId_actividad(int id_actividad) {
		this.id_actividad = id_actividad;
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
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public int getId_tipo_evaluacion_act() {
		return id_tipo_evaluacion_act;
	}
	public void setId_tipo_evaluacion_act(int id_tipo_evaluacion_act) {
		this.id_tipo_evaluacion_act = id_tipo_evaluacion_act;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	public String getEvaluacion() {
		return evaluacion;
	}
	public void setEvaluacion(String evaluacion) {
		this.evaluacion = evaluacion;
	}
	public String getCreador() {
		return creador;
	}
	public void setCreador(String creador) {
		this.creador = creador;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getCant_horas() {
		return cant_horas;
	}
	public void setCant_horas(int cant_horas) {
		this.cant_horas = cant_horas;
	}
}