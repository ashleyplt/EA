<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="vistas.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.*"%>
<%@page import="entidades.*"%>

<%
//Listar Materiales
ArrayList<Material> listMaterials = new ArrayList<Material>();
DTMaterial Materiales = new DTMaterial();
listMaterials = Materiales.listarMateriales();
//Listar Tipos de Actividad
ArrayList<TipoActividad> listTipoActividad = new ArrayList<TipoActividad>();
DTTipoActividad TActividad = new DTTipoActividad();
listTipoActividad = TActividad.listarTActividad();
//Listar modalidad de las actividades
ArrayList<ModalidadActividad> listModalidades = new ArrayList<ModalidadActividad>();
DTModalidadActividad mActividad = new DTModalidadActividad();
listModalidades = mActividad.listarTMActividad();
//Listar los tipos de evaluaciones de las actividades
ArrayList<TipoEvaluacionActividad> listTEvaluacion = new ArrayList<TipoEvaluacionActividad>();
DTTEActividad teActividad = new DTTEActividad();
listTEvaluacion = teActividad.listarTEActividad();
%>

<!DOCTYPE html>

<html lang="es">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="es">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Creación de Actividades</title>
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
<script type="text/javascript" src="../dist/jquery2.min.js"></script>

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


<link rel="stylesheet"
	href="../dist/plugins/sweetalert2/dist/sweetalert2.min.css">


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
									<img src="https://i.imgur.com/IZRXXvB.png"
										style="border-radius: 20px 20px; width: 40px; height: 40px"
										alt="icono.jpg">
								</div>
								<div>
									Crear Actividades
									<div class="page-title-subheading">Rellene los campos y
										de click en guardar para crear una nueva actividad</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="main-card  card">
								<div class="card-header">
									Crear Actividad
									<div class="btn-actions-pane-right">
										<div role="group" class="btn-group-sm btn-group">
											<div class="page-title-actions">
												<!-- Acciones en el encabezado de la tarjeta -->
											</div>
										</div>
									</div>
								</div>
								<%
								HttpSession usuario = request.getSession();
								int id = (int) usuario.getAttribute("id_usuario");
								%>
								<!-- Inicia el Form de crear actividad -->
								<form method="post" action="../SLActividad"
									class="needs-validation" novalidate>

									<input name="opcion" value=1 style="display: none;"> <input
										name="id_usuario" value=<%=id%> style="display: none;">

									<div class="col-md-12 mt-2">
										<div class="form-group">
											<label>Nombre</label> <input type="text" class="form-control"
												style="width: 100%;" name="actName" id="actName"
												placeholder="Escriba el nombre de la Actividad" required>
											<div class="valid-feedback">Nombre válido</div>
											<div class="invalid-feedback">Por favor ingrese un
												nombre válido</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label>Descripción</label> <input type="text"
												class="form-control " style="width: 100%;" id="actDesc"
												name="actDesc"
												placeholder="Escriba una descripcion para la Actividad"
												required>
											<div class="valid-feedback">Descripción válida</div>
											<div class="invalid-feedback">Por favor ingrese una
												descripción válida</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label>Tipo</label> <select class="select2" name="tActividad"
												id="tActividad"
												data-placeholder="Seleccione el tipo de actividad"
												style="width: 100%;" required>
												<option value="">Selecione al menos uno</option>
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
											<label>Modalidad</label> <select class="select2"
												name="mActividad" id="mActividad"
												data-placeholder="Seleccione la modalidad de actividad"
												style="width: 100%;" required>
												<option value="">Selecione al menos uno</option>
												<%
													for (ModalidadActividad r : listModalidades) {
												%>
												<option value="<%=r.getId_modalidad_actividad()%>"><%=r.getNombre()%></option>
												<%
													}
												%>

											</select>
											<div class="valid-feedback">Modalidad Válida</div>
											<div class="invalid-feedback">Por favor selecione una
												modalidad de una actividad</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label>Evaluación</label> <select class="select2"
												name="teActividad" id="teActividad"
												data-placeholder="Seleccione el tipo de evaluacion de actividad"
												style="width: 100%;" required>
												<option value="">Selecione al menos uno</option>
												<%
													for (TipoEvaluacionActividad u : listTEvaluacion) {
												%>
												<option value="<%=u.getId()%>"><%=u.getDescripcion()%></option>
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
											<label class="form-label">Numero de horas</label> <input
												type="number" class="form-control w-100" min="1" max="5"
												name="cant_horas" id="cant_horas"
												placeholder="Número de horas estimadas para realizar la actividad"
												value="1" required>

											<div class="valid-feedback">Número valido</div>
											<div class="invalid-feedback">Por favor ponga una
												número en el rango de 1-5</div>
										</div>
									</div>
									<div class="col-md-12">
										<button type="button" class="btn btn-secondary btn-sm"
											onclick="material()">Agregar Materiales</button>
									</div>
									<div class="d-block text-center card-footer">
										<button type="submit" class="btn-wide btn btn-success">Guardar</button>
									</div>
									<iframe style="display: none;" name="iframe"> </iframe>
								</form>
								<!-- Termina el Form de crear actividad -->
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
	<!--Scripts-->
	<!-- Selectt 2 -->
	<script src="../dist/plugins/select2/js/select2.full.min.js"></script>

	<!-- script Principal -->
	<script type="text/javascript" src="../dist/assets/scripts/main.js"></script>

	<script src="../dist/plugins/sweetalert2/dist/sweetalert2.min.js"></script>

	<script>
        $(function () {
            //Initialize Select2 Elements
            $('.select2').select2()
            //Initialize Select2 Elements
            $('.select2bs4').select2({
                theme: 'bootstrap4'
            })
        })
    </script>

	<script>
    function aMaterial(){
    	document.formulario.target = "iframe";
    	document.formulario.action = "../SLMaterial";
    	document.formulario.submit();	
    }
    </script>

	<!--Validación del formulario-->
	<script>
       (function () {
           'use strict'
           //id del formulario
           var forms = document.querySelectorAll('.needs-validation')
           // validacion al intentar enviar datos
           Array.prototype.slice.call(forms)
               .forEach(function (form) {
                   form.addEventListener('submit', function (event) {
                       if (!form.checkValidity()) {
                           event.preventDefault()
                           event.stopPropagation()
                       }
                       form.classList.add('was-validated')
                   }, false)
               })
       });
       
       function material(){
    	   
    	   Swal.fire({
    		   title: 'Añade materiales para tu actividad',   html:
    		     '<form name=\"formulario\" method=\"post\" class=\"container\"' 
    		     + '<div class=\"col-md-12\">' 
    		     + '<div class="form-group">' 
    		     + '<label style="position:relative; left:-40%;">Nombre: </label>'
    		     + '<input type="text" class="form-control " style="width: 100%;"'
    		     + 'id="nMaterial" name="nMaterial" placeholder="Escriba un nombre para el Material" required>'
    		     + '<div class="valid-feedback">'
    		     + 'Nombre válido'
    		     + '</div>'
    		     + '<div class="invalid-feedback">'
    		     + 'Por favor ingrese un nombre válido'
    		     + '</div>'
    		     + '</div>'
    		     + '</div>'
    		     + '<div class=\"col-md-12\">' 
    		     + '<div class="form-group">' 
    		     + '<label style="position:relative; left:-40%;">Descripción: </label>'
    		     + '<input type="text" class="form-control " style="width: 100%;"'
    		     + 'id="dMaterial" name="dMaterial" placeholder="Escriba una descripción para el Material" required>'
    		     + '<div class="valid-feedback">'
    		     + 'Descripción válida '
    		     + '</div>'
    		     + '<div class="invalid-feedback">'
    		     + 'Por favor ingrese una descripción válida'
    		     + '</div>'
    		     + '</div>'
    		     + '</div>'
    		     + '<div class=\"col-md-12\">' 
    		     + '<div class="form-group">' 
    		     + '<label style="position:relative; left:-48%;">URL: </label>'
    		     + '<input type="text" class="form-control " style="width: 100%;"'
    		     + 'id="urlMaterial" name="urlMaterial" placeholder="Escriba una url para el Material" required>'
    		     + '<div class="valid-feedback">'
    		     + 'Url válido '
    		     + '</div>'
    		     + '<div class="invalid-feedback">'
    		     + 'Por favor ingrese una url válida ' 
    		     + '</div>'
    		     + '</div>'
    		     + '</div>'
    		     + '</form>',
    		   inputAttributes: {
    		     autocapitalize: 'off'
    		   },
    		   showCancelButton: true,
    		   confirmButtonText: 'Guardar',
    		   showLoaderOnConfirm: true,
    		   preConfirm: (login) => {
    			   aMaterial()
    		   },
    		   allowOutsideClick: () => !Swal.isLoading()
    		 }).then((result) => {
    		   if (result.isConfirmed) {
    		     Swal.fire({
    		       title: `${result.value.login}Se ha Guardado en la Base de datos`
    		     })
    		   }
    		 })  	  
       }
    </script>
</body>
</html>