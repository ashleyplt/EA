package servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTAsignatura;

/**
 * Servlet implementation class SLExpediente
 */
@WebServlet("/SLAsignatura")
public class SLAsignatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLAsignatura() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			request.setCharacterEncoding("UTF-8");
			String nombre = request.getParameter("nombre").trim();
			String codigo = request.getParameter("codigo").trim();
			String descripcion = request.getParameter("descripcion").trim();
			
			DTAsignatura dta = new DTAsignatura();
			
			
			File directory = new File("");// Set as current folder 
			try{
				String path = directory.getCanonicalPath();
				String currentUsersHomeDir = directory.getAbsolutePath();
				String location = request.getClass().getProtectionDomain().getClassLoader().getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			    String nuevo = location;
			System.out.println(nuevo);// Get the standard path 
			System.out.println();// Get Absolute Path 
			}catch(Exception e){}
			
		    
			
			if(dta.existeAsignatura(nombre, codigo)) {
				
			} else {
				response.sendRedirect("layout/edicion-asignatura.jsp?msg=2");
			}
			/*DTExpediente dte = new DTExpediente();
			
			String codigo = request.getParameter("codigo");
			int estado = Integer.parseInt(request.getParameter("estado"));
			
			
			if(dte.deshabilitarExpediente(codigo, estado))
			{
				response.sendRedirect("layout/ver-expediente.jsp?deshabilitado="+estado);
			}
			else
			{
				response.sendRedirect("layout/ver-expediente.jsp?error");
			}*/
		} 
		catch (Exception e) 
		{
			System.err.println("SL ASIGNATURA: Error al guardar asignatura " +e.getMessage());
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
