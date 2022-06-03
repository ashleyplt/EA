package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.DTEdicionAsignatura;
import entidades.EdicionAsignatura;

/**
 * Servlet implementation class SLEdicionAsignatura
 */
@WebServlet("/SLEdicionAsignatura")
public class SLEdicionAsignatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLEdicionAsignatura() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try 
		{
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			DTEdicionAsignatura dte = new DTEdicionAsignatura();
			EdicionAsignatura ea = new EdicionAsignatura();
			
			String edicion = request.getParameter("edicion").trim();
			String fechas = request.getParameter("daterange").trim();
			
			String[] rangoFechas = fechas.split(" - ");
			Date fecha_inicio, fecha_fin;
			
			fecha_inicio = Date.valueOf(rangoFechas[0]);
			fecha_fin = Date.valueOf(rangoFechas[1]);
			
			EdicionAsignatura activo = dte.getEdicionActiva();
			
			if(activo != null) {
				ea.setId(Integer.parseInt(request.getParameter("id")));
				ea.setNombre(edicion);
				ea.setFecha_inicio(fecha_inicio);
				ea.setFecha_cierre(fecha_fin);
				boolean guardar = false;

				if(activo.getNombre().equals(edicion)) {
					guardar = true;
				} else {
					if(!dte.existeEdicionAsignatura(edicion)) {
						guardar = true;
					} else {
						session.setAttribute("error", "Ya existe una edición con ese nombre.");
						response.sendRedirect("layout/edicion-asignatura.jsp");
					}
				}
				
				if(guardar) {
					if(dte.modificarEdicionAsignatura(ea)) {
						response.sendRedirect("layout/edicion-asignatura.jsp");
					} else {
						session.setAttribute("error", "Hubo un error al modificar la edición.");
						response.sendRedirect("layout/edicion-asignatura.jsp");
					}
				}
			} else {
				if(!dte.existeEdicionAsignatura(edicion))
				{
					ea.setNombre(edicion);
					ea.setFecha_inicio(fecha_inicio);
					ea.setFecha_cierre(fecha_fin);
					if(dte.guardarEdicion(ea)) {
						response.sendRedirect("layout/edicion-asignatura.jsp");
					} else {
						session.setAttribute("error", "Hubo un error al guardarse la edición.");
						response.sendRedirect("layout/edicion-asignatura.jsp");
					}
				}
				else
				{
					session.setAttribute("error", "Ya existe una edición con ese nombre.");
					response.sendRedirect("layout/edicion-asignatura.jsp");
				}
			}
			
			
		} 
		catch (Exception e) 
		{
			System.err.println("SL EXPEDIENTE: Error al deshabilitar expediente " +e.getMessage());
			e.printStackTrace();
		}
	}

}
