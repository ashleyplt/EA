package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.DTMaterial;
import entidades.Material;

/**
 * Servlet implementation class SLMaterial
 */
@WebServlet("/SLMaterial")
public class SLMaterial extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLMaterial() {
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

		DTMaterial dtm = new DTMaterial();
		Material material = new Material();
		
		String nombre = request.getParameter("nMaterial");
		String descripcion = request.getParameter("dMaterial");
		String url = request.getParameter("urlMaterial");
		
		material.setNombre(nombre);
		material.setDescripcion(descripcion);
		material.setUrl(url);

		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		List<String> ListaID = (List<String>) session.getAttribute("Lista_de_Id");
		
		if(ListaID==null) {
			ListaID = new ArrayList<String>();
			session.setAttribute("Lista_de_Id", ListaID);
			
		}
		PrintWriter html = response.getWriter();
		
		if(dtm.agregarMaterial(material)) {
			
			 // aqui se inserta 
			
            ArrayList<Material> listarUltimo= new ArrayList<Material>();
            listarUltimo= dtm.listarUltimoMaterial();
            
            for (Material r : listarUltimo) {
            	
            
			int Int_id = r.getId_material() ;
			String id =Integer.toString(Int_id) ;
			 
			ListaID.add(id);
			// aqui a la sesion 
            }
		
			
		}else {
			//html.print("<script>alert(\"no pudo\")</script>");
		}

 
		
	}

}