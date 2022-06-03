package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTCoordinacion;

/**
 * Servlet implementation class SLEliminarCoordinacion
 */
@WebServlet("/SLEliminarCoordinacion")
public class SLEliminarCoordinacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEliminarCoordinacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try 
		{
			DTCoordinacion dtcrd = new DTCoordinacion();
			
			int id_coordinacion= Integer.parseInt(request.getParameter("id_coordinacion"));
			int estado = Integer.parseInt(request.getParameter("estado"));
			
			
			if(dtcrd.eliminarCoordinacion(id_coordinacion, estado))
			{
				response.sendRedirect("layout/ver-vistamaestracoordinacion.jsp?eliminado="+estado);
			}
			else
			{
				response.sendRedirect("layout/ver-coordinacion.jsp?error");
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
