package servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.DTActividad;
import datos.DTAgenda;
import datos.DTArchivos;
import entidades.Actividad;
import entidades.Agenda;
import entidades.Archivo;

/**
 * Servlet implementation class JSAgenda
 */
@WebServlet("/SLAgenda")
public class SLAgenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLAgenda() {
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
	//	doGet(request, response);

		request.setCharacterEncoding("UTF-8");
		//int opc = Integer.parseInt(request.getParameter("opcion"));


		HttpSession session = request.getSession();
		String 	edicion = (String) session.getAttribute("nombre_edicion") ;
		Date 	f1 = (Date) session.getAttribute("f1") ;
		Date 	f2 = (Date) session.getAttribute("f2") ;
		String intro = request.getParameter("intro2");
		Agenda ag = new Agenda();
		DTAgenda dta = new DTAgenda();

		//int id_carpeta = 0;
		
		//int id_carpeta =  Integer.parseInt(request.getParameter("carpeta"));

		/*ar.setNombre(Nombre);
		ar.setNombre_archivo(Nombre_archivo);
		ar.setNombre_edicion(Nombre_edicion);
		ar.setTipo(Tipo);
		ar.setId_carpeta(id_carpeta);
		ar.setEstado(estado);*/
		
		ag.setEdicion(edicion);
		ag.setEstado(1);
		ag.setFecha_final(f1);
		ag.setFecha_inicio(f2);
		ag.setId_syllabus(9);
		ag.setNum_agenda(1);
		if(dta.gardarAgenda(ag))
		{
			session.setAttribute("estado_agenda", "exito");

			response.sendRedirect("layout/expediente-archivos.jsp?id=2&&error=0");
		}
		else
		{
			session.setAttribute("estado_agenda", "error");

			response.sendRedirect("layout/expediente-archivos.jsp?id=2&&error=1"); 
		}

	} 
		
		
	}

 
