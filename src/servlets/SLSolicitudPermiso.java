package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.DTEnviarCorreo;
import datos.DTVCargoPersonal;
import datos.DTVDatosPersonal;
import datos.DTVExpedienteCarrera;
import datos.DTVPersonalUsuario;
import vistas.VW_Cargo_Personal;
import vistas.VW_Datos_Personal;

/**
 * Servlet implementation class SLEnviarCorreo
 */
@WebServlet("/SLSolicitudPermiso")
public class SLSolicitudPermiso extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SLSolicitudPermiso() {
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
		
		request.setCharacterEncoding("utf-8");
		
		String motivo = request.getParameter("motivo").trim();
        String expediente = request.getParameter("expediente");
        
        HttpSession usuario = request.getSession();
    	String nombre = (String) usuario.getAttribute("nombre_user");
        DTVDatosPersonal dtvdp = new DTVDatosPersonal();
		VW_Datos_Personal personal = dtvdp.getDatosPersonal(nombre);
		
		DTVExpedienteCarrera dtvec = new DTVExpedienteCarrera();
		String carrera = dtvec.getCarrera(expediente);
		
		DTVCargoPersonal dtvcp = new DTVCargoPersonal();
		VW_Cargo_Personal cp = dtvcp.getCoordinadorCarrera(carrera);
		
		String destinatario = "adamary.rivas8799@est.uca.edu.ni";
        
        DTEnviarCorreo dtec = new DTEnviarCorreo();
        try {
        	if(dtec.enviarEmailSolicitud(personal.getNombre(), personal.getCorreo(), destinatario, expediente, motivo)) {
            	usuario.setAttribute("error", "1");
        		response.sendRedirect("layout/solicitud-expediente.jsp");
            } else {
            	usuario.setAttribute("error", "2");
            	response.sendRedirect("layout/index.jsp");
            }
		} catch (Exception e) {
			System.out.println("SLSolicitudPermiso, el error es: " + e.getMessage());
			e.printStackTrace();
		}
        
 
        
 
    }
}
