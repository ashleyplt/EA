<%@page import="java.util.ArrayList"%>
<%@page import="vistas.*"%>
<%@page import="datos.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="es">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Plan de estudio</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />

<link rel="stylesheet"
	href="../dist/plugins/Bootstrap/css/bootstrap.min.css">

<link rel="stylesheet"
	href="../dist/plugins/table/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="../dist/plugins/table/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="../dist/plugins/table/datatables-buttons/css/buttons.bootstrap4.min.css">

<link href="../dist/plugins/FontAwesome/css/all.min.css"
	rel="stylesheet">

<link href="../dist/main.css" rel="stylesheet">

</head>
<body>
	<div
		class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">

		<!--comentario include de header y settings-->
		<jsp:include page="./component/header.jsp"></jsp:include>
		<jsp:include page="./component/settings.jsp"></jsp:include>

		<div class="app-main">
			<!--comentario include de los menus-->
			<jsp:include page="./component/movil-menu.jsp"></jsp:include>
			<jsp:include page="./component/menu.jsp"></jsp:include>

			<%
			boolean coordinador = false;
			String carrera = "";
			
			HttpSession usuario = request.getSession();
			String nombre = usuario.getAttribute("nombre_user").toString();

			String cargo = usuario.getAttribute("cargo").toString();
			if(cargo.equals("")){
			%>
			<script>
			window.location.href = "index.jsp";
			</script>
			<%
			}
			else coordinador = true;
			
			if(coordinador){
				carrera = usuario.getAttribute("carrera").toString();
				DTVExpedienteCarrera dtec = new DTVExpedienteCarrera();
				ArrayList<VW_Expediente_Carrera> listExpedientes = dtec.listarPlanEstudio(carrera);
				
			%>
			<div class="app-main__outer">
				<div class="app-main__inner">
					<div class="app-page-title">
						<div class="page-title-wrapper">
						
							
							<div class="page-title-heading">
								<div class="page-title-icon">
									<i class="fas fa-stream icon-gradient bg-mean-fruit"> </i>
								</div>
								<div>
									Plan de estudio
									<div class="page-title-subheading"><%=carrera%></div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="card-header">
									<div style="position:absolute; right:20px;">
										<button type="button" class="btn btn-secondary btn-sm"
											onclick="window.location.href='crear-asignatura.jsp'">
											<i class="fas fa-plus"></i> Añadir asignatura
										</button>
									</div>
								</div>
								<!-- /.card-header -->
								<div class="card-body">
								<%
								if(listExpedientes.size() > 0){
								%>
									<table id="example1" class="table table-bordered table-striped" width="100%">
										<thead>
											<tr>
												<th>Código</th>
												<th>Asignatura</th>
												<th>Créditos</th>
												<th>Acciones</th>
											</tr>
										</thead>
										<tbody>
										<%
										for(VW_Expediente_Carrera ec : listExpedientes){
											String estado = "table-default";
						                    String imgEstado = "fa-ban";
						                    String titleEstado = "Deshabilitar";
			                            	if(ec.getEstado() == 0){
			                          		  estado = "table-danger";
			                          		  imgEstado = "fa-check";
			                          		  titleEstado = "Habilitar";
			                            	}
										%>
											<tr>
												<td><%=ec.getCodigo()%></td>
												<td><%=ec.getAsignatura()%></td>
												<td><%=ec.getCreditos()%></td>
												<td><span title="Editar"> <a
														href="crear-asignatura.jsp?opc=2&id=<%=ec.getId_asignatura()%>">
															<i class="fas fa-edit"></i>
													</a></span>
													<%
													DTVExpedienteDocente dted = new DTVExpedienteDocente();
													if(!dted.existeExpedienteActual(ec.getId_asignatura())){
													%>
													<span title="<%=titleEstado%>"> <a
														href="../SLAsignatura?idplan=<%=ec.getId_plan()%>&idasignatura=<%=ec.getId_asignatura()%>&estado=<%=ec.getEstado()%>">
															<i class="fas <%=imgEstado%>"></i>
													</a></span>
													<%
													} else {
													%>
													<i class="text-secondary fas fa-ban" id="popover" data-trigger="hover" data-container="body" data-toggle="popover" data-placement="right" data-content="Hay una edición que está siendo cursada y no se puede deshabilitar." ></i>
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
												<th>Código</th>
												<th>Asignatura</th>
												<th>Créditos</th>
												<th>Acciones</th>
											</tr>
										</tfoot>
									</table>
									<%
									} else {
									%>
									<div class="card container position-relative w-100 bg-light">
										<label class="text-center"><i
											class="far fa-folder-open position-relative text-center mb-4 mt-5 br"
											style="font-size: 4rem; color: #212529;"></i> </label>
										<h4 class="text-center mb-3">No tiene plan de estudio.</h4>
										<h6 class="text-secondary text-center mb-5">
											No tiene ninguna asignatura perteneciente a la carrera de <%=carrera%>. Puede <a
												href="crear-asignatura.jsp">empezar a crear.</a>
										</h6>
									</div>
									<%
									}
									%>
								</div>
								<!-- /.card-body -->
							</div>
						</div>
					</div>
				</div>
				<%
				} else {
				%>
				<script>
				window.location.href = "../index.jsp";
				location.reload
				</script>
				<%}%>

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

	<script type="text/javascript" src="../dist/assets/scripts/main.js"></script>

	<script src="../dist/plugins/table/datatables/jquery.dataTables.min.js"></script>
	<script
		src="../dist/plugins/table/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
	<script
		src="../dist/plugins/table/datatables-responsive/js/dataTables.responsive.min.js"></script>
	<script
		src="../dist/plugins/table/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
	<script
		src="../dist/plugins/table/datatables-buttons/js/dataTables.buttons.min.js"></script>
	<script
		src="../dist/plugins/table/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
	<script src="../dist/plugins/table/jszip/jszip.min.js"></script>
	<script src="../dist/plugins/table/pdfmake/pdfmake.min.js"></script>
	<script src="../dist/plugins/table/pdfmake/vfs_fonts.js"></script>
	<script
		src="../dist/plugins/table/datatables-buttons/js/buttons.html5.min.js"></script>
	<script
		src="../dist/plugins/table/datatables-buttons/js/buttons.print.min.js"></script>
	<script
		src="../dist/plugins/table/datatables-buttons/js/buttons.colVis.min.js"></script>

	<script>
		$(function() {
			$("#example1").DataTable({
				"responsive" : true,
				"lengthChange" : false,
				"autoWidth" : false,
				"buttons" : [ 
				{
		           extend: 'pdf',
		           exportOptions: {
		                columns: [0,1,2]
		            },
		            tableHeader: {
		                bold:true,
		                fontSize:18,
		                color:'black',
		                fillColor:'#F0F8FF',
		                alignment:'center'
		            },
		            customize: function (doc) {
		                doc.content[1].table.widths = 
		                    Array(doc.content[1].table.body[0].length + 1).join('*').split('');
		              }
			    }, {
					extend : 'print',
					text : 'Imprimir'
				}, {
					extend : 'colvis',
					text : 'Columnas'
				} ],
				"language" : {
					"search" : "Buscar:",
					"zeroRecords" : "No hay asignaturas disponibles.",
					"info" : "Mostrando _START_ a _END_ de _TOTAL_ asignaturas",
					"infoEmpty" : "Mostrando 0 de 0 asignaturas",
					"infoFiltered": "",
					paginate : {
						previous : 'Atrás',
						next : 'Siguiente'
					}
				}
			}).buttons().container().appendTo(
					'#example1_wrapper .col-md-6:eq(0)');
			$('#example2').DataTable({
				"paging" : true,
				"lengthChange" : false,
				"searching" : false,
				"ordering" : true,
				"info" : true,
				"autoWidth" : false,
				"responsive" : true,
			});
		});
	</script>
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
</body>
</html>