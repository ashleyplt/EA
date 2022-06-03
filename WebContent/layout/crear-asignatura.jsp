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

<title>Asignatura</title>

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
									Asignatura
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="main-card  card">
								<div class="card-header">Crear nueva asignatura</div>
								<form method="post" action="../SLAsignatura" class="needs-validation" novalidate>
									<!-- Inicio de campo -->
									<div class="form-row">
									<div class="col-md-8 ml-3 mt-2">
										<div class="form-group">
											<label>Nombre de la nueva asignatura</label>
											<input type="text"
												name="nombre" maxlength="50"
												class="form-control" 
												id="validationCustomUsername"
												placeholder="Escriba el nombre" required>
											<div class="valid-feedback">Validado.</div>
											<div class="invalid-feedback">Escriba un nombre válido.</div>
										</div>
									</div>
									<!-- Termina de campo -->
									<div class="col-md mt-2 mr-3">
										<div class="form-group ">
											<label>Código de asignatura</label>
											<input type="text"
												name="codigo" maxlength="10"
												class="form-control" style="width: 100%;"
												id="validationCustomUsername"
												placeholder="Escriba el código" required>
											<div class="valid-feedback">Validado.</div>
											<div class="invalid-feedback">Escriba un nombre válido.</div>
										</div>
									</div>
									</div>
									
									<div class="col-md-12 mt-2">
										<div class="form-group">
											<label>Descripción</label>
											<input type="text"
												name="descripcion" maxlength="150"
												class="form-control" style="width: 100%;"
												id="validationCustomUsername"
												placeholder="Escriba alguna descripción de la asignatura" required>
											<div class="valid-feedback">Validado.</div>
											<div class="invalid-feedback">Escriba un nombre válido.</div>
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
	
	<%
		String opc = request.getParameter("msg");
		String msg = "";
		
		if(opc != null){
			switch(opc){
			case "1":
				msg = "Hubo un error al guardarse.";
				break;
			case "2":
				msg = "Ya existe una asignatura con ese nombre o código.";
				break;
			}
	%>
	<script>
	function msg(){
		Swal.fire({
			icon: 'error',
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