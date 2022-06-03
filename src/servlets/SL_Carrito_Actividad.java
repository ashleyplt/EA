package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SL_Carrito_Actividad
 */
@WebServlet("/SL_Carrito_Actividad")
public class SL_Carrito_Actividad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SL_Carrito_Actividad() {
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
		//doGet(request, response);
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		 
		String id = request.getParameter("id"); 
  
			@SuppressWarnings("unchecked")
			List<String> Lista_actividades = (List<String>) session.getAttribute("actividades_agenda");
			
			if(Lista_actividades==null) {
				Lista_actividades = new ArrayList<String>();
				session.setAttribute("actividades_agenda", Lista_actividades);
				
			}
			
			Lista_actividades.add(id);

			
		 
	}

}
