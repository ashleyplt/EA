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
    <meta http-equiv="Content-Language" content="es">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Solicitud de permiso.</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />

    <!--comentario Estilos Date Picker --> 
    <link rel="stylesheet" type="text/css" href="../dist/plugins/DatePicker/daterangepicker.css" />
     
    <!--comentario Estilos select2 -->
    <link rel="stylesheet" href="../dist/plugins/select2/css/select2.min.css">
    <link rel="stylesheet" href="../dist/plugins/select2-theme/select2-bootstrap.min.css">

    <!--comentario Estilos de bootstrap 5 -->
    <link rel="stylesheet" href="../dist/plugins/Bootstrap/css/bootstrap.min.css">

    <!--comentario estilos secundarios-->
    <link href="../dist/styles.min.css" rel="stylesheet">

    <link href="../dist/plugins/DatePicker/daterangepicker.css" rel="stylesheet">

    <!--comentario Font awesome-->
    <link href="../dist/plugins/FontAwesome/css/all.min.css" rel="stylesheet">
	<link rel="stylesheet" href="../dist/plugins/sweetalert2/dist/sweetalert2.min.css">
    
    <!--comentario Estilos Principales-->
    <link href="../dist/main.css" rel="stylesheet">
</head>


<body>

    <div class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">

        <!--comentario include de header y settings -->
        <jsp:include page="./component/header.jsp"></jsp:include>
        <jsp:include page="./component/settings.jsp"></jsp:include>
        
        <%
        HttpSession usuario = request.getSession();

        boolean coordinador = false;

		String carrera = "";
		String cargo = usuario.getAttribute("cargo").toString();
		if(cargo.equals("")) coordinador = false;
		else{
			coordinador = true;
			carrera = usuario.getAttribute("carrera").toString();
		}
		
        %>

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
                                    <i class="fas fa-user-plus icon-gradient bg-mean-fruit">
                                    </i>
                                </div>
                                <div>
                                	Expediente de asignatura
                                    <div class="page-title-subheading">Solicitud de acceso a un expediente de asignatura.
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="main-card  card">
                                <div class="card-header">Solicitar permiso de acceso
                                    <div class="btn-actions-pane-right">
                                        <div role="group" class="btn-group-sm btn-group">
                                            <div class="page-title-actions">

                                               <!-- Acciones en el encabezado de la tarjeta -->
                                               
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <form method="post" action="../SLSolicitudPermiso" class="needs-validation" novalidate>
									<div class="col-md-12 mt-2">
                                        <div class="form-group">
                                            <label>Asunto</label> 
                                            <input type="text" class="form-control" style="width: 100%;" readonly="readonly"
                                                name="asunto" value="Solicitud de permiso de acceso a expediente de asignatura." required>
                                            
                                        </div>
                                    </div>
                                     <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Expediente de asignatura</label>
                                            <select class="select2" name="expediente" data-placeholder="Seleccione un expediente" style="width: 100%;" required>
                                                <option value=""> Selecione un expediente de asignatura</option>
                                                <%
                                                String nombre = usuario.getAttribute("nombre_user").toString();

                                        		DTVPersonalUsuario dtvpu = new DTVPersonalUsuario();
                                        		int idpersonal = dtvpu.buscaridPersonal(nombre);
                                                
                                        		DTAsignatura dta = new DTAsignatura();
                                                ArrayList<Asignatura> listAsignatura = new ArrayList<>();
                                                
                                                //Verificar si no tiene ya un permiso o asignado a un expediente
                                                DTVPermisosExpediente dtpe = new DTVPermisosExpediente();
                                                DTVExpedienteDocente dted = new DTVExpedienteDocente();
                                                
                                                if(coordinador){
                                                	DTVExpedienteCarrera dtvec = new DTVExpedienteCarrera();
                                                	ArrayList<VW_Expediente_Carrera> listaAsign = dtvec.listarExpedientesNoCarrera(carrera);
                                                	
                                                	for(VW_Expediente_Carrera ec : listaAsign){
                                                		if(!dtpe.existePermiso(ec.getAsignatura(), idpersonal) && !dted.existeDocenteExpediente(ec.getAsignatura(), idpersonal)){
                                                %>
                                                <option value="<%=ec.getAsignatura()%>"><%=ec.getAsignatura()%></option>
                                                <%			
                                                		}
                                                	}
                                                } else {
	                                                listAsignatura = dta.listarAsignaturas();
													for (Asignatura a : listAsignatura) {
														if(!dtpe.existePermiso(a.getNombre(), idpersonal) && !dted.existeDocenteExpediente(a.getNombre(), idpersonal)){
												%>
												<option value="<%=a.getNombre()%>"><%=a.getNombre()%></option>
												<%
														}
													}
												}
												%>
                                                
                                            </select>
                                            <div class="invalid-feedback">Por favor selecione una opción.</div>
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleFormControlTextarea1">Motivo</label>
                                            <textarea name="motivo" class="form-control" id="exampleFormControlTextarea1" rows="4" style="width: 100%;" required></textarea>
                                            <div class="invalid-feedback">Escriba el motivo de la solicitud.</div>
                                        </div>                               
                                    </div>
									<div class="d-block text-center card-footer">
                                        <button type= "submit" class="btn-wide btn btn-success">Guardar</button>
                                    </div>
                                </form>
                                <!-- Termina el formulario -->
                            </div>
                        </div>
                    </div>
                </div>

                <!--comentario include del footer-->
                <jsp:include page="./component/footer.jsp"></jsp:include>

            </div>
           
        </div>
    </div>

    <script src="../dist/jquery.min.js"></script> 
    
	<!-- Bootstrap 5 -->
    <script src="../dist/plugins/Bootstrap/js/popper.js"></script>
    <script src="../dist/plugins/Bootstrap/js/bootstrap.min.js"></script>
    <script src="../dist/plugins/sweetalert2/dist/sweetalert2.min.js"></script>

    <!-- Jquery -->
    <script type="text/javascript" src="../dist/plugins/jquery/jquery.min.js"></script>

    <!-- DataPicker -->
    <script type="text/javascript" src="../dist/plugins/DatePicker/moment.min.js"></script>
    <script type="text/javascript" src="../dist/plugins/DatePicker/daterangepicker.min.js"></script>
    <!--Scripts-->
    <!-- Selectt 2 -->
    <script src="../dist/plugins/select2/js/select2.full.min.js"></script>

    <!-- script Principal -->
    <script type="text/javascript" src="../dist/assets/scripts/main.js"></script>

	<%  
	String error = (String) usuario.getAttribute("error");
	if(error.equals("")) error = null;
	if(error !=  null){
		if(error.equals("1")){
	%>
    <script>
    Swal.fire({
		icon: 'success',
		title: 'Mensaje enviado.',
		confirmButtonText: 'Aceptar',
		confirmButtonColor: '#3085d6'
	}).then((result) => {
		if(result.isConfirmed){
			window.location.href = "index.jsp";
		}
	});
    </script>
    <%
		} else {
	%>	
	<script>
    Swal.fire({
		icon: 'error',
		title: 'Error al enviar el mensaje.',
		confirmButtonText: 'Aceptar',
		confirmButtonColor: '#3085d6'
	});
    </script>
	<%	
		}
		session.setAttribute("error", "");
	}
	%>
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

 <!--Validacion del formulario-->
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
    })()
    </script>
    
</body>
</html>
 