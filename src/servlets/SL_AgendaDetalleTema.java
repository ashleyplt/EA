package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class SL_AgendaDetalleTema
 */
@WebServlet("/SL_AgendaDetalleTema")
public class SL_AgendaDetalleTema extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SL_AgendaDetalleTema() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		HttpSession session = request.getSession();
	
	//	List<String> Listanull ;
		
		

	//	Listanull= new ArrayList<String>();
		//session.setAttribute("objetivos_agenda", Listanull);
	//session.setAttribute("temas_agenda", Listanull);
//
		
		
		@SuppressWarnings("unchecked")
		int contador = 0 ;
		int contador2 = 0 ;
		 
		List<String> Lista_tema = (List<String>) session.getAttribute("temas_agenda");
		if(Lista_tema==null){
			Lista_tema= new ArrayList<String>();
			session.setAttribute("temas_agenda", Lista_tema);
		}
			
		List<String> Lista_tema2;
		Lista_tema2= new ArrayList<String>();
			for (String temp : Lista_tema) {
		
				    contador += 1 ;
			}
			
			for (String temp2 : Lista_tema) {

			    contador2 += 1 ;
			    if(contador2!= contador) {

					Lista_tema2.add(temp2);
			    }
		}
			
			
 
		
			List<String> Listanull ;
			
			

			Listanull= new ArrayList<String>();
			session.setAttribute("temas_agenda", Listanull);
			session.setAttribute("temas_agenda", Lista_tema2);
			
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		
		 
	 
	  

	 
		
		
		request.setCharacterEncoding("UTF-8");
 
		String enviar = request.getParameter("opcion3"); 
		String tema = request.getParameter("tema2");
		HttpSession session = request.getSession();

		if(enviar.equals("1")){  
			@SuppressWarnings("unchecked")
			List<String> Lista_tema = (List<String>) session.getAttribute("temas_agenda");
			
			if(Lista_tema==null) {
				Lista_tema = new ArrayList<String>();
				session.setAttribute("temas_agenda", Lista_tema);
				
			}
			
			Lista_tema.add(tema);

			
		}
		
		
		
	}

}
