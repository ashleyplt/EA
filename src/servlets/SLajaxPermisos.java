package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTPersonal;
import datos.DTVPermisosExpediente;
import vistas.VW_Permisos_Expediente;

/**
 * Servlet implementation class SLajaxPermisos
 */
@WebServlet("/SLajaxPermisos")
public class SLajaxPermisos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLajaxPermisos() {
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
		//response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String expediente = request.getParameter("expediente");
		String salida = "";
		try
		{
			DTVPermisosExpediente dtv = new DTVPermisosExpediente();
			DTPersonal dtp = new DTPersonal();
			ArrayList<VW_Permisos_Expediente> listaPermisos = dtv.listarPermisosSegunExpediente(expediente);
			if(listaPermisos.isEmpty()) {
				salida = "<div class=\"card container position-relative w-100 bg-light\">\r\n"
						+ "							<label class=\"text-center\"><i\r\n"
						+ "								class=\"fas fa-users-slash position-relative text-center mb-2 mt-2 br-g\"\r\n"
						+ "								style=\"font-size: 1.3rem; color: gray;\"></i> </label>\r\n"
						+ "							<h6 class=\"text-secondary text-center mb-2\">\r\n"
						+ "								No hay usuarios que tengan acceso a este expediente.</a>\r\n"
						+ "							</h6>\r\n"
						+ "						</div>";
			} else {
				salida += "<table class=\"table table-hover dt-responsive\">\r\n <tbody id=\"tbody-content\">";
				for (VW_Permisos_Expediente vpe : listaPermisos)
				{
					System.out.println("Correo: " + dtp.getCorreo(vpe.getId_personal()));
					int id = vpe.getId_personal();
					String correo = dtp.getCorreo(id);
					salida += "<tr>\r\n"
							+ "							<td>\r\n"
							+ "							<div class=\"row align-items-center\">\r\n"
							+ "								<div class=\"col-2\">\r\n"
							+ "									<img width=\"40\"\r\n"
							+ "									class=\" rounded-circle\"\r\n"
							+ "									src=\"../dist/assets/images/avatars/1.jpg\" alt=\"\">\r\n"
							+ "								</div>\r\n"
							+ "								<div class=\"col\">\r\n"
							+ "								<strong>"+vpe.getDocente()+"</strong>\r\n"
							+ "								<div class=\"text-muted\">"+dtp.getCorreo(vpe.getId_personal())+"</div>\r\n"
							+ "								</div>\r\n"
							+ "							</div>\r\n"
							+ "							\r\n"
							+ "							</td>\r\n"
							+ "							<td>"+vpe.getTipo_permiso()+"</td>\r\n"
							+ "							<td>							\r\n"
							+ "							<div class=\"dropdown\">\r\n"
							+ "								<a href=\"\"\r\n"
							+ "									class=\"btn btn-sm btn-icon btn-trigger\"\r\n"
							+ "									data-toggle=\"dropdown\"><em\r\n"
							+ "									class=\"icon ni fas fa-ellipsis-v\"></em></a>\r\n"
							+ "								<div class=\"dropdown-menu dropdown-menu-right\">\r\n"
							+ "									<ul style=\"list-style: none;\"\r\n"
							+ "										class=\"link-list-plain no-bdr\">\r\n"
							+ "										<li><a href=\"#\" onclick=\"optionsmodal("+vpe.getId_permiso()+", 1)\"> <em\r\n"
							+ "												class=\"icon ni fas fa-info-circle\"></em><span>Ver detalles</span>\r\n"
							+ "										</a></li>\r\n"
							+ "										<li><a href=\"#\" onclick=\"optionsmodal("+vpe.getId_permiso()+", 2)\"> <em\r\n"
							+ "												class=\"icon ni fas fa-ban\"></em><span>Quitar permiso</span>\r\n"
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
			System.err.println("SL AJAX PERMISOS: EL ERROR ES: "+e.getMessage());
		}
	}

}
