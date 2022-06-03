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
<link rel="shortcut icon" href="dist/img/icono.png">
<title>Iniciar Sesión</title>

<!--comentario Estilos de bootstrap 5 -->
<link rel="stylesheet" href="./dist/plugins/Bootstrap/css/bootstrap.min.css">

<script src="./dist/plugins/Bootstrap/js/popper.js"></script>
<script src="./dist/plugins/Bootstrap/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="./dist/sweetalert2.min.css">
<link href="./dist/css/login.css" rel="stylesheet">

<script src="./dist/sweetalert2.all.min.js"></script>


</head>

<body class="bg-gradient-primary">
	<%
	HttpSession usuario = request.getSession(); 
	String nombre = (String) usuario.getAttribute("nombre_user");
	String error = (String) usuario.getAttribute("error");
	String nombre2 = nombre;
	if(nombre2 == null || nombre2 == ""){
	%>
	<%
	} else {
	%>
	<script>
		//function redireccionar(){ 
			//window.location.href="./layout/";
		//}
		//redireccionar()
		Swal.fire({
  title: '¡Ya hay una sesión activa!',
  text: "¿Desea continuar como <%=nombre%>?",
  icon: 'warning',
  showCancelButton: true,
  confirmButtonColor: '#3085d6',
  cancelButtonColor: '#d33',
  confirmButtonText: 'Continuar sesión'
}).then((result) => {
  if (result.isConfirmed) {
	  window.location.href="./layout/";
  }else{

	  window.location.href="./SLLogin?session=1";
  }
})
	</script>
	<% 
	}
	
	if(error ==  "no entraste"){
	%>

    <script>
        Swal.fire({
        	title: 'Usuario o contraseña incorrecta.',
            icon:'error'
        })
    </script>

    <%
	session.setAttribute("error", "");
	%>
 <!-- aqui -->
 
	<%  
	}
	%>
	
	<div class="container">


		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">

						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-login-image">
								<img src="./dist/assets/images/loginpic.jpg">
							</div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">¡Bienvenidos!</h1>
									</div>
									<form class="user" action="./SLLogin" method="post">
										<div class="form-group">
											<input type="text" class="form-control form-control-user"
												name="user" aria-describedby="emailHelp"
												placeholder="Usuario" required>
										</div>
										<div class="form-group">
											<input type="password" class="form-control form-control-user"
												name="password" placeholder="Contraseña" required>
										</div>
										<div class="form-group">
											<div class="custom-control custom-checkbox small">
												<input type="checkbox" class="custom-control-input"
													id="customCheck" name="recuerdame"> 
											</div>
										</div>
										<input type="submit"
											class="btn btn-primary btn-user btn-block"
											value=" Iniciar sesión">
									</form>
									<hr>
									<div class="text-center">
										<a class="small" href="layout/forgot-password.jsp">¿Olvidaste tu
											contraseña?</a>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap 5 -->
	<script src="./dist/plugins/Bootstrap/js/popper.js"></script>
	<script src="./dist/plugins/Bootstrap/js/bootstrap.min.js"></script>
	<!-- AdminLTE App_ -->
	<script src="./dist/js/adminlte.min.js"></script>
</body>
</html>