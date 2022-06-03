<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <%@page import="vistas.*"%>
    <%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.*"%>
<%@page import="entidades.*"%>
<title>Insert title here</title>
</head>
<body>
<%



List<String> Listanull ;

Listanull= new ArrayList<String>();
session.setAttribute("actividades_agenda", Listanull);	
session.setAttribute("objetivos_agenda", Listanull);	
session.setAttribute("temas_agenda", Listanull);	



%>
</body>
</html>