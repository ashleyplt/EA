 <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>

<html lang="es">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Formulario</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
<!-- <link rel="shortcut icon" href="../dist/img/icono.png">-->
<!--comentario Estilos Date Picker -->
<link rel="stylesheet" type="text/css"
	href="../dist/plugins/DatePicker/daterangepicker.css" />

<!-- Cometario Estilos Syllabus -->	
<link rel="stylesheet" type="text/css"
	href="../dist/syllabus.css" />

<!--comentario Estilos select2 -->
<link rel="stylesheet"
	href="../dist/plugins/select2/css/select2.min.css">
<link rel="stylesheet"
	href="../dist/plugins/select2-theme/select2-bootstrap.min.css">

<!--comentario Estilos de bootstrap 5 -->
<link rel="stylesheet"
	href="../dist/plugins/Bootstrap/css/bootstrap.min.css">

<!--comentario estilos secundarios-->
<link href="../dist/styles.min.css" rel="stylesheet">

<link href="../dist/plugins/DatePicker/daterangepicker.css"
	rel="stylesheet">

<!--comentario Font awesome-->
<link href="../dist/plugins/FontAwesome/css/all.min.css"
	rel="stylesheet">


<!--comentario Estilos Principales-->
<link href="../dist/main.css" rel="stylesheet">
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

			<div class="app-main__outer">
				<div class="app-main__inner">
					<div class="app-page-title">
						<div class="page-title-wrapper">

							<!--comentario inicia la card debajo del header-->
							<div class="page-title-heading">
								<div class="page-title-icon">
									<i class="fas fa-file-pdf"> </i>
								</div>
								<div>
									Creación de Syllabus
									<div class="page-title-subheading">Esta es un ejemplo de Syllabus
									 </div>
								</div>
							</div>
						</div>
					</div> 
						 
						 
						 <div class="card position-relative w-100 h-auto" style="">
							 <div class="container card-body">
							 	<div class="cabecera">
							 		<img class="logouca content-center" alt="Logo UCA" src="../dist/imagen/UCA_Logo.png">
								 		<%String expediente = request.getParameter("expediente");%>
								 		<p class="text-center">
								 			SYLLABUS DE <%=expediente%>
								 		</p>
								 		<div class="Syllabus-cuerpo mt-3 mb-3">
								 			<form action="../SLCreacionSyllabus" method="POST">
								 				<%String pagina = request.getParameter("pagina");
								 				if(pagina == null){
								 					%>
								 					<script>
								 					function volverIndex(){
								 						window.location.href="index.jsp";
								 					}
								 					volverIndex()
								 					</script>
								 					<% 
								 				}
								 				int pagina2 = Integer.parseInt(pagina);								 			
								 				switch(pagina2){
								 				case 0:{
								 					%>
								 					<jsp:include page="component/syllabus/pagina1.jsp?pagina=<%=pagina2+1%>"></jsp:include>
								 					<% 
								 				}
								 				break;
									 				case 1:
									 				{
									 			%>	
									 				<jsp:include page="component/syllabus/pagina1.jsp?pagina=<%=pagina2%>"></jsp:include>
									 			<% 
									 				}
									 				break;
									 				case 2:
									 				{
									 			%>
									 				<jsp:include page="component/syllabus/pagina2.jsp?pagina=<%=pagina2%>"></jsp:include>
									 			<% 
									 				}
									 				break;
									 				
									 				case 3:
									 				{
									 			%>
									 				<jsp:include page="component/syllabus/pagina3.jsp?pagina=<%=pagina2%>"></jsp:include>
									 			<% 
									 				}
									 				break;
									 				
									 				case 4:
									 				{
									 			%>
									 				<jsp:include page="component/syllabus/pagina4.jsp?pagina=<%=pagina2%>"></jsp:include>
									 			<% 
									 				}
									 				break;
									 				
									 				default:
									 				{
									 					%>
									 					<jsp:include page="component/syllabus/finSyllabus.jsp?pagina=<%=pagina2%>"></jsp:include>
									 					<%
									 				}
									 				break;
								 				}
								 				%>
								 				 <input hidden="true"  name="pagina2" value="<%=pagina2%>">
								 				 <input hidden="true"  name="expediente" value="<%=expediente%>">
								 				<input type="submit" class="btn btn-primary btn-block">
								 			</form>
								 		</div>
							 	</div>
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

	<!-- Jquery -->
	<script type="text/javascript"
		src="../dist/plugins/jquery/jquery.min.js"></script>

	<!-- DataPicker -->
	<script type="text/javascript"
		src="../dist/plugins/DatePicker/moment.min.js"></script>
	<script type="text/javascript"
		src="../dist/plugins/DatePicker/daterangepicker.min.js"></script>
	<!----Scripts-->

	<!---- Data range-->
	<script>
		$(function() {
			$('input[name="daterange"]')
					.daterangepicker(
							{

								"locale" : {
									"applyLabel" : "Aceptar",
									"cancelLabel" : "Cancelar",
									"format" : "YYYY-MM-DD",
									"daysOfWeek" : [ "D", "L", "M", "X", "J",
											"V", "S" ],
									"monthNames" : [ "Enero", "Febrero",
											"Marzo", "Abril", "Mayo", "Junio",
											"Julio", "Agosto", "Septiembre",
											"Octubre", "Noviembre", "Diciembre" ],
									"firstDay" : 1,
									orientation : 'bottom'
								},
								"drops" : "up",
								"startDate" : moment(),
								"minDate" : moment(),
								container : '#end',
								setValue : function(start, end) {
									$('#start').val(s1);
									$('#end').val(s2);
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

	<!----Validacion del formulario-->
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


</body>

</html>