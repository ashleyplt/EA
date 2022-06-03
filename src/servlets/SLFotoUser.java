package servlets;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import datos.DTUsuario;

/**
 * Servlet implementation class SL_FotoUser
 */
@WebServlet("/SLFotoUser")
public class SLFotoUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SLFotoUser() {
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
		try
		{
			DTUsuario dtu = new DTUsuario();

			HttpSession session = request.getSession();
	
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory); 
			List<FileItem> items = upload.parseRequest(request);
			File fichero = null;
			String path = request.getServletContext().getInitParameter("path");
			String idusuario = null;
			String rutaFichero = null;
			
			for(FileItem item: items)
			{
				FileItem uploaded = item;
				if(uploaded.isFormField())
				{
					String key = uploaded.getFieldName();
					String valor = uploaded.getString();
					if(key.equals("iduser"))
					{
						idusuario = valor;
					}
					
				}
			}
			for(FileItem item : items)
			{
				FileItem uploaded = item;
				if(!uploaded.isFormField())
				{
					/////////TAMA�O DEL ARCHIVO ////////
					long size = uploaded.getSize();
					System.out.println("size: "+size);
					
					/////// GUARDAMOS EN UN ARREGLO LOS FORMATOS QUE SE DESEAN PERMITIR
					List<String> formatos = Arrays.asList("image/png");
					List<String> formatosjpg = Arrays.asList("image/jpg");
					List<String> formatosjpeg = Arrays.asList("image/jpeg");
					List<String> formatosgif = Arrays.asList("image/gif");
					
					
					///// COMPROBAR SI EL TAMA�O Y FORMATO SON PERMITIDOS //////////if(formatos.contains(uploaded.getContentType()) && size <= 102400)
					if(formatos.contains(uploaded.getContentType()) || formatosjpg.contains(uploaded.getContentType()) || formatosjpeg.contains(uploaded.getContentType()) || formatosgif.contains(uploaded.getContentType()))
					{
						System.out.println("Filetype: "+uploaded.getContentType());
						
						rutaFichero = "fotoUsuario_"+idusuario+".jpg";
						path += File.separator + "fotos_usuarios" + File.separator;
						System.out.println(path+rutaFichero);
						
						fichero = new File(path+rutaFichero);
						System.out.println(path+rutaFichero);
						
						///////// GUARDAR EN EL SERVIDOR //////////////
						uploaded.write(fichero);
						
						System.out.println("SERVIDOR: FOTO GUARDADA CON EXITO!!!");
						/////// ACTUALIZAMOS EL CAMPO URLFOTO EN LA BASE DE DATOS
						String url = "fotos_usuarios/"+rutaFichero;
						
						if(dtu.guardarFotoUser(Integer.parseInt(idusuario),url))
						{ 
							response.sendRedirect("layout/ver-perfil.jsp");	
						}
						else
						{
 
							session.setAttribute("error", "No se guardo correctamente");
							response.sendRedirect("layout/cambiar-imagen.jsp");	
						}
					}
					else
					{
						System.out.println("SERVIDOR: VERIFIQUE QUE EL ARCHIVO CUMPLA CON LAS ESPECIFICACIONES REQUERIDAS!!!");
 
						session.setAttribute("error", "Formato Invalido");
						response.sendRedirect("layout/cambiar-imagen.jsp");						
					}
				}	
			}
		}
		catch(Exception e)
		{
			String ms = e.getMessage();
			System.out.println("SERVLET: ERROR AL SUBIR LA FOTO: " + ms);
		}	
	}

}