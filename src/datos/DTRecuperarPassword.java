package datos;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class DTRecuperarPassword {
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

		
		public boolean enviarRecuperarPassword(String correo, String password) throws MessagingException {

			boolean debug = false;

			// Correo del solicitante
			String email_destinatario = correo;

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
			String myMsg = "<strong>RECUPERAR CONTRASEÑA</strong><br><br>";
			myMsg += "Estimado usuario.<br><br> Te brindamos tu nueva contraseña: " + password;
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

		
}
