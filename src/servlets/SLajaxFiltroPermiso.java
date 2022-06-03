package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringEscapeUtils;

import datos.DTVCargoPersonal;
import datos.DTVExpedienteDocente;
import datos.DTVPermisosExpediente;
import datos.DTVPersonalUsuario;
import vistas.VW_Cargo_Personal;

/**
 * Servlet implementation class SLajaxFiltroPermiso
 */
@WebServlet("/SLajaxFiltroPermiso")
public class SLajaxFiltroPermiso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLajaxFiltroPermiso() {
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String expediente = request.getParameter("expediente");
		String lugar = request.getParameter("lugar");
		String cargo = request.getParameter("cargo");
		
		if(cargo == null) cargo = "";
		if(lugar == null) lugar = "";
		
		DTVCargoPersonal dtcp = new DTVCargoPersonal();
		ArrayList<VW_Cargo_Personal> listaFiltro = dtcp.filtrarPersonalPermiso(lugar, cargo);
		
		DTVPermisosExpediente dtpe = new DTVPermisosExpediente();
		
		//Para evitar duplicados de personales
		ArrayList<VW_Cargo_Personal> personales = new ArrayList<>();
		int cont = 0;;
		for (VW_Cargo_Personal cp : listaFiltro) {
			if(personales.isEmpty()) personales.add(cp);
			String idpers = String.valueOf(personales.get(cont).getId_personal());
			if (!idpers.equals(String.valueOf(cp.getId_personal()))) {
				personales.add(cp);
				cont++;
			}
			
		}
		
		//Evitar mostrar personal si este imparte esa asignatura
		DTVExpedienteDocente dted = new DTVExpedienteDocente();
		
		//Evitar mostrar el personal activo de la sesión
		HttpSession usuario = request.getSession();
		String nombre = usuario.getAttribute("nombre_user").toString();

		DTVPersonalUsuario dtvpu = new DTVPersonalUsuario();
		int idactivo = dtvpu.buscaridPersonal(nombre);
		
		String salida = "";
		try
		{
			if(listaFiltro.isEmpty()) {
				salida +="<option selected=\"selected\" value=\"\"> No hay docentes.</option>";
			} else {
				for (VW_Cargo_Personal vpe : personales)
				{
					String personal = vpe.getNombre() + " " + vpe.getApellido();
					if(!dtpe.existePermiso(expediente, vpe.getId_personal()) && !dted.existeDocenteExpediente(expediente, vpe.getId_personal()) && vpe.getId_personal() != idactivo) {
						salida +="<option value=\""+vpe.getId_personal()+"\">"+StringEscapeUtils.escapeHtml4(personal)+"</option>";
					}
				}
			}
			
			if(salida.equals("")) salida +="<option  selected=\"selected\" value=\"\"> No hay docentes.</option>";
			
		    PrintWriter pw = response.getWriter();
			pw.write(salida);
			pw.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("SL AJAX FILTRO PERMISOS: EL ERROR ES: "+e.getMessage());
		}
	}

}
