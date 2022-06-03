package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.*;
import vistas.*;

/**
 * Servlet implementation class SLajaxDocentesExp
 */
@WebServlet("/SLajaxDocentesExp")
public class SLajaxDocentesExp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLajaxDocentesExp() {
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String expediente = request.getParameter("expediente");
		String salida = "";
		try
		{
			DTVExpedienteDocente dtv = new DTVExpedienteDocente();
			DTPersonal dtp = new DTPersonal();
			ArrayList<VW_Expediente_Docente> listaPermisos = dtv.getDocentesEdicionAsignatura(expediente);
			if(listaPermisos.isEmpty()) {
				salida = "<div class=\"card container position-relative w-100 bg-light\">\r\n"
						+ "							<label class=\"text-center\"><i\r\n"
						+ "								class=\"fas fa-users-slash position-relative text-center mb-2 mt-2 br-g\"\r\n"
						+ "								style=\"font-size: 1.3rem; color: gray;\"></i> </label>\r\n"
						+ "							<h6 class=\"text-secondary text-center mb-2\">\r\n"
						+ "								No hay docentes impartiendo esta asignatura actualmente.</a>\r\n"
						+ "							</h6>\r\n"
						+ "						</div>";
			} else {
				salida += "<table class=\"table table-hover dt-responsive\">\r\n <tbody id=\"tbody-content\">";
				for (VW_Expediente_Docente ved : listaPermisos)
				{
					System.out.println("Correo: " + dtp.getCorreo(ved.getId_docente()));
					int id = ved.getId_docente();
					DTUsuario dtu = new DTUsuario();
					String img = dtu.getImagenUsuario(dtp.getIdUsuario(id));
					String defaultuser = "dist/imagen/user.png";
					if(img == null) {
						img = defaultuser;
					}else {
						String filename = request.getServletContext().getInitParameter("path");
						File dir = new File(filename + File.separator + img);
						if(!dir.exists()) img = defaultuser;
					}
					String correo = dtp.getCorreo(id);
					salida += "<tr>\r\n"
							+ "							<td>\r\n"
							+ "							<div class=\"row align-items-center\">\r\n"
							+ "								<div class=\"col-2\">\r\n"
							+ "									<img width=\"40\"\r\n"
							+ "									class=\" rounded-circle\"\r\n"
							+ "									src=\"../"+img+"\" style=\"border-radius: 20px 20px; width: 40px; height: 40px\" alt=\"foto\">\r\n"
							+ "								</div>\r\n"
							+ "								<div class=\"col\">\r\n"
							+ "								<strong>"+ved.getDocente()+"</strong>\r\n"
							+ "								<div class=\"text-muted\">"+dtp.getCorreo(ved.getId_docente())+"</div>\r\n"
							+ "								</div>\r\n"
							+ "							</div>\r\n"
							+ "							\r\n"
							+ "							</td>\r\n"
							+ "                         <td>"
							+ "							<div class=\"dropdown\">\r\n"
							+ "								<a href=\"\"\r\n"
							+ "									class=\"btn btn-sm btn-icon btn-trigger\"\r\n"
							+ "									data-toggle=\"dropdown\"><em\r\n"
							+ "									class=\"icon ni fas fa-ellipsis-v\"></em></a>\r\n"
							+ "								<div class=\"dropdown-menu dropdown-menu-right\">\r\n"
							+ "									<ul style=\"list-style: none;\"\r\n"
							+ "										class=\"link-list-plain no-bdr\">\r\n"
							+ "										<li><a href=\"#\" onclick=\"quitardocente("+ved.getId_expdoc()+")\"> <em\r\n"
							+ "												class=\"icon ni fas fa-ban\"></em><span>Quitar docente</span>\r\n"
							+ "										</a></li>\r\n"
							+ "									</ul>\r\n"
							+ "								</div>\r\n"
							+ "							</div>\r\n"
							+ "							</td>\r\n"
							+ "						</tr>";
				}
				salida += "</tbody></table>";
			}
			
		    PrintWriter pw = response.getWriter();
			pw.write(salida);
			pw.flush();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.err.println("SL AJAX DOCENTES EXPEDIENTE: EL ERROR ES: "+e.getMessage());
		}
	}

}
