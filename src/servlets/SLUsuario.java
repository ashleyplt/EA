package servlets;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import datos.DTUsuario;
import entidades.Usuario;
/**
 * Servlet implementation class SLusuario
 */
@WebServlet("/SLusuario")
public class SLUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLUsuario() {
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
		
		//Construir el objeto de usuario
		DTUsuario dtu = new DTUsuario();
		Usuario user = new Usuario();
		
		//Declaración de variables
		int opc = Integer.parseInt(request.getParameter("opcion"));	
		int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
		String pwd = request.getParameter("pwd2");
		user.setPwd(pwd);
		user.setId(id_usuario);
		
		
		switch (opc){
		case 1:{
			String usuario = request.getParameter("usuario");
			user.setUsuario(usuario);
			try {
				if(	dtu.modificarNombreUsuario(user)) {

					HttpSession session = request.getSession();
					session.setAttribute("nombre_user", usuario);
					response.sendRedirect("layout/ver-perfil.jsp?msj=1");
				}
				else {
					response.sendRedirect("layout/ver-perfil.jsp?msj=2");
				}
			}
			catch(Exception e) {
				System.out.println("Error en el servelet : " + e.getMessage());
				e.printStackTrace();
			}
			break;
		}
		case 2:{
					
					try {
						if(	dtu.modificarPasswordUsuario(user)) {
							response.sendRedirect("layout/ver-perfil.jsp?msj=1");
						}
						else {
							response.sendRedirect("layout/ver-perfil.jsp?msj=2");
						}
					}
					catch(Exception e) {
						System.out.println("Error en el servelet : " + e.getMessage());
						e.printStackTrace();
					}
					break;
				}
		default:
			response.sendRedirect("ver-actividades.jsp?msj=3");	
			break;
		}		
	}
}