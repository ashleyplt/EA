<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@page import="vistas.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.*"%>
<%@page import="entidades.*"%>



<!DOCTYPE html>
<html lang="es">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Cambiar Nombre de Usuario</title>

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


<link rel="stylesheet" href="../dist/sweetalert2.min.css"> 

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



<%
    response.setHeader("Cache-Control","no-cache,post-check=0,pre-check=0");
	response.setHeader("Pragma","no-cache");
	response.setHeader("Expires","Thu,01 Dec 1994 16:00:00 GMT");
	response.setDateHeader("Expires", -1);





HttpSession usuario = request.getSession();  
String error = (String) usuario.getAttribute("error"); 
if(error != null && error != "0"){
%>

<script>
    Swal.fire(
        '' + <%=error%>,
        'Click en el botom para continuar',
        'error'
    )
</script>

<%
session.setAttribute("error", "");
%>
<!-- aqui -->

<%  
}
%>
			<div class="app-main__outer">
				<div class="app-main__inner">
					<div class="app-page-title">
						<div class="page-title-wrapper">

							<!--comentario inicia la card debajo del header-->
							<div class="page-title-heading">
								
								<% 
								int id = (int) usuario.getAttribute("id_usuario");
								
	                            DTVUsuarioSeguridad dtvus = new DTVUsuarioSeguridad(); 
	                            ArrayList<VW_Usuarios> listarUsuarios = new ArrayList<VW_Usuarios>();
	                            listarUsuarios = dtvus.listarUsuariosenPerfil(id);
	                            
	                            for(VW_Usuarios u : listarUsuarios){
	                    
								%>
								<div>
								<img class="rounded-circle" src="../<%=u.getImagen()==null?"dist/imagen/user.png":u.getImagen()%>" 
                                style="border-radius: 50px 50px; width: 200px; height: 200px; " class="center-block" alt="Foto Usuario">
									<label> </label>
								</div>						
								<div class="col-md-12">
									 <%=u.getNombre() %> <%= u.getApellido() %>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="main-card  card">
								<div class="card-header">
								<h5> Cambiar Foto de Perfil </h5>
									<div class="btn-actions-pane-right">
										<div role="group" class="btn-group-sm btn-group">
											<div class="page-title-actions">
											</div>
										</div>
									</div>
								</div>
								<div id="foto" class="panel">
								<form class="form" name="frm-foto" method="post" action="../SLFotoUser" enctype="multipart/form-data">
								<div class="cuadro-fotoNima" align="center">
									<img id="preview" src="C:/ServidoresWeb/<%=u.getImagen()==null?"#":u.getImagen()%>" name="preview"  alt="Foto Usuario"
										style="width: 400px; height: 400px; border-bottom-color: white; margin: 2px;" />
								</div>
										
								<input type="file" id="foto" name="foto" onchange="Test.UpdatePreview(this)" required="required">&nbsp; 
								
								<input type="hidden" name="iduser" value="<%=id%>">
	
						 		<hr>
	                            <div class="text-center">
	                                <input class="btn btn-primary btn-user btn-block" type="submit" value="Guardar" />
	                            </div>
	                            <div class="text-center">
	                                <input class="btn btn-google btn-user btn-block" type="reset" value="Cancelar" />
	                            </div>
								</form>
								<%
								}
								%>
							</div>
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
	<!----Scripts-->
	
	
	<!-- Método para la imagen de usuario -->
	
	<script>
	    $(document).ready(function() 
		{
//////////////////////////////////FUNCION PARA PREVISUALIZAR LA FOTO QUE SUBE EL USUARIO//////////////////////////
	    	Test = {
	    	        UpdatePreview: function(obj)
	    	        {
	    	          // if IE < 10 doesn't support FileReader
	    	          if(!window.FileReader)
	    	          {
	    	             
	    	          } 
	    	          else 
	    	          {
	    	             var reader = new FileReader();
	    	             var target = null;
	    	             
	    	             reader.onload = function(e) 
	    	             {
	    	              target =  e.target || e.srcElement;
	    	               $("#preview").prop("src", target.result);
	    	             };
	    	              reader.readAsDataURL(obj.files[0]);
	    	          }
	    	        }
	    	    };
		});
	</script>
	
	<!-- Método para cargar el nombre -->
	
	<script>
	function cargarElemento(elemento){
		var file = elemento.files[0];
		   var nameimg = document.subirArchivo.nameimg;
		   
		  nameimg.value = file.name;
		  //document.subirArchivo.target = "null";
		} 
	</script>
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