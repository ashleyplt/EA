package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.DTUsuario;
import entidades.Usuario;

/**
 * Servlet implementation class SLVerificarPwd
 */
@WebServlet("/SLVerificarPwd")
public class SLVerificarPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLVerificarPwd() {
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
		request.setCharacterEncoding("UTF-8");
		//Construir el objeto de usuario
				DTUsuario dtu = new DTUsuario();
				Usuario user = new Usuario();
				HttpSession session = request.getSession();
				//Declaración de variables
				String pwd = request.getParameter("usuario");
				int id_usuario = (int) session.getAttribute("id_usuario");
				user.setId(id_usuario);
				user.setPwd(pwd);
				
				if(dtu.verificarPwd(user)) {

					response.sendRedirect("layout/cambiar-password.jsp");
					session.setAttribute("ocultarf", "block");
				}else {					
					session.setAttribute("Error_al_verificar", "error");
					response.sendRedirect("layout/cambiar-password.jsp");
				}
	}

}