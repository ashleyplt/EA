<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="vistas.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.*"%>
<%@page import="entidades.*"%> 

<%@page session="true"%>
<!DOCTYPE html>

<html lang="es">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="es">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Perfil</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />

    <!-- <link rel="shortcut icon" href="../dist/img/icono.png">-->
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

    <!-- <jsp:include page="component/menu.jsp"></jsp:include> -->

    <div class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">

        <!--comentario include de header y settings -->
        <jsp:include page="./component/header.jsp"></jsp:include>
        <jsp:include page="./component/settings.jsp"></jsp:include>

        <div class="app-main">
            <!--comentario include de los menus-->
            <jsp:include page="./component/movil-menu.jsp"></jsp:include>
            <jsp:include page="./component/menu.jsp"></jsp:include>
   
            <div class="app-main__outer">
                <div class="app-main__inner">
                     <%
						HttpSession usuario = request.getSession();
						int id = (int) usuario.getAttribute("id_usuario");
						String nombre = usuario.getAttribute("nombre_user").toString();
						
                        DTVUsuarioSeguridad dtvus = new DTVUsuarioSeguridad(); 
                        ArrayList<VW_Usuarios> listarUsuarios = new ArrayList<VW_Usuarios>();
                        listarUsuarios = dtvus.listarUsuariosenPerfil(id);
                        DTVPersonalUsuario dtvpu = new DTVPersonalUsuario();
                   		int idPersonal = dtvpu.buscaridPersonal(nombre);
                           for(VW_Usuarios u : listarUsuarios){
                   
					%>
                    <div class="row gutters-sm">
			            <div class="col-md-4 mb-3">
			              <div class="card" style="height:270px;">
			                <div class="card-body">
			                  <div class="d-flex flex-column align-items-center text-center">
			                    <img src="../<%=u.getImagen()==null?"dist/imagen/user.png":u.getImagen()%>" style="border-radius: 50px 50px; width: 150px; height: 150px; " class="rounded-circle" width="150">
			                    <div class="mt-3">
			                      <h4><%= u.getNombre()%> <%=u.getApellido()%></h4>
			                    </div>
			                  </div>
			                </div>
			              </div>
			            </div>
			            <div class="col-md-8">
			              <div class="card mb-3" style="height:270px;">
			              <div class="card-header">Información personal</div>
			                <div class="card-body">
			                  <div class="row">
			                    <div class="col-sm-3">
			                      <h6 class="mb-0">Usuario</h6>
			                    </div>
			                    <div class="col-sm-9 text-secondary">
			                      <%=u.getUsuario()%>
			                    </div>
			                  </div>
			                  <div tabindex="-1" class="dropdown-divider"></div>
			                  <div class="row">
			                    <div class="col-sm-3">
			                      <h6 class="mb-0">Correo electrónico</h6>
			                    </div>
			                    <div class="col-sm-9 text-secondary">
			                    <%
			                    DTPersonal dtp = new DTPersonal();
			                    Personal p = dtp.getPersona(idPersonal);
			                    %>
			                      <%=p.getCorreo()%>
			                    </div>
			                  </div>
			                  <div tabindex="-1" class="dropdown-divider"></div>
			                  <div class="row">
			                    <div class="col-sm-3">
			                      <h6 class="mb-0">Teléfono</h6>
			                    </div>
			                    <div class="col-sm-9 text-secondary">
			                      <%=p.getTelefono()%>
			                    </div>
			                  </div>
			                </div>
			              </div>
			            </div>
			          </div>
			          <div class="row">
                        <div class="col-md-12">
                            <div class="main-card card card-body">
			                    <div class="counter">
			                    <div class="row text-center justify-content-center">
			                    <%
			                    DTVExpedienteDocente dtved = new DTVExpedienteDocente();
			                    ArrayList<VW_Expediente_Docente> listExp = dtved.listarExpedienteSegunDocente(idPersonal);
			                    DTVPermisosExpediente dtvpe = new DTVPermisosExpediente();
			                    ArrayList<VW_Permisos_Expediente> listPermisos = dtvpe.listarPermisosSegunDocente(idPersonal);
			                    
			                    DTVExpedienteCarrera dtec = new DTVExpedienteCarrera();
			                    ArrayList<VW_Expediente_Carrera> listExpCarrera = new ArrayList<>();
			                    
			                    boolean coordinador;
			                    
			                    String personal = usuario.getAttribute("cargo").toString();
			                    if(personal.equals("")) coordinador = false;
			                    else coordinador = true;
			                    if(coordinador){
			                    	String carrera = usuario.getAttribute("carrera").toString();
			                    	listExpCarrera = dtec.listarExpedientesCarrera(carrera);
			                    %>
			                    	<div class="col-6 col-lg-3">
			                            <div class="count-data text-center">
			                                <h6 class="count h2 font-weight-bold" data-to="500" data-speed="500"><%=listExpCarrera.size()%></h6>
			                                <p class="m-0px font-w-600 text-secondary">Expedientes de asignatura de la carrera</p>
			                            </div>
			                        </div>
			                    <%
			                    }
			                    %>
			                        <div class="col-6 col-lg-3">
			                            <div class="count-data text-center">
			                                <h6 class="count h2 font-weight-bold" data-to="500" data-speed="500"><%=listExp.size()%></h6>
			                                <p class="m-0px font-w-600 text-secondary">Expedientes de asignatura asignados</p>
			                            </div>
			                        </div>
			                        <div class="col-6 col-lg-3">
			                            <div class="count-data text-center">
			                                <h6 class="count h2 font-weight-bold" data-to="150" data-speed="150"><%=listPermisos.size()%></h6>
			                                <p class="m-0px font-w-600 text-secondary">Permisos de acceso a expedientes de asignatura</p>
			                            </div>
			                        </div>
			                    </div>
			                    </div>
		                    </div>
                    	</div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="main-card  card">
                                <div class="card-header">
                                    Configuración de la cuenta
                                </div>
                                <div class="card-body">
                                <div class="col-md-12 mt-2">
                                        <div class="form-group">
                                            <a href="cambiar-imagen.jsp">Cambiar foto de perfil</a>                                
                                        </div>

                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <a href="cambiar-nombreusuario.jsp">Cambiar nombre de usuario</a>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <a href="cambiar-password.jsp">Cambiar contraseña</a>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <a href="../SLLogin?session=1">Cerrar sesión</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>              
                    </div> 
                    <%
                           }
                    %>                   
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
    <script type="text/javascript" src="../dist/plugins/jquery/jquery.min.js"></script>

    <!-- DataPicker -->
    <script type="text/javascript" src="../dist/plugins/DatePicker/moment.min.js"></script>
    <script type="text/javascript" src="../dist/plugins/DatePicker/daterangepicker.min.js"></script>
    <!----Scripts-->

     <!---- Data range-->
    <script>
        $(function () {
            $('input[name="daterange"]').daterangepicker({
             
                "locale": {
                        "applyLabel": "Aceptar",
                        "cancelLabel": "Cancelar",
                        "format": "YYYY-MM-DD",
                        "daysOfWeek": ["D", "L", "M", "X", "J", "V", "S"],
                        "monthNames": ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                            "Julio",
                            "Agosto",
                            "Septiembre", "Octubre", "Noviembre", "Diciembre"
                        ],
                        "firstDay": 1,
                        orientation: 'bottom'
                    },
                    "drops": "up",
                    "startDate": moment(),
                    "minDate": moment(),
                    container: '#end',
                    setValue: function(start, end) {
                        $('#start').val(s1);
                        $('#end').val(s2);
                    } 
                 
            }, function (start, end, label) {
                console.log("A new date selection was made: " + start
                    .format('YYYY-MM-DD') + ' to ' + end.format(
                        'YYYY-MM-DD'));
            });
        });
    </script>
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

 <!----Validacion del formulario-->
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