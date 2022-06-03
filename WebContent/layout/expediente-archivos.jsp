<%@page import="datos.*"%>
<%@page import="vistas.*"%>
<%@page import="entidades.*"%>
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
<title>Archivos</title>
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
<link rel="stylesheet"
	href="../dist/plugins/sweetalert2/dist/sweetalert2.min.css">

<!--comentario Estilos Principales-->
<link href="../dist/main.css" rel="stylesheet">


<link rel="stylesheet" href="../dist/sweetalert2.min.css">


<script src="../dist/sweetalert2.all.min.js"></script>
</head>

<body>


	
	<!-- <jsp:include page="component/menu.jsp"></jsp:include> -->
	<div
		class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">

		<!--comentario include de header y settings -->
		<jsp:include page="./component/header.jsp"></jsp:include>
		<jsp:include page="./component/settings.jsp"></jsp:include>

		<div class="app-main">
			<!--comentario include de los menus-->
			<jsp:include page="./component/movil-menu.jsp"></jsp:include>
			<jsp:include page="./component/menu.jsp"></jsp:include>


<%
		HttpSession usuario = request.getSession();
		String name = usuario.getAttribute("nombre_user").toString();
		String placeholder = usuario.getAttribute("idexpediente").toString();
		int idexpediente = Integer.parseInt(placeholder);
		DTVPersonalUsuario dtvpu = new DTVPersonalUsuario();
		int idp = dtvpu.buscaridPersonal(name);
		DTVCargoPersonal dtvCP = new DTVCargoPersonal();
		ArrayList<VW_Cargo_Personal> listaCP = dtvCP.buscarCargoPersonal(idp);
		String cargo = usuario.getAttribute("cargo").toString();
		boolean coordinador = false;
		if(cargo.equals("")) coordinador = false;
		else coordinador = true;
	String estadoArchivo = (String) usuario.getAttribute("estado_archivo");
	if (estadoArchivo == null || estadoArchivo == "") {
	%>
	<%
		} else {
	if (estadoArchivo == "exito") {
		session.setAttribute("estado_archivo", "");
	%><script>
	
	 
	   Swal.fire(
			   '¡ Proceso Finalizado!',
			   'De click para finalizar!',
			   'success'
			 ) 
 
	</script>
	<%
		} else {
	if (estadoArchivo == "error") {
		session.setAttribute("estado_archivo", "");
	%><script>
	
	 
	   Swal.fire(
			   '¡ Error al subir Archivo!',
			   'De click para finalizar!',
			   'error'
			 ) 
 
	</script>
	<%
		}
	}
	 
		}
	 
	String estado = (String) usuario.getAttribute("estado_valoracion"); 
	if(estado == null || estado == ""){
	 
	} else {
		if(estado == "exito"){
			session.setAttribute("estado_valoracion", "");
			
			%><script>
	
	 
	   Swal.fire(
			   '¡Su aporte fue guardado con exito!',
			   'De click para finalizar!',
			   'success'
			 ) 
 
	</script>
			<%
			} else { 
				if(estado == "error"){
								session.setAttribute("estado_valoracion", "");
						%><script>
	
	 
	   Swal.fire(
			   '¡Error al gardar s valoracion!',
			   'De click para finalizar!',
			   'error'
			 ) 
 
	</script>
			<%
					} 
			}
		
		


	}  
	String edicion_actual = (String) session.getAttribute("nombre_edicion") ; 
	String asignatura =  (String) session.getAttribute("nombre_expediente") ;
	%>
	
	
	
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
									Archivos del expediente : <%=asignatura %>
									<div class="page-title-subheading">Edicion :  <%=edicion_actual %></div>
								</div>
							</div>
						</div>
					</div>
					<div class="row" id="lista-archivos">
						<div class="col-md-12">
							<div class="main-card mb-3 card">
								<div class="card-header">
									Vista general de archivos
									<div class="btn-actions-pane-right">
										<div role="group" class="btn-group-sm btn-group">
											<div class="page-title-actions">
												<div class=" dropdown">
													<button type="button" data-toggle="dropdown"
														aria-haspopup="true" aria-expanded="false"
														class="btn-shadow dropdown-toggle btn btn-info">
														<span class="btn-icon-wrapper pr-2 opacity-7"> <i
															class="fa fa-cog fa-w-20"></i>
														</span> Opciones
													</button>
													<div tabindex="-1" role="menu" aria-hidden="true"
														class="dropdown-menu dropdown-menu-right">
														<ul class="nav flex-column">
															<li class="nav-item"><a
																href="valoracion-docente.jsp" class="nav-link"> <i
																	class="nav-link-icon lnr-inbox"></i> <span  
																		style="cursor: pointer;"> Valorar Asignatura 
																</span>
															</a></li>
															<li class="nav-item"><a href="#" data-toggle="modal"
																data-target="#myModal" class="nav-link"> <i
																	class="nav-link-icon lnr-book"></i> <span> Subir
																		Archivo </span>
															</a>
															</li>
															
															<li class="nav-item"><a
																href="crear-agenda.jsp" class="nav-link"> <i
																	class="nav-link-icon lnr-inbox"></i> <span  
																		style="cursor: pointer;"> Agendas                            
																</span>
															</a></li>
															
															<li class="nav-item"><a href="vista-actividad.jsp"
																class="nav-link"> <i class="nav-link-icon lnr book"></i>
																	<span> Histórico de Actividades </span>
															</a></li>
															<%
																DTSyllabus dts = new DTSyllabus();
																Syllabus s = dts.buscarSyllabusporExp(idexpediente);
																if(s.getId_syllabus() == 0){
															%>
															<li class="nav-item"><a href="crear-syllabus.jsp?asignatura=<%=asignatura%>&expediente=<%=edicion_actual%>&idexpediente=<%=idexpediente%>" class="nav-link"><i class="nav-link-icon lnr book"></i>
																	<span> Crear Syllabus</span>
															</a></li>
															<% }%>
															
															<%
																if(coordinador){
															%>
															<li class="nav-item"><a href="crear-ayudamemoria.jsp?edicion=<%=edicion_actual%>&asignatura=<%=asignatura%>"
																class="nav-link"> <i class="nav-link-icon lnr book"></i>
																	<span> Ayuda de memoria</span>
															</a></li>
															<%} %>

														</ul>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card-body">
									<div class="tab-content">
										<div class="tab-pane active" id="file-grid-view">
											<div class="nk-files nk-files-view-grid">
												<div class="container">
													<div class="row">

														<div class="nk-files-list col-ms-6">
															<%
																boolean valor = false;
															DTArchivos dta = new DTArchivos();
															ArrayList<Archivo> listarArchivos = new ArrayList<Archivo>();
															String id = request.getParameter("id");
															if (id == null) {
																id = (String) session.getAttribute("id_carpeta");
															}
															String pdf ="none";
															usuario.setAttribute("id_carpeta", id);
															String id2 = (String) session.getAttribute("id_carpeta");
															String edicion = (String) session.getAttribute("nombre_edicion");
															int ID = Integer.parseInt(id);
															String nombre = (String) session.getAttribute("nombre_edicion");
															listarArchivos = dta.listarArchivos(ID, nombre);
															if (id == null || edicion == null) {
																response.sendRedirect("./index.jsp");
															}
															//far fa-file
															for (Archivo a : listarArchivos) {
																String color = "gray";
																String icono = "far fa-file";
																String Tipo = a.getTipo();
																if (Tipo.equals("application/pdf")) {
																	icono = "far fa-file-pdf";
																	pdf="block";
																} else {
																	if (Tipo.equals("application/octet-stream")) {
																icono = "far fa-file-archive";
																	} else {
																if (Tipo.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
																	icono = "far fa-file-word";
																	//color = "blue";
																} else {
																	if (Tipo.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation")) {
																		icono = "far fa-file-powerpoint";
																		//color = "orange";
																	} else {
																		if (Tipo.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
																			icono = "far fa-file-excel";
																			//color = "green";
																		} else {
																			if (Tipo.equals("audio/mp3")) {
																				icono = "far fa-play-circle";
																				//color = "black";
																			} else {
																				if (Tipo.equals("video/mp4")) {
																					icono = "fas fa-photo-video";
																					//color = "black";
																				} else {
																					if (Tipo.equals("application/x-zip-compressed")) {
																						icono = "far fa-file-archive";
																						//color = "gray";
																					} else {
																						if (Tipo.equals("text/javascript")) {
																							icono = "fab fa-js";
																							//color = "yellow";
																						}
																					}
																				}
																			}
																		}
																	}
																}
																	}
																}
															%>
															<div class="nk-file-item nk-file" id="archivo">
																<!-- .nk-file -->
																<div class="nk-file-info">
																	<div class="nk-file-title">
																		<div class="nk-file-icon">
																			<a class="nk-file-icon-link" href="#"> <span
																				class="nk-file-icon-type"> <label
																					style="color:<%=color%>;font-size:80px;"> <i
																						class="<%=icono%>"></i>
																				</label>
																			</span>
																			</a>
																		</div>
																		<div class="nk-file-name">
																			<div class="nk-file-name-text">
																				<a href="#" class="title"><%=a.getNombre()%></a> <br>

																				<%
																					valor = true;
																				%>
																				<div class="asterisk">
																					<a href="#"><em
																						class="asterisk-off icon ni ni-star"></em><em
																						class="asterisk-on icon ni ni-star-fill"></em></a>
																				</div>
																			</div>
																		</div>


																	</div>
																	<ul class="nk-file-desc">

																		<!----<li class="date" style="list-style: none;">texto quemado<br></li>-->

																	</ul>
																</div>
																<div class="nk-file-actions" style="widht: 100px;">
																	<div class="dropdown">
																		<a href="#"
																			class="dropdown-toggle btn btn-sm btn-icon btn-trigger"
																			data-toggle="dropdown"><em
																			class="icon ni ni-more-h"></em></a>
																		<div class="dropdown-menu dropdown-menu-right">
																			<ul style="list-style: none;"
																				class="link-list-plain no-bdr">
																				<li><a
																					href="../expedientes/<%=a.getNombre_archivo()%>"
																					download="<%=a.getNombre()%>" data-toggle="modal">
																						</em> <span>Descargar</span>
																				</a></li>
																				<li style=display:"<%=pdf%>;"><a 
																					href="../expedientes/<%=a.getNombre_archivo()%>"
																					target="_blank" data-toggle="modal"> <span>Abrir
																							en otra pestaña</span>
																				</a></li>
																				<li><a
																					href="../SLArchivo?ida=<%=a.getId_archivo()%>&&idc=<%=ID%>"
																					data-toggle="modal"> <span>Eliminar</span>
																				</a></li>

																			</ul>
																		</div>
																	</div>

																</div>

															</div>
															<!-- .nk-file -->
															<%
																}
															if (id.equals("2")) {
															DTValoracion dtv = new DTValoracion();
															ArrayList<Valoracion> listarValoracion = new ArrayList<Valoracion>();
															String periodo = (String) session.getAttribute("nombre_edicion");
															listarValoracion = dtv.listarValoracion(periodo);
															//far fa-file
															for (Valoracion va : listarValoracion) {
																String color = "blue";
																String icono = "far fa-file";
															%>
															<div class="nk-file-item nk-file" id="archivo">
																<!-- .nk-file -->
																<div class="nk-file-info">
																	<div class="nk-file-title">
																		<div class="nk-file-icon">
																			<a class="nk-file-icon-link" href="#"> <span
																				class="nk-file-icon-type"> <label
																					style="color:<%=color%>;font-size:80px;"> <i
																						class="<%=icono%>"></i>
																				</label>
																			</span>
																			</a>
																		</div>
																		<div class="nk-file-name">
																			<div class="nk-file-name-text">
																				<a href="#" class="title"> Periodo :<%=va.getPeriodo()%>
																					<br> Docente : <%=va.getNombre()%>
																				</a>
																				<%
																					valor = true;
																				%>
																				<div class="asterisk">
																					<a href="#"><em
																						class="asterisk-off icon ni ni-star"></em><em
																						class="asterisk-on icon ni ni-star-fill"></em></a>
																				</div>
																			</div>
																		</div>


																	</div>
																	<ul class="nk-file-desc">

																		<!----<li class="date" style="list-style: none;">texto quemado<br></li>-->

																	</ul>
																</div>
																<div class="nk-file-actions" style="widht: 100px;">
																	<div class="dropdown">
																		<a href="#"
																			class="dropdown-toggle btn btn-sm btn-icon btn-trigger"
																			data-toggle="dropdown"><em
																			class="icon ni ni-more-h"></em></a>
																		<div class="dropdown-menu dropdown-menu-right">
																			<ul style="list-style: none;"
																				class="link-list-plain no-bdr">
																				<li><a href="#"  onclick="verRptvaloracion(<%=va.getId_valoracion()%>);"> </em> <span>Descargar</span>
																				</a></li>

																			</ul>
																		</div>
																	</div>

																</div>

															</div>
															<!-- .nk-file -->
															<%
																}
															
															} 
															//Ayudas de memoria
															if(id.equals("2")){
																DTAyudaMemoria dtam = new DTAyudaMemoria();
																ArrayList<AyudaMemoria> listarAyudaMemoria = new ArrayList<AyudaMemoria>();
																String periodo = (String) session.getAttribute("nombre_edicion");
																listarAyudaMemoria = dtam.listarAyudasMemorias(periodo);
																//far fa-file
																for (AyudaMemoria am : listarAyudaMemoria) {
																	String color = "blue";
																	String icono = "far fa-file";
															
															%>
																<div class="nk-file-item nk-file" id="archivo">
																<div class="nk-file-info">
																	<div class="nk-file-title">
																		<div class="nk-file-icon">
																		<a class="nk-file-icon-link" href="#">
																			<span class="nk-file-icon-type">
																				<label style="color:<%=color%>;font-size:80px;">
																					<i class="<%=icono%>"></i>
																				</label>
																			</span>
																		</a>
																		</div>
																		<div class="nk-file-name">
																			<div class="nk-file-name-text">
																				<a href="#" class="title">
																				Ayuda de memoria de reunión de colectivo 
																					<br><%=am.getPeriodo_academico()%>
																					<br>Fecha: <%=am.getFecha_reunion()%>
																				</a>
																				
																				<%
																					valor = true;
																				%>
																				<div class="asterisk">
																					<a href="#"><em
														                                class="asterisk-off icon ni ni-star"></em><em
														                                class="asterisk-on icon ni ni-star-fill"></em></a>
																				</div>
																				
																			</div>
																		</div>
																	</div>
																	<ul class="nk-file-desc">
																	
																	</ul>
																</div>
																<div class="nk-file-actions" style="widht: 100px;">
																	<div class="dropdown">
																		<a href="#"
														                    class="dropdown-toggle btn btn-sm btn-icon btn-trigger"
														                    data-toggle="dropdown"><em
                    														class="icon ni ni-more-h"></em>
																		</a>
																		<div class="dropdown-menu dropdown-menu-right">
																			<ul style="list-style: none;"
														                        class="link-list-plain no-bdr">
														                       <li><a 
														                        	href="#"  onclick="verRptAyudaMemoria(<%=am.getId_ayuda_memoria()%>);"> 
														                        	</em> <span>Descargar</span>
														                        </a></li>
														                        <%
																					if(coordinador){
																				%>
														                        <li><a href="#"  onclick="eliminarAyudaMemoria('<%=am.getId_ayuda_memoria()%>','<%=am.getEstado()%>');"> </em> <span>Eliminar</span>
														                        <%
																					}
														                        %>
														                        </a></li>
														                    </ul>
																		</div>
																	</div>
																</div>
															</div>
															<%	}
															}
															
															if(id.equals("2")){
																if(s.getId_syllabus()!=0){
																	DTVDatosSyllabus dtvds = new DTVDatosSyllabus();
																	VW_Datos_Syllabus vds = new VW_Datos_Syllabus();
																	vds = dtvds.buscarSyllabus(s.getId_syllabus());
																	String color = "blue";
																	String icono = "far fa-file";
																	%>
															<div class="nk-file-item nk-file" id="archivo">
																<div class="nk-file-info">
																	<div class="nk-file-title">
																		<div class="nk-file-icon">
																		<a class="nk-file-icon-link" href="#">
																			<span class="nk-file-icon-type">
																				<label style="color:<%=color%>;font-size:80px;">
																					<i class="<%=icono%>"></i>
																				</label>
																			</span>
																		</a>
																		</div>
																		<div class="nk-file-name">
																			<div class="nk-file-name-text">
																				<a href="#" class="title">
																				Syllabus de <%=vds.getAsignatura()%>
																					<br><%=vds.getCarrera()%>
																					<br><%=vds.getEdicion()%>
																				</a>

																				<%
																					valor = true;
																				%>
																				<div class="asterisk">
																					<a href="#"><em
														                                class="asterisk-off icon ni ni-star"></em><em
														                                class="asterisk-on icon ni ni-star-fill"></em></a>
																				</div>

																			</div>
																		</div>
																	</div>
																	<ul class="nk-file-desc">

																	</ul>
																</div>
																<div class="nk-file-actions" style="widht: 100px;">
																	<div class="dropdown">
																		<a href="#"
														                    class="dropdown-toggle btn btn-sm btn-icon btn-trigger"
														                    data-toggle="dropdown"><em
                    														class="icon ni ni-more-h"></em>
																		</a>
																		<div class="dropdown-menu dropdown-menu-right">
																			<ul style="list-style: none;"
														                        class="link-list-plain no-bdr">
														                        <li><a href="#"  onclick="verRptAyudaMemoria();"> </em> <span>Descargar</span></a></li>
														                        <li><a href="./editar-syllabus.jsp?idSyllabus=<%=vds.getId_syllabus()%>">
														                        	</em> <span>Editar Syllabus</span>
														                        </a></li>
														                         <li><a href="./crear-objetivo.jsp?idsyllabus=<%=vds.getId_syllabus()%>">
														                        	</em> <span>Objetivos de asignatura</span>
														                        </a></li>
														                        <!--
														                       	 <li><a href="./crear-gruposhorarios.jsp?idexpediente=<%=vds.getId_expediente_asignatura()%>">
														                        	</em> <span>Agregar grupos y horarios</span>
														                        </a></li>-->
														                        <%
																					if(coordinador){
																				%>
														                        <li><a href="#"  onclick="eliminarSyllabus('<%=vds.getId_syllabus()%>');"> </em> <span>Eliminar</span>
														                        <%
																					}
														                        %>
														                        </a></li>
														                    </ul>
																		</div>
																	</div>
																</div>
															</div>
															<!-- .nk-file -->

																	<% 
																}
															}
							
															
															if (valor == false) {
															%>

															<div
																class=" card-body container position-relative w-100  "
																style="border: 0px;">
																<label class="text-center"
																	style="position: relative; left: 50%;"><i
																	class="far fa-folder-open position-relative text-center mb-4 mt-5 br"
																	style="font-size: 4rem; color: #212529;"></i> </label>
																<h4 class="text-center mb-3">No hay archivos en
																	esta edición de la asignatura</h4>

															</div>



															<%
																}%>
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



				



					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="shared-content" class="modal-body">
				
				
					<form name="uploadFile" action="../SLArchivo" method="post"  class="mt-3 mb-3"
						enctype="multipart/form-data">
						<label for="fichero" style="cursor: pointer; position:relative; left:30%; font-size:20px;" class="mt-2 text-center"> Seleccionar
							Archivo</label> 
							
							<input type="file" style="display: none;" id="fichero"
							name="fichero" /> 
							<input type="submit" onclick="alertdowload()"
							class="btn btn-primary btn-block" value="Subir fichero" />

					</form>
				
				
				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap 5 -->
	<script src="../dist/plugins/Bootstrap/js/popper.js"></script>
	<script src="../dist/plugins/Bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="../dist/plugins/jquery/jquery.min.js"></script>

	<!----Moment-->

	<!-- script Principal -->
	<script type="text/javascript" src="../dist/assets/scripts/main.js"></script>

	<script src="../dist/plugins/sweetalert2/dist/sweetalert2.min.js"></script>


	<script>
	 
	 
	   function alertdowload(){
		   
		   let timerInterval
		   Swal.fire({
		     title: 'Subiendo archivo',
		     html: '<p>Espere mientras se sube el archivo</p>',
		     timer:0,
		     timerProgressBar: false,
		     didOpen: () => {
		       Swal.showLoading()
		     //  const b = Swal.getHtmlContainer().querySelector('b')
		       timerInterval = setInterval(() => {
		         b.textContent = Swal.getTimerLeft()
		       }, 0)
		     },
		     willClose: () => {
		       clearInterval(timerInterval)
		     }
		   }).then((result) => { 
		     
		   })
		    
	   }
	</script>
	<script>
$(document).ready(function(){
	  $("#myInput").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#lista-archivos #archivo").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	});
	
	
	$("#oculto-div").addClass("d-block");
	
	function eliminarSyllabus(idsyllabus){
		window.location.href="../SLSyllabus?id_syllabus="+idsyllabus;
		location.reload
	}
	
	function eliminarAyudaMemoria(idayudamemoria, estado){
		window.location.href="../SLAyudaMemoria?id_ayuda_memoria="+idayudamemoria+"&estado="+estado;
		location.reload
	}
	
	function verRptvaloracion(idval)
	{
		window.open("../SLRvaloracion?idvaloracion="+idval, '_blank');
	}
	
	function verRptAyudaMemoria(idayuda)
	{
		window.open("../SLDocAyudaMemoria?id_ayuda_memoria="+idayuda, '_blank');
	}
</script>
</body>
</html>