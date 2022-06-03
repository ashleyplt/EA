package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

import datos.DTPlanDeEstudio;
import entidades.PlanDeEstudio;

/**
 * Servlet implementation class SLPlanDeEstudio
 */
@WebServlet("/SLPlanDeEstudio")
public class SLPlanDeEstudio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLPlanDeEstudio() {
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
		request.setCharacterEncoding("UTF-8");
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcionPlanDeEstudio"));
		
		PlanDeEstudio pde = new PlanDeEstudio();
		DTPlanDeEstudio dtpde = new DTPlanDeEstudio();
		
		int estado = 0;
		String nombre = "";
		Date fecha_creacion;
		
		nombre = request.getParameter("nombre");
		estado = Integer.parseInt(request.getParameter("estado"));
		
		pde.setNombre(nombre);
		pde.setEstado(estado);
		
		switch (opc) 
		{
			case 1:
			{
				try 
				{
					
					if(dtpde.guardarPlanDeEstudio(pde))
					{
						response.sendRedirect("./layout/ver-plandeestudio.jsp");
					}
					else
					{
						response.sendRedirect("PlanDeEstudio.jsp?error");
					}
				 
				 
				}
				catch (Exception e) 
				{
					System.err.println("SL Plan de Estudio: Error al guardar Plan de Estudio " +e.getMessage());
					e.printStackTrace();

				}
				break;
			}
			
			case 2:
			{
				int id_plan_de_estudio = Integer.parseInt(request.getParameter("idplandeestudio"));

				pde.setId_plan_estudio(id_plan_de_estudio);
				try {
					if(	dtpde.modificarPlanDeEstudio(pde)) {
						response.sendRedirect("layout/ver-plandeestudio.jsp?msj=1");
					}
					else {
						response.sendRedirect("layout/ver-plandeestudio.jsp?msj=2");
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
