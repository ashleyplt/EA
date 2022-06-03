package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.DTUsuario;
/**
 * Servlet implementation class SLlogin
 */
@WebServlet("/SLLogin")
public class SLLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SLLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int sesionval = Integer.parseInt(request.getParameter("session"));
		if (sesionval == 1) {
			HttpSession session = request.getSession();
			session.invalidate();

			response.sendRedirect("./index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String nombre = request.getParameter("user");
		String password = request.getParameter("password");

		DTUsuario dtu = new DTUsuario();

		try {
			if (dtu.dtverificarLogin(nombre, password)) {
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(60 * 30);
				
//				VW_Cargo_Personal info = new VW_Cargo_Personal();
//				DTVCargoPersonal dtvinfo = new DTVCargoPersonal();
//				info = dtvinfo.buscarInfoPersonal(nombre);

				//for (VW_Info_Personal i : listinfo) {
				session.setAttribute("nombre_user", nombre);
				//}

				response.sendRedirect("layout/index.jsp");

			} else {
 
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(60 * 30);
				session.setAttribute("error", "no entraste");
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			System.out.println("Servlet: El error es: " + e.getMessage());
			e.printStackTrace();
		}
		// doGet(request, response);
	}

}
