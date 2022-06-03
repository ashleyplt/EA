package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.*;
import entidades.*;

/**
 * Servlet implementation class SLactividad
 */
@WebServlet("/SLActividad")
public class SLActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SLActividad() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		try 
		{
			DTActividad dta = new DTActividad();

			int estado;
			int id;

			id = Integer.parseInt(request.getParameter("id_actividad"));
			estado = Integer.parseInt(request.getParameter("estado"));
			System.out.println("Estado: " + estado);

			if(dta.eliminarActividad(estado, id))
			{
				response.sendRedirect("layout/ver-actividad.jsp?deshabilitado=1");
			}
			else
			{
				response.sendRedirect("layout/ver-actividad.jsp?deshabilitado=2");
			}
		} 
		catch (Exception e) 
		{
			System.err.println("SL EXPEDIENTE: Error al deshabilitar la actividad" +e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int opc = Integer.parseInt(request.getParameter("opcion"));

		//CONSTRUIR EL OBJETO Actividad
		DTActividad dta = new DTActividad();
		Actividad act = new Actividad(); 

		String nombre = request.getParameter("actName");
		String descripcion = request.getParameter("actDesc");
		int estado = 1;
		int id_tipo_actividad = Integer.parseInt(request.getParameter("tActividad"));
		int id_modalidad_actividad = Integer.parseInt(request.getParameter("mActividad"));
		int cant_horas = Integer.parseInt(request.getParameter("cant_horas"));
		int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
		int id_evaluacion_act = Integer.parseInt(request.getParameter("teActividad"));

		act.setNombre(nombre);
		act.setDescripcion(descripcion);
		act.setEstado(estado);
		act.setId_tipo_actividad(id_tipo_actividad);
		act.setId_modalidad_actividad(id_modalidad_actividad);
		act.setId_evaluacion_actividad(id_evaluacion_act);
		act.setCant_horas(cant_horas);
		act.setId_usuario(id_usuario);

		switch (opc){
		case 1:{

			try {
				if(	dta.guardarActividad(act)) {
					ArrayList<Actividad> listaultimo = new ArrayList<Actividad>();
					listaultimo = dta.listarUltimaActividad();
					MaterialActividad ma = new MaterialActividad();
					DTMaterialActividad dtma = new DTMaterialActividad();

					for (Actividad r :listaultimo) {

						int id_actividad = r.getId_actividad();
						response.sendRedirect("layout/vista-actividad.jsp?msj=1");

						// @SuppressWarnings("unchecked")

						HttpSession session = request.getSession();
						//@SuppressWarnings("unchecked")
						//List<String> ListaID = (List<String>) session.getAttribute("Lista_de_Id");
						
						@SuppressWarnings("unchecked")
						List<String> ListaID = (List<String>) session.getAttribute("Lista_de_Id");
						if(ListaID==null){
							ListaID= new ArrayList<String>();
							session.setAttribute("Lista_de_Id", ListaID);
						}
						
						
						
						for (String temp : ListaID) {

							int id_material = Integer.parseInt(temp);
							ma.setId_actividad(id_actividad);
							ma.setId_material(id_material);

							// Aqui el insert del detalle

							if(dtma.agregarMaterial(ma)) { 
								
							}else {
								response.sendRedirect("layout/crear-actividad.jsp?msj=2");
							}
						}
						
						List<String> Listanull ;

						Listanull= new ArrayList<String>();
						session.setAttribute("Lista_de_Id", Listanull);
					} 
				}
				else {
					response.sendRedirect("layout/vista-actividad.jsp?msj=2");
				}
			}
			catch(Exception e) {
				System.out.println("Error en el servelet : " + e.getMessage());
				e.printStackTrace();
			}
			break;
		}
		case 2:{

			int idActividad = Integer.parseInt(request.getParameter("id_actividad"));
			act.setId_actividad(idActividad);

			try {
				if(	dta.modificarActividad(act)) {
					response.sendRedirect("layout/vista-actividad.jsp?msj=1");
				}
				else {
					response.sendRedirect("layout/vista-actividad.jsp?msj=2");
				}

			}
			catch(Exception e) {
				System.out.println("Error en el servelet : " + e.getMessage());
				e.printStackTrace();
			}
			break;
		}
		default:
			response.sendRedirect("ver-actividad.jsp?msj=3");	
			break;
		}		
	}
}