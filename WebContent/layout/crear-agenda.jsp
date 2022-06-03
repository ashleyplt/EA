<%@page import="vistas.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
<title>Creación de agenda</title>
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
<link href="../dist/style-form.css" rel="stylesheet">

<link rel="stylesheet" href="../dist/sweetalert2.min.css">


<script src="../dist/sweetalert2.all.min.js"></script>
<script src="../dist/jquery2.min.js"></script>
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

ol {
	widht: 70%;
	word-break: break-all;
}
</style>
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
			 
			 
			int contador = 0 ; 
			 
			List<String> Lista_actividades = (List<String>) session.getAttribute("actividades_agenda");
			if(Lista_actividades==null){
				Lista_actividades= new ArrayList<String>();
				session.setAttribute("actividades_agenda", Lista_actividades);
			}
				  
				for (String temp1 : Lista_actividades) {
			
					    contador += 1 ;
				}
			
			
			if(contador <= 3){


				session.setAttribute("estado_agenda", "error");
				%>
			<script>
					function redireccionar() {
						window.location.href = "vista-actividad.jsp";
					}
					redireccionar()
				</script>
			<%
				
				
			}
			
		 
			 
			int nregistros = 3;
			int paginacionInt = Math.round(contador/ nregistros);
			 
			int excedente =contador - (paginacionInt * nregistros);
			int paginacionT = paginacionInt;
			if (excedente > 0){
				paginacionT +=1;
			}
			
			
				String edicion_actual = (String) session.getAttribute("nombre_edicion"); 
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
									Creación de agenda para la edición :
									<%=edicion_actual%>
									<div class="page-title-subheading"></div>
								</div>
							</div>
						</div>
					</div>
					<%

				    response.setHeader("Cache-Control","no-cache,post-check=0,pre-check=0");
					response.setHeader("Pragma","no-cache");
					response.setHeader("Expires","Thu,01 Dec 1994 16:00:00 GMT");
					response.setDateHeader("Expires", -1);

						String ciclo = (String) session.getAttribute("nombre_edicion");
					String clase = (String) session.getAttribute("nombre_expediente");
					String docente = (String) session.getAttribute("nombre_usuario_completo");
					String carrera = (String) session.getAttribute("carrera");
					%>

					<div class="   card ">
						<div class="card-body ">
							<div class="   mb-3 container   ">


								<div class="container card-header">
									<p style="color: black; font-size: 15px;">
										Ciclo Académico
										<%=ciclo%>
										<br> <span style="font-weight: bold;"> <%=clase%></span>

									</p>

									<img alt="Logo_uca"
										style="widht: 100px; height: 100px; position: absolute; right: 0;"
										src="../dist/imagen/UCA_Logo.png">

								</div>

							</div>
							<iframe style="display: none;" name="iframe" id="iframe"></iframe>
							<!-- Progress bar -->
 
							<div class=" form-step  form-step-active">
							<!-- Steps --><form name="form" id="form" class="form card-body    "
									method="post">


								
									<div class="form-group">
										<label for="asignatura">Carrera :</label> <input type="text"
											class="form-control mt-1" name="carrera" id="carrera"
											value="<%=carrera%>" disabled />
									</div>
									<div class="form-group">
										<label for="asignatura">Asignatura :</label> <input
											type="text" class="form-control mt-1" name="asignatura"
											value="<%=clase%>" id="asignatura" disabled />
									</div>
									<div class="form-group mb-3">
										<label>Docente :</label> <input type="text"
											class="form-control " name="docente2" id="docente2"
											placeholder="Añada otro docente" />
									</div>
									<div class="form-group mb-3">
										<label>fecha de inicio :</label> <input type="date"
											class="form-control " name="fi" id="fi"
											placeholder="Sección" />
									</div>
									<div class="form-group mb-3">
										<label>Fecha de finalización :</label> <input type="date"
											class="form-control " name="ff" id="ff"
											placeholder="Añada Otro Docente" />
									</div>

									<div class="form-group mt-5 mb-3">
										<label>Introducción :</label>
										<textarea name="introduccion" class="form-control" cols="10"
											id="introduccion" rows="10"></textarea>
									</div>
									<div class="mt-3 mb-5">
										<a  type="submit" class="btn btn-next btn-primary active"
											onclick="n1()">Siguiente</a>
									</div>
								


								<script>
 
							//	 $("#introduccion").bind("input propertychange",function(event){
									 

									//var texto = $("#introduccion").val();
								       //console.log($("#introduccion").val())
								       
								     //  console.log($("#introduccion").val())
								     
								     
								     
							//	});
								 
								 
								 
							//	 $("#introduccion").on("change keyup paste", function(){
								//	 console.log($("#introduccion").val())
								//	})
								
								
								
								//document.form.target = "iframe";
			                     //  document.form.action = "../SL_AgendaDe";
			                     //  document.form.submit();
								
			                     
								 </script>
							</form>
							</div>
							<div class="form-step">


								<form name="form1" id="form1" class="form card-body mt-3  "
									method="post">
									<h5 class="mt-2 mb-2 ">
										<b>INSTRUCCIÓN DIDÁCTICA (Objetivos)</b>
									</h5>




									<ol class="mt-3" id="ol1">

									</ol>
									<div class="mt-5  w-100">
										<input type="hidden" name="opcion2" id="opcion2" value="0">
										<input class="form-control w-20" id="objetivo" name="objetivo"
											type="text"> <input id="objetivo2" name="objetivo2"
											type="hidden"> <input id="cantidadO" name="cantidadO"
											type="hidden" value="0"> <input
											class="btn btn-primary mt-3" style="left: 40%;" id="btn1"
											value="Añadir un nuevo objetivo a la agenda &nbsp;&nbsp;&nbsp;"
											type="submit" onclick="enviar()">
									</div>

								</form>

								<form name="form7" id="form7" class="form card-body  "
									method="get">
									<div class=" w-100">

										<input class="btn btn-danger " style="left: 40%;" id="btn2"
											value="Eliminar el ultimo objetivo de la agenda"
											type="submit" onclick="eliminar_objetivo()">



									</div>

								</form>
								<div class="btns-group mt-3 mb-5">
									<a href="#" class="btn btn-prev btn-primary">Anterior</a> <a
										class="btn btn-next btn-primary active" onclick="n2()">Siguiente</a>
								</div>




								<script>
								 
								function eliminar_objetivo() {
									var x = document.getElementById("ol1").lastChild.remove();;
									 

									var contador = parseInt($("#cantidadO").val()); 
									var resta =  contador -1; 
									$('#cantidadO').val(resta);

									document.form7.target = "iframe";
			                        document.form7.action = "../SL_AgendaDetalle";
			                        document.form7.submit();
									
								}
									
								
								
								function enviar(){

									var value = $('#objetivo').val();
									var enviar= false;
									$('#opcion2').val('0');
									$('#objetivo2').val(value);
									if (value == "") { 
										Swal.fire({
																						
											icon : 'error',
											title : 'Campo Vacio',
											text : '¡Rellene el campo para agregar el objetivo!',
											footer : ' '
																					
										})
																		
									} else {
																			
										if (value.length < 30) {
																				
											Swal.fire({
																							
												icon : 'error',
												title : 'El objetivo debe contener como minimo 30 caracteres'
																						
											})
																			
										}else{

											 $("#ol1").append("<li class=\"text-danger mt-2\">"  + value + "</li>");
											 
											$('#opcion2').val('1');

											var contador = parseInt($("#cantidadO").val()); 
											var suma =  contador +1; 
											$('#cantidadO').val(suma);

											$('#objetivo').val('');
										}
										}
									
									
									
									 
										
											document.form1.target = "iframe";
					                        document.form1.action = "../SL_AgendaDetalle";
					                        document.form1.submit();
									 	


			                       
								}

							 								
									 
								
								</script>
							</div>


							<div class="form-step">


								<form name="form4" id="form4" class="form card-body mt-5  "
									method="post">
									<h5 class="mt-2 mb-2 ">
										<b>INSTRUCCIÓN DIDÁCTICA (Temas)</b>
									</h5>



									<ol class="mt-3" id="ol2">

									</ol>
									<div class="mt-5  w-100">
										<input class="form-control w-20" id="tema" name="tema"
											type="text"> <input class="form-control w-20"
											id="tema2" name="tema2" type=hidden> <input
											class="form-control w-20" id="cantidadt" name="cantidadt"
											type=hidden value="0"> <input type="hidden"
											name="opcion3" id="opcion3" value="0"> 
											<input
											class="btn btn-primary mt-3" style="left: 40%;" id="btn2"
											value="Añadir un nuevo tema a la agenda "
											type="submit" onclick="enviar_tema()">



									</div>

								</form>




								<form name="form5" id="form5" class="form card-body  "
									method="get">
									<div class=" w-100">

										<input class="btn btn-danger " style="left: 40%;" id="btn2"
											value="Eliminar el ultimo tema de la agenda" type="submit"
											onclick="eliminar_tema()">



									</div>

								</form>



								<div class="btns-group mt-3 mb-5">
									<a href="#" class="btn btn-prev btn-primary">Anterior</a> <a
										class="btn btn-next btn-primary active" onclick="n3()">Siguiente</a>
								</div>



								<script>
								 
								function eliminar_tema() {

									var x = document.getElementById("ol2").lastChild.remove();
									var contador = parseInt($("#cantidadt").val()); 
									var resta =  contador -1; 
									$('#cantidadt').val(resta);
									 
									document.form5.target = "iframe";
			                        document.form5.action = "../SL_AgendaDetalleTema";
			                        document.form5.submit();
									
								}
										function enviar_tema(){
											var value = $('#tema').val();
											var enviar= false;

											$('#tema2').val(value);
											$('#opcion3').val('0');
											if (value == "") { 
												Swal.fire({
																								
													icon : 'error',
													title : 'Campo Vacio',
													text : '¡Rellene el campo para agregar el tema!',
													footer : ' '
																							
												})
																				
											} else {
																					
												if (value.length < 20) {
																						
													Swal.fire({
																									
														icon : 'error',
														title : 'El tema debe contener como minimo 20 caracteres'
																								
													})
																					
												}else{

													enviar=true;
													$('#tema').val('');
													$('#opcion3').val('1');
													var contador = parseInt($("#cantidadt").val()); 
													var suma =  contador +1; 
													$('#cantidadt').val(suma);
												}
												}
											
											
											
											
											if(enviar==true){
												 $("#ol2").append("<li class=\"text-danger mt-2\" >"+ value + "</li>");
													
											}
											document.form4.target = "iframe";
					                        document.form4.action = "../SL_AgendaDetalleTema";
					                        document.form4.submit();
											

										 		
										}
									</script>

							</div>



					<form method="post" name="final" id="final" action="../SLAgenda">
						 		<div class="form-step">	
										<%
										int contador_actividad =0;
										int contador_global=0;
										//@SuppressWarnings("unchecked")
										 
										//List<String> Lista_actividades = (List<String>) session.getAttribute("actividades_agenda");
										if(Lista_actividades==null){
											Lista_actividades= new ArrayList<String>();
											session.setAttribute("actividades_agenda", Lista_actividades);
										}
											
											
											for (String temporal : Lista_actividades) {
												
											  contador_actividad +=1; 
												int idActividad = Integer.parseInt(temporal);
										 
												 %>
												 <p class="mb-3"><%=idActividad %></p>
												 
									
												 
												 <% 
												 
												 
												 if (contador_actividad == nregistros && contador_global != paginacionT){
													 contador_actividad = 0 ;
													 contador_global +=1;
													 %>
													 
													 
											<div class="btns-group mt-3 mb-5">
											<a href="#" class="btn btn-prev btn-primary">Anterior</a> <a
											class="btn btn-next btn-primary active" onclick="n2()">Siguiente</a>
											</div>
													 
										</div>
													 
													 
													 
						 		<div class="form-step">	
													 <%  
													 
												 } 
											}
										%>
										 <div class="btns-group mt-3 mb-5">
															<a href="#" class="btn btn-prev btn-primary">Anterior</a> 
															<input type="submit" class="btn btn-next btn-primary active"  value="Guardar">
															</div>
																
						 		
						 		</div>
						 		<input type="text" id="intro2" name="intro2">
						 		<input type="date" id="fecha1" name="fecha1">
						 		<input type="date" id="fecha2" name="fecha2">
						 		</form>
						</div>
					</div>




				</div>
				<!--comentario include del footer-->
				<jsp:include page="./component/footer.jsp"></jsp:include>

			</div>
		</div>
	</div>
	<script>

function salert(input) {
    Swal.fire({
        position: 'center',
        icon: 'warning',
        title: 'Rellene el campo ' + input,
        showConfirmButton: false,
        timer: 8000
    })
}  
function salert2(input) {
    Swal.fire({
        position: 'center',
        icon: 'warning',
        title: '' + input,
        showConfirmButton: false,
        timer: 8000
    })
}
      

</script>

	<!-- Bootstrap 5 -->
	<script src="../dist/plugins/Bootstrap/js/popper.js"></script>
	<script src="../dist/plugins/Bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="../dist/plugins/jquery/jquery.min.js"></script>
	<!----Moment-->

	<!-- Selectt 2 -->
	<script src="../dist/plugins/select2/js/select2.full.min.js"></script>

	<!-- script Principal -->
	<script type="text/javascript" src="../dist/assets/scripts/main.js"></script>

	<script src="../dist/plugins/sweetalert2/dist/sweetalert2.min.js"></script>
	<script src="../dist/script-form2.js"></script>
	<script src="../dist/jqueri2.min.js"></script>

	<script>
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
		function salert(input) {
			Swal.fire({
				position : 'center',
				icon : 'warning',
				title : 'Rellene el campo ' + input,
				showConfirmButton : false,
				timer : 1500
			})
		}
		function salert2(input) {
			Swal.fire({
				position : 'center',
				icon : 'warning',
				title : '' + input,
				showConfirmButton : false,
				timer : 1500
			})
		}
	</script>


	<script src="../dist/script-form2.js"></script>

</body>
</html>