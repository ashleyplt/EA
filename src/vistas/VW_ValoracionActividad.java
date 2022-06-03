package vistas;

public class VW_ValoracionActividad {

	//Declaracion de actividades
	private int id_actividad;
	private int id_usuario;
	private int id_personal;
	
	private String actividad;
	private String docente;
	private String comentario;
	private String imagen;
	private int valor;
	
	//Getters and Setters
	public int getId_actividad() {
		return id_actividad;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public void setId_actividad(int id_actividad) {
		this.id_actividad = id_actividad;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public int getId_personal() {
		return id_personal;
	}
	public void setId_personal(int id_personal) {
		this.id_personal = id_personal;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public String getDocente() {
		return docente;
	}
	public void setDocente(String docente) {
		this.docente = docente;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
}