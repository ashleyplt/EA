<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<meta charset="UTF-8">
<link rel="shortcut icon" href="../dist/img/icono.png">
<title>Olvidaste la contraseña</title>

<!--comentario Estilos de bootstrap 5 -->
<link rel="stylesheet" href="../dist/plugins/Bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="../dist/plugins/sweetalert2/dist/sweetalert2.min.css">

<script src="../dist/plugins/Bootstrap/js/popper.js"></script>
<script src="../dist/plugins/Bootstrap/js/bootstrap.min.js"></script>

<link href="../dist/css/OTC.css" rel="stylesheet">


</head>

<body class="bg-gradient-primary">

	<div class="container " >
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">

								<div class="p-5">
								<form class="user" action="../SLForgotPassword" method="post"> 
									<div class="text-center">
									 
										<h1 class="mb-4">¡Olvidaste tu contraseña! </h1>
										<p class="text-secondary mb-4">Para configurar su contraseña, envíe su dirección de correo electrónico. Si podemos encontrarlo en la base de datos, le enviaremos un email con una nueva contraseña para poder acceder de nuevo al sistema.</p>
										 <div class="col-md-12">
										 
                                         <div class="form-group">
                                            <label>Correo Electrónico:</label> 
                                            <input type="email" class="form-control item" style="width: 100%;"
                                                id="correo" name="correo" placeholder="Escriba su correo electrónico" maxlength="100"required>
                                            <div class="invalid-feedback">Por favor ingrese un correo electrónico válido</div>
                                        </div>
                                         
                                    </div>
									</div>
									
										
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
	<script src="../dist/plugins/sweetalert2/dist/sweetalert2.min.js"></script>
	<!-- AdminLTE App_ -->
	<script src="../dist/js/adminlte.min.js"></script>
	
	<% 
	HttpSession usuario = request.getSession();
    String nombre = (String)usuario.getAttribute("error");
    String error = nombre;
	if(error == null || error.equals("")){
	} else {
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
	
	<script type="text/javascript">
	function activar(){
		window.location.href=url+"../SL="+exp;
	}
	</script>
</body>