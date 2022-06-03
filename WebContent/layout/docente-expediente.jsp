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
<title>Asignación docente</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />

<link rel="stylesheet"
	href="../dist/plugins/select2/css/select2.min.css">
<link rel="stylesheet"
	href="../dist/plugins/select2-theme/select2-bootstrap.min.css">

<link rel="stylesheet"
	href="../dist/plugins/Bootstrap/css/bootstrap.min.css">
<link href="../dist/styles.min.css" rel="stylesheet">
<link href="../dist/plugins/FontAwesome/css/all.min.css"
	rel="stylesheet">

<link href="../dist/main.css" rel="stylesheet">
</head>


<body>
	<div
		class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">

		<!--comentario include de header y settings -->
		<jsp:include page="./component/header.jsp"></jsp:include>
		<jsp:include page="./component/settings.jsp"></jsp:include>

		<%
		request.setCharacterEncoding("UTF-8");
		HttpSession usuario = request.getSession();
		String cargo = usuario.getAttribute("cargo").toString();
		String carrera = "";
		
		if(cargo.equals("")) cargo = null;
		else carrera = usuario.getAttribute("carrera").toString();
		
		String expediente = request.getParameter("expediente");
		boolean pertenece = false;
		
		//Verifica si el expediente pertenece al plan de estudio del coordinador activo
		if(expediente != null){
			DTVExpedienteCarrera dtvec = new DTVExpedienteCarrera();
			if(dtvec.existeExpedienteEnCarrera(expediente, 1, carrera)) pertenece = true;
		}
		
		//Verifica si hay una edición activa
		DTEdicionAsignatura dteda = new DTEdicionAsignatura();
		EdicionAsignatura eda = dteda.getEdicionActiva();
		
		if(cargo == null || expediente == null || !pertenece || eda == null){
		%>
		<script>
		window.location.href = "index.jsp";
		</script>
		<%
		}
		VW_Permisos_Expediente vpe = new VW_Permisos_Expediente();
		DTVPermisosExpediente dtpe = new DTVPermisosExpediente();
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
									Expediente de asignatura docente.
									<div class="page-title-subheading">
									Asignación de docente a un curso de asignatura activo.
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="main-card  card">
								<div class="card-header">Asignación docente.
									<div class="btn-actions-pane-right">
										<div role="group" class="btn-group-sm btn-group">
											<div class="page-title-actions">
											</div>
										</div>
									</div>
								</div>
								<form method="post" action="../SLExpedienteDocente?expediente=<%=expediente%>"
									class="needs-validation" novalidate>
									<div class="col-md-12 mt-2">
										<div class="form-group">
											<label>Expediente de asignatura</label>
											<div class="input-group align-items-center">
												<%
												ArrayList<VW_Expediente_Carrera> listExpediente = new ArrayList<>();
												DTVExpedienteCarrera dtec = new DTVExpedienteCarrera();
												String carreraexp = dtec.getCarrera(expediente);
												
												listExpediente = dtec.listarExpedientesCarrera(carreraexp);
												
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
											<label>Docentes</label>
											<div class="input-group align-items-center">
											<select id="docente" name="docente"
												data-placeholder="Seleccione un docente" multiple="multiple" class="select2"
												style="width:96%" required>
												<option value=""></option>
												<%
												DTVCargoPersonal dtcp = new DTVCargoPersonal();
												ArrayList<VW_Cargo_Personal> listPersonal = new ArrayList<>();
												
												listPersonal = dtcp.listarParaPermisos();
												
												String nombre = usuario.getAttribute("nombre_user").toString();
	
												DTVPersonalUsuario dtvpu = new DTVPersonalUsuario();
												int idactivo = dtvpu.buscaridPersonal(nombre);
												
												//Evitar mostrar personal si este imparte esa asignatura
												DTVExpedienteDocente dted = new DTVExpedienteDocente();
												
												for(VW_Cargo_Personal vcp : listPersonal){
													String personal = vcp.getNombre() + " " + vcp.getApellido();
													if(!dted.existeDocenteExpediente(expediente, vcp.getId_personal()) && vcp.getId_personal() != idactivo){
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
										</div>
									</div>
									<div class="d-block text-center card-footer">
										<button type="submit" class="btn-wide btn btn-success">Aceptar</button>
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

	<% 
	String error = (String) usuario.getAttribute("error");
	if(error.equals("")) error = null;
	if(error !=  null){
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
	function closemodal(){
		$('#myModal').modal('hide');
	};
	
	
	function gotopage(expediente) {
		$('#docente').val(null).trigger("change");
		$.ajax({		    
	          url: "../SLajaxFiltroDocenteExp",
	          type: "post",
	          datatype:"html",
	          data:  {"expediente":expediente, "lugar":$('#lugar').val() },
	          success: function(data) {
	        		$('#docente').html(data);
	          }
	        });
		$('#myModal').modal('hide');
	};
	</script>
	
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
