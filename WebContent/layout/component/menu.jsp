<%@page import="java.util.ArrayList"%>
<%@page import="vistas.*"%>
<%@page import="datos.*"%>




<%

HttpSession session2 = request.getSession();
String sidebar = (String) session2.getAttribute("bgsidebar");
 
 


%>
<div class="app-sidebar sidebar-shadow <%=sidebar%>">
    <div class="app-header__logo">
        <div class="logo-src"></div>
        <div class="header__pane ml-auto">
            <div>
                <button type="button" class="hamburger close-sidebar-btn hamburger--elastic" data-class="closed-sidebar">
                    <span class="hamburger-box">
                        <span class="hamburger-inner"></span>
                    </span>
                </button>
            </div>
        </div>
    </div>
        
    
    <div class="scrollbar-sidebar">
        <div class="app-sidebar__inner">
            <ul class="vertical-nav-menu">
                <li class="app-sidebar__heading"></li>
                <li>
                    <a href="./index.jsp">
                        <i class="metismenu-icon fas fa-home"></i>
                        Expediente de Asignatura
                    </a>
                </li>
                <li class="app-sidebar__heading">Expedientes</li>
                <%
                HttpSession usuario = request.getSession();
        		String nombre = usuario.getAttribute("nombre_user").toString();

                boolean coordinador, admin;
                
               String personal = session.getAttribute("cargo").toString();
               if(personal.equals("")) coordinador = false;
               else coordinador = true;
               
               String rol = session.getAttribute("rol").toString();
               if(personal.equals("")) admin = false;
               else admin = true;
        		
                if(coordinador){
                %>
                <li >
                    <a href="#">
                        <i class="metismenu-icon fas fa-folder-open"></i>
                        Gestión de carrera
                        <i class="metismenu-state-icon caret-left fas fa-chevron-down" style="font-size:15px;"></i>
                    </a>
                    <ul>
                        <li>
                            <a href="./plan-estudio.jsp">
                                <i class="metismenu-icon"></i>
                                Plan de estudio
                            </a>
                        </li>
                        <li>
                            <a href="./edicion-asignatura.jsp">
                                <i class="metismenu-icon"></i>
                                Ediciones asignatura
                            </a>
                        </li>
                        
                    </ul>
                    
                </li>
                <%} %>
                 <li>
                    <a href="#">
                        <i class="metismenu-icon fas fa-envelope-open-text"></i>
                        Permisos expediente
                        <i class="metismenu-state-icon caret-left fas fa-chevron-down" style="font-size:15px;"></i>
                    </a>
                    <ul>
                        <li>
                            <a href="./solicitud-expediente.jsp">
                                <i class="metismenu-icon"></i>
                                Solicitud de permiso
                            </a>
                        </li>
                    </ul>
                </li>
                
                <%if(admin){ %>
                <li >
                    <a href="#">
                        <i class="metismenu-icon fas fa-shield-alt"></i>
                        Seguridad
                        <i class="metismenu-state-icon  caret-left fas fa-chevron-down" style="font-size:15px;"></i>
                    </a>
                    <ul>
                        <li>
                            <a href="./ver-usuarios.jsp">
                                <i class="metismenu-icon"></i>
                                Usuario
                            </a>
                        </li>
                        <li>
                            <a href="./ver-roles.jsp">
                                <i class="metismenu-icon">
                                </i>Rol
                            </a>
                        </li>
                        <li>
                            <a href="./ver-opciones.jsp">
                                <i class="metismenu-icon">
                                </i>Opciones
                            </a>
                        </li>
                    </ul>
                </li>
                 <li >
                    <a href="#">
                        <i class="metismenu-icon fas fa-users-cog"></i>
                        Administrar Personal
                        <i class="metismenu-state-icon  caret-left fas fa-chevron-down" style="font-size:15px;"></i>
                    </a>
                    <ul>
                        <li>
                            <a href="./agregar-personal.jsp">
                                <i class="metismenu-icon"></i>
                                Agregar Personal
                            </a>
                        </li>
                        <li>
                            <a href="./ver-personal.jsp">
                                <i class="metismenu-icon"></i>
                                Ver Personal
                            </a>
                        </li>
                    </ul>
                    
                </li>
                <li>
                    <a href="#">
                        <i class="metismenu-icon fas fa-stream"></i>
                        Actividades
                        <i class="metismenu-state-icon caret-left fas fa-chevron-down" style="font-size:15px;"></i>
                    </a>
                    <ul>
                        <li>
                            <a href="./ver-actividad.jsp">
                                <i class="metismenu-icon"></i>
                                Ver Actividades
                            </a>
                        </li>
                        <li>
                            <a href="./crear-actividad.jsp">
                                <i class="metismenu-icon">
                                </i>Crear Actividades
                            </a>
                        </li>
                    </ul>
                </li>
                <%} %>
            </ul>
        </div>
    </div>
</div>  