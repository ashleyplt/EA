package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SLBG_sidebar_header
 */
@WebServlet("/SLBG_sidebar_header")
public class SLBG_sidebar_header extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLBG_sidebar_header() {
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
		//doGet(request, response);
		
		HttpSession session = request.getSession();
		String opcion =  request.getParameter("opcion");

		String bg =  request.getParameter("sidedar");
		if(opcion.equals("1") ){
			String bg_header =  request.getParameter("header");
			String colot_text =  request.getParameter("headertext");

			session.setAttribute("bg-header", bg_header);
			session.setAttribute("color-header", colot_text);
			
		}
		
			if(opcion.equals("2") ){
				String bg_sidebar =  request.getParameter("sidebar");
				session.setAttribute("bgsidebar", bg_sidebar);
			
		}
		
		
		 
	}

}
