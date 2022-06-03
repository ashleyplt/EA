<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="entidades.EdicionAsignatura"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vistas.*"%>
<%@page import="datos.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>

<html lang="es">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="es">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Expediente de asignatura</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />

<!--comentario Estilos de bootstrap 5 -->
<link rel="stylesheet"
	href="../dist/plugins/Bootstrap/css/bootstrap.min.css">

<!--comentario estilos secundarios-->
<link href="../dist/styles.min.css" rel="stylesheet">

<!--comentario Estilos font awesome  -->
<link href="../dist/plugins/FontAwesome/css/all.min.css"
	rel="stylesheet">

<!--comentario file manager-->
<link href="../dist/plugins/file-manager/dashlite.css" rel="stylesheet">
<link rel="stylesheet" href="../dist/plugins/sweetalert2/dist/sweetalert2.min.css">

<!--comentario Estilos Principales-->
<link href="../dist/main.css" rel="stylesheet">
</head>
<style>
.br {
	padding-left: 19px;
	padding-right: 15px;
	padding-bottom: 18px;
	padding-top: 18px;
	border: 5px solid #212529;
	border-radius: 100%;
}
.br-g {
	padding-left: 15px;
	padding-right: 15px;
	padding-bottom: 18px;
	padding-top: 18px;
	border: 3px solid gray;
	border-radius: 100%;
}
</style>
<body>

	<!-- <jsp:include page="component/menu.jsp"></jsp:include> -->
	<div
		class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">

		<!--comentario include de header y settings -->
		<jsp:include page="./component/header.jsp"></jsp:include>
		<jsp:include page="./component/settings.jsp"></jsp:include>
		<%
		HttpSession usuario = request.getSession();
		String nombre = usuario.getAttribute("nombre_user").toString();

		boolean coordinador = false;
		boolean accesoExpedientes = false;
		String carrera = "";

		DTVPersonalUsuario dtvpu = new DTVPersonalUsuario();
		int id = dtvpu.buscaridPersonal(nombre);

		DTVCargoPersonal dtvCP = new DTVCargoPersonal();
		ArrayList<VW_Cargo_Personal> listaCP = dtvCP.buscarCargoPersonal(id);

		String cargo = usuario.getAttribute("cargo").toString();
		carrera = usuario.getAttribute("carrera").toString();
		if(cargo.equals("")) coordinador = false;
		else coordinador = true;
		%>
		<div class="app-main">
			<!--comentario include de los menus-->
			<jsp:include page="./component/movil-menu.jsp"></jsp:include>
			<jsp:include page="./component/menu.jsp"></jsp:include>

			<div class="app-main__outer">
				<div class="app-main__inner">
					<div class="app-page-title">
						<div class="page-title-wrapper">

							<!--comentario inicia la card debajo del header-->
							<div class="page-title-heading">
								<div class="page-title-icon">
									<i class="fas fa-house-user icon-gradient bg-sunny-morning"> </i>
								</div>
								<div>
									Sistema de gestión de expedientes de asignaturas
									<div class="page-title-subheading">Universidad Centroamericana</div>
								</div>
							</div>
						</div>
					</div>
					<%
					DTVExpedienteCarrera dtve = new DTVExpedienteCarrera();
					ArrayList<VW_Expediente_Carrera> listaExpedienteCarrera = new ArrayList<>();

					ArrayList<VW_Expediente_Docente> listarExpedientes = new ArrayList<>();
					DTVExpedienteDocente dte = new DTVExpedienteDocente();

					DTVPermisosExpediente dtpe = new DTVPermisosExpediente();
					ArrayList<VW_Permisos_Expediente> listarPermisosExpedientes = new ArrayList<>();

					DTEdicionAsignatura dtea = new DTEdicionAsignatura();
					EdicionAsignatura ea = null;
					
					boolean expedientedoc = false;
					
					Calendar cal = Calendar.getInstance();
					
					Date cierre = null;
					Date actual = null;
					
					if (coordinador) {
						listaExpedienteCarrera = dtve.listarExpedientesCarrera(carrera);
						listarExpedientes = dte.listarExpediente();
						
						ArrayList<VW_Expediente_Docente> tmpList = dte.listarExpedienteSegunDocente(id);
						if(tmpList.size() != 0) expedientedoc = true;
						
						listarPermisosExpedientes = dtpe.listarPermisosExpediente();
					} else {
						listarExpedientes = dte.listarExpedienteSegunDocente(id);
						if(listarExpedientes.size() != 0) expedientedoc = true;
						ea = dtea.getEdicionActiva();
					}
					
					if(ea != null){
						cal.setTime(ea.getFecha_cierre());
						cal.add(Calendar.DATE, -7);
						cierre = new Date(cal.getTime().getTime());
						actual = new Date(Calendar.getInstance().getTime().getTime());
					}
					
					%>
					<!-- Carrusel -->
					<div class=" container mb-5">
						<div id="carouselExampleCaptions" class="carousel slide"
							data-bs-ride="carousel">
							<div class="carousel-indicators">
								<button type="button" data-bs-target="#carouselExampleCaptions"
									data-bs-slide-to="0" class="active" aria-current="true"
									aria-label="Slide 1"></button>
								<button type="button" data-bs-target="#carouselExampleCaptions"
									data-bs-slide-to="1" aria-label="Slide 2"></button>
								<%
								if(expedientedoc){
									if(actual.toString().equals(ea.getFecha_cierre().toString()) || actual.after(cierre)){
									
								%>
								<button type="button" data-bs-target="#carouselExampleCaptions"
									data-bs-slide-to="2" aria-label="Slide 3"></button>
								<%
									}
								}
								%>
							</div>
							<div class="carousel-inner">
							<%
							
								if(expedientedoc){
									if(actual.toString().equals(ea.getFecha_cierre().toString()) || actual.after(cierre)){
										int milisecondsByDay = 86400000;
										int dias = (int) Math.round((ea.getFecha_cierre().getTime() - actual.getTime()) / (double) 86400000);
										SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
										
							%>
								<div class="carousel-item active">
									<img
										src="../dist/imagen/recordatorio.png"
										class="d-block w-100" alt="...">
									<div class="carousel-caption d-none d-md-block">
										<h5><%=sdf.format(ea.getFecha_cierre())%></h5>
										<p>
										<% if(dias == 0) { %>
										Hoy es el último día. <%} else { %> Faltan <%=dias%> días. <%} %> </p>
									</div>
								</div>
							<%
									}
							} 
							%>
								<div class="carousel-item <% if(!expedientedoc){%> active <%}%>">
									<img
										src="../dist/imagen/bienvenido.png"
										class="d-block w-100" alt="...">
									<div class="carousel-caption d-none d-md-block">
									</div>
								</div>
								<div class="carousel-item">
									<img
										src="../dist/imagen/contactanos.png"
										class="d-block w-100" alt="...">
									<div class="carousel-caption d-none d-md-block">
									</div>
								</div>
								
							</div>
							<button class="carousel-control-prev" type="button"
								data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
								<span class="carousel-control-prev-icon" aria-hidden="true"></span>
								<span class="visually-hidden">Anterior</span>
							</button>
							<button class="carousel-control-next" type="button"
								data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
								<span class="carousel-control-next-icon" aria-hidden="true"></span>
								<span class="visually-hidden">Siguiente</span>
							</button>
						</div> <!-- carrusel -->
					</div>
					<%
					DTEdicionAsignatura dteda = new DTEdicionAsignatura();
					EdicionAsignatura eda = dteda.getEdicionActiva();
					
					//Lista de expediente según el docente
					if (listarExpedientes.size() != 0 || listaExpedienteCarrera.size() != 0) {
						accesoExpedientes = true;
					%>
					<div class="row">
						<div class="col-md-12">
							<div class="main-card mb-3 card">
								<div class="card-header">
									Vista general de expedientes
									<%if(coordinador && eda == null){%>
									<i class="ml-2 icon ni fas fa-question-circle" id="popover" data-trigger="hover" data-container="body" data-toggle="popover" data-placement="right" data-content="Para poder asignar docentes a un expediente de asignatura debe primero aperturar una nueva edición." style="cursor:pointer;"></i> 
									<%
									}
									if(coordinador){
									%>
									<div class="btn-actions-pane-right">
										<div role="group" class="btn-group-sm btn-group">
											<div class="page-title-actions">
												<%
													if(eda == null){
												%>
													<button type="button" onclick="nuevoCiclo()"
														aria-haspopup="true" aria-expanded="false"
														class="btn-shadow btn" style="background-color:#3f6ad8; color: white;">
														<span class="btn-icon-wrapper pr-2 opacity-7"> <i
															class="fa fa-plus fa-w-20"></i>
														</span>
														Aperturar edición para asignaturas
													</button>
												<%
													}
													else {
												%>
										<p class="text-muted text-capitalize font-weight-normal">Edición: <%=eda.getNombre()%></>
										<span onclick="location.href='edicion-asignatura.jsp'" title="Editar edición" class="btn-icon-wrapper mr-2 ml-1 opacity-7"> <i
											class="fa fa-edit" style="color:#3f6ad8; cursor:pointer;"></i>
										</span>
												<%		
													}
												%>
											</div>
										</div>
									</div>
								<%} %>
								</div>
								<div class="card-body">
									<div class="tab-content">
										<div class="tab-pane active" id="file-grid-view">
											<div class="nk-files nk-files-view-grid">
												<div class="container">
													<div class="row">
														<div class="nk-files-list col-ms-6">
															<%
																ArrayList<String> expedientes = new ArrayList<>();
															if (!coordinador) {
																for (VW_Expediente_Docente ed : listarExpedientes) {
																	if (!expedientes.contains(ed.getAsignatura())) {
																expedientes.add(ed.getAsignatura());
																	}
																}
															} else {
																for (VW_Expediente_Carrera ec : listaExpedienteCarrera) {
																	if (!expedientes.contains(ec.getAsignatura())) {
																expedientes.add(ec.getAsignatura());
																	}
																}
															}
															for (String expediente : expedientes) {
															%>
															<div class="nk-file-item nk-file">
																<!-- .nk-file -->
																<div class="nk-file-info">
																	<div class="nk-file-title">
																		<div class="nk-file-icon">
																			<a class="nk-file-icon-link" href="#"> <span
																				class="nk-file-icon-type"> <svg
																						xmlns="http://www.w3.org/2000/svg"
																						viewBox="0 0 72 72">
                                                                                <g>
                                                                                    <rect
																							x="32" y="16" width="28" height="15" rx="2.5"
																							ry="2.5" style="fill:#f29611" />
                                                                                    <path
																							d="M59.7778,61H12.2222A6.4215,6.4215,0,0,1,6,54.3962V17.6038A6.4215,6.4215,0,0,1,12.2222,11H30.6977a4.6714,4.6714,0,0,1,4.1128,2.5644L38,24H59.7778A5.91,5.91,0,0,1,66,30V54.3962A6.4215,6.4215,0,0,1,59.7778,61Z"
																							style="fill:#ffb32c" />
                                                                                    <!--Para ver si compartió el expediente-->
                                                                                    <path
																							d="M8.015,59c2.169,2.3827,4.6976,2.0161,6.195,2H58.7806a6.2768,6.2768,0,0,0,5.2061-2Z"
																							style="fill:#ffb32c" />
																					<%
																						if (coordinador) {
																						for (VW_Permisos_Expediente pe : listarPermisosExpedientes) {
																							if (pe.getAsignatura().equals(expediente)) {
																					%>
                                                                                    <path
																							d="M29.6309,37.36a3.0236,3.0236,0,0,1-.86-2.39A4.3748,4.3748,0,0,1,32.9961,31h.0078a4.36,4.36,0,0,1,4.22,3.9121,3.0532,3.0532,0,0,1-.8545,2.4482A4.4158,4.4158,0,0,1,33.23,38.53c-.0771,0-.1533-.002-.23-.0049A4.519,4.519,0,0,1,29.6309,37.36Zm13.8359,2.7549a1,1,0,1,0-.9336,1.77c.7207.38,1.4658,2.126,1.4668,4.39V48a1,1,0,0,0,2,0V46.2744C45.999,43.3447,45.0049,40.9268,43.4668,40.1152ZM40.165,37.3965c-.1445.084-.29.168-.4316.2549a1,1,0,0,0,.5215,1.8535.9887.9887,0,0,0,.52-.1465c.1289-.0781.2607-.1543.3916-.23a4.2311,4.2311,0,0,0,2.1465-2.124.9839.9839,0,0,0,.0313-.1045A3.8411,3.8411,0,0,0,40.5,32.5352a1,1,0,0,0-.4922,1.9395,1.8773,1.8773,0,0,1,1.4,1.9092A2.835,2.835,0,0,1,40.165,37.3965ZM36.5,41h-7c-2.5234,0-4.5,2.7822-4.5,6.333V48.5a.8355.8355,0,0,0,.0588.2914.9731.9731,0,0,0,.3508.4946C26.4646,50.2812,29.4614,51,33,51s6.5353-.7187,7.59-1.7139a.9726.9726,0,0,0,.3509-.4949A.8361.8361,0,0,0,41,48.5V47.333C41,43.7822,39.0234,41,36.5,41Z"
																							style="fill:#c67424"></path>
																					<%
																						}
																					}
																					}
																					%>
                                                                                </g>
                                                                            </svg>
																			</span>
																			</a>
																		</div>
																		<div class="nk-file-name">
																			<div class="nk-file-name-text">
																				<a href="expediente-asignatura.jsp?expediente=<%=expediente%>"
																					class="title"><%=expediente%></a>
																				<div class="asterisk">
																					<a href="#"><em
																						class="asterisk-off icon ni ni-star"></em><em
																						class="asterisk-on icon ni ni-star-fill"></em></a>
																				</div>
																			</div>
																		</div>
																	</div>
																	<ul class="nk-file-desc">
																		<%
																			if (coordinador) {
																			for (VW_Expediente_Docente e : listarExpedientes) {
																				if (expediente.equals(e.getAsignatura())) {
																		%>
																		<li class="date" style="list-style: none; " class="siu"><%=e.getDocente()%></li>                                       
																		<%
																			}
																		}
																		%>
																	</ul>
																</div>
																<div class="nk-file-actions">
																	<div class="dropdown">
																		<a href=""
																			class="dropdown-toggle btn btn-sm btn-icon btn-trigger"
																			data-toggle="dropdown"><em
																			class="icon ni ni-more-h"></em></a>
																		<div class="dropdown-menu dropdown-menu-right">
																			<ul style="list-style: none;"
																				class="link-list-plain no-bdr">
																				<li><a href="#" onclick="vercompartidos('<%=expediente%>')" data-target="#myModal"><em
																					class="icon ni fas fa-users"></em><span>Compartir</span></a>
																				<%if(eda != null){ %>
																				<li><a href="#" data-toggle="modal" onclick="verdocentes('<%=expediente%>')" data-target="#modalDocente"><em
																						class="icon ni fas fa-user-plus"></em><span>Asignar docentes</span></a></li>
																				<%} %>
																			</ul>
																		</div>
																	</div>
																	<%
																		}
																	%>
																</div>

															</div>
															<!-- .nk-file -->
															<%
																}
															%>
														</div>
														<!-- .tab-pane -->
													</div>
												</div>
											</div>
											<!-- .tab-content -->
										</div>
										<!-- .tab-content -->
									</div>
								</div>

							</div>
							<!--TERMINA -->
							<%
								} //Si el usuario tiene o no expedientes asignados
							%>

							<!-- INICIA EL OTRO -->
							<%
								//Lista de permisos de expediente según el docente                          	
							listarPermisosExpedientes = dtpe.listarPermisosSegunDocente(id);
							if (listarPermisosExpedientes.size() != 0) {
								accesoExpedientes = true;
							%>
							<div class="main-card mb-3 card">
								<!-- INICIA EL DE LOS PERMISOS -->
								<div class="card-header">
									Accesos a otros expedientes
								</div>
								<div class="card-body">
									<!-- termina -->
									<div class="tab-content">
										<div class="tab-pane active" id="file-grid-view">
											<div class="nk-files nk-files-view-grid">
												<div class="container">
													<div class="row">
														<div class="nk-files-list col-ms-6">
															<%
																for (VW_Permisos_Expediente pe : listarPermisosExpedientes) {
															%>
															<div class="nk-file-item nk-file">
																<!-- .nk-file -->
																<div class="nk-file-info">
																	<div class="nk-file-title">
																		<div class="nk-file-icon">
																			<a class="nk-file-icon-link" href="#"> <span
																				class="nk-file-icon-type"> <svg
																						xmlns="http://www.w3.org/2000/svg"
																						viewBox="0 0 72 72">
                                                                                <g>
                                                                                    <rect
																							x="32" y="16" width="28" height="15" rx="2.5"
																							ry="2.5" style="fill:#f29611" />
                                                                                    <path
																							d="M59.7778,61H12.2222A6.4215,6.4215,0,0,1,6,54.3962V17.6038A6.4215,6.4215,0,0,1,12.2222,11H30.6977a4.6714,4.6714,0,0,1,4.1128,2.5644L38,24H59.7778A5.91,5.91,0,0,1,66,30V54.3962A6.4215,6.4215,0,0,1,59.7778,61Z"
																							style="fill:#ffb32c" />
                                                                                    <!--Para ver si compartió el expediente-->
                                                                                    <path
																							d="M8.015,59c2.169,2.3827,4.6976,2.0161,6.195,2H58.7806a6.2768,6.2768,0,0,0,5.2061-2Z"
																							style="fill:#ffb32c" />
                                                                                </g>
                                                                            </svg>
																			</span>
																			</a>
																		</div>
																		<div class="nk-file-name">
																			<div class="nk-file-name-text">
																				<a href="expediente-asignatura.jsp?expediente=<%=pe.getAsignatura()%>" class="title"><%=pe.getAsignatura()%></a>
																				<div class="asterisk">
																					<a href="#"><em
																						class="asterisk-off icon ni ni-star"></em><em
																						class="asterisk-on icon ni ni-star-fill"></em></a>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
															<!-- .nk-file -->
															<%
																}
															%>
														</div>
														<!-- .tab-pane -->
													</div>
												</div>
											</div>
											<!-- .tab-content -->
										</div>
										<!-- .tab-content -->
									</div>
								</div>

							</div>
							<!-- termina -->
							<%
								}
							%>
							<%
								if (accesoExpedientes == false) {
							%>
							<div class="card container position-relative w-100 bg-light">
								<label class="text-center"><i
									class="far fa-folder-open position-relative text-center mb-4 mt-5 br"
									style="font-size: 4rem; color: #212529;"></i> </label>
								<h4 class="text-center mb-3">No hay expedientes de
									asignatura.</h4>
								<h6 class="text-secondary text-center mb-5">
									No tienes acceso a ningún expediente de asignatura. Puede <a
										href="solicitud-expediente.jsp">solicitar permiso a uno.</a>
								</h6>
							</div>

							<%
								}
							%>
						</div>

					</div>
				</div>
				<!--comentario include del footer-->
				<jsp:include page="./component/footer.jsp"></jsp:include>

			</div>
		</div>
	</div>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel"></h4>
					<button type="button" onclick="closemodal()" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="shared-content" class="modal-body">							
						
				</div>
				<div class="modal-footer">
					<button type="button" onclick="closemodal()"class="btn btn-default" data-dismiss="modal">Cerrar</button>
					<button type="button" onclick="gotopage(1, 0)"
					class="btn btn-primary">Añadir nuevo permiso</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="modalDocente" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabelDoc"></h4>
					<button type="button" onclick="closemodal()" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="docente-content" class="modal-body">							
						
				</div>
				<div class="modal-footer">
					<button type="button" onclick="closemodal()"class="btn btn-default" data-dismiss="modal">Cerrar</button>
					<button type="button" onclick="nuevodocente()"
					class="btn btn-primary">Asignar nuevo docente</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap 5 -->
	<script src="../dist/plugins/Bootstrap/js/popper.js"></script>
	<script src="../dist/plugins/Bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="../dist/plugins/jquery/jquery.min.js"></script>
	<script type="text/javascript"
		src="../dist/plugins/DatePicker/moment.min.js"></script>
	<script type="text/javascript"
		src="../dist/plugins/DatePicker/daterangepicker.min.js"></script>
	<!----Moment-->
	<script>
	$(function() {
		$('input[name="daterange"]').daterangepicker(
				{
					orientation : 'bottom',
					widgetPositioning : {
						horizontal : 'auto', //  'auto', 'left', 'right'
						vertical : 'top' //  'auto', 'top', 'bottom'
					}
				},
				function(start, end, label) {
					console.log("A new date selection was made: "
							+ start.format('YYYY-MM-DD') + ' to '
							+ end.format('YYYY-MM-DD'));
				});
	});
	</script>
	<!-- Selectt 2 -->
	<script src="../dist/plugins/select2/js/select2.full.min.js"></script>

	<!-- script Principal -->
	<script type="text/javascript" src="../dist/assets/scripts/main.js"></script>
	
	<script src="../dist/plugins/sweetalert2/dist/sweetalert2.min.js"></script>
	<%  
	String error = (String) usuario.getAttribute("error");
	if(error.equals("")) error = null;
	if(error !=  null){
	%>

    <script>
    Swal.fire({
		icon: 'error',
		title: '<%=error%>',
		confirmButtonText: 'Aceptar',
		confirmButtonColor: '#3085d6'
	})
    </script>
    <%
	session.setAttribute("error", "");
	}
	%>
	<script>
	var exp;
	function vercompartidos(expediente){
		exp = expediente;
		 $.ajax({		    
	          url: "../SLajaxPermisos",
	          type: "post",
	          datatype:"html",
	          data: {'expediente':expediente},
	          success: function(data) 
	          {
	        		$('#shared-content').html(data);
	        		document.getElementById('myModalLabel').innerHTML = expediente;
	        		$('#myModal').modal("show");
	          }
	        }); 
	}
	
	function verdocentes(expediente){
		exp = expediente;
		 $.ajax({		    
	          url: "../SLajaxDocentesExp",
	          type: "post",
	          datatype:"html",
	          data: {'expediente':expediente},
	          success: function(data) 
	          {
	        		$('#docente-content').html(data);
	        		document.getElementById('myModalLabelDoc').innerHTML = expediente;
	        		$('#modalDocente').modal("show");
	          }
	        }); 
	}
	
	function closemodal(){
		$('#myModal').modal('hide');
		$('#modalDocente').modal('hide');
	}
	
	function optionsmodal(id, opcion){
		var url = "permisos-expediente.jsp";
		if(opcion == 1){
			window.location.href=url+"?opc=3&id="+id;
			location.reload
		}
		if(opcion == 2){
			Swal.fire({
				confirmButtonColor: '#3085d6',
				  title: '¿Está seguro que quiere quitarle los permisos?',
				  showCloseButton: true,
				  showCancelButton: true,
				  cancelButtonText: 'Cancelar',
				  confirmButtonText: 'Aceptar'
				}).then((result) => {
				  
				  if (result.isConfirmed) {
					  window.location.href="../SLPermisosAsignatura?opc=3&idpermiso="+id;
					  location.reload
				  }
				})
		} 
		if(opcion == 3){
			window.location.href=url+"?opc=1&expediente="+exp;
			location.reload
		}
	}
	
	function quitardocente(id){
		Swal.fire({
			confirmButtonColor: '#3085d6',
			  title: '¿Está seguro que quiere quitar al docente del expediente?',
			  showCloseButton: true,
			  showCancelButton: true,
			  cancelButtonText: 'Cancelar',
			  confirmButtonText: 'Aceptar'
			}).then((result) => {
			  if (result.isConfirmed) {
				  window.location.href="../SLExpedienteDocente?id="+id;
				  location.reload
			  }
			});
	}
	
	function nuevodocente(){
		window.location.href="docente-expediente.jsp?expediente="+exp;
		location.reload
	}
	
	function gotopage(opcion, expediente){
		var url = "permisos-expediente.jsp";
		window.location.href="edicion-asignatura.jsp";
			
		if(opcion == 1){
			window.location.href=url+"?opc="+opcion+"&expediente="+exp;
			location.reload
		} else {
			window.location.href=url+"?opc="+opcion+"&id="+expediente;
		}
	}
	</script>
	<script>
		$(function() {
			//Initialize Select2 Elements
			$('.select2').select2()

			//Initialize Select2 Elements
			$('.select2bs4').select2({
				theme : 'bootstrap4'
			})
		})
	</script>

	<script>
		(function() {
			'use strict'
			//id del formulario
			var forms = document.querySelectorAll('.needs-validation')

			// validacion al intentar enviar datos
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}
					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>
	<script>
		function nuevoCiclo()
		{
			window.location.href="edicion-asignatura.jsp";
			location.reload
		}

		$("#oculto-div").addClass("d-block");
	</script>
</body>
</html>
