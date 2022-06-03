package entidades;

public class Notificacion {
	private int id_notificacion;
	private int id_remitente; 
	private int id_destinatario; 
	private int estado; 
	
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	private String mensaje;

	public int getId_notificacion() {
		return id_notificacion;
	}

	public void setId_notificacion(int id_notificacion) {
		this.id_notificacion = id_notificacion;
	}

	public int getId_remitente() {
		return id_remitente;
	}

	public void setId_remitente(int id_remitente) {
		this.id_remitente = id_remitente;
	}

	public int getId_destinatario() {
		return id_destinatario;
	}

	public void setId_destinatario(int id_destinatario) {
		this.id_destinatario = id_destinatario;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	} 
	
}
