package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SLCreacionSyllabus
 */
@WebServlet("/SLCreacionSyllabus")
public class SLCreacionSyllabus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLCreacionSyllabus() {
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
		int pagina = Integer.parseInt(request.getParameter("pagina2"));
		String expediente = request.getParameter("expediente");
		if(pagina==4) {
			int opcion = Integer.parseInt(request.getParameter("confirmacion"));
			if(opcion==0) {
				response.sendRedirect("./layout/create-Syllabus.jsp?expediente="+expediente+"&pagina="+pagina+"");
			}else {
				pagina+=1;
				response.sendRedirect("./layout/create-Syllabus.jsp?expediente="+expediente+"&pagina="+pagina+"");
			}
		}else{
		pagina+=1;
		response.sendRedirect("./layout/create-Syllabus.jsp?expediente="+expediente+"&pagina="+pagina+"");
		}
	}

}
