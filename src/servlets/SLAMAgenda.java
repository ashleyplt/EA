package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTAyudaMemoriaAcuerdo;
import datos.DTAyudaMemoriaAgenda;
import entidades.AyudaMemoriaAcuerdo;
import entidades.AyudaMemoriaAgenda;


/**
 * Servlet implementation class SLAMAgenda
 */
@WebServlet("/SLAMAgenda")
public class SLAMAgenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLAMAgenda() {
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
			DTAyudaMemoriaAgenda dtamg = new DTAyudaMemoriaAgenda();
			
			int id_agenda= Integer.parseInt(request.getParameter("id_agenda"));
			int id_ayuda_memoria = Integer.parseInt(request.getParameter("id_ayuda_memoria"));
			
			if(dtamg.eliminarAyudaMemoriaAgenda(id_agenda))
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
			System.err.println("SLAMAgenda: Error al eliminar Agenda " +e.getMessage());
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
		opc = Integer.parseInt(request.getParameter("opcionAgenda"));
		
		AyudaMemoriaAgenda amag = new AyudaMemoriaAgenda();
		DTAyudaMemoriaAgenda dtamag = new DTAyudaMemoriaAgenda();
		
		int id_ayuda_memoria , id_ayuda_memoria_agenda = 0;
		String agenda = "";
		
		id_ayuda_memoria = Integer.parseInt(request.getParameter("idayudamemoria1"));
		agenda = request.getParameter("agenda");
		amag.setId_ayuda_memoria(id_ayuda_memoria);
		amag.setAgenda(agenda);
		
		switch(opc) {
			case 1:
			{
				try {
					if(dtamag.guardarAyudaMemoriaAgenda(amag)) {
						response.sendRedirect("./layout/datos-ayudadememoria.jsp?idam="+id_ayuda_memoria);
					}else {
						response.sendRedirect("./layout/datos-ayudadememoria.jsp?idam="+id_ayuda_memoria+"&error=0");
					}
				}
				catch (Exception e){
					System.err.println("SLAMAcuerdo: Error al guardar Agenda" +e.getMessage());
					e.printStackTrace();
				}
			
				break;
			}
			
			case 2:
			{
				id_ayuda_memoria_agenda = Integer.parseInt(request.getParameter("idagenda"));

				amag.setId_ayuda_memoria_agenda(id_ayuda_memoria_agenda);
				try {
					if(	dtamag.modificarAyudaMemoriaAgenda(amag)) {
						response.sendRedirect("./layout/datos-ayudadememoria.jsp?idam="+id_ayuda_memoria);
					}
					else {
						response.sendRedirect("./layout/datos-ayudadememoria.jsp?idam="+id_ayuda_memoria+"&error=0");
					}

				}
				catch(Exception e) {
					System.out.println("Error en el servelet : " + e.getMessage());
					e.printStackTrace();
				}

			}
		}
	}

}
