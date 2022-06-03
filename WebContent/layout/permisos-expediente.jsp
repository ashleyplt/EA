<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="vistas.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.*"%>
<%@page import="entidades.*"%>

<%
ArrayList<Cargo> listcargo = new ArrayList<Cargo>();
DTCargo cargo = new DTCargo();
listcargo = cargo.listarCargo();

ArrayList<VW_Coordinacion_Departamento> listCD = new ArrayList<VW_Coordinacion_Departamento>();
DTVCoordinacionDepartamentos cd = new DTVCoordinacionDepartamentos();
listCD = cd.listarCoordinacionDepartamentos();

ArrayList<Rol> listRol = new ArrayList<Rol>();
DTRol rol = new DTRol();
listRol = rol.listarRol();
%>

<!DOCTYPE html>

<html lang="es">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="es">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Permiso de expediente</title>
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

    
    <!--comentario Estilos Principales-->
    <link href="../dist/main.css" rel="stylesheet">
</head>


<body>
    <div class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">

        <!--comentario include de header y settings -->
        <jsp:include page="./component/header.jsp"></jsp:include>
        <jsp:include page="./component/settings.jsp"></jsp:include>
        
        <% 
        int opc = Integer.parseInt(request.getParameter("opc")); 
        int id = 0;
        String expediente = request.getParameter("expediente");
        String sub = "Nuevo permiso de expediente de aisgnatura";
        String cheader = "Conceder permiso";
        
        
        
        switch(opc){
        case 2:
        	sub = "Modificación del permiso de expediente de asignatura";
        	cheader = "Modificar permiso";
        	id = Integer.parseInt(request.getParameter("id"));
        	break;
        case 3:
        	sub = "Visualización de detalles del permiso de expediente de asignatura";
        	cheader = "Detalles de permiso";
        	id = Integer.parseInt(request.getParameter("id"));
        	break;
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
                                	Permiso de expediente de asignatura.
                                    <div class="page-title-subheading"><%=sub%>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="main-card  card">
                                <div class="card-header"><%=cheader%>
                                    <div class="btn-actions-pane-right">
                                        <div role="group" class="btn-group-sm btn-group">
                                            <div class="page-title-actions">

                                               <!-- Acciones en el encabezado de la tarjeta -->
                                               
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <form method="post" action="../SLPermisoExpediente" class="needs-validation" novalidate>										
									
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Lugar de trabajo</label>
                                            <select class="select2" name="lugar" id="lugar" data-placeholder="Seleccione un lugar de trabajo" style="width: 100%;" required>
                                                <option value=""></option>
                                                <%
												ArrayList<Departamento> listaDept = new ArrayList<>();
												DTDepartamento dtd = new DTDepartamento();
												listaDept = dtd.listarDepartamentos();
										        DTVExpedienteCarrera dtec = new DTVExpedienteCarrera();
										        String carrera = dtec.getCarrera(expediente);
										        
												%>
												<option value="<%=carrera%>"><%=carrera%></option>
												<%
												for (Departamento d : listaDept){
												%>
												<option value="<%=d.getNombre()%>"><%=d.getNombre()%></option>
												<%
												}
												%>
                                            </select>
                                            <div class="valid-feedback">Válido</div>
                                            <div class="invalid-feedback">Por favor selecione una opción</div>
                                        </div>
                                    </div>
                                     
                                     <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Lugar de trabajo</label>
                                            <i class="icon ni fas fa-exclamation-circle" id="popover" data-trigger="hover" data-container="body" data-toggle="popover" data-placement="right" data-content="Al cambiar de opción se eliminarán los personales seleccionados." style="cursor:pointer;"></i> 
											<select id="opcion" onchange="cambiarvalores()" class="select2" data-placeholder="Seleccione un cargo" name="expediente" style="width: 100%;" required>
												<option value=""></option>
												<%
												ArrayList<Cargo> listaCargos = new ArrayList<>();
												DTCargo dtc = new DTCargo();
												listaCargos = dtc.listarCargo();
										        
												for (Cargo c : listaCargos){
												%>
												<option value="<%=c.getNombre()%>"><%=c.getNombre()%></option>
												<%
												}
												%>
                                            </select>
                                            <div class="valid-feedback">Válido</div>
                                            <div class="invalid-feedback">Por favor selecione una opción.</div>
                                        </div>
                                     </div>
                                     <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Docente</label>
                                            <select id="cargo" multiple="multiple" data-placeholder="Seleccione un personal" onchange="cambiarvalores()" class="select2" name="expediente" style="width: 100%;" required>
												
                                            </select>
                                            <div class="valid-feedback">Válido</div>
                                            <div class="invalid-feedback">Por favor selecione una opción.</div>
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
	function cambiarvalores(){
		$.ajax({		    
	          url: "../SLajaxPermisos",
	          type: "post",
	          datatype:"html",
	          data: {'opcion':document.getElementById('opcion').value},
	          success: function(data) 
	          {
	        		$('#lugar').html(data);
	          }
	        }); 
	}
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
 