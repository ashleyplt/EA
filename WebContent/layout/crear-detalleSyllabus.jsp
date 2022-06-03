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
    <title>Gestión de objetivos de asignatura</title>
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
    
      <!--comentario estilos tabla-->
    <link rel="stylesheet" href="../dist/plugins/table/datatables-bs4/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="../dist/plugins/table/datatables-responsive/css/responsive.bootstrap4.min.css">
    <link rel="stylesheet" href="../dist/plugins/table/datatables-buttons/css/buttons.bootstrap4.min.css">
    
</head>

<body>



    <!-- <jsp:include page="component/menu.jsp"></jsp:include> -->

    <div class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">

        <!--comentario include de header y settings -->
        <jsp:include page="./component/header.jsp"></jsp:include>
        <jsp:include page="./component/settings.jsp"></jsp:include>


		<%
		
		int idsyllabus = Integer.parseInt(request.getParameter("idsyllabus"));

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
                                    <i class="fas fa-calendar-alt">
                                    </i>
                                </div>
                                <div>Gestión de agendas
                                    <div class="page-title-subheading">Ingrese una nueva agenda
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="main-card  card">
                                <div class="card-header">Nueva agenda
                                </div>
                                <form action="../SLObjetivoSyllabus" method="post" class="needs-validation" novalidate>
                             	 <input hidden="true"  id="id_Objetivo" name="id_Objetivo" value="0">
                                <input hidden="true"  id="opcionObjetivo" name="opcionObjetivo" value="1">
                                <input hidden="true"  id="id_Syllabus" name="id_Syllabus" value="<%=idsyllabus%>">
                                    <div class="col-md-12 mt-2">
                                        <div class="form-group">
                                            <label>Objetivo de asignatura</label>
                                            <input type="text" id="objetivo" name="objetivo" class="form-control " style="width: 100%;"
                                                id="validationCustomUsername" placeholder="Escriba el objetivo de la asignatura" required>
                                            <div class="valid-feedback">
                                                Objetivo valido
                                            </div>
                                            <div class="invalid-feedback">
                                                Por favor ingrese un objetivo valido
                                            </div>
                                        </div>

                                    </div>
                                    <div class="d-block text-center card-footer">
                                   		<a class="btn-wide btn btn-primary" href="ver-syllabus.jsp?idexpediente=&idsyllabus=<%=idsyllabus%>">Regresar al syllabus</a>
                                        <button class="btn-wide btn btn-success">Guardar</button>
                                        <a onClick="limpiarCampos()" class="btn-wide btn btn-danger">Cancelar</a>
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
        <script src="../dist/plugins/jquery/jquery.min.js"></script>
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
    <script type="text/javascript" src="../dist/assets/scripts/main.js"></script>
    

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
	
	
    <!-- script tablas -->
    <script src="../dist/plugins/table/datatables/jquery.dataTables.min.js"></script>
    <script src="../dist/plugins/table/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
    <script src="../dist/plugins/table/datatables-responsive/js/dataTables.responsive.min.js"></script>
    <script src="../dist/plugins/table/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
    <script src="../dist/plugins/table/datatables-buttons/js/dataTables.buttons.min.js"></script>
    <script src="../dist/plugins/table/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
    <script src="../dist/plugins/table/jszip/jszip.min.js"></script>
    <script src="../dist/plugins/table/pdfmake/pdfmake.min.js"></script>
    <script src="../dist/plugins/table/pdfmake/vfs_fonts.js"></script>
    <script src="../dist/plugins/table/datatables-buttons/js/buttons.html5.min.js"></script>
    <script src="../dist/plugins/table/datatables-buttons/js/buttons.print.min.js"></script>
    <script src="../dist/plugins/table/datatables-buttons/js/buttons.colVis.min.js"></script>

 <!-- inicializar tabla -->
    <script>
        $(function () {
            $("#example1").DataTable({
                "responsive": true,
                "lengthChange": false,
                "autoWidth": false,
            }).buttons().container().appendTo('#example1_wrapper .col-md-6:eq(0)');
            $('#example2').DataTable({
                "paging": true,
                "lengthChange": false,
                "searching": false,
                "ordering": true,
                "info": true,
                "autoWidth": false,
                "responsive": true,
            });
        });
    </script>

	<script type="text/javascript">
	
	function editarObjetivo(idSyllabus, objetivo, idObjetivo){
		document.getElementById("opcionObjetivo").setAttribute('value', 2);
		document.getElementById("id_Syllabus").setAttribute('value', idSyllabus);
		document.getElementById("objetivo").setAttribute('value', objetivo);
		document.getElementById("id_Objetivo").setAttribute('value', idObjetivo);
		
	}
	
	function limpiarCampos(){
		document.getElementById("opcionObjetivo").setAttribute('value', 1);
		document.getElementById("objetivo").setAttribute('value', '');
		document.getElementById("id_Objetivo").setAttribute('value', 0);
	}
	
	function eliminarObjetivo(idObjetivo, idSyllabus){
		window.location.href="../SLObjetivoSyllabus?id_objetivo="+idObjetivo+"&id_syllabus="+idSyllabus;
		location.reload
	}
	
	</script>

</body>

</html>