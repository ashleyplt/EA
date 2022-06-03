<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@page import="vistas.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.*"%>
<%@page import="entidades.*"%>

<%
	response.setHeader("Cache-Control", "no-cache,post-check=0,pre-check=0");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "Thu,01 Dec 1994 16:00:00 GMT");
response.setDateHeader("Expires", -1);
%>

<!DOCTYPE html>

<html lang="es">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Cambiar Contraseña</title>
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


<!-- Sweet Alert -->
<link href="../dist/sweetalert2.css" rel="stylesheet">
<script src="../dist/sweetalert2.all.min.js"></script>

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
									<i class="fas fa-file-signature"></i>
								</div>

								<%
								String ocultar = "none";
								HttpSession usuario = request.getSession();
								int id = (int) usuario.getAttribute("id_usuario");
								String error1 = (String) usuario.getAttribute("Error_al_verificar");
								String ocultar2 = (String) usuario.getAttribute("ocultarf");
								String ocultar3 = "block";
								if (ocultar2 != null && ocultar2.equals("block")) {
									ocultar = ocultar2;
									ocultar3 = "none";
								}
								if (error1 == null || error1 == "") {
								} else {
								%>

								<script>
									Swal.fire({
										title : 'Contraseña no coincide.',
										icon : 'error'
									})
								</script>

								<%
									session.setAttribute("Error_al_verificar", "");
								}
								DTVUsuarioSeguridad dtvus = new DTVUsuarioSeguridad();
								ArrayList<VW_Usuarios> listarUsuarios = new ArrayList<VW_Usuarios>();
								listarUsuarios = dtvus.listarUsuariosenPerfil(id);
								for (VW_Usuarios u : listarUsuarios) {
								%>
								<div>
									<%=u.getNombre()%>
									<%=u.getApellido()%>
								</div>
								<%
									}
								%>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="main-card  card">
								<div class="card-header">
									<h5>Cambiar Contraseña de Usuario</h5>
									<div class="btn-actions-pane-right">
										<div role="group" class="btn-group-sm btn-group">
											<div class="page-title-actions"></div>
										</div>
									</div>
								</div>
								<form name="form" id="form" class="form card-body" method="post"
									style="display:<%=ocultar3%>">
									<!-- Steps -->
									<div class="col-md-12 mt-2">
										<div class="form-group">
											<label>Contraseña Anterior</label> <input type="password"
												class="form-control" style="width: 100%;" id="usuario"
												name="usuario" placeholder="Escriba su contraseña anterior">
											<div class="valid-feedback">Contraseña válido</div>
											<div class="invalid-feedback">Por favor ingrese una
												contraseña válida</div>
										</div>
									</div>
									<div class="container btns-group mt-3 mb-5">
										<button class="btn btn-next btn-primary active" type="submit"
											onclick="n1()">Siguiente</button>
									</div>

								</form>

								<form id="form2" name="form2" class="container " style="display:<%=ocultar%>" method="post">
								<input name="opcion" value="2" style="display: none;"> 
								<input name="id_usuario" value="<%=id%>" style="display: none;">

									<div class="mt-2">
										<div class="col-md-12 mt-2">
											<div class="form-group">
												<label>Nueva Contraseña</label> <input type="password"
													class="form-control" style="width: 100%;" id="pwd1"
													name="pwd1" placeholder="Escriba su contraseña anterior"
													required>
												<div class="valid-feedback">Contraseña válido</div>
												<div class="invalid-feedback">Por favor ingrese una
													contraseña válida</div>
											</div>

										</div>
										<div class="col-md-12 mt-2">
											<div class="form-group">
												<label>Vuelva a escribir su contraseña</label> <input
													type="password" class="form-control" style="width: 100%;"
													id="pwd2" name="pwd2"
													placeholder="Escriba su contraseña anterior" required>
												<div class="valid-feedback">Contraseña válido</div>
												<div class="invalid-feedback">Por favor ingrese una
													contraseña válida</div>
											</div>

										</div>
									</div>
									<div class="container btns-group mt-3 mb-5">

										<button class="btn btn-next btn-primary active" onclick="n2()">Cambiar</button>
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

	<!-- script Principal -->
	<script type="text/javascript" src="../dist/assets/scripts/main.js"></script>

	<script>
		function salert(input) {
			Swal.fire({
				position : 'top',
				icon : 'warning',
				title : 'Rellene el campo ' + input,
				showConfirmButton : false,
				timer : 3500
			})
		}
		function salertigual(input) {
			Swal.fire({
				position : 'top',
				icon : 'warning',
				title : 'Las Contraseñas no son iguales',
				showConfirmButton : false,
				timer : 3500
			})
		}
		function n1() {
			var texto = $("#usuario").val();
			if (texto == "") {
				input = "contraseña";
				salert(input)
				return false;
			}
			document.form.action = "../SLVerificarPwd";
			document.form.submit();
		}
		function n2() {
			var pwd1 = $("#pwd1").val();
			var pwd2 = $("#pwd2").val();
			
			if(pwd1 == "" || pwd2 == ""){
				input = "contraseña";
				salert(input)
				return false;	 	
			}else{
				if (pwd1 === pwd2) {					
					document.form2.action = "../SLusuario";
			    	document.form2.submit();	
				}else{
					salertigual()
					return false;
				}	
			}
		}
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