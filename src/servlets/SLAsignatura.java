package servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.DTAsignatura;
import datos.DTCarrera;
import datos.DTPlanCarrera;
import datos.DTPlanDeEstudio;
import datos.DTPlanEstudioDet;
import datos.DTVExpedienteCarrera;
import entidades.Asignatura;
import entidades.PlanCarrera;
import entidades.PlanDeEstudio;
import entidades.PlanEstudioDet;

/**
 * Servlet implementation class SLExpediente
 */
@WebServlet("/SLAsignatura")
public class SLAsignatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLAsignatura() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		DTPlanEstudioDet dtped = new DTPlanEstudioDet();
		int idplan = Integer.parseInt(request.getParameter("idplan"));
		int idasignatura = Integer.parseInt(request.getParameter("idasignatura"));
		int estado = Integer.parseInt(request.getParameter("estado"));
		int id = dtped.getId(idplan, idasignatura);
		if(dtped.modificarEstadoAsignatura(id, estado)) {
			response.sendRedirect("layout/plan-estudio.jsp");
		} else {
			String msj = "";
			session.setAttribute("error", "Hubo un error al deshabilitar/habilitar la asignatura del plan.");
			response.sendRedirect("layout/plan-estudio.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try 
		{
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			
			String nombre = request.getParameter("nombre").trim();
			String codigo = request.getParameter("codigo").trim();
			String descripcion = request.getParameter("descripcion").trim();
			int creditos = Integer.parseInt(request.getParameter("creditos"));
			
			DTAsignatura dta = new DTAsignatura();
			Asignatura a = new Asignatura();
			
			a.setNombre(nombre);
			a.setCodigo(codigo);
			a.setDescripcion(descripcion);
			a.setCreditos(creditos);
			
			int opc = Integer.parseInt(request.getParameter("opc"));
			
			if(opc == 1) {
//				String path = request.getServletContext().getInitParameter("path");
//				path += File.separator + "expedientes";
//				File dir = new File(path);
				
				if(!dta.existeAsignatura(nombre, codigo)) {
					if(dta.guardarAsignatura(a)){
						int id = dta.buscarId(nombre, codigo);
//						String fileName = "expediente_"+id;
//						path += File.separator + fileName;
//						dir = new File(path);
//						if(!dir.exists()) {
//							dir.mkdirs();
//						}
						DTVExpedienteCarrera dtvec = new DTVExpedienteCarrera();
						DTPlanEstudioDet dted = new DTPlanEstudioDet();
						PlanEstudioDet ped = new PlanEstudioDet();
						
						ped.setId_asignatura(id);
						int idplan = dtvec.getIdPlanEstudio(session.getAttribute("carrera").toString());
					
						if(idplan == 0) {
							DTPlanDeEstudio dtpe = new DTPlanDeEstudio();
							String nombreplan = "Plan de estudio " + session.getAttribute("carrera").toString();
							PlanDeEstudio pde = new PlanDeEstudio();
							pde.setNombre(nombreplan);
							if(dtpe.guardarPlanDeEstudio(pde)) {
								DTCarrera dtc = new DTCarrera();
								int idcarrera = dtc.getIdCarrera(session.getAttribute("carrera").toString());
								idplan = dtpe.getIdPlanEstudio(nombreplan);
								DTPlanCarrera dtpc = new DTPlanCarrera();
								PlanCarrera pc = new PlanCarrera();
								pc.setId_carrera(idcarrera);
								pc.setId_plan(idplan);
								if(dtpc.guardarPlanCarrera(pc));
							}
							
						}
						
						ped.setId_plan_estudio(idplan);
											
						if(!dted.guardarAsignaturaEnPlan(ped)) {
							session.setAttribute("error", "Hubo un error al guardar la asignatura en el plan de estudio.");
							response.sendRedirect("layout/crear-asignatura.jsp");
						} else {
							response.sendRedirect("layout/plan-estudio.jsp");
						}
					} else {
						session.setAttribute("error", "Hubo un error al guardar la asignatura.");
						response.sendRedirect("layout/crear-asignatura.jsp");
					}
				} else {
					session.setAttribute("error", "Ya existe una asignatura con ese nombre o código.");
					response.sendRedirect("layout/crear-asignatura.jsp");
				}
			} else {
				int idasign = Integer.parseInt(request.getParameter("id"));
				a.setId(idasign);
				Asignatura anterior = dta.getAsignaturaPorId(idasign);
				boolean guardar = false;
				if(anterior.getCodigo().equals(codigo) && anterior.getNombre().equals(nombre)) {
					guardar = true;
				} else {
					if(!dta.existeAsignatura(nombre, codigo)) {
						guardar = true;
					} else {
						session.setAttribute("error", "Ya existe una asignatura con ese nombre o código.");
						response.sendRedirect("layout/crear-asignatura.jsp");
					}
				}
				if(guardar) {
					if(dta.modificarAsignatura(a)) {
						response.sendRedirect("layout/plan-estudio.jsp");
					} else {
						session.setAttribute("error", "Hubo un error al modificar la asignatura.");
						response.sendRedirect("layout/crear-asignatura.jsp");
					}
				}
			}
			
		} catch (Exception e) {
			String msj = e.getMessage();
			System.err.println("SL ASIGNATURA: Error al guardar asignatura " + msj);
			e.printStackTrace();
		}
	}

}
