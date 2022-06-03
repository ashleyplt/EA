<%@page import="datos.DTVExpedienteCarrera"%>
<%@page import="datos.DTAsignatura"%>
<%@page import="entidades.Asignatura"%>
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
			<%
			HttpSession usuario = request.getSession();
			String cargo = usuario.getAttribute("cargo").toString();
			
			int opc = 0;
			int id = 0;
			Asignatura a = new Asignatura();
			String tmp = request.getParameter("opc");
			
			if(tmp != null) opc = Integer.parseInt(tmp);
			if(opc <= 1 || opc > 2 || tmp == null) opc = 1;
			if(opc == 2) {
				id = Integer.parseInt(request.getParameter("id"));
				DTAsignatura dta = new DTAsignatura();
				a = dta.getAsignaturaPorId(id);
			}
			
			String carrera = "";
			
			if(!cargo.equals("")) carrera = session.getAttribute("carrera").toString();
			
			DTVExpedienteCarrera dtvec = new DTVExpedienteCarrera();
			boolean existePlan = dtvec.existeExpedienteEnCarrera(String.valueOf(id), 2, carrera);
			
			if(cargo.equals("") || (opc == 2 && !existePlan)){
			%>
			<script>
			window.location.href = "index.jsp";
			</script>
			<%
			}
			%>
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
								<form method="post" action="../SLAsignatura?opc=<%=opc%><%if(opc == 2){ %>&id=<%=id%> <%} %>" class="needs-validation" novalidate>
									<!-- Inicio de campo -->
									<div class="form-row">
									<div class="col-md-7 ml-3 mt-2">
										<div class="form-group">
											<label>Nombre de la nueva asignatura</label>
											<input type="text"
												name="nombre" maxlength="50"
												class="form-control" <%if(opc == 2){ %> value="<%=a.getNombre() %>" <%} %>
												id="validationCustomUsername"
												placeholder="Escriba el nombre" required>
											<div class="invalid-feedback">Escriba el nombre de la asignatura.</div>
										</div>
									</div>
									<!-- Termina de campo -->
									<div class="col-md-3 mt-2">
										<div class="form-group ">
											<label>Código de asignatura</label>
											<input type="text"
												name="codigo" maxlength="10"
												class="form-control" style="width: 100%;"
												id="validationCustomUsername" <%if(opc == 2){ %> value="<%=a.getCodigo()%>" <%} %>
												placeholder="Escriba el código" required>
											<div class="invalid-feedback">Escriba un código no más de 10 caracteres.</div>
										</div>
									</div>
									<div class="col-md mt-2 mr-3">
										<div class="form-group ">
											<label>Créditos</label>
											<input type="number"
												name="creditos" min="1" max="7" <%if(opc == 2){ %> value="<%=a.getCreditos() %>" <%} %>
												class="form-control" style="width: 100%;"
												id="validationCustomUsername"
												placeholder="No. créditos" required>
											<div class="invalid-feedback">Ingrese los créditos. Máximo 7.</div>
										</div>
									</div>
									</div>
									
									<div class="col-md-12 mt-2">
										<div class="form-group">
											<label>Descripción</label>
											<input type="text"
												name="descripcion" maxlength="150"
												class="form-control" style="width: 100%;"
												id="validationCustomUsername" <%if(opc == 2){ %> value="<%=a.getDescripcion() %>" <%} %>
												placeholder="Escriba alguna descripción de la asignatura" required>
											<div class="invalid-feedback">Escriba una breve descripción.</div>
										</div>
									</div>
									<div class="d-block text-center card-footer">
										<button class="btn-wide btn btn-success" ><%if(opc == 2){ %> Modificar <%} else { %> Guardar <%} %></button>
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