package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Date;
import java.sql.Time;

import datos.DTAyudaMemoria;
import datos.DTAyudaMemoriaAsistente;
import entidades.AyudaMemoria;
import entidades.AyudaMemoriaAsistentes;

/**
 * Servlet implementation class SLAyudaMemoria
 */
@WebServlet("/SLAyudaMemoria")
public class SLAyudaMemoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLAyudaMemoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		try {
			DTAyudaMemoria dtam = new DTAyudaMemoria();
			int id_ayuda_memoria = Integer.parseInt(request.getParameter("id_ayuda_memoria"));
			int estado = Integer.parseInt(request.getParameter("estado"));
			if(dtam.eliminarAyudaMemoria(id_ayuda_memoria, estado)) {
				response.sendRedirect("./layout/expediente-archivos.jsp?id=2");
			}else {
				response.sendRedirect("./layout/expediente-archivos.jsp?id=2?error");
			}
		}catch(Exception e) {
			System.err.println("SLAyudaMemoria: Error al eliminar ayuda de memoria " +e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int opc = 0;
		opc = Integer.parseInt(request.getParameter("opcionAyuda"));
		
		AyudaMemoria am = new AyudaMemoria();
		DTAyudaMemoria dtam = new DTAyudaMemoria();
		
		AyudaMemoriaAsistentes amas = new AyudaMemoriaAsistentes();
		DTAyudaMemoriaAsistente dtamas = new DTAyudaMemoriaAsistente();  
		
		int id_ayuda_memoria, id_coordinador = 0;
		Date fecha_reunion; 
		Time hora_inicio = Time.valueOf("00:00:00"), hora_finalizacion = Time.valueOf("00:00:00");
		String asignatura, periodo_academico, lugar = "";
		
		if(opc == 2) {
			
		}else {
			String hi = request.getParameter("hora_inicio")+":00";
			String hf = request.getParameter("hora_finalizacion")+":00";
			hora_inicio = Time.valueOf(hi);
			hora_finalizacion = Time.valueOf(hf);
		}
		id_coordinador = Integer.parseInt(request.getParameter("idcoordinador"));
		fecha_reunion = Date.valueOf(request.getParameter("fecha"));
		asignatura = request.getParameter("asignatura");
		periodo_academico = request.getParameter("periodo");
		lugar = request.getParameter("lugar");
		
		am.setAsignatura(asignatura);
		am.setPeriodo_academico(periodo_academico);
		am.setFecha_reunion(fecha_reunion);
		am.setHora_inicio(hora_inicio);
		am.setHora_finalizacion(hora_finalizacion);
		am.setPeriodo_academico(periodo_academico);
		am.setLugar(lugar);
		am.setEstado(1);
		
		switch(opc) {
			case 1:
			{
				try {
					if(dtam.guardarAyudaMemoria(am)) {
						AyudaMemoria am2 = dtam.ultimaAyudaMemoriaCreada(asignatura, periodo_academico);
						amas.setId_ayuda_memoria(am2.getId_ayuda_memoria());
						amas.setId_personal(id_coordinador);
						
						if(dtamas.guardarAyudaMemoriaAsistentes(amas)) {
							response.sendRedirect("./layout/datos-ayudadememoria.jsp?idam="+am2.getId_ayuda_memoria());
						}
						
					}else {
						response.sendRedirect("index.jsp?error");
					}
				}
				catch(Exception e) {
					System.err.println("SL AyudaMemoria: Error al guardar Ayuda de memoria " +e.getMessage());
					e.printStackTrace();
				}
			}
			break;
		}
		
	}

}
