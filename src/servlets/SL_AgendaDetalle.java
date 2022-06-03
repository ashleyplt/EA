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
 * Servlet implementation class SL_AgendaDetalle
 */
@WebServlet("/SL_AgendaDetalle")
public class SL_AgendaDetalle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SL_AgendaDetalle() {
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
		
		@SuppressWarnings("unchecked")
		int contador = 0 ;
		int contador2 = 0 ;
		 
		List<String> Lita_objetivo = (List<String>) session.getAttribute("objetivos_agenda");
		if(Lita_objetivo==null){
			Lita_objetivo= new ArrayList<String>();
			session.setAttribute("temas_agenda", Lita_objetivo);
		}
			
		List<String> Lita_objetivo2;
		Lita_objetivo2= new ArrayList<String>();
			for (String temp : Lita_objetivo) {
		
				    contador += 1 ;
			}
			
			for (String temp2 : Lita_objetivo) {

			    contador2 += 1 ;
			    if(contador2!= contador) {

			    	Lita_objetivo2.add(temp2);
			    }
		}
			
			
 
		
			List<String> Listanull ;
			
			

			Listanull= new ArrayList<String>();
			session.setAttribute("objetivos_agenda", Listanull);
			session.setAttribute("objetivos_agenda", Lita_objetivo2);
			
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		
		request.setCharacterEncoding("UTF-8");
 
		String enviar = request.getParameter("opcion2"); 
		HttpSession session = request.getSession();

		if(enviar.equals("1")){  
			String objetivo = request.getParameter("objetivo2");
			@SuppressWarnings("unchecked")
			List<String> Lista_Objetivo = (List<String>) session.getAttribute("objetivos_agenda");
			
			if(Lista_Objetivo==null) {
				Lista_Objetivo = new ArrayList<String>();
				session.setAttribute("objetivos_agenda", Lista_Objetivo);
				
			}
			
			Lista_Objetivo.add(objetivo);

			
		}
		
	}

}
