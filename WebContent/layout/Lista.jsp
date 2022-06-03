 
    <%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="vistas.*"%>
    <%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.*"%>
<%@page import="entidades.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<%


			@SuppressWarnings("unchecked")
 
			List<String> Lista_tema = (List<String>) session.getAttribute("temas_agenda");
			if(Lista_tema==null){
				Lista_tema= new ArrayList<String>();
				session.setAttribute("temas_agenda", Lista_tema);
			}
				
				
				for (String temp2 : Lista_tema) {
			
					 %>
					 <p> temas <%=temp2 %></p>
					 <% 
				}
				
				
	


%>


<hr>

 <%


			@SuppressWarnings("unchecked")
 
			List<String> Lista_objetivos = (List<String>) session.getAttribute("objetivos_agenda");
			if(Lista_objetivos==null){
				Lista_objetivos= new ArrayList<String>();
				session.setAttribute("objetivos_agenda", Lista_objetivos);
			}
				
				
				for (String temp22 : Lista_objetivos) {
			
					 %>
					 <p> objetivosd <%=temp22 %></p>
					 <% 
				}
	

%>
 <hr>
<%


			@SuppressWarnings("unchecked")
 
			List<String> Lista_actividades = (List<String>) session.getAttribute("actividades_agenda");
			if(Lista_actividades==null){
				Lista_actividades= new ArrayList<String>();
				session.setAttribute("actividades_agenda", Lista_actividades);
			}
				
				
				for (String temp23 : Lista_actividades) {
			
					 %>
					 <p> activi <%=temp23 %></p>
					 <% 
				}
				

				List<String> Listanull ;

				Listanull= new ArrayList<String>();
		//session.setAttribute("actividades_agenda", Listanull);	
	


%>

</body>
</html>