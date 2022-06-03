package servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.*;
import vistas.*;
/**
 * Servlet implementation class SLlogin
 */
@WebServlet("/SLLogin")
public class SLLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SLLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int sesionval = Integer.parseInt(request.getParameter("session"));
		if (sesionval == 1) {
			HttpSession session = request.getSession();
			session.invalidate();

			response.sendRedirect("./index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String nombre = request.getParameter("user");
		String password = request.getParameter("password");

		DTUsuario dtu = new DTUsuario();

		try {
			if (dtu.dtverificarLogin(nombre, password)) {
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(120 * 30);
				
				session.setAttribute("nombre_user", nombre);
				int idusuario = dtu.buscaridUsuario(nombre);

				session.setAttribute("id_usuario", idusuario);
				
				//Actualizar estados de permisos
				DTVPersonalUsuario dtvpu = new DTVPersonalUsuario();
				int id = dtvpu.buscaridPersonal(nombre);
				session.setAttribute("id_personal", id);
				DTVCargoPersonal dtvCP = new DTVCargoPersonal();
				ArrayList<VW_Cargo_Personal> listaCP = dtvCP.buscarCargoPersonal(id);
				
				String carrera = "";
				boolean coordinador = false;
				
				//Actualizar última fecha de ingreso
				dtu.modificarFechaIngreso(idusuario);
				
				//Verificar si es coordinador
				session.setAttribute("cargo", "");
				session.setAttribute("carrera", "");
				session.setAttribute("rol", "");
				for (VW_Cargo_Personal cp : listaCP) {
					if (cp.getCargo().equals("Coordinador")) {
						session.setAttribute("cargo", cp.getCargo());
						session.setAttribute("carrera", cp.getCarrera());
						coordinador = true;
						carrera = cp.getCarrera();
					}
				}
				
				DTVUsuarioSeguridad dtvu = new DTVUsuarioSeguridad();
				VW_Usuarios vu = dtvu.getVistaDeUsuario(nombre);
				if(vu != null) session.setAttribute("rol", vu.getRol());
				
				//Creación de carpetas para guardar archivos
				String path = request.getServletContext().getInitParameter("path");
				File parent = new File(path);
				File dir = new File(path);
				
				String userPhotos = path;
				path += File.separator + "expedientes";
				dir = new File(path);
				if(!dir.exists()) {
					dir.mkdirs();
				}
				
				userPhotos += File.separator + "fotos_usuarios";
				dir = new File(userPhotos);
				if(!dir.exists()) {
					dir.mkdirs();
				}
				session.setAttribute("error", "");
				response.sendRedirect("layout/index.jsp");

				session.setAttribute("bg-header", "");
				session.setAttribute("bg-sidebar", "");

			} else {
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(60 * 30);
				session.setAttribute("error", "no entraste");
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			System.out.println("Servlet: El error es: " + e.getMessage());
			e.printStackTrace();
		}
		// doGet(request, response);
	}

}
