package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.*;

import org.apache.commons.fileupload.*;

import org.apache.commons.fileupload.disk.*;

import org.apache.commons.fileupload.servlet.*;

import org.apache.commons.io.*;

import datos.DTActividad;
import datos.DTArchivos;
import datos.DTCarrera;
import entidades.Archivo;
import entidades.Carrera;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class SL_archivo
 */
@WebServlet("/SLArchivo")
public class SLArchivo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SLArchivo() {
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
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		request.setCharacterEncoding("UTF-8");
		try {
			DTArchivos dta = new DTArchivos();

			int estado;
			int id;

			id = Integer.parseInt(request.getParameter("ida")); 
 
			estado = 3;

			if (dta.eliminarArchivo(estado, id)) {
				response.sendRedirect("layout/expediente-archivos.jsp");
			} else {
				response.sendRedirect("/layout/expediente-archivos.jsp");
			}
		} catch (Exception e) {
			System.err.println("SL ARCHIVO : error al eliminar archivo" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

 

		//request.setCharacterEncoding("UTF-8");


		//Integer id_carpeta =  Integer.parseInt(request.getParameter("carpeta"));

		HttpSession session = request.getSession();
		//int id_carpeta = (int) session.getAttribute("id_carpeta") ;
		
		String 	Nombre_edicion = (String) session.getAttribute("nombre_edicion") ;
		String id2 = (String) session.getAttribute("id_carpeta") ;
		int id_carpeta =  Integer.parseInt (id2);
			  try {

				  


					FileItemFactory file_factory = new DiskFileItemFactory();


					ServletFileUpload servlet_up = new ServletFileUpload(file_factory);


					String path = request.getServletContext().getInitParameter("path");
					path += File.separator +"expedientes" +  File.separator ;
					   
					//String path = "c:\\payara5\\glassfish\\domains\\domain1\\eclipseApps\\files";

					List archivo = servlet_up.parseRequest(request);

					for(int i=0;i<archivo.size();i++){

						FileItem item = (FileItem) archivo.get(i);



						String s = item.getName();
						String Nombre = item.getName();
						String Tipo = item.getContentType();

			 		      
						 String[] caracteresMalos = {" ","ñ","|","à","á","À","Á","è","é","È","É","ì","í","Ì","Í","ò","ó","Ò","Ó","ù","ú","Ù","Ú","\b","/",":","<","*","?",">","#"};
					        String[] caracteresBuenos = {"_","n","_","a","a","A","A","e","e","E","E","i","i","I","I","o","o","O","O","u","u","U","U","_","_","_","_","","_","_","_"};
				        for (String letraMala : caracteresMalos) {
				            if(s.contains(letraMala)){
				                s = s.replace(letraMala,caracteresBuenos[Arrays.asList(caracteresMalos).indexOf(letraMala)]);
				            }
				        }
						
						
			 		     String Nombre_archivo = s;

						if (! item.isFormField()){

							File archivo_server = new File(path+Nombre_archivo);


							item.write(archivo_server);
						}


		 
						int estado = 1;
						Archivo ar = new Archivo();
						DTArchivos dta = new DTArchivos();

						//int id_carpeta = 0;
						
						//int id_carpeta =  Integer.parseInt(request.getParameter("carpeta"));

						ar.setNombre(Nombre);
						ar.setNombre_archivo(Nombre_archivo);
						ar.setNombre_edicion(Nombre_edicion);
						ar.setTipo(Tipo);
						ar.setId_carpeta(id_carpeta);
						ar.setEstado(estado);
						if(dta.guardarArchivo(ar))
						{
							session.setAttribute("estado_archivo", "exito");

							response.sendRedirect("layout/expediente-archivos.jsp");
						}
						else
						{
							session.setAttribute("estado_archivo", "error");

							response.sendRedirect("layout/expediente-archivos.jsp"); 
						}

					} 





		}catch(Exception e ) {
  
			session.setAttribute("estado_archivo", "error");


			response.sendRedirect("layout/expediente-archivos.jsp");

		}




	}

}
