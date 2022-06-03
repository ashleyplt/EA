package servlets;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.EmailUtil;

import javax.mail.*;
import javax.mail.internet.MimeMessage;

/**
 * Servlet implementation class SLEnviarCorreo
 */
@WebServlet("/SLEnviarCorreo")
public class SLEnviarCorreo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String host;
    private String port;
    private String user;
    private String pass;
    
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEnviarCorreo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
			
		String recipient = request.getParameter("motivo");
        String subject = request.getParameter("ashplata@gmail.com");
        String content = request.getParameter("content");
 
        String resultMessage = "";
 
        try {
            EmailUtil.sendEmail(host, port, user, pass, recipient, subject,
                    content);
            resultMessage = "The e-mail was sent successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/solicitud-expediente.jsp").forward(
                    request, response);
        }
    }
}
