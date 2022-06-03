package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.DTValoracion;
import entidades.Valoracion;

/**
 * Servlet implementation class SLValoracion
 */
@WebServlet("/SLValoracion")
public class SLValoracion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLValoracion() {
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

		HttpSession session = request.getSession();
		


		try {
			
		
		
		
		String periodo = (String) session.getAttribute("nombre_edicion") ;
		String nombre  = (String) session.getAttribute("nombre_usuario_completo");
		String asignatura =  (String) session.getAttribute("nombre_expediente") ;
		String seccion = request.getParameter("seccion"); 
		
		
		int total = Integer.parseInt(request.getParameter("total"));
		int totalr = Integer.parseInt(request.getParameter("totalr"));
		int totala = Integer.parseInt(request.getParameter("totala"));
		int sdc = Integer.parseInt(request.getParameter("sdc"));
				
		String valoracion = request.getParameter("valoracion"); 
		String valoracionC = request.getParameter("valoracionC"); 
		String valoraionCon = request.getParameter("valoracionCon"); 
		
		String valoracionMet = request.getParameter("valoracionMet"); 
		String valoracionT = request.getParameter("valoracionT"); 
		String valoracionP = request.getParameter("valoracionP"); 
		String otros = request.getParameter("otros"); 
		
		if (otros == "") {
			otros = "Ninguno";
		}
		
		Valoracion val = new Valoracion();
		
		DTValoracion dtv = new DTValoracion();
		
		val.setPeriodo(periodo);
		val.setNombre(nombre);
		val.setAsignatura(asignatura);
		val.setSeccion(seccion);
		
		val.setTotal(total);
		val.setTotalr(totalr);
		val.setTotala(totala);
		val.setSdc(sdc);
		
		val.setValoracion(valoracion);
		val.setValoracionC(valoracionC);
		val.setValoracionCon(valoraionCon);
		
		val.setValoracionMet(valoracionMet);
		val.setValoracionT(valoracionT);
		val.setValoracionP(valoracionP);
		val.setOtros(otros);

		if(dtv.guardarValoracion(val))
		{
			session.setAttribute("estado_valoracion", "exito");

			response.sendRedirect("layout/expediente-archivos.jsp?id=2");
		}
		else
		{
			session.setAttribute("estado_valoracion", "error");

			response.sendRedirect("layout/expediente-archivos.jsp?id=2"); 
		}
}catch(Exception e) {

	session.setAttribute("estado_valoracion", "error");

	response.sendRedirect("layout/expediente-archivos.jsp?id=2");
		}

	}

}
