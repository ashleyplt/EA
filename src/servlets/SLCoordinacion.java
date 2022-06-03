package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTCoordinacion;
import entidades.Coordinacion;

/**
 * Servlet implementation class SLCoordinacion
 */
@WebServlet("/SLCoordinacion")
public class SLCoordinacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLCoordinacion() {
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
		opc = Integer.parseInt(request.getParameter("opcionCoordinacion"));
		
		Coordinacion crd = new Coordinacion();
		DTCoordinacion dtc = new DTCoordinacion();
		
		int id_coordinacion, id_carrera, estado = 0;
		String nombre, telefono = "";
		
		nombre = request.getParameter("nombre");
		telefono = request.getParameter("telefono");
		id_carrera = Integer.parseInt(request.getParameter("id_carrera"));
		estado = Integer.parseInt(request.getParameter("estado"));
		
		crd.setNombre(nombre);
		crd.setTelefono(telefono);
		crd.setId_carrera(id_carrera);
		crd.setEstado(estado);
		
		switch (opc) 
		{
			case 1:
			{
				try 
				{
					
					if(dtc.guardarCoordinacion(crd))
					{
						response.sendRedirect("./layout/ver-coordinacion.jsp");
					}
					else
					{
						response.sendRedirect("coordinacion.jsp?error");
					}
				 
				 
				}
				catch (Exception e) 
				{
					System.err.println("SL Coordinacion: Error al guardar coordinacion " +e.getMessage());
					e.printStackTrace();

				}
				break;
			}
			
			case 2:
			{
				int idCoordinacion = Integer.parseInt(request.getParameter("idcoordinacion"));

				crd.setId(idCoordinacion);
				try {
					if(	dtc.modificarCoordinacion(crd)) {
						response.sendRedirect("layout/ver-vistamaestracoordinacion.jsp?msj=1");
					}
					else {
						response.sendRedirect("layout/ver-vistamaestracoordinacion.jsp?msj=2");
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
