<%@page import="datos.*"%>
<%@page import="vistas.*"%>
<%@page import="entidades.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>

<html lang="es">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Creación de syllabus</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
<!-- <link rel="shortcut icon" href="../dist/img/icono.png">-->
<!--comentario Estilos Date Picker -->
<link rel="stylesheet" type="text/css"
	href="../dist/plugins/DatePicker/daterangepicker.css" />

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
		
		<%
		int idexpediente = Integer.parseInt(request.getParameter("idexpediente"));
		String edicion =  (String) session.getAttribute("nombre_edicion") ;
		String asignatura = (String) session.getAttribute("nombre_expediente") ;
		
		ArrayList<Carrera> listCarrera = new ArrayList<Carrera>();
		DTCarrera Carrera = new DTCarrera();
		listCarrera = Carrera.listarCarreras();
		%>
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
									<i class="fas fa-scroll icon-gradient bg-mean-fruit"> </i>
								</div>
								<div>
									Crear Syllabus
									<div class="page-title-subheading">Indique los datos que se le piden</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="main-card  card">
								<div class="card-header">
									Datos del syllabus
								</div>
								<form action="../SLSyllabus" method="post" class="needs-validation" novalidate>
								 <input hidden="true"  id="opcionSyllabus" name="opcionSyllabus" value="1">
								 <input hidden="true"  id="id_expediente" name="id_expediente" value="<%=idexpediente%>">
								  
								  <div class="col-md-12 mt-2">
										<div class="form-group">
											<label>Asignatura </label> 
											<input type="text" class="form-control " name="asignatura" value="<%=asignatura%>"  style="width: 100%;" readonly />
										</div>

									</div>
									
									<div class="col-md-12 mt-2">
										<div class="form-group">
											<label>Periodo Académico</label> 
											<input type="text" class="form-control " name="periodo" value="<%=edicion%>" style="width: 100%;" readonly />
										</div>

									</div>
								  
									  <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Carrera</label> 
                                            <select class="select2"   name="id_carrera" data-placeholder="Seleccione una Carrera" style="width: 100%;" required>
                                                <option value=""> Selecione al menos una</option>
                                                <%
												for (Carrera c : listCarrera) {
												%>
												<option value="<%=c.getId_carrera()%>"><%=c.getNombre()%></option>
												<%
													}
												%>
                                            </select>
                                            <div class="valid-feedback">
                                                Carrera Valida
                                            </div>
                                            <div class="invalid-feedback">
                                                Por favor selecione una carrera
                                            </div>
                                        </div>
                                    </div>
								
									<div class="d-block text-center card-footer">
										<button class="btn-wide btn btn-success">Guardar</button>
									</div>
								</form>

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