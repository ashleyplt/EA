<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    import="entidades.*,datos.*, java.util.*"%>
<!DOCTYPE html>

<html lang="es">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Registrar nueva carrera</title>
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


		<%
		ArrayList<Facultad> listFacultad = new ArrayList<Facultad>();
		DTFacultad Facultad = new DTFacultad();
		listFacultad = Facultad.listarFacultades();
		
		ArrayList<Departamento> listDepartamento = new ArrayList<Departamento>();
		DTDepartamento Departamento = new DTDepartamento();
		listDepartamento = Departamento.listarDepartamentos();
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
                                    <i class="pe-7s-car icon-gradient bg-mean-fruit">
                                    </i>
                                </div>
                                <div>Formularios de registro de carrera
                                    <div class="page-title-subheading">Ingrese los datos de la nueva carrera
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="main-card  card">
                                <div class="card-header">Nueva carrera
                                    <div class="btn-actions-pane-right">
                                        <div role="group" class="btn-group-sm btn-group">
                                            <div class="page-title-actions">

                                                <div class=" dropdown">
                                                    <button type="button" data-toggle="dropdown" aria-haspopup="true"
                                                        aria-expanded="false"
                                                        class="btn-shadow dropdown-toggle btn btn-info">
                                                        <span class="btn-icon-wrapper pr-2 opacity-7">
                                                            <i class="fa fa-cog fa-w-20"></i>
                                                        </span>
                                                        Opciones
                                                    </button>
                                                    <div tabindex="-1" role="menu" aria-hidden="true"
                                                        class="dropdown-menu dropdown-menu-right">
                                                        <ul class="nav flex-column">
                                                            <li class="nav-item">
                                                                <a href="#" class="nav-link">
                                                                    <i class="nav-link-icon lnr-inbox"></i>
                                                                    <span>
                                                                        Crear Expediente
                                                                    </span>
                                                                </a>
                                                            </li>
                                                            <li class="nav-item">
                                                                <a href="#" class="nav-link">
                                                                    <i class="nav-link-icon lnr-book"></i>
                                                                    <span>
                                                                        Buscar Expediente
                                                                    </span>
                                                                </a>
                                                            </li>
                                                            <li class="nav-item">
                                                                <a href="#" class="nav-link disabled">
                                                                    <i class="nav-link-icon"></i>
                                                                    <span>
                                                                        Otra Opcion
                                                                    </span>
                                                                </a>
                                                            </li>

                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <form action="../SLCarrera" method="post" class="needs-validation" novalidate>
                                <input hidden="true"  name="opcionCarrera" value="1">
                                <input hidden="true"  name="estado" value="1">
                                    <div class="col-md-12 mt-2">
                                        <div class="form-group">
                                            <label>Nombre de la carrera</label>
                                            <input type="text" name="nombre" class="form-control " style="width: 100%;"
                                                id="validationCustomUsername" placeholder="Escriba el nombre de la carrea a registrar" required>
                                            <div class="valid-feedback">
                                                Nombre valido
                                            </div>
                                            <div class="invalid-feedback">
                                                Por favor ingrese un nombre valido
                                            </div>
                                        </div>

                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Facultad</label> 
                                            <select class="select2"   name="id_facultad" data-placeholder="Seleccione una Facultad" style="width: 100%;" required>
                                                <option value=""> Selecione al menos una</option>
                                                <%
												for (Facultad f : listFacultad) {
												%>
												<option value="<%=f.getId_facultad()%>"><%=f.getNombre()%></option>
												<%
													}
												%>
                                            </select>
                                            <div class="valid-feedback">
                                                Facultad Valida
                                            </div>
                                            <div class="invalid-feedback">
                                                Por favor selecione una facultad
                                            </div>
                                        </div>
                                    </div>
                                     <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Departamento</label> 
                                            <select class="select2"   name="id_departamento" data-placeholder="Seleccione un departamento" style="width: 100%;" required>
                                                <option value=""> Selecione al menos uno</option>
                                                <%
												for (Departamento d : listDepartamento) {
												%>
												<option value="<%=d.getId()%>"><%=d.getNombre()%></option>
												<%
													}
												%>
                                            </select>
                                            <div class="valid-feedback">
                                               Departamento valido
                                            </div>
                                            <div class="invalid-feedback">
                                                Por favor selecione un departamento
                                            </div>
                                        </div>
                                    </div>
                                    <div class="d-block text-center card-footer">
                                        <button class="mr-2 btn-icon btn-icon-only btn btn-success"><i
                                                class="pe-7s-trash btn-icon-wrapper"> </i></button>
                                        <button class="btn-wide btn btn-success">Guardar</button>
                                         <button type="button"
                                            class="btn-shadow p-1 btn btn-primary btn-sm show-toastr-example">
                                            <i class="fa text-white fa-calendar pr-1 pl-1"></i>
                                        </button>
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