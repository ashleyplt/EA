package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.DTCargoPersonal;
import datos.DTCoordinacion;
import datos.DTDepartamento;
import datos.DTPersonal;
import datos.DTUsuario;
import datos.DTUsuarioRol;
import entidades.CargoPersonal;
import entidades.Personal;
import entidades.Usuario;
import entidades.UsuarioRol;

/**
 * Servlet implementation class SLPersonal
 */
@WebServlet("/SLPersonal")
public class SLPersonal_Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SLPersonal_Admin() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//int opc = Integer.parseInt(request.getParameter("opcion"));
		
		//CONSTRUIR EL OBJETO 
		DTPersonal dtp = new DTPersonal();
		Personal p = new Personal();
		DTCargoPersonal dtcp = new DTCargoPersonal();
		CargoPersonal cp = new CargoPersonal();
		DTUsuario dtu = new DTUsuario();
		Usuario u = new Usuario();
		DTUsuarioRol dtur = new DTUsuarioRol();
		UsuarioRol ur = new UsuarioRol();
		DTDepartamento dtd = new DTDepartamento();
		DTCoordinacion dtc = new DTCoordinacion();
		
		//personal
		String nombre = request.getParameter("nombre_personal");
		String apellido = request.getParameter("apellidos");
		String correo = request.getParameter("correo");
		String telefono = request.getParameter("telefono");
		
		//Lugar del cargo
		String lugar = request.getParameter("lugar");
		
		//CargoPersonal
		String[] listaRol = request.getParameterValues("rol");
		int id_cargo = Integer.parseInt(request.getParameter("cargo")); 
		
		//Usuario
		String usuario =  request.getParameter("usuario");
		String password = request.getParameter("contraseña");
		
		//UsuarioRol
		int id_usuario_rol = Integer.parseInt(request.getParameter("rol"));
		//int id_rol = Integer.parseInt(request.getParameter("id_rol"));
		
		int id_usuario = 0;
		// Usuario rol
		
		//Usuario
		u.setUsuario(usuario);
		u.setPwd(password);
		
		//personal
		p.setNombre(nombre);
		p.setApellido(apellido);
		p.setCorreo(correo);
		p.setTelefono(telefono);
		
		//Verifica en qué lugar el personal trabaja
		int id_lugar = 0;
		
		id_lugar = dtc.getIdCoordinacion(lugar);
		if(id_lugar == 0) {
			id_lugar = dtd.getIdDepartamento(lugar);
			p.setId_departamento(id_lugar);
		} else {
			p.setId_coordinacion(id_lugar);
		}
		
		//cargo personal
		cp.setId_cargo(id_cargo);
		//solo se guarda en usuario
		int cont = 0;
		if (dtu.guardarUsuario(u)) {
			id_usuario = dtu.buscaridUsuario(usuario);
			ur.setId_usuario(id_usuario);
			p.setId_usuario(id_usuario);
			//Ciclo para guardar los diferentes roles de un usuario
			while (cont < listaRol.length) {
				//Esto cambia el id rol para agregar todos los roles que tendrá el usuario y guardarlo
				//sin cambiar el id usuario
				ur.setId_rol(Integer.parseInt(listaRol[cont]));
				if(dtur.guardarUsuarioRol(ur)) {
					cont++;
				} else {
					System.out.println("Hubo un error al guardar los roles del usuario");
				}
				
				
			}
			//Guardado de personal
			if(dtp.guardarPersonal(p)){
				int id_personal = dtp.getIdPersonal(p.getCorreo());
				cp.setId_personal(id_personal);
				if(dtcp.guardarCargoPersonal(cp)) {
					response.sendRedirect("layout/index.jsp");		
				}
			}
		} else {
			
		}
	}

}
