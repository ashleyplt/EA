<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="entidades.*,datos.*,java.util.*;"%>

<%
		//Modalidad de la Actividad
		ArrayList<ModalidadActividad> listModalidades = new ArrayList<ModalidadActividad>();
		DTModalidadActividad dtma = new DTModalidadActividad();
		listModalidades = dtma.listarTMActividad();
		//Tipo de la Actividad
		ArrayList<TipoActividad> listTipoActividad = new ArrayList<TipoActividad>();
		DTTipoActividad dtta = new DTTipoActividad();
		listTipoActividad = dtta.listarTActividad();
		//Evaluación de la Actividad
		ArrayList<TipoEvaluacionActividad> listTipoEActividad = new ArrayList<TipoEvaluacionActividad>();
		DTTEActividad dttea = new DTTEActividad();
		listTipoEActividad = dttea.listarTEActividad();
%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Editar Actividad</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />

<!-- <link rel="shortcut icon" href="../dist/img/icono.png">-->

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
							<%
							//Obtenemos los datos de la activididad en base al id que recuperamos anteriormente
							String id_actividad = request.getParameter("id_actividad");
							Actividad a = new Actividad();
							DTActividad dta = new DTActividad();
							a = dta.getActividad(Integer.parseInt(id_actividad));
							
							//Obtener el id del usuario que está en la sesión
							HttpSession usuario = request.getSession();
							int id_user = (int) usuario.getAttribute("id_usuario");
							%>
							<div class="page-title-heading">
								<div class="page-title-icon">
									<img src="https://i.imgur.com/IZRXXvB.png"
										style="border-radius: 20px 20px; width: 40px; height: 40px"
										alt="icono.jpg">
								</div>
								<div>
									<h1><%=a.getNombre()%></h1>
									<div class="page-title-subheading"><%=a.getDescripcion()%></div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="main-card  card">
								<div class="card-header">Editar Datos de Actividad</div>
								<form action="../SLActividad?id_actividad=<%=id_actividad%>"
									method="post" class="needs-validation" novalidate>
									<input name="opcion" value=2 style="display: none;"> <input
										name="id_usuario" value="<%=id_user%>" style="display: none;">
									<div class="col-md-12 mt-2">
										<div class="form-group">
											<label>Nombre</label> <input type="text"
												class="form-control " style="width: 100%;" name="actName"
												id="validationCustomUsername"
												placeholder="Escriba el nombre" value="<%=a.getNombre() %>"
												required>
											<div class="valid-feedback">Nombre valido</div>
											<div class="invalid-feedback">Por favor ingrese un
												nombre valido</div>
										</div>

									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label>Descripción</label> <input type="text"
												class="form-control" style="width: 100%;" id=actDesc
												name="actDesc"
												placeholder="Escriba una descripcion para la Actividad"
												value="<%=a.getDescripcion()%>" required>
											<div class="valid-feedback">Descripción válida</div>
											<div class="invalid-feedback">Por favor ingrese una
												descripción válida</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<%
												TipoActividad ta = new TipoActividad();
												DTTipoActividad dttaa = new DTTipoActividad();
												ta = dttaa.getTActividad(a.getId_tipo_actividad());
										%>
											<label>Tipo</label> <select class="select2" name="tActividad"
												id="tActividad" data-placeholder="<%=ta.getNombre()%>"
												style="width: 100%;" required>

												<%
													for (TipoActividad u : listTipoActividad) {
												%>

												<option value="<%=u.getId()%>"><%=u.getNombre()%></option>
												<%
													}
												%>

											</select>
											<div class="valid-feedback">Tipo Válido</div>
											<div class="invalid-feedback">Por favor selecione un
												tipo de actividad</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<%
												ModalidadActividad ma = new ModalidadActividad();
												DTModalidadActividad dtm = new DTModalidadActividad();
												ma = dtm.getMActividad(a.getId_modalidad_actividad());
										%>
											<label>Modalidad</label> <select class="select2"
												name="mActividad" id="mActividad"
												data-placeholder="<%=ma.getNombre()%>" style="width: 100%;"
												required>
												<%
													for (ModalidadActividad u : listModalidades) {
												%>

												<option value="<%=u.getId_modalidad_actividad()%>"><%=u.getNombre()%></option>
												<%
													}
												%>

											</select>
											<div class="valid-feedback">Modalidad Válida</div>
											<div class="invalid-feedback">Por favor selecione una
												modalidad de actividad</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<%
												TipoEvaluacionActividad tea = new TipoEvaluacionActividad();
												DTTEActividad dtea = new DTTEActividad();
												tea = dtea.getTEActividad(a.getId_evaluacion_actividad());
										%>
											<label>Evaluación</label> <select class="select2"
												name="teActividad" id="teActividad"
												data-placeholder="<%=tea.getDescripcion()%>"
												style="width: 100%;" required>
												<%
													for (TipoEvaluacionActividad u : listTipoEActividad) {
												%>

												<option value="<%=u.getId()%>"><%=u.getDescripcion()%></option>
												<%
													}
												%>
											</select>
											<div class="valid-feedback">Evaluación Válida</div>
											<div class="invalid-feedback">Por favor selecione una
												modalidad de actividad</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label class="form-label">Numero de horas</label> <input
												type="number" class="form-control w-100" min="1" max="5"
												name="cant_horas" id="cant_horas"
												placeholder="Número de horas estimadas para realizar la actividad"
												value="<%=a.getCant_horas()%>" required>

											<div class="valid-feedback">Número valido</div>
											<div class="invalid-feedback">Por favor ponga una
												número en el rango de 1-5</div>
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
	<!----Scripts-->

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