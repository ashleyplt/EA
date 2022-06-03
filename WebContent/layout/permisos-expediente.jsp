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
<title>Permiso de expediente</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />

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


<!--comentario Estilos Principales-->
<link href="../dist/main.css" rel="stylesheet">
</head>


<body>
	<div
		class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">

		<!--comentario include de header y settings -->
		<jsp:include page="./component/header.jsp"></jsp:include>
		<jsp:include page="./component/settings.jsp"></jsp:include>

		<%
		HttpSession usuario = request.getSession();
		String cargo = usuario.getAttribute("cargo").toString();
		if(cargo.equals("")){
		%>
		<script>
		window.location.href = "index.jsp";
		window.reload
		</script>
		<%
		}
		request.setCharacterEncoding("UTF-8");
		
		int opc = 0;
		String opcion = request.getParameter("opc");
		if(opcion == null) opc = 1;
		else opc = Integer.parseInt(opcion);
		if(opc <= 0 || opc > 3) opc = 1;
		int id = 0;
		
		String expediente = request.getParameter("expediente");
		if(request.getParameter("id") == null) id = 0;
		else{
			id = Integer.parseInt(request.getParameter("id"));
			expediente = "";
		}
		if(expediente == null){
		%>
		<script>
		window.location.href = "index.jsp";
		window.reload
		</script>
		<%
		}
		String sub = "Nuevo permiso de expediente de aisgnatura";
		String cheader = "Conceder permiso";
		
		VW_Permisos_Expediente vpe = new VW_Permisos_Expediente();
		DTVPermisosExpediente dtpe = new DTVPermisosExpediente();

		switch (opc) {
			case 2 :
				sub = "Modificación del permiso de expediente de asignatura";
				cheader = "Modificar permiso";				
				vpe = dtpe.getPermiso(id);
				expediente = vpe.getAsignatura();
				break;
			case 3 :
				sub = "Visualización de detalles del permiso de expediente de asignatura";
				cheader = "Detalles de permiso";
				vpe = dtpe.getPermiso(id);
				expediente = vpe.getAsignatura();
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
									<i class="fas fa-user-plus icon-gradient bg-mean-fruit"> </i>
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
								<form method="post" action="../SLPermisosAsignatura?opc=<%=opc%>&expediente=<%=expediente%>"
									class="needs-validation" onSubmit="enablePath();" novalidate>
									<div class="col-md-12 mt-2">
										<div class="form-group">
											<label>Expediente de asignatura</label>
											<div class="input-group align-items-center">
												<%
												ArrayList<VW_Expediente_Carrera> listExpediente = new ArrayList<>();
												DTVExpedienteCarrera dtec = new DTVExpedienteCarrera();
												String carrera = dtec.getCarrera(expediente);
												
												listExpediente = dtec.listarExpedientesCarrera(carrera);
												
												for(VW_Expediente_Carrera vec : listExpediente){
													if(expediente.equals(vec.getAsignatura())){%> 
												<input readonly type="text" class="form-control" style="width: 100%;"
                                                name="expediente" value="<%=vec.getAsignatura()%>">
                                                <input readonly type="hidden" class="form-control" style="width: 100%;"
                                                name="idexpediente" value="<%=vec.getId_asignatura()%>">
												<%
													}
												}
												%>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label>Tipo de permiso</label>
											<select <%if(opc == 3){%> disabled <%} %>id="tpermiso" name="tpermiso"
												data-placeholder="Seleccione un tipo de permiso" class="select2"
												style="width:100%" required>
												<option value=""></option>
												<%
												ArrayList<TipoPermiso> listTipo = new ArrayList<>();
												DTTipoPermiso dtp = new DTTipoPermiso();
												
												listTipo = dtp.listarTipoPermiso();
												
												for(TipoPermiso tp : listTipo){
												%>
												<option <%if(opc > 1 && vpe.getTipo_permiso().equals(tp.getNombre())){%> selected="selected" <%}%>value="<%=tp.getId()%>"><%=tp.getNombre()%></option>
												<%
												}
												%>
											</select>
											<div class="invalid-feedback">Por favor selecione una
												opción.</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
										<%
										if(opc == 1){
										%>
											<label>Personal</label>
											<div class="input-group align-items-center">
											<select <%if(opc == 3) {%> disabled <%} %> id="docente" name="docente"
												data-placeholder="Seleccione un personal" multiple="multiple" class="select2"
												name="expediente" style="width:96%" required>
												<option value=""></option>
												<%
												DTVCargoPersonal dtcp = new DTVCargoPersonal();
												ArrayList<VW_Cargo_Personal> listPersonal = new ArrayList<>();
												
												listPersonal = dtcp.listarParaPermisos();
												
												//Para evitar duplicados de personales
												ArrayList<VW_Cargo_Personal> personales = new ArrayList<>();
												int cont = 0;;
												for (VW_Cargo_Personal cp : listPersonal) {
													if(personales.isEmpty()) personales.add(cp);
													String idpers = String.valueOf(personales.get(cont).getId_personal());
													if (!idpers.equals(String.valueOf(cp.getId_personal()))) {
														personales.add(cp);
														cont++;
													}
													
												}
												
												String nombre = usuario.getAttribute("nombre_user").toString();

												DTVPersonalUsuario dtvpu = new DTVPersonalUsuario();
												int idactivo = dtvpu.buscaridPersonal(nombre);
												
												//Evitar mostrar personal si este imparte esa asignatura
												DTVExpedienteDocente dted = new DTVExpedienteDocente();
												
												for(VW_Cargo_Personal vcp : personales){
													String personal = vcp.getNombre() + " " + vcp.getApellido();
													if(!dtpe.existePermiso(expediente, vcp.getId_personal()) && !dted.existeDocenteExpediente(expediente, vcp.getId_personal()) && vcp.getId_personal() != idactivo){
												%>
												<option value="<%=vcp.getId_personal()%>"><%=personal%></option>
												<%
													}
												}
												%>
											</select>
											<div class="input-group-append">
											<span data-toggle="modal" data-target="#myModal" class="ml-3"style="cursor:pointer;" title="Filtrar personales"> 
												<a>
												<i style="color:#212529;" class="fas fa-filter fa-lg"></i>
												</a>
											</span>
											</div>
											<div class="invalid-feedback">Por favor selecione una
												opción.</div>
											</div>
										<%
										} else {
										%>
                                            <label>Personal</label> 
                                            <input readonly type="text" class="form-control" style="width: 100%;"
                                                name="docente" value="<%=vpe.getDocente()%>">
                                                
                                             <input readonly type="hidden" class="form-control" style="width: 100%;"
                                                name="idpermiso" value="<%=vpe.getId_permiso()%>">
										<%
										}
										%>
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											<label for="formFileMultiple" class="form-label">Rango
												de fecha de acceso.</label> 
											<div class="input-group">
												<div class="input-group-prepend">
													<span class="input-group-text"> <i
														class="far fa-calendar-alt"></i>
													</span>
												</div>
												<input type="text" class="form-control float-right"
												<%
												String fecha = vpe.getFecha_inicio()+" - "+vpe.getFecha_final(); 
												if(opc > 1){ %> value="<%=fecha%>" <%}
												if(opc == 3){%> readonly <%} %>
													type="date" name="daterange" id="drango" required>
											</div>
											<div class="invalid-feedback">Por favor selecione un
												rango de fecha válido</div>
										</div>
									</div>
									<div class="d-block text-center card-footer">
										<%if(opc == 3) {%>
										<button onclick="optionactions(1, 0)" type="button" class="btn-wide btn btn-secondary">Volver</button>
										<button onclick="optionactions(2, <%=id%>)" type="button" class="btn-wide btn btn-primary">Modificar</button>
										<%} if(opc == 2){%>
										<button onclick="optionactions(1, 0)" type="button" class="btn-wide btn btn-secondary">Cancelar</button>
										<%} if(opc == 1 || opc == 2) {%>
										<button type="submit" class="btn-wide btn btn-success">Aceptar</button>
										<%} %>
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

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">Filtrar docentes</h4>
						<i class="ml-2 mt-3 icon ni fas fa-exclamation-circle" id="popover" data-trigger="hover" data-container="body" data-toggle="popover" data-placement="right" data-content="Al filtrar los datos del personal se eliminarán los que seleccionó anteriormente." style="cursor:pointer;"></i> 	
					
					<button type="button" onclick="closemodal()" class="close"
						data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div id="shared-content" class="modal-body">
					<div class="col-md-12 mt-2">
						<label>Coordinación</label> <select class="select2"
							name="lugar" id="lugar"
							data-placeholder="Seleccione un lugar de trabajo"
							style="width: 100%;" required>
							<option value=""></option>
							<%
							ArrayList<Coordinacion> listaCoordinacion = new ArrayList<>();
							DTCoordinacion dtd = new DTCoordinacion();
							listaCoordinacion = dtd.listarCoordinacion();
							for (Coordinacion c : listaCoordinacion) {
							%>
							<option value="<%=c.getNombre()%>"><%=c.getNombre()%></option>
							<%
							}
							%>
						</select>
						<div class="invalid-feedback">Por favor selecione una opción</div>

					</div>

					<div class="col-md-12 mt-2 mb-2">
						<label>Cargo</label> <select id="cargo" name="cargo"
							class="select2" data-placeholder="Seleccione un cargo"
							name="expediente" style="width: 100%;" required>
							<option value=""></option>
							<%
								ArrayList<Cargo> listaCargos = new ArrayList<>();
							DTCargo dtc = new DTCargo();
							listaCargos = dtc.listarCargo();

							for (Cargo c : listaCargos) {
							%>
							<option value="<%=c.getNombre()%>"><%=c.getNombre()%></option>
							<%
							}
							%>
						</select>
						<div class="invalid-feedback">Por favor selecione una
							opción.</div>

					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
					<button type="button" onclick="gotopage('<%=expediente%>')" class="btn btn-primary"
						data-dismiss="modal">Aceptar</button>
				</div>
			</div>
		</div>
	</div>



	<script src="../dist/jquery.min.js"></script>

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
		
	<!-- Selectt 2 -->
	<script src="../dist/plugins/select2/js/select2.full.min.js"></script>

	<!-- script Principal -->
	<script type="text/javascript" src="../dist/assets/scripts/main.js"></script>

	<script>
	function volver(){
		window.location.href = "index.jsp";
		window.reload
	}
	</script>
	<%if(opc != 3){ %>
	<script>
	$(function() {
	    $('input[name="daterange"]').daterangepicker({
	    	 "locale": {
	    		 	"applyLabel": "Aceptar",
	    	        "cancelLabel": "Cancelar",
	    	        "format": "YYYY-MM-DD",
	    	        "daysOfWeek": ["D", "L", "M", "X", "J", "V", "S"],
	    	        "monthNames": ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", 
  	        				"Septiembre", "Octubre", "Noviembre", "Diciembre"],
	    	        "firstDay": 1
	    	    },
	    	    "drops": "up",
	    	    "startDate": <%if(opc == 2) {%> "<%=vpe.getFecha_inicio()%>" <%} else {%> moment() <%}%>,
	    	    "endDate": <%if(opc == 2) {%> "<%=vpe.getFecha_final()%>" <%} else {%> moment().add(5, "days") <%}%>,
	    	    "minDate": <%if(opc == 2) {%> "<%=vpe.getFecha_inicio()%>" <%} else {%> moment() <%}%>,
	    	    "maxDate": moment().add(6, 'months'),
	    	    container: '#end',
	    	    setValue: function(start, end)
	    		{
	    			$('#start').val(s1);
	    			$('#end').val(s2);
	    		}
	    }, function(start, end) {
	      startDate = start;
	      endDate = end;
	    });
    });
	</script>
	<%} %>
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
	
	function optionactions(opcion, id){
		if(opcion == 1){
			window.location.href="index.jsp";
			location.reload
		}
		if(opcion == 2){
			window.location.href="permisos-expediente.jsp?opc=2&id="+id;
			location.reload
		}
	}
	
	function closemodal(){
		$('#myModal').modal('hide');
	}
	
	function enablePath() {
	    document.getElementById('expasign').disabled= "";
	}
	
	function gotopage(expediente) {
		$('#docente').val(null).trigger("change")
		$.ajax({		    
	          url: "../SLajaxFiltroPermiso",
	          type: "post",
	          datatype:"html",
	          data:  {"expediente":expediente, "lugar":$('#lugar').val(), "cargo":$('#cargo').val() },
	          success: function(data) {
	        		$('#docente').html(data);
	          }
	        });
		$('#myModal').modal('hide');
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
