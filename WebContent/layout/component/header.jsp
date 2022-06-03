<%@page import="datos.DTVPersonalUsuario"%>
<%@page import="datos.DTVCargoPersonal"%>
<%@page import="vistas.VW_Cargo_Personal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.DTVCargoPersonal"%>
<%@page session="true"%>


<%
	HttpSession usuario = request.getSession();
	String nombre = (String) usuario.getAttribute("nombre_user");
	String nombre2 = nombre;
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
%>

<div class="app-header header-shadow">
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
	<div class="app-header__menu">
		<span>
			<button type="button"
				class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
				<span class="btn-icon-wrapper"> <i
					class="fa fa-ellipsis-v fa-w-6"></i>
				</span>
			</button>
		</span>
	</div>
	<div class="app-header__content">
		<div class="app-header-left">
			<div class="search-wrapper">
				<div class="input-holder">
					<input type="text" class="search-input"
						placeholder="Buscar expediente">
					<button class="search-icon">
						<span></span>
					</button>
				</div>
				<button class="close"></button>
			</div>
			<ul class="header-menu nav">
				<li class="nav-item"><a href="javascript:void(0);"
					class="nav-link"> <i class="nav-link-icon fa fa-database">
					</i> Opcion 1
				</a></li>
				<li class="btn-group nav-item"><a href="javascript:void(0);"
					class="nav-link"> <i class="nav-link-icon fa fa-edit"></i>
						opcion 2
				</a></li>
				<li class="dropdown nav-item"><a href="javascript:void(0);"
					class="nav-link"> <i class="nav-link-icon fa fa-cog"></i>
						opcion 3
				</a></li>
			</ul>
		</div>
		<div class="app-header-right">
			<div class="header-btn-lg pr-0">
				<div class="widget-content p-0">
					<div class="widget-content-wrapper">
						<div class="widget-content-left">
							<div class="btn-group">
								<a data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false" class="p-0 btn"> <img width="42"
									class="rounded-circle"
									src="../dist/assets/images/avatars/1.jpg" alt=""> <i
									class="fa fa-angle-down ml-2 opacity-8"></i>
								</a>

								<div tabindex="-1" role="menu" aria-hidden="true"
									class="dropdown-menu dropdown-menu-right nombre1">
									<%
									DTVPersonalUsuario info = new DTVPersonalUsuario();
									String personal = info.buscarNombrePersonal(nombre);
									%>
									<h6 tabindex="-1" class="dropdown-header nombre"><%=personal%></h6>
									<button type="button" tabindex="0" class="dropdown-item">Ver
										Perfil</button>
									<button type="button" tabindex="0" class="dropdown-item">Settings</button>
									<h6 tabindex="-1" class="dropdown-header">Header</h6>
									<button type="button" tabindex="0" class="dropdown-item">Actions</button>
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