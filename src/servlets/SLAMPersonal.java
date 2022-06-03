package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTAyudaMemoriaAsistente;
import entidades.AyudaMemoriaAsistentes;

/**
 * Servlet implementation class SLAMPersonal
 */
@WebServlet("/SLAMPersonal")
public class SLAMPersonal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLAMPersonal() {
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
			DTAyudaMemoriaAsistente dtamp = new DTAyudaMemoriaAsistente();
			
			int id_asistente= Integer.parseInt(request.getParameter("id_personal"));
			int id_ayuda_memoria = Integer.parseInt(request.getParameter("id_ayuda_memoria"));
			
			if(dtamp.eliminarAyudaMemoriaAsistente(id_asistente))
			{
				response.sendRedirect("./layout/datos-ayudadememoria.jsp?idam="+id_ayuda_memoria);
			}
			else
			{
				response.sendRedirect("./layout/datos-ayudadememoria.jsp?idam="+id_ayuda_memoria+"&error=0");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SLAMAcuerdo: Error al eliminar asistente " +e.getMessage());
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
		opc = Integer.parseInt(request.getParameter("opcionParticipante"));
		
		AyudaMemoriaAsistentes amast = new AyudaMemoriaAsistentes();
		DTAyudaMemoriaAsistente dtamast = new DTAyudaMemoriaAsistente();
		
		int id_ayuda_memoria, id_ayuda_asistentes, id_personal = 0;
		id_ayuda_memoria = Integer.parseInt(request.getParameter("idayudamemoria"));
		id_personal = Integer.parseInt(request.getParameter("idpersonal"));
		amast.setId_ayuda_memoria(id_ayuda_memoria);
		amast.setId_personal(id_personal);
		
		switch(opc) 
		{
			case 1:
			{
				try {
					if(dtamast.guardarAyudaMemoriaAsistentes(amast)) {
						response.sendRedirect("./layout/datos-ayudadememoria.jsp?idam="+id_ayuda_memoria);
					}else {
						response.sendRedirect("./layout/datos-ayudadememoria.jsp?idam="+id_ayuda_memoria+"&error=0");
					}
					
				}
				catch(Exception e)
				{
					System.err.println("SLAMPersonal: Error al guardar personal" +e.getMessage());
					e.printStackTrace();
				}
				break;
			}
		}
	}

}
