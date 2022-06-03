package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import datos.DTAyudaMemoriaAcuerdo;
import entidades.AyudaMemoriaAcuerdo;

/**
 * Servlet implementation class SLAMAcuerdo
 */
@WebServlet("/SLAMAcuerdo")
public class SLAMAcuerdo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLAMAcuerdo() {
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
			DTAyudaMemoriaAcuerdo dtamc = new DTAyudaMemoriaAcuerdo();
			
			int id_acuerdo= Integer.parseInt(request.getParameter("id_acuerdo"));
			int id_ayuda_memoria = Integer.parseInt(request.getParameter("id_ayuda_memoria"));
			
			if(dtamc.eliminarAyudaMemoriaAcuerdo(id_acuerdo))
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
			System.err.println("SLAMAcuerdo: Error al eliminar acuerdo " +e.getMessage());
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
		opc = Integer.parseInt(request.getParameter("opcionAcuerdo"));
		
		AyudaMemoriaAcuerdo amac = new AyudaMemoriaAcuerdo();
		DTAyudaMemoriaAcuerdo dtamac = new DTAyudaMemoriaAcuerdo();
		
		int id_ayuda_memoria , id_ayuda_memoria_acuerdo = 0;
		String acuerdo = "";
		
		id_ayuda_memoria = Integer.parseInt(request.getParameter("idayudamemoria"));
		acuerdo = request.getParameter("acuerdo");
		amac.setId_ayuda_memoria(id_ayuda_memoria);
		amac.setAcuerdo(acuerdo);
		
		switch(opc) {
			case 1:
			{
				try {
					if(dtamac.guardarAyudaMemoriaAcuerdo(amac)) {
						response.sendRedirect("./layout/datos-ayudadememoria.jsp?idam="+id_ayuda_memoria);
					}else {
						response.sendRedirect("./layout/datos-ayudadememoria.jsp?idam="+id_ayuda_memoria+"&error=0");
					}
				}
				catch (Exception e){
					System.err.println("SLAMAcuerdo: Error al guardar Acuerdo" +e.getMessage());
					e.printStackTrace();
				}
			
				break;
			}
			
			case 2:
			{
				id_ayuda_memoria_acuerdo = Integer.parseInt(request.getParameter("idacuerdo"));

				amac.setId_ayuda_memoria_acuerdo(id_ayuda_memoria_acuerdo);
				try {
					if(	dtamac.modificarAyudaMemoriaAcuerdo(amac)) {
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
