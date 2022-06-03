package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTPlanDeEstudio;

/**
 * Servlet implementation class SLEliminarPlanDeEstudio
 */
@WebServlet("/SLEliminarPlanDeEstudio")
public class SLEliminarPlanDeEstudio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEliminarPlanDeEstudio() {
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
			DTPlanDeEstudio dtpde = new DTPlanDeEstudio();
			
			int id_plan_estudio= Integer.parseInt(request.getParameter("id_plan_estudio"));
			int estado = Integer.parseInt(request.getParameter("estado"));
			
			
			if(dtpde.eliminarPlanDeEstudio(id_plan_estudio, estado))
			{
				response.sendRedirect("layout/ver-vistamaestraplandeestudio.jsp?eliminado="+estado);
			}
			else
			{
				response.sendRedirect("layout/ver-plandeestudio.jsp?error");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL CARRERA: Error al eliminar plan de estudio" +e.getMessage());
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
