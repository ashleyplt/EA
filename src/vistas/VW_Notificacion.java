package vistas;

public class VW_Notificacion {
	

	private int id_remitente;
	private String remitente;
	private int id_destinatario;
	private String destinatario;
	private String mensaje;
	private int estado;
	public int getId_remitente() {
		return id_remitente;
	}
	public void setId_remitente(int id_remitente) {
		this.id_remitente = id_remitente;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
	public int getId_destinatario() {
		return id_destinatario;
	}
	public void setId_destinatario(int id_destinatario) {
		this.id_destinatario = id_destinatario;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}


	
	 
}
