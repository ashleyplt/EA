package datos;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class DTEnviarCorreo {

	// ATRIBUTOS

	/*---------------------- Configuración Localhost------------------------------*/
	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	private static final String SMTP_AUTH_USER = "moonixgestorexpediente@gmail.com";
	private static final String SMTP_AUTH_PWD = "somosuca";

	// Enlace
	String linkHR = "http://localhost:8080/EA/";

	
	// DECLARAMOS UNA CLASE PRIVADA COMO ATRIBUTO QUE HEREDA
	// JAVAX.MAIL.AUTHENTICATOR
	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}

	/*----------------------------------------------------------------------------*/

	// METODO QUE ENVIA EL EMAIL DE VERIFICACION
	public boolean enviarEmailSolicitud(String personal, String correo, String destinatario,
			String expediente, String motivo) throws MessagingException {

		boolean debug = false;

		// Correo del solicitante
		String email_destinatario = destinatario;

		// Correo del remitente
		String email_remitente = SMTP_HOST_NAME;

		// Obtener propiedades del sistema
		Properties properties = new Properties();

		/*---------------------- Configuración del servidor de correo---------------------------*/
		properties.setProperty("mail.smtp.host", SMTP_HOST_NAME);
		properties.put("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		/*--------------------------------------------------------------------------------------*/

		Authenticator auth = new SMTPAuthenticator();

		Session session = Session.getInstance(properties, auth);
		session.setDebug(debug);

		// Create a default MimeMessage object.
		MimeMessage message = new MimeMessage(session);

		// Establecer De (remitente)
		message.setFrom(new InternetAddress(email_remitente));

		// Establecer Para (solicitante)
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email_destinatario));

		// Asunto: encabezado del archivo
		message.setSubject("SOLICITUD DE PERMISO DE ACCESO A EXPEDIENTE DE ASIGNATURA");

		// Cuerpo del correo
		String myMsg = "<strong>PERMISO DE ACCESO A EXPEDIENTE DE ASIGNATURA</strong><br><br>";
		myMsg += "El usuario con los datos a continuaci&oacute;n ha solicitado ";
		myMsg += "un permiso de acceso al expediente <strong>"+expediente+"</strong> con el siguiente motivo: <br><br>";
		myMsg += motivo + "<br><br>";
		myMsg += "<strong><u> DATOS DEL SOLICITANTE </u></strong> <br><br>";
		myMsg += "<strong>Nombres y apellidos: </strong> " + personal + "<br>";
		myMsg += "<strong>Correo electr&oacute;nico: </strong> " + correo + "<br>";
		myMsg += "Si desea concederle permisos, inicie sesi&oacute;n en el sistema y entre a este <a href=\""+linkHR
				+ "permisos-expediente.jsp?opc=1&expediente=" + expediente + "\">enlace.</a><br>";
		myMsg += "<br>----------------------------------------------------------<br>";
		myMsg += "Sistema de Expediente de Asignatura - EA<br>";
		myMsg += "Universidad Centroamericana";

		message.setContent(myMsg, "text/html");

		// Enviar Correo
		Transport transport = session.getTransport("smtp");
		transport.connect(SMTP_HOST_NAME, SMTP_AUTH_USER, SMTP_AUTH_PWD);
		Transport.send(message);
		debug = true;
		System.out.println("El mensaje fue enviado con éxito");
		return debug;
	}

	public boolean enviarEmailVerificacion(int id_usuario, String nombre,String usuario, String destinatario,
			String password) throws MessagingException {

		boolean debug = false;

		// Correo del solicitante
		String email_destinatario = destinatario;

		// Correo del remitente
		String email_remitente = SMTP_HOST_NAME;

		// Obtener propiedades del sistema
		Properties properties = new Properties();

		/*---------------------- Configuración del servidor de correo---------------------------*/
		properties.setProperty("mail.smtp.host", SMTP_HOST_NAME);
		properties.put("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		/*--------------------------------------------------------------------------------------*/

		Authenticator auth = new SMTPAuthenticator();

		Session session = Session.getInstance(properties, auth);
		session.setDebug(debug);

		// Create a default MimeMessage object.
		MimeMessage message = new MimeMessage(session);

		// Establecer De (remitente)
		message.setFrom(new InternetAddress(email_remitente));

		// Establecer Para (solicitante)
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email_destinatario));

		// Asunto: encabezado del archivo
		message.setSubject("ACCESO AL SISTEMA DE EXPEDIENTE DE ASIGNATURA - EA");

		// Cuerpo del correo
		String myMsg = "<strong>VERIFICACION DE CUENTA PERSONAL</strong><br><br>";
		myMsg += "Hola, "+nombre+".<br><br> Te damos la bienvenida al sistema Expediente de Asignatura - EA "
				+ "Te hemos creado un nuevo usuario con las siguientes credenciales de acceso: <br><br>";
		myMsg += "<strong>Nombre de usuario: </strong> " + usuario + "<br>";
		myMsg += "<strong>Contraseña: </strong> " + password + "<br><br>";
		myMsg += "Aseg&uacute;rate de hacer clic en este <a href=\""+linkHR+"layout/verificar-correo.jsp?id="+id_usuario+"\">enlace</a> para activar tu cuenta dentro del sistema.";
	    myMsg += "<br>----------------------------------------------------------<br>";
		myMsg += "Sistema de Gesti&oacute;n de Expediente de Asignatura - EA<br>";
		myMsg += "Universidad Centroamericana";

		message.setContent(myMsg, "text/html");

		// Enviar Correo
		Transport transport = session.getTransport("smtp");
		transport.connect(SMTP_HOST_NAME, SMTP_AUTH_USER, SMTP_AUTH_PWD);
		Transport.send(message);
		debug = true;
		System.out.println("El mensaje fue enviado con éxito");
		return debug;
	}

	
	public static void main(String[] args) {

	}
}
