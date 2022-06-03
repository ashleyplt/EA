package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import datos.DTSyllabus;
import entidades.Syllabus;

/**
 * Servlet implementation class SLSyllabus
 */
@WebServlet("/SLSyllabus")
public class SLSyllabus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLSyllabus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		try {
			DTSyllabus dts = new DTSyllabus();
			int id_syllabus = Integer.parseInt(request.getParameter("id_syllabus"));
			if(dts.eliminarSyllabus(id_syllabus)) {
				response.sendRedirect("./layout/expediente-archivos.jsp?id=2");
			}
			else {
				response.sendRedirect("./layout/expediente-archivos.jsp?id=2?error");
			}
		}catch(Exception e) {
			System.err.println("SLSyllabus: Error al eliminar syllabus " +e.getMessage());
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
		opc = Integer.parseInt(request.getParameter("opcionSyllabus"));
		
		Syllabus s = new Syllabus();
		DTSyllabus dts = new DTSyllabus();
		
		int id_syllabus, id_expediente, id_carrera, estado= 0;
		Date fecha_creacion, fecha_entrega, fecha_modificacion;
		
		id_expediente = Integer.parseInt(request.getParameter("id_expediente"));
		id_carrera = Integer.parseInt(request.getParameter("id_carrera"));
		
		
		s.setId_expediente_asignatura(id_expediente);
		s.setId_carrera(id_carrera);
		switch(opc) {
			case 1:{
				try {
					if(dts.guardarSyllabus(s)) {
						response.sendRedirect("./layout/expediente-archivos.jsp?id=2");
					}
					else {
						response.sendRedirect("./layout/expediente-archivos.jsp?id=2?error");
					}
				}catch(Exception e) {
					System.err.println("SL Syllabus: Error al guardar Syllabus " +e.getMessage());
					e.printStackTrace();
				}
				break;
			}
			
			case 2:{
				id_syllabus = Integer.parseInt(request.getParameter("id_syllabus"));
				s.setId_syllabus(id_syllabus);
				try {
					if(dts.modificarSyllabus(s)) {
						response.sendRedirect("./layout/expediente-archivos.jsp?id=2");
					}else {
						response.sendRedirect("./layout/expediente-archivos.jsp?id=2?error=2");
					}
				}catch (Exception e){
					System.err.println("SL Syllabus: Error al guardar Syllabus " +e.getMessage());
					e.printStackTrace();
				}
				break;
			}
		}
	}

}
