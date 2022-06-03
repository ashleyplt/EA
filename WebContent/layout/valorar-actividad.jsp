<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="entidades.*,datos.*,java.util.*;"%>
	


<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Valorar Actividad</title>
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

<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<link href="http://netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet"/>

<!--comentario Font awesome-->
<link href="../dist/plugins/FontAwesome/css/all.min.css"
	rel="stylesheet">

<!--comentario Estilos Principales-->
<link href="../dist/main.css" rel="stylesheet">

<!-- Comentario de starrr -->
<link href="../dist/starrr.css" rel="stylesheet"/>


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
								<div class="card-header">Valoración de Actividad</div>
								<form action="../SLValoracionActividad?id_actividad=<%=id_actividad%>" 
									method="post" class="needs-validation"  >
									<input name="opcion" value=3 style="display: none;"> <input
										name="id_usuario" value="<%=id_user%>" style="display: none;">
									<div class="col-md-12 mt-2">
										<div class="form-group">
											<label>Comentario: </label> <input type="text"
												class="form-control " style="width: 100%;" name="comentario"
												id="validationCustomUsername"
												placeholder="Escriba un comentario de la actividad"
												required>
											<div class="valid-feedback">comentario válido</div>
											<div class="invalid-feedback">Por favor ingrese un
												comentario válido</div>
										</div>

									</div>
						             
						           <div class="container mb-3">
						            Calificar: <span id="Estrellas"></span>
						            <input type="hidden" name ="temp" id="temp"  value ="0">
						             
									<div class="d-block text-center card-footer mt-3">
										<input class="btn-wide btn btn-success" type="submit" value="Guardar"> 
									</div>
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

	<!----Validacion del formulario-->
	<script src="../dist/starrr.js"></script>
	<script>
	
	   $("#Estrellas").starrr({
	       rating:6,
	       change:function(e, valor){   
	           $("#temp").val(valor);
	       }
	   })
	
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