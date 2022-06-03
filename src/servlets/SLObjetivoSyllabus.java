package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTCarrera;
import datos.DTObjetivoSyllabus;
import entidades.ObjetivoSyllabus;

/**
 * Servlet implementation class SLObjetivoSyllabus
 */
@WebServlet("/SLObjetivoSyllabus")
public class SLObjetivoSyllabus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLObjetivoSyllabus() {
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
			DTObjetivoSyllabus dtos = new DTObjetivoSyllabus();
			
			int id_objetivo= Integer.parseInt(request.getParameter("id_objetivo"));
			int id_syllabus = Integer.parseInt(request.getParameter("id_syllabus"));
			
			if(dtos.eliminarObjetivo(id_objetivo))
			{
				response.sendRedirect("./layout/crear-objetivo.jsp?idsyllabus="+id_syllabus);
			}
			else
			{
				response.sendRedirect("./layout/crear-objetivo.jsp?idsyllabus="+id_syllabus+"&error=0");
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
		
		request.setCharacterEncoding("UTF-8");
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcionObjetivo"));
		
		ObjetivoSyllabus os = new ObjetivoSyllabus();
		DTObjetivoSyllabus dtos = new DTObjetivoSyllabus();
		
		int id_objetivo, id_syllabus = 0;
		String objetivo = "";
		
		id_syllabus = Integer.parseInt(request.getParameter("id_Syllabus"));
		objetivo = request.getParameter("objetivo");
		
		os.setId_syllabus(id_syllabus);
		os.setObjetivo(objetivo);
		
		switch(opc) {
			case 1:
			{
				try {
					if(dtos.guardarObjetivo(os)) {
						response.sendRedirect("./layout/crear-objetivo.jsp?idsyllabus="+id_syllabus);
					}else {
						response.sendRedirect("./layout/crear-objetivo.jsp?idsyllabus="+id_syllabus+"&error=0");
					}
				}
				catch (Exception e){
					System.err.println("SL ObjetivoSyllabus: Error al guardar Objetivo de Syllabus " +e.getMessage());
					e.printStackTrace();
				}
			
				break;
			}
			
			case 2:
			{
				int idObjetivo = Integer.parseInt(request.getParameter("id_Objetivo"));

				os.setId_objetivo(idObjetivo);
				try {
					if(	dtos.modificarObjetivo(os)) {
						response.sendRedirect("./layout/crear-objetivo.jsp?idsyllabus="+id_syllabus);
					}
					else {
						response.sendRedirect("./layout/crear-objetivo.jsp?idsyllabus="+id_syllabus+"&error=0");
					}

				}
				catch(Exception e) {
					System.out.println("Error en el servelet : " + e.getMessage());
					e.printStackTrace();
				}

				break;
			}
		}
	}

}
