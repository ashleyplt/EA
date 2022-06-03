package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTOpcion;

/**
 * Servlet implementation class SLopcion
 */
@WebServlet(description = "Servlet para el crud de la tabla de seguridad de opciones", urlPatterns = { "/SLopcion" })
public class SLOpcion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLOpcion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		try 
		{
			DTOpcion dto = new DTOpcion();
			
			int estado;
			int id;
			
			id = Integer.parseInt(request.getParameter("idopcion"));
			estado = Integer.parseInt(request.getParameter("estado"));
			System.out.println("Estado: " + estado);
			

			if(dto.eliminarOpcion(estado, id))
			{
				response.sendRedirect("layout/ver-opciones.jsp?deshabilitado=1");
			}
			else
			{
				response.sendRedirect("/layout/ver-opciones.jsp?deshabilitado=2");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL Opciones: Error al deshabilitar la opcion " +e.getMessage());
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
