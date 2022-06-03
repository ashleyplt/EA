package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTDepartamento;
import entidades.Departamento;

/**
 * Servlet implementation class SLDepartamento
 */
@WebServlet("/SLDepartamento")
public class SLDepartamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLDepartamento() {
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
		opc = Integer.parseInt(request.getParameter("opcionDepartamento"));
		
		Departamento d = new Departamento();
		DTDepartamento dtd = new DTDepartamento();
		
		int Id_departamento, Id_facultad = 0;
		String nombre, descripcion = "";
		
		nombre = request.getParameter("nombre");
		descripcion = request.getParameter("descripcion");
		Id_facultad = Integer.parseInt(request.getParameter("id_facultad"));
		
		d.setNombre(nombre);
		d.setDescripcion(descripcion);
		d.setId_facultad(Id_facultad);
		
		switch (opc) 
		{
			case 1:
			{
				try 
				{
					
					if(dtd.guardarDepartamento(d))
					{
						response.sendRedirect("./layout/ver-departamento.jsp");
					}
					else
					{
						response.sendRedirect("departamento.jsp?error");
					}
				 
				 
				}
				catch (Exception e) 
				{
					System.err.println("SL Departamento: Error al guardar departamento " +e.getMessage());
					e.printStackTrace();

				}
				break;
			}
			
			case 2:
			{
				int idDepartamento = Integer.parseInt(request.getParameter("iddepartamento"));

				d.setId(idDepartamento);
				try 
				{
					if(dtd.modificarDepartamento(d)) {
						response.sendRedirect("layout/ver-departamento.jsp?msj=1");
					}
					else {
						response.sendRedirect("layout/ver-departamento.jsp?msj=2");
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
