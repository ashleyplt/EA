<%@page import="vistas.VW_Expediente_Carrera"%>
<%@page import="datos.*"%>
<%@page import="vistas.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.DTVExpedienteDocente"%>
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
</style>
<body>

	<!-- <jsp:include page="component/menu.jsp"></jsp:include> -->
	<div class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">

		<!--comentario include de header y settings -->
		<jsp:include page="./component/header.jsp"></jsp:include>
		<jsp:include page="./component/settings.jsp"></jsp:include>
		<%
		request.setCharacterEncoding("UTF-8");
		HttpSession usuario = request.getSession();
		String nombre = "";
		String cargo = usuario.getAttribute("cargo").toString();
		
		String expediente = request.getParameter("expediente");
		boolean coordinador = false;
		if(!cargo.equals("")) coordinador = true;
		
		DTVExpedienteAsignatura dtea = new DTVExpedienteAsignatura();
		DTAsignatura dta = new DTAsignatura();
		ArrayList<VW_Expediente_Asignatura> listEA = dtea.listarExpedientesAsignatura(expediente);		
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
									<i class="fas fa-user icon-gradient bg-mean-fruit"> </i>
								</div>
								<div>
									<%=expediente%> - <%=dta.getCodigo(expediente)%>
									<% 
		            				usuario.setAttribute("nombre_expediente", expediente); %>
									<div class="page-title-subheading">Expediente de asignatura</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12">
							<%
							if (listEA.size() != 0) {
								DTVExpedienteDocente dted = new DTVExpedienteDocente();
								ArrayList<VW_Expediente_Docente> listDocentes = dted.getDocentesEdicionAsignatura(expediente);						
							%>
							<div class="main-card mb-3 card">
								<!-- INICIA EL DE LOS PERMISOS -->
								<div class="card-header">
									Ediciones de expediente
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
																for (VW_Expediente_Asignatura ea : listEA) {
															%>
															<div class="nk-file-item nk-file">
															<!-- .nk-file -->
															<div class="nk-file-info">
																<div class="nk-file-title">
																	<div class="nk-file-icon">
																			<a class="nk-file-icon-link" href="#"> <span
																				class="nk-file-icon-type"> 
																			<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 72 72">
                                                                                <g>
                                                                                    <rect
																							x="32" y="16" width="28" height="15" rx="2.5"
																							ry="2.5" style="fill:#f29611" />
                                                                                    <path
																							d="M59.7778,61H12.2222A6.4215,6.4215,0,0,1,6,54.3962V17.6038A6.4215,6.4215,0,0,1,12.2222,11H30.6977a4.6714,4.6714,0,0,1,4.1128,2.5644L38,24H59.7778A5.91,5.91,0,0,1,66,30V54.3962A6.4215,6.4215,0,0,1,59.7778,61Z"
																							style="fill:#ffb32c" />
                                                                                    <!--Para ver si comparti?? el expediente-->
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
																			<a href="estructura.jsp?edicion=<%=ea.getEdicion()%>&&idexpediente=<%=ea.getId_expediente()%>" class="title"><%=ea.getEdicion()%></a>
																			
																			
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
																	if (coordinador && listDocentes.size() > 0) {
																		if(ea.getEdicion().equals(listDocentes.get(0).getEdicion())){
																			for (VW_Expediente_Docente e : listDocentes) {
																%>
																<li class="date" style="list-style: none;"><%=e.getDocente()%><br></li>
																<%
																		}
																	}
																}
																%>
																</ul>
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
								} else {
							%>
							<div class="card container position-relative w-100 bg-light">
								<label class="text-center"><i
									class="far fa-folder-open position-relative text-center mb-4 mt-5 br"
									style="font-size: 4rem; color: #212529;"></i> </label>
								<h4 class="text-center mb-3">La asignatura no ha tenido ninguna edici??n.</h4>
								<h6 class="text-secondary text-center mb-5">
									Puede <a href="solicitud-expediente.jsp"> iniciar una nueva edici??n.</a>
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

	<!-- Bootstrap 5 -->
	<script src="../dist/plugins/Bootstrap/js/popper.js"></script>
	<script src="../dist/plugins/Bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="../dist/plugins/jquery/jquery.min.js"></script>
	<!-- script Principal -->
	<script type="text/javascript" src="../dist/assets/scripts/main.js"></script>
	
	<script src="../dist/plugins/sweetalert2/dist/sweetalert2.min.js"></script>
</body>
</html>