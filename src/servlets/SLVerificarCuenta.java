package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTUsuario;
import entidades.Usuario;

/**
 * Servlet implementation class SLVerificarCuenta
 */
@WebServlet("/SLVerificarCuenta")
public class SLVerificarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLVerificarCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DTUsuario dtu = new DTUsuario();
		Usuario u = new Usuario();
		
		int iduser = Integer.parseInt(request.getParameter("id_usuario"));
		if(dtu.activarCuenta(iduser)) {
			
			response.sendRedirect("index.jsp");
		}
	}

}
