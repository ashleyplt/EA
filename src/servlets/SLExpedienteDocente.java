package servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.*;
import entidades.*;

/**
 * Servlet implementation class SLExpedienteDocente
 */
@WebServlet("/SLExpedienteDocente")
public class SLExpedienteDocente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ExpedienteDocente ed;
	private DTExpedienteDocente dted;
	HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLExpedienteDocente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dted = new DTExpedienteDocente();
		session = request.getSession();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		if(dted.quitarDocente(id)) {
			response.sendRedirect("layout/index.jsp");
		} else {
			session.setAttribute("error", "Hubo un error al quitar al docente.");
			response.sendRedirect("layout/index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		session = request.getSession();
		ed = new ExpedienteDocente();
		dted = new DTExpedienteDocente();
		
		int idasignatura = Integer.parseInt(request.getParameter("idexpediente"));
		String asignatura = request.getParameter("expediente");
		
		DTEdicionAsignatura dteda = new DTEdicionAsignatura();
		EdicionAsignatura eda = dteda.getEdicionActiva();
		
		ExpedienteAsignatura ea = new ExpedienteAsignatura();
		DTExpedienteAsignatura dtea = new DTExpedienteAsignatura();
		ea.setId_asignatura(idasignatura);
		ea.setId_edicion(eda.getId());
		
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		if(eda.getFecha_inicio().before(date) || eda.getFecha_inicio().toString().equals(date.toString())) ea.setEstado(1);
		if(eda.getFecha_inicio().after(date)) ea.setEstado(0);
		
		int idexpa = dtea.getId(idasignatura, ea.getId_edicion());
//		File parent = new File(getServletContext().getRealPath("")).getParentFile();
//		String path = parent.getPath() + File.separator + "eafiles" + File.separator + "expedientes" + File.separator + "expediente_"+idasignatura;
//		File dir = new File(path);
//		if(!dir.exists()) dir.mkdirs();
		//Verifica si la edición de expediente ya existe
		if(idexpa == 0) {
			if(dtea.guardarExpedienteAsignatura(ea)) {
				idexpa = dtea.getId(ea.getId_asignatura(), ea.getId_edicion());
			}
		}
//		path += File.separator + "edicion_" + idexpa;
//		dir = new File(path);
//		if(!dir.exists()) dir.mkdirs();
		
		//Creación de la estructura de expediente
//		String tmp = path;
//		path += File.separator + "actividades_virtuales";
//		tmp += File.separator + "documentos_academicos";
//		dir = new File(tmp);
//		if(!dir.exists()) dir.mkdirs();
//		dir = new File(path);
//		if(!dir.exists()) dir.mkdirs();
		
		//Asignación de docentes a una edición de asignatura
		String[] listaPersonal = request.getParameterValues("docente");
		ed.setId_expediente(idexpa);
		int cont = 0;
		boolean error = true;
		while(cont < listaPersonal.length){
			ed.setId_personal(Integer.parseInt(listaPersonal[cont]));
			if(dted.guardarAsignatura(ed)) {
				cont++;
				error = false;
			} else {
				error = true;
				cont = listaPersonal.length;
				session.setAttribute("error", "Hubo un error al asignar los docentes en la edición del expediente.");
				response.sendRedirect("layout/docente-expediente.jsp?expediente="+asignatura);
			}
		}
		if(!error) {
			response.sendRedirect("layout/index.jsp");
		}
		
	}

}
