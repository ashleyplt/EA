package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTCarrera;
import entidades.Carrera;

/**
 * Servlet implementation class SLCarrera
 */
@WebServlet("/SLCarrera")
public class SLCarrera extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLCarrera() {
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
		opc = Integer.parseInt(request.getParameter("opcionCarrera"));
		
		Carrera c = new Carrera();
		DTCarrera dtc = new DTCarrera();
		
		int Id_facultad, estado, Id_departamento = 0;
		String nombre = "";
		
		nombre = request.getParameter("nombre");
		estado = Integer.parseInt(request.getParameter("estado"));
		Id_facultad = Integer.parseInt(request.getParameter("id_facultad"));
		Id_departamento = Integer.parseInt(request.getParameter("id_departamento"));
		c.setNombre(nombre);
		c.setEstado(estado);
		c.setId_facultad(Id_facultad);
		c.setId_departamento(Id_departamento);
		
		switch(opc) {
			case 1: 
			{
				try 
				{
					
					if(dtc.guardarCarrera(c))
					{
						response.sendRedirect("./layout/ver-carrera.jsp");
					}
					else
					{
						response.sendRedirect("carrera.jsp?error");
					}
				 
				 
				}
				catch (Exception e) 
				{
					System.err.println("SL Carrera: Error al guardar carrera " +e.getMessage());
					e.printStackTrace();

				}
				break;
			}
			
			case 2:
			{
				int idCarrera = Integer.parseInt(request.getParameter("idcarrera"));

				c.setId_carrera(idCarrera);
				try {
					if(	dtc.modificarCarrera(c)) {
						response.sendRedirect("layout/ver-vistamaestracarrera.jsp?msj=1");
					}
					else {
						response.sendRedirect("layout/ver-vistamaestracarrera.jsp?msj=2");
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
