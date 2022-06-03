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
								<div class="card-header">Crear Edición</div>
								<form method="post" action="../SLEdicionAsignatura" class="needs-validation" novalidate>
									<!-- Inicio de campo -->
									<div class="col-md-12 mt-2">
										<div id="form-group-edicion" class="form-group">
											<label>Nombre de la nueva edición</label>
											<i class="icon ni fas fa-question-circle" id="popover" data-trigger="hover" data-container="body" data-toggle="popover" data-placement="right" data-content="Ejemplo: IISemestre2021" style="cursor:pointer;"></i> 
											<input type="text"
												name="edicion" maxlength="80"
												class="form-control" style="width: 100%;"
												id="validationCustomUsername"
												placeholder="Escriba el nombre" required>
											<div class="valid-feedback">Validado.</div>
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
											<div class="valid-feedback">Rango de fecha valido</div>
											<div class="invalid-feedback">Por favor selecione un
												rango de fecha valido</div>
										</div>
									</div>
									<div class="d-block text-center card-footer">
										<button   class="btn-wide btn btn-success" >Guardar</button>
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
	    	    "startDate": moment(),
	    	    "endDate": moment().add(5, "days"),
	    	    "minDate": moment(),
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
		String opc = request.getParameter("msg");
		String msg = "";
		String title = "Oops";
		String icon = "error";
		
		if(opc != null){
			switch(opc){
			case "1":
				msg = "Se guardó con éxito";
				icon = "success";
				
				break;
			case "2":
				msg = "Hubo un error al guardarse.";
				break;
			case "3":
				msg = "Ya existe una edición con ese nombre.";
				break;
			}
	%>
	<script>
	function msg(){
		Swal.fire({
			icon: '<%=icon%>',
			title: '<%=msg%>',
			confirmButtonText: 'Aceptar',
			confirmButtonColor: '#3085d6'
			})
	}
	msg()
	</script>
	<%
		}
	%>
	<script>
		
		
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
</body>
</html>