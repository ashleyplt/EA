package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTValoracionActividad;
import entidades.ValoracionActividad;

/**
 * Servlet implementation class SLValoracionActividad
 */
@WebServlet("/SLValoracionActividad")
public class SLValoracionActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLValoracionActividad() {
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

		DTValoracionActividad dtva = new DTValoracionActividad();
		ValoracionActividad va = new ValoracionActividad();
		
		int id_actividad = Integer.parseInt(request.getParameter("id_actividad"));
		int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
		int valor = Integer.parseInt(request.getParameter("temp"));
		String comentario = request.getParameter("comentario");
		
		va.setId_actividades(id_actividad);
		va.setId_usuario(id_usuario);
		va.setValor(valor);
		va.setComentario(comentario);
		
		if(dtva.guardarValoracion(va)) {
			response.sendRedirect("layout/vista-actividad.jsp?msj=1");
		}else {
			response.sendRedirect("layout/vista-actividad.jsp?msj=2");
		}	
	}

}
