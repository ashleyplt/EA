package servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTPermisosAsignatura;
import entidades.PermisosAsignatura;

/**
 * Servlet implementation class SLPermisosAsignatura
 */
@WebServlet("/SLPermisosAsignatura")
public class SLPermisosAsignatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int opc;
	private PermisosAsignatura pa;
	private DTPermisosAsignatura dtpa;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLPermisosAsignatura() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		opc = Integer.parseInt(request.getParameter("opc"));
		
		pa = new PermisosAsignatura();
		dtpa = new DTPermisosAsignatura();
		
		int id = Integer.parseInt(request.getParameter("idpermiso"));
		
		pa.setId(id);
		if(dtpa.quitarPermiso(pa)) {
			response.sendRedirect("layout/index.jsp");
		} else {
			response.sendRedirect("layout/permisos-expediente.jsp?msg=1");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		opc = Integer.parseInt(request.getParameter("opc"));
		
		pa = new PermisosAsignatura();
		dtpa = new DTPermisosAsignatura();
		
		String fechas = request.getParameter("daterange");
		int asignatura = Integer.parseInt(request.getParameter("idexpediente"));
		
		int permiso = Integer.parseInt(request.getParameter("tpermiso"));
		
		String[] rangoFechas = fechas.split(" - ");
		Date fecha_inicio, fecha_final;
		
		fecha_inicio = Date.valueOf(rangoFechas[0]);
		fecha_final = Date.valueOf(rangoFechas[1]);
		
		pa.setFecha_inicio(fecha_inicio);
		pa.setFecha_final(fecha_final);
		pa.setTipo_permiso(permiso);
		
		switch(opc) {
		case 1: {
			String[] listaPersonal = request.getParameterValues("docente");
			int cont = 0;
			pa.setId_asignatura(asignatura);
			while (cont < listaPersonal.length) {
				pa.setId_personal(Integer.parseInt(listaPersonal[cont]));
				if(dtpa.guardarPermiso(pa)) {
					response.sendRedirect("layout/index.jsp");
					cont++;
				} else {
					response.sendRedirect("layout/permisos-expediente.jsp?msg=1");
					break;
				}
			}
			break;
		}
		case 2: {
			int id = Integer.parseInt(request.getParameter("idpermiso"));
			pa.setId(id);
			if(dtpa.modificarPermiso(pa)) {
				response.sendRedirect("layout/index.jsp");
			} else {
				response.sendRedirect("layout/permisos-expediente.jsp?msg=1");
			}
			break;
		}
		}
		
	}

}
