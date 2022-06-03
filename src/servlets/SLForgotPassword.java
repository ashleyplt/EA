package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

import datos.DTEnviarCorreo;
import datos.DTPersonal;
import datos.DTRecuperarPassword;
import datos.DTUsuario;
import entidades.Personal;
import entidades.Usuario;

/**
 * Servlet implementation class SLForgotPassword
 */
@WebServlet("/SLForgotPassword")
public class SLForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLForgotPassword() {
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
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		DTPersonal dtp = new DTPersonal();
		Personal p = new Personal();
		DTUsuario dtu = new DTUsuario();
		Usuario u = new Usuario();
		String correo = request.getParameter("correo").trim();
		String password = RandomStringUtils.randomAlphanumeric(8);
		
		u.setPwd(password);
		
		boolean existe = false;
		if ( dtp.existeCorreo(correo)) {
			existe = true;
		}
		
		if (existe) {
			DTRecuperarPassword dtrp = new DTRecuperarPassword();
			try {
				int id_usuario = dtu.obteneridUsuario(correo);
				if(dtu.modificarPassword(id_usuario, password)) {
					if (dtrp.enviarRecuperarPassword(correo, password)) {
						response.sendRedirect("index.jsp");
					} else {
						session.setAttribute("error",
								"Hubo un error al enviarle las credenciales al correo del personal.");
						response.sendRedirect("layout/forgot-password.jsp");
					}
				}
				
			} catch (Exception e) {
				System.out.println("SLForgotPassword, el error es: " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			session.setAttribute("error",
					"El correo no existe.");
			response.sendRedirect("layout/forgot-password.jsp");
		}
		
		
		
		
	}

}
