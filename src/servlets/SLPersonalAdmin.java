package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import datos.DTCargoPersonal;
import datos.DTCoordinacion;
import datos.DTCoordinacionPersonal;
import datos.DTEnviarCorreo;
import datos.DTPersonal;
import datos.DTUsuario;
import datos.DTUsuarioRol;
import datos.DTVDatosPersonal;
import entidades.CargoPersonal;
import entidades.CoordinacionPersonal;
import entidades.Personal;
import entidades.Usuario;
import entidades.UsuarioRol;

/**
 * Servlet implementation class SLPersonal
 */
@WebServlet("/SLPersonal")
public class SLPersonalAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SLPersonalAdmin() {
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");
		int opc = Integer.parseInt(request.getParameter("opcion"));

		// CONSTRUIR EL OBJETO
		DTPersonal dtp = new DTPersonal();
		Personal p = new Personal();
		DTCargoPersonal dtcp = new DTCargoPersonal();
		CargoPersonal cp = new CargoPersonal();
		DTUsuario dtu = new DTUsuario();
		Usuario u = new Usuario();
		DTUsuarioRol dtur = new DTUsuarioRol();
		UsuarioRol ur = new UsuarioRol();
		DTCoordinacionPersonal dtcpp = new DTCoordinacionPersonal();
		CoordinacionPersonal cpp = new CoordinacionPersonal();

		// personal
		String nombre = request.getParameter("nombre_personal").trim();
		String apellido = request.getParameter("apellidos").trim();
		String correo = request.getParameter("correo").trim();
		String telefono = request.getParameter("telefono").trim();

		//Roles
		String[] listaRol = request.getParameterValues("rol");

		//CargoPersonal
		String[] listaCargo = request.getParameterValues("cargo");

		// CoordinacionPersonal
		String[] listaCoordinacion = request.getParameterValues("lugar");

		// Usuario
		String usuario = request.getParameter("usuario");
		String password = RandomStringUtils.randomAlphanumeric(8);

		int id_usuario = 0;

		// personal
		p.setNombre(nombre);
		p.setApellido(apellido);
		p.setCorreo(correo);
		p.setTelefono(telefono);

		// cargo personal
		// cp.setId_cargo(id_cargo);
		// solo se guarda en usuario
		u.setUsuario(usuario);
		u.setPwd(password);

		int cont = 0;

		boolean existe = false;
		if (dtu.existeNombreUsuario(usuario) || dtp.existeCorreo(correo)) {
			existe = true;
		}

		switch (opc) {
		case 1: {
			if (!existe) {
				// Agregar usuario
				if (dtu.guardarUsuario(u)) {
					id_usuario = dtu.buscaridUsuario(usuario);
					ur.setId_usuario(id_usuario);
					p.setId_usuario(id_usuario);
					// Ciclo para guardar los diferentes roles de un usuario

					while (cont < listaRol.length) {
						ur.setId_rol(Integer.parseInt(listaRol[cont]));
						if (dtur.guardarUsuarioRol(ur)) {
							cont++;
						} else {
							System.out.println("Hubo un error al guardar los roles del usuario");
						}
					}
					// Guardado de personal
					if (dtp.guardarPersonal(p)) {
						int id_personal = dtp.getIdPersonal(p.getCorreo());
						id_personal = 38;
						cp.setId_personal(id_personal);
						cpp.setId_personal(id_personal);
						cont = 0;

						while (cont < listaCoordinacion.length) {
							cpp.setId_coordinacion(Integer.parseInt(listaCoordinacion[cont]));
							if (dtcpp.guardarCoordinacionPersonal(cpp)) {
								cont++;
							} else {
								System.out.println("Hubo un error al guardar las coordinaciones del usuario");
							}

						}

						cont = 0;
						boolean guardado = false;
						while (cont < listaCargo.length) {
							// Esto cambia el id rol para agregar todos los cargos que tendrá el usuario y
							// guardarlo
							// sin cambiar el id usuario
							cp.setId_cargo(Integer.parseInt(listaCargo[cont]));
							if (dtcp.guardarCargoPersonal(cp)) {
								cont++;
								guardado = true;
							} else {
								System.out.println("Hubo un error al guardar los cargos del personal.");
							}
						}
						if (guardado) {
							DTEnviarCorreo dtec = new DTEnviarCorreo();
							try {
								if (dtec.enviarEmailVerificacion(id_usuario, nombre, usuario, correo, password)) {
									session.setAttribute("error",
											"0");
									response.sendRedirect("layout/agregar-personal.jsp");
								} else {
									session.setAttribute("error",
											"Hubo un error al enviarle las credenciales al correo del personal.");
									response.sendRedirect("layout/agregar-personal.jsp");
								}
							} catch (Exception e) {
								System.out.println("SLPersonal, el error es: " + e.getMessage());
								e.printStackTrace();
							}
						}
					}
				} else {

				}
			} else {
				session.setAttribute("error", "Ya existe un personal con ese correo o usuario.");
				String error = session.getAttribute("error").toString();
				response.sendRedirect("layout/agregar-personal.jsp?error");
			}
			break;
		}
		}
	}
}
