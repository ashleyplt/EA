<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page session="true"%>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta charset="ISO-8859-1">
<link rel="shortcut icon" href="../dist/img/icono.png">
<title>Verificar correo</title>

<!--comentario Estilos de bootstrap 5 -->
<link rel="stylesheet" href="../dist/plugins/Bootstrap/css/bootstrap.min.css">

<script src="../dist/plugins/Bootstrap/js/popper.js"></script>
<script src="../dist/plugins/Bootstrap/js/bootstrap.min.js"></script>

<link href="../dist/css/Verificar.css" rel="stylesheet">


</head>

<body class="bg-gradient-primary">
	<%
	int user = Integer.parseInt(request.getParameter("id"));
	%>
	<div class="container ">
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">

								<div class="p-5">
								
									<div class="text-center">
									
										<h1 class="h4 text-gray-900 mb-4">¡Bienvenido al sistema de Expediente de Asignatura! </h1>
										<p class="text-secondary mb-4">Da click en continuar para activar tu cuenta e iniciar sesión.</p>
										
									</div>
									
										<form class="user" action="../SLVerificarCuenta?id_usuario=<%=user%>" method="post">
										<input type="submit"
											class="btn btn-primary btn-user btn-block"
											value="Continuar">
										</form>
									<hr>
							</div>
							
					
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap 5 -->
	<script src="../dist/plugins/Bootstrap/js/popper.js"></script>
	<script src="../dist/plugins/Bootstrap/js/bootstrap.min.js"></script>
	<!-- AdminLTE App_ -->
	<script src="../dist/js/adminlte.min.js"></script>
	
	<script type="text/javascript">
	function activar(){
		window.location.href=url+"../SL="+exp;
	}
	</script>
</body>