<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    import="entidades.*,datos.*,vistas.*, java.util.*"%>
<!DOCTYPE html>

<html lang="es">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Reunión de colectivo docente</title>
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
			int idayudamemoria = Integer.parseInt(request.getParameter("idam"));
			String edicion_actual = (String) session.getAttribute("nombre_edicion") ; 
			String asignatura =  (String) session.getAttribute("nombre_expediente") ;
			int id_coord = (int) session.getAttribute("id_personal");

			DTVExpedienteDoc doc = new DTVExpedienteDoc();
			ArrayList<VW_Expediente_Doc> listDoc = new ArrayList<VW_Expediente_Doc>();
			listDoc = doc.listarVWDocentes(asignatura, edicion_actual);
			
			ArrayList<VW_Expediente_Doc> docentes = new ArrayList<VW_Expediente_Doc>();
			int contador = 0;
			for(VW_Expediente_Doc vwed : listDoc){
				if(docentes.isEmpty()) docentes.add(vwed);
				String iddocentes = String.valueOf(docentes.get(contador).getId_docente());
				if(!iddocentes.equals(String.valueOf(vwed.getId_docente()))){
					docentes.add(vwed);
					contador++;
				}
			}
			
			DTAyudaMemoriaAsistente dtamas = new DTAyudaMemoriaAsistente();
        	ArrayList<VW_AyudaMemoriaAsistentes> listasistentes = new ArrayList<VW_AyudaMemoriaAsistentes>();
        	listasistentes = dtamas.listarVWAsistentes(idayudamemoria);
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
                                    <i class="fas fa-users icon-gradient bg-mean-fruit">
                                    </i>
                                </div>
                                <div>Gestión de ayuda de memoria
                                    <div class="page-title-subheading">Gestiones los datos de la ayuda de memoria
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                     <div class="row">
                        <div class="col-md-12">
                            <div class="main-card  card">
                                <div class="card-header">Puntos a tratar
                                </div>
                                <form action="../SLAMAgenda" method="post" class="needs-validation" novalidate>
                             	 <input hidden="true"  id="idagenda" name="idagenda" value="0">
                                <input hidden="true"  id="opcionAgenda" name="opcionAgenda" value="1">
                                <input hidden="true"  id="idayudamemoria1" name="idayudamemoria1" value="<%=idayudamemoria%>">
                                    <div class="col-md-12 mt-2">
                                        <div class="form-group">
                                            <label>Agenda</label>
                                            <input type="text" id="agenda" name="agenda" class="form-control " style="width: 100%;"
                                                id="validationCustomUsername" placeholder="Escriba un acuerdo" required>
                                            <div class="valid-feedback">
                                                Agenda valida
                                            </div>
                                            <div class="invalid-feedback">
                                                Por favor ingrese una Agenda valida
                                            </div>
                                        </div>

                                    </div>
                                    <div class="d-block text-center card-footer">
                                        <button class="btn-wide btn btn-success">Guardar</button>
                                        <a onClick="limpiarCamposAgenda()" class="btn-wide btn btn-danger" style="color:#ffffff;">Cancelar</a>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                    <%
                    	DTAyudaMemoriaAgenda dtamag = new DTAyudaMemoriaAgenda();
                    	ArrayList<AyudaMemoriaAgenda> listagendas = new ArrayList<AyudaMemoriaAgenda>();
                    	listagendas = dtamag.listarAgendas(idayudamemoria);
                    	
                    	if(!listagendas.isEmpty()){
                    %>
                    
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h3 class="card-title">Agenda de reunión del colectivo docente</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
	                            
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Punto a tratar</th>
                                                <th>Acciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<%
                                        		for(AyudaMemoriaAgenda amag : listagendas){
                                        	%>
                                        		<tr>
                                        			<td><%=amag.getAgenda()%></td>
                                        			<td>
	                                        			<span title= "Editar Acuerdo">
	                                        				<a onClick="editarAgenda('<%=amag.getId_ayuda_memoria()%>','<%=amag.getAgenda()%>','<%=amag.getId_ayuda_memoria_agenda()%>')"><i class="fas fa-edit" style= "color:blue;"></i></a>
	                                        			</span>
	                                        			<span title= "Eliminar Acuerdo">
	                                        				<a onClick="eliminarAgenda('<%=amag.getId_ayuda_memoria_agenda()%>','<%=amag.getId_ayuda_memoria()%>')"><i class="fas fa-trash" style= "color:red;"></i></a>
	                                        			</span>
                                        			</td>
                                        		</tr>
                                        	<%
                                        		}
                                        	%>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <th>Punto a tratar</th>
                                                 <th>Acciones</th>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>
                                <!-- /.card-body -->
                            </div>
                        </div>
                    </div>
                    <%} %>
                    
                    <div class="row">
                        <div class="col-md-12">
                            <div class="main-card  card">
                                <div class="card-header">Acuerdos de la reunión de colectivo docente
                                </div>
                                <form action="../SLAMAcuerdo" method="post" class="needs-validation" novalidate>
                             	 <input hidden="true"  id="idacuerdo" name="idacuerdo" value="0">
                                <input hidden="true"  id="opcionAcuerdo" name="opcionAcuerdo" value="1">
                                <input hidden="true"  id="idayudamemoria" name="idayudamemoria" value="<%=idayudamemoria%>">
                                    <div class="col-md-12 mt-2">
                                        <div class="form-group">
                                            <label>Acuerdo</label>
                                            <input type="text" id="acuerdo" name="acuerdo" class="form-control " style="width: 100%;"
                                                id="validationCustomUsername" placeholder="Escriba un acuerdo" required>
                                            <div class="valid-feedback">
                                                Acuerdo valido
                                            </div>
                                            <div class="invalid-feedback">
                                                Por favor ingrese un Acuerdo valido
                                            </div>
                                        </div>

                                    </div>
                                    <div class="d-block text-center card-footer">
                                        <button class="btn-wide btn btn-success">Guardar</button>
                                        <a onClick="limpiarCamposAcuerdo()" class="btn-wide btn btn-danger" style="color:#ffffff;">Cancelar</a>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                    <%
                    	DTAyudaMemoriaAcuerdo dtamac = new DTAyudaMemoriaAcuerdo();
                    	ArrayList<AyudaMemoriaAcuerdo> listacuerdos = new ArrayList<AyudaMemoriaAcuerdo>();
                    	listacuerdos = dtamac.listarAcuerdos(idayudamemoria);
                    	
                    	if(!listacuerdos.isEmpty()){
                    %>
                    
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h3 class="card-title">Acuerdos del colectivo docente</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
	                            
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Acuerdos</th>
                                                <th>Acciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<%
                                        		for(AyudaMemoriaAcuerdo amac : listacuerdos){
                                        	%>
                                        		<tr>
                                        			<td><%=amac.getAcuerdo()%></td>
                                        			<td>
	                                        			<span title= "Editar Acuerdo">
	                                        				<a onClick="editarAcuerdo('<%=amac.getId_ayuda_memoria()%>','<%=amac.getAcuerdo()%>','<%=amac.getId_ayuda_memoria_acuerdo()%>')"><i class="fas fa-edit" style= "color:blue;"></i></a>
	                                        			</span>
	                                        			<span title= "Eliminar Acuerdo">
	                                        				<a onClick="eliminarAcuerdo('<%=amac.getId_ayuda_memoria_acuerdo()%>','<%=amac.getId_ayuda_memoria()%>')"><i class="fas fa-trash" style= "color:red;"></i></a>
	                                        			</span>
                                        			</td>
                                        		</tr>
                                        	<%
                                        		}
                                        	%>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <th>Acuerdos</th>
                                                 <th>Acciones</th>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>
                                <!-- /.card-body -->
                            </div>
                        </div>
                    </div>
                    <%} %>
                    
                    <%
                    	if(!listDoc.isEmpty()){
                    %>
                    
                     <div class="row">
                        <div class="col-md-12">
                            <div class="main-card  card">
                                <div class="card-header">Participantes de la reunión
                                </div>
                                <form action="../SLAMPersonal" method="post" class="needs-validation" novalidate>
                             	 <input hidden="true"  id="idparticpante" name="idparticpante" value="0">
                                <input hidden="true"  id="opcionParticipante" name="opcionParticipante" value="1">
                                <input hidden="true"  id="idayudamemoria" name="idayudamemoria" value="<%=idayudamemoria%>">
                                    <div class="col-md-12 mt-2">
                                        <div class="form-group">
                                            <label>Docentes</label>
                                            <select class="select2" name="idpersonal" data-placeholder="Seleccione un docente" style="width: 100%;" required>
                                             <option value=""> Selecione al menos una</option>
                                             <%
                                             	for(VW_Expediente_Doc dp: docentes){
                                             	if(!dtamas.yaEsParticipante(idayudamemoria, dp.getId_docente())){
                                             %>
                                             	<option value="<%=dp.getId_docente()%>"><%=dp.getDocente()%></option>
                                             <%
                                             		}
                                             	}
                                             %>
                                            </select>
                                            <div class="valid-feedback">
                                                Docente Valido
                                            </div>
                                            <div class="invalid-feedback">
                                                Por favor selecione un docente
                                            </div>
                                        </div>

                                    </div>
                                    <div class="d-block text-center card-footer">
                                        <button class="btn-wide btn btn-success">Guardar</button>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                    <%
                    	}
                    %>
                    <%
                    	
                    	if(!listasistentes.isEmpty()){
                    %>
                    
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h3 class="card-title">Participantes de la reunión</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
	                            
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Participante</th>
                                                <th>Acciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<%
                                        		for(VW_AyudaMemoriaAsistentes vwamas : listasistentes){
                                        	%>
                                        		<tr>
                                        			<td><%=vwamas.personal%></td>
                                        			<td>
	                                        			<%
	                                        				if(vwamas.id_personal != id_coord){ 
	                                        			%>
	                                        			<span title= "Eliminar personal">
	                                        				<a onClick="eliminarPersonal('<%=vwamas.getId_ayuda_memoria_asistentes()%>','<%=vwamas.getId_ayuda_memoria()%>')"><i class="fas fa-trash" style= "color:red;"></i></a>
	                                        			</span>
	                                        			<%
	                                        				}
	                                        			%>
                                        			</td>
                                        		</tr>
                                        	<%
                                        		}
                                        	%>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                                <th>Participante</th>
                                                 <th>Acciones</th>
                                            </tr>
                                        </tfoot>
                                    </table>
                                </div>
                                <!-- /.card-body -->
                            </div>
                        </div>
                    </div>
                    <%} %>
                    
                   
                    
                    
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
	
	function editarAcuerdo(idAyudaMemoria, acuerdo, idAcuerdo){
		document.getElementById("opcionAcuerdo").setAttribute('value', 2);
		document.getElementById("idayudamemoria").setAttribute('value', idAyudaMemoria);
		document.getElementById("acuerdo").setAttribute('value', acuerdo);
		document.getElementById("idacuerdo").setAttribute('value', idAcuerdo);
		
	}
	
	function editarAgenda(idAyudaMemoria, agenda, idAgenda){
		document.getElementById("opcionAgenda").setAttribute('value', 2);
		document.getElementById("idayudamemoria1").setAttribute('value', idAyudaMemoria);
		document.getElementById("agenda").setAttribute('value', agenda);
		document.getElementById("idagenda").setAttribute('value', idAgenda);
		
	}
	
	function limpiarCamposAcuerdo(){
		document.getElementById("opcionAcuerdo").setAttribute('value', 1);
		document.getElementById("acuerdo").setAttribute('value', '');
		document.getElementById("idacuerdo").setAttribute('value', 0);
	}
	
	function limpiarCamposAgenda(){
		document.getElementById("opcionAgenda").setAttribute('value', 1);
		document.getElementById("agenda").setAttribute('value', '');
		document.getElementById("idagenda").setAttribute('value', 0);
	}
	
	function eliminarAgenda(idAgenda, idAyudaMemoria){
		window.location.href="../SLAMAgenda?id_agenda="+idAgenda+"&id_ayuda_memoria="+idAyudaMemoria;
		location.reload
	}
	
	
	function eliminarAcuerdo(idAcuerdo, idAyudaMemoria){
		window.location.href="../SLAMAcuerdo?id_acuerdo="+idAcuerdo+"&id_ayuda_memoria="+idAyudaMemoria;
		location.reload
	}
	
	function eliminarPersonal(idPersonal, idAyudaMemoria){
		window.location.href="../SLAMPersonal?id_personal="+idPersonal+"&id_ayuda_memoria="+idAyudaMemoria;
		location.reload
	}
	
	
	</script>

</body>

</html>