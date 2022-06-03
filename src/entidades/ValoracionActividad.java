package entidades;

public class ValoracionActividad {
	//Declaracion de variables
	private int id_valoracionesA;
	private int id_actividades;
	private int id_usuario;
	private String comentario;
	private int valor;
	
	//Gether and Setter
	public int getId_valoracionesA() {
		return id_valoracionesA;
	}
	public void setId_valoracionesA(int id_valoracionesA) {
		this.id_valoracionesA = id_valoracionesA;
	}
	public int getId_actividades() {
		return id_actividades;
	}
	public void setId_actividades(int id_actividades) {
		this.id_actividades = id_actividades;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
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