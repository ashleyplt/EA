package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTCarrera;

/**
 * Servlet implementation class SLEliminarCarrera
 */
@WebServlet("/SLEliminarCarrera")
public class SLEliminarCarrera extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEliminarCarrera() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			DTCarrera dtc = new DTCarrera();
			
			int id_carrera= Integer.parseInt(request.getParameter("id_carrera"));
			int estado = Integer.parseInt(request.getParameter("estado"));
			
			
			if(dtc.eliminarCarrera(id_carrera, estado))
			{
				response.sendRedirect("layout/ver-vistamaestracarrera.jsp?eliminado="+estado);
			}
			else
			{
				response.sendRedirect("layout/ver-carrera.jsp?error");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL CARRERA: Error al elimianr carrera " +e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
