<%@page import="entidades.EdicionAsignatura"%>
<%@page import="datos.DTEdicionAsignatura"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>

<html lang="es">

<head>
<meta charset="utf-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="es">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Edición de asignatura</title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
<link rel="stylesheet"
	href="../dist/plugins/Bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="../dist/plugins/select2/css/select2.min.css">
<link rel="stylesheet"
	href="../dist/plugins/select2-theme/select2-bootstrap.min.css">
<link href="../dist/styles.min.css" rel="stylesheet">
<link href="../dist/plugins/DatePicker/daterangepicker.css"
	rel="stylesheet">
<link href="../dist/plugins/FontAwesome/css/all.min.css"
	rel="stylesheet">
<link href="../dist/main.css" rel="stylesheet">
<link rel="stylesheet" href="../dist/plugins/sweetalert2/dist/sweetalert2.min.css">
</head>
<body>
	<div
		class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">
		<jsp:include page="./component/header.jsp"></jsp:include>
		<jsp:include page="./component/settings.jsp"></jsp:include>
		<%
		HttpSession usuario = request.getSession();
		String cargo = usuario.getAttribute("cargo").toString();
		if(cargo.equals("")) cargo = null;
		if(cargo == null){
		%>
		<script>
		window.location.href = "index.jsp";
		window.reload
		</script>
		<%
		}
		%>
		<div class="app-main">
			<jsp:include page="./component/movil-menu.jsp"></jsp:include>
			<jsp:include page="./component/menu.jsp"></jsp:include>
			<div class="app-main__outer">
				<div class="app-main__inner">
					<div class="app-page-title">
						<div class="page-title-wrapper">
							<div class="page-title-heading">
								<div class="page-title-icon">
									<i class="far fa-calendar-plus icon-gradient bg-mean-fruit">
									</i>
								</div>
								<div>
									Ediciones de asignatura  
									<div class="page-title-subheading">Nuevo ciclo académico
										para nuevas ediciones de asignaturas.</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="main-card  card">
								<div class="card-header">Aperturar ediciones de asignatura</div>
								<%
									DTEdicionAsignatura dtea = new DTEdicionAsignatura();
									EdicionAsignatura ea = dtea.getEdicionActiva();
								%>
								<form method="post" action="../SLEdicionAsignatura<%if(ea != null)%><%="?id="+ea.getId()%>"<%; %> class="needs-validation" novalidate>
									
									<div class="col-md-12 mt-2">
										<div id="form-group-edicion" class="form-group">
											<label>Nombre de la nueva edición</label>
											<i class="icon ni fas fa-question-circle" id="popover" data-trigger="hover" data-container="body" data-toggle="popover" data-placement="right" data-content="Ejemplo: IISemestre2021" style="cursor:pointer;"></i> 
											<input type="text"
												name="edicion" maxlength="80"
												class="form-control" style="width: 100%;"
												id="validationCustomUsername" <%if(ea != null) { %> value="<%=ea.getNombre()%>" <%} %>
												placeholder="Escriba el nombre" required>
											<div class="invalid-feedback">Escriba un nombre válido.</div>
											<div id="check-availability"></div>
										</div>
									</div>
									<!-- Termina de campo -->
									<div class="col-md-12">
										<div class="form-group">
											<label for="formFileMultiple" class="form-label">Rango
												de fecha.</label> 
											<i class="icon ni fas fa-question-circle" id="popover" data-trigger="hover" data-container="body" data-toggle="popover" data-placement="right" data-content="Estos rangos de fecha controlan los accesos y cierre de una edición de expediente a un docente impartiendo una asignatura." style="cursor:pointer;"></i> 
											<div class="input-group">
												<div class="input-group-prepend">
													<span class="input-group-text"> <i
														class="far fa-calendar-alt"></i>
													</span>
												</div>
												<input type="text" class="form-control float-right"
													type="date" name="daterange" id="drango" required>
											</div>
											<div class="invalid-feedback">Por favor selecione un
												rango de fecha válido.</div>
										</div>
									</div>
									<div class="d-block text-center card-footer">
										<button class="btn-wide btn btn-success" ><%if(ea != null) {%> Modificar <%} else {%> Guardar <%}%></button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="../dist/assets/scripts/main.js"></script>
	<!-- Bootstrap 5 -->
	<script src="../dist/plugins/Bootstrap/js/popper.js"></script>
	<script src="../dist/plugins/Bootstrap/js/bootstrap.min.js"></script>

	<!-- JQuery -->
	<script type="text/javascript"
		src="../dist/plugins/jquery/jquery.min.js"></script>

	<!-- DataPicker -->
	<script type="text/javascript"
		src="../dist/plugins/DatePicker/moment.min.js"></script>
	<script type="text/javascript"
		src="../dist/plugins/DatePicker/daterangepicker.min.js"></script>
	<script src="../dist/plugins/sweetalert2/dist/sweetalert2.min.js"></script>
	<script>
	$(function() {
	    $('input[name="daterange"]').daterangepicker({
	    	 "locale": {
	    		 	"applyLabel": "Aceptar",
	    	        "cancelLabel": "Cancelar",
	    	        "format": "YYYY-MM-DD",
	    	        "daysOfWeek": ["D", "L", "M", "X", "J", "V", "S"],
	    	        "monthNames": ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", 
  	        				"Septiembre", "Octubre", "Noviembre", "Diciembre"],
	    	        "firstDay": 1
	    	    },
	    	    "drops": "up",
	    	    "startDate": <%if(ea != null) {%> "<%=ea.getFecha_inicio()%>" <%} else {%> moment() <%}%>,
	    	    "endDate": <%if(ea != null) {%> "<%=ea.getFecha_cierre()%>" <%} else {%> moment().add(5, "days") <%}%>,
	    	    "minDate": <%if(ea != null) {%> "<%=ea.getFecha_inicio()%>" <%} else {%> moment() <%}%>,
	    	    "maxDate": moment().add(6, 'months'),
	    	    container: '#end',
	    	    setValue: function(start, end)
	    		{
	    			$('#start').val(s1);
	    			$('#end').val(s2);
	    		}
	    }, function(start, end) {
	      startDate = start;
	      endDate = end;
	    });
    });
	</script>
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