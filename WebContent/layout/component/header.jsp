<%@page import="vistas.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.*"%>
<%@page session="true"%>


<%@page import="datos.*"%>
<%@page import="vistas.*"%>
<%@page import="java.util.ArrayList"%>
<%@page session="true"%>


<%
	HttpSession usuario = request.getSession();
	String nombre = (String) usuario.getAttribute("nombre_user");
	String nombre2 = nombre;
	//String imagen = (String) usuario.getAttribute("imagen_user");
	System.out.println("Header: " + nombre); 
	if (nombre2 == null) {
%>
<script>
	function redireccionar() {
		window.location.href = "../index.jsp";
	}
	redireccionar()
</script>
<%
	} else {
		int id = (int) usuario.getAttribute("id_usuario");
		String bg_header = (String) session.getAttribute("bg-header");
		String color_text = (String) session.getAttribute("color-header");
%>

<div class="app-header header-shadow <%=bg_header %>" >
	<div class="app-header__logo">
		<div class="logo-src"></div>
		<div class="header__pane ml-auto">
			<div>
				<button type="button"
					class="hamburger close-sidebar-btn hamburger--elastic"
					data-class="closed-sidebar">
					<span class="hamburger-box"> <span class="hamburger-inner"></span>
					</span>
				</button>
			</div>
		</div>
	</div>
	<div class="app-header__mobile-menu">
		<div>
			<button type="button"
				class="hamburger hamburger--elastic mobile-toggle-nav">
				<span class="hamburger-box"> <span class="hamburger-inner"></span>
				</span>
			</button>
		</div>
	</div>
	<div class="app-header__menu <%=color_text%> header-text-light">
		<span class="<%=color_text%>">
			<button type="button"
				class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav <%=color_text %> header-text-light">
				<span class="btn-icon-wrapper "> <i
					class="fa fa-ellipsis-v fa-w-6"></i>
				</span>
			</button>
		</span>
	</div>
	<style>
	.ocultar {
	display:none;
	} 
	</style>
	<div class="app-header__content">
		<div class="app-header-left">
			<div class="search-wrapper ocultar" id="oculto-div">
				<div class="input-holder">
					<input id="myInput" type="text" class="search-input <%=color_text %> "
						placeholder="Buscar" >
					<button class="search-icon">
						<span></span>
					</button>
				</div>
				<button class="close"></button>
			</div>
		</div>
		<div class="app-header-right">
		 
 
		
    <li class="nav-item dropdown" style="text-decoration:none;  list-style:none;">
    <a class="nav-link <%=color_text %>"  " data-toggle="dropdown" href="#" id="bell">
      <i class="far fa-bell " style="font-size:1.18rem;position:relative; right:20px;" ></i>
      
    </a>
    <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
      <span class="dropdown-item dropdown-header">15 Notifications </span>
      <div class="dropdown-divider"></div>
      
       	<%  					                    
			DTVNotificacion dtvu = new DTVNotificacion(); 
			ArrayList<VW_Notificacion> listaUsuarios = new ArrayList<VW_Notificacion>();
			listaUsuarios = dtvu.listarNotificaciones();
			                            
			for (VW_Notificacion n : listaUsuarios) {
		%>
      <a href="#" class="dropdown-item">
        <i class="fas fa-envelope mr-2"></i><p>Mensaje:<%=n.getMensaje()%></p>
       <!--   <span class="float-right text-muted text-sm">3 mins</span>--->
      </a>
      <div class="dropdown-divider"></div>
       <%
       }
       %>
      <a href="#" class="dropdown-item dropdown-footer">Ver todas las notificaciones</a>
    </div>
  </li>
                       
                       
			<div class="header-btn-lg pr-0">
				<div class="widget-content p-0">
					<div class="widget-content-wrapper">
						<div class="widget-content-left">
							<div class="btn-group">
								<%								
									DTVUsuarioSeguridad dtvus = new DTVUsuarioSeguridad(); 
		                            ArrayList<VW_Usuarios> listarUsuarios = new ArrayList<VW_Usuarios>();
		                            listarUsuarios = dtvus.listarUsuariosenPerfil(id);
		                            
		                            for(VW_Usuarios u : listarUsuarios){
									%>
								<a data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="p-0 btn">
									<img class="rounded-circle" src="../<%=u.getImagen()==null?"dist/imagen/user.png":u.getImagen()%>" 
                                style="border-radius: 50px 50px; width: 50px; height: 50px;" class="center-block" alt="Foto"> <i
									class="fa fa-angle-down ml-2 opacity-8"></i>
								</a>
								
								<%
		                            }
								%>

								<div tabindex="-1" role="menu" aria-hidden="true"
									class="dropdown-menu dropdown-menu-right nombre1">
									<%
									DTVPersonalUsuario info = new DTVPersonalUsuario();
									String personal = info.buscarNombrePersonal(nombre);
									%>
									<h6 tabindex="-1" class="dropdown-header nombre"><%=personal%></h6>
									<% usuario.setAttribute("nombre_usuario_completo", personal); %>
									<a type="button" tabindex="0" class="dropdown-item" href="ver-perfil.jsp">Ver
										Perfil</a>
									
									<div tabindex="-1" class="dropdown-divider"></div>
									<a type="button" tabindex="0" class="dropdown-item"
										href="../SLLogin?session=1">Cerrar sesión</a>
								</div>
							</div>
						</div>
						<style> 
		 				.nombre1 {
							width:260px;
						}
						</style>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>
<%
	}
%>
