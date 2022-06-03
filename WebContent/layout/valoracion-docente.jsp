<%@page import="vistas.VW_Expediente_Carrera"%>
<%@page import="datos.DTVExpedienteCarrera"%>
<%@page import="datos.DTVPermisosExpediente"%>
<%@page import="vistas.VW_Permisos_Expediente"%>
<%@page import="datos.DTVPersonalUsuario"%>
<%@page import="vistas.VW_Cargo_Personal"%>
<%@page import="datos.DTVCargoPersonal"%>
<%@page import="vistas.VW_Expediente_Docente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.DTVExpedienteDocente"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>

<html lang="es">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="es">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Valoración Docente</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />

<!--comentario Estilos de bootstrap 5 -->
<link rel="stylesheet"
	href="../dist/plugins/Bootstrap/css/bootstrap.min.css">

<!--comentario estilos secundarios-->
<link href="../dist/styles.min.css" rel="stylesheet">

<!--comentario Estilos font awesome  -->
<link href="../dist/plugins/FontAwesome/css/all.min.css"
	rel="stylesheet">

<!--comentario file manager-->
<link href="../dist/plugins/file-manager/dashlite.css" rel="stylesheet">
<link rel="stylesheet" href="../dist/plugins/sweetalert2/dist/sweetalert2.min.css">

<!--comentario Estilos Principales-->
<link href="../dist/main.css" rel="stylesheet">
<link href="../dist/style-form.css" rel="stylesheet">

<link rel="stylesheet" href="../dist/sweetalert2.min.css">


<script src="../dist/sweetalert2.all.min.js"></script>
</head>
<style>
.br {
	padding-left: 19px;
	padding-right: 15px;
	padding-bottom: 18px;
	padding-top: 18px;
	border: 5px solid #212529;
	border-radius: 100%;
}
.br-g {
	padding-left: 15px;
	padding-right: 15px;
	padding-bottom: 18px;
	padding-top: 18px;
	border: 3px solid gray;
	border-radius: 100%;
}
</style>
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
									<i class="fas fa-user icon-gradient bg-mean-fruit"> </i>
								</div>
								<div>
									Valoración Docente
									<div class="page-title-subheading"> </div>
								</div>
							</div>
						</div>
					</div>
					
					
					
<%
	 
	HttpSession usuario = request.getSession(); 
 

	String 	Nombre_edicion = (String) session.getAttribute("nombre_edicion") ;
	String nombre = (String) usuario.getAttribute("nombre_usuario_completo"); 
	String Asignatura = (String) usuario.getAttribute("nombre_expediente"); 
	if (Nombre_edicion == null || Asignatura== null){
		response.sendRedirect("./index.jsp");
	}
	String nombre2 = nombre;%>		
	
    <div class="mt-5  container card" >
    	<div class="card-header mt-3 mb-3">
            <h1 class="text-center "> Informe Académico</h1>
            
         </div>

        <form   name="form" id="form" class="form card-body" method="post">
            <!-- Progress bar -->
            <div class="progressbar mt-3">
                <div class="progress" id="progress"></div>

                <div class="progress-step progress-step-active" data-title="Datos"></div>
                <div class="progress-step" data-title="Resultados"></div>
                <div class="progress-step" data-title="Valoraciones "></div>
                <div class="progress-step" data-title="Final"></div>
            </div> 
            <!-- Steps -->
            <div class="form-step form-step-active">
                <div class="mt-2">
                    <label for="periodo">Periodo Académico :</label>
                    <input type="text" class="form-control mt-1" name="periodo" id="periodo" value="<%=Nombre_edicion%>" disabled />
                </div>
                <div class="mt-2">
                    <label for="nombre">Nombre del Docente :</label>
                    <input type="text" class="form-control mt-1" name="nombre" id="nombre" value="<%=nombre2 %>" disabled />
                </div>
                <div class="mt-2">
                    <label for="asignatura">Asignatura   :</label>
                    <input type="text" class="form-control mt-1" name="asignatura" value="<%=Asignatura%>"id="asignatura" disabled />
                </div>
                <div class="mt-2">
                    <label for="seccion">  Sección :</label>
                    <input type="text" class="form-control mt-1" name="seccion" id="seccion" />
                </div>
                <div class="mt-2 " style="display:none;">
                    <input type="date" class="form-control mt-1" name="fecha" id="fecha" />
                </div>
                <div class="mt-3 mb-5">
                    <a href="#" class="btn btn-next btn-primary" onclick="n1()">Siguiente</a>
                </div>
            </div>
            <div class="form-step">


                <div class="mt-2">
                    <label for="total">Total de estudiantes Matriculados :</label>
                    <input type="number" class="form-control mt-1" name="total" id="total" />
                </div>


              
                <div class="mt-2">
                    <label for="totalr">Cantidad de estudiantes Reprobados  :</label>
                    <input type="number" class="form-control mt-1" name="totalr" id="totalr" />
                </div>
  				<div class="mt-2">
                    <label for="totala">Cantidad de estudiantes Aprobados  :</label>
                    <input type="number" class="form-control mt-1" name="totala" id="totala" />
                </div>
                <div class="mt-2">
                    <label for="sdc">Cantidad de estudiantes SDC :</label>
                    <input type="number" class="form-control mt-1"  name="sdc" id="sdc" />
                </div>


                <div class="btns-group mt-3 mb-5">
                    <a href="#" class="btn btn-prev btn-primary">Anterior</a>
                    <a href="#" class="btn btn-next btn-primary" onclick="n2()">Siguiente</a>
                </div>
            </div>
            <div class="form-step">



                <div class="mt-2">
                    <label for="valoracion">  Valoración de la participación y disciplina académica del grupo atendido :</label>
                    <input type="text" class="form-control mt-1" name="valoracion" id="valoracion" />
                </div>



                <div class="mt-2">
                    <label for="valoracionC">  Valoración del cumplimiento de los objetivos propuestos en el curso :</label>
                    <input type="text" class="form-control mt-1" name="valoracionC" id="valoracionC" />
                </div>
                <div class="mt-4">
                    <label for="valoracionCon">  Valoración de los contenidos del programa de la asignatura (inclusión-exclusión de temas, ajustes, 
                      fondo de hora asignado, cumplimiento del programa conforme al syllabus previsto)
                       :</label>
                    <input type="text" class="form-control mt-1" name="valoracionCon" id="valoracionCon" />
                </div>
                <div class="btns-group mt-3 mb-5">
                    <a href="#" class="btn btn-prev btn-primary">Anterior</a>
                    <a href="#" class="btn btn-next btn-primary" onclick="n3()">Siguiente</a>
                </div>
            </div>
            <div class="form-step">



                <div class="mt-2">
                    <label for="valoracionMet">  Valoración de la eficacia y pertinencia de la metodología de enseñanza aplicada :</label>
                    <input type="text" class="form-control mt-1" name="valoracionMet" id="valoracionMet" />
                </div>
                <div class="mt-2">
                    <label for="valoracionT">  Valoración de los tipos de evaluación utilizados :</label>
                    <input type="text" class="form-control mt-1" name="valoracionT" id="valoracionT" />
                </div>


                <div class="mt-2">
                    <label for="valoracionP"> Valoración de la pertinencia y utilización del material bibliográfico :</label>
                    <input type="text" class="form-control mt-1" name="valoracionP" id="valoracionP" />
                </div>
                <div class="mt-4">
                    <label for="otros">Otros aspectos de relevancia que considera oportuno compartir con la coordinación de carrera 
                      para el seguimiento del grupo atendido
                       :</label>
                    <input type="text" class="form-control mt-1" name="otros" id="otros" />
                </div>


                <div class="btns-group mt-3 mb-5">
                    <a href="#" class="btn btn-prev btn-primary">Anterior</a>
                    <a href="#" class="btn btn-next btn-primary" onclick="n4()">Siguiente</a>
                </div>
            </div>
        </form>
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
	<script type="text/javascript" src="../dist/plugins/jquery/jquery.min.js"></script> 
	<!----Moment-->
 
	<!-- Selectt 2 -->
	<script src="../dist/plugins/select2/js/select2.full.min.js"></script>

	<!-- script Principal -->
	<script type="text/javascript" src="../dist/assets/scripts/main.js"></script>
	
	<script src="../dist/plugins/sweetalert2/dist/sweetalert2.min.js"></script>
	<script src="../dist/script-form.js"></script>
	<script src="../dist/jqueri2.min.js"></script>

	<script>
	 
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

 <script>
 function salert(input) {
     Swal.fire({
         position: 'center',
         icon: 'warning',
         title: 'Rellene el campo ' + input,
         showConfirmButton: false,
         timer: 5000
     })
 }  
 function salert2(input) {
     Swal.fire({
         position: 'center',
         icon: 'warning',
         title: '' + input,
         showConfirmButton: false,
         timer: 5000
     })
 }
       
 
 
 
 </script>
 
 
<script src="../dist/script-form.js"></script>
	 
</body>
</html>
