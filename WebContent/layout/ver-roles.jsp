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
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Roles</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />

<!--comentario Estilos de bootstrap 5 -->
<link rel="stylesheet"
	href="../dist/plugins/Bootstrap/css/bootstrap.min.css">

<!--comentario estilos tabla-->
<link rel="stylesheet"
	href="../dist/plugins/table/datatables-bs4/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="../dist/plugins/table/datatables-responsive/css/responsive.bootstrap4.min.css">
<link rel="stylesheet"
	href="../dist/plugins/table/datatables-buttons/css/buttons.bootstrap4.min.css">

<!--comentario Font awesome-->
<link href="../dist/plugins/FontAwesome/css/all.min.css"
	rel="stylesheet">

<!--comentario Estilos Principales-->
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

			<div class="app-main__outer">
				<div class="app-main__inner">
					<div class="app-page-title">
						<div class="page-title-wrapper">

							<!--comentario inicia la card debajo del header-->
							<div class="page-title-heading">
								<div class="page-title-icon">
									<i class="pe-7s-car icon-gradient bg-mean-fruit"> </i>
								</div>
								<div>Roles</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="card">
								<div class="card-header">
									<h3 class="card-title">Lista de Roles</h3>
								</div>

								<!-- /.card-header -->
								<div class="card-body"> 
								<table id="example1" class="table table-bordered table-striped">
                                   
										<thead>
											<tr>
												<th>Rol</th>
												<th>Descripción</th>
												<th>Opción</th>
												<th>Estado</th>
												<th>Acciones</th>
											</tr>
										</thead>
										<tbody>

											<%  					                    
										DTVRol dtvr = new DTVRol(); 
			                            ArrayList<VW_Roles> listaRoles = new ArrayList<VW_Roles>();
			                            listaRoles = dtvr.listarRoles();
			                            
			                            for (VW_Roles r : listaRoles) {
			                            	String estado = "table-default";
						                    String imgEstado = "fa-ban";
						                    String titleEstado = "Deshabilitar";
			                            	if(r.getEstado() == 0){
			                          		  estado = "table-danger";
			                          		  imgEstado = "fa-check";
			                          		  titleEstado = "Habilitar";
			                            	}
			                        %>
											<tr class="<%=estado%>">
												<td><%=r.getNombre()%></td>
												<td><%=r.getDescripcion()%></td>
												<td><%=r.getOpciones()%></td>
												<td><%=r.getEstado()%></td>
											<td><span title="Editar"> <a
														href="editar-actividad.jsp?idRoles=">
															<i class="fas fa-edit"></i>
													</a>
												</span> <span title="<%=titleEstado%>"> <a
														href="../SLactividad?idRoles=&estado=">
															<i class="fas <%=imgEstado%>"></i>
													</a>
												</span></td>
											</tr>
											<%
									}
									%>
										</tbody>

										<tfoot>
											<tr>
												<th>Rol</th>
												<th>Descripción</th>
												<th>Opción</th>
												<th>Estado</th>
												<th>Acciones</th>											</tr>
										</tfoot>
									</table>
								</div>
								<!-- /.card-body -->
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

	<!-- Selectt 2 -->
	<script src="../dist/plugins/select2/js/select2.full.min.js"></script>

	<script type="text/javascript" src="../dist/assets/scripts/main.js"></script>


	<!-- script tablas -->
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




	<!-- inicializar tabla -->
 	<script>
        $(function () {
        	$("#example1").DataTable({
      	      "responsive": true, "lengthChange": false, "autoWidth": false,
      	      "buttons": [ {
      	          extend: 'copy',
      	          text: 'Copiar'
      	      }, "csv", "excel", "pdf", {
      	          extend: 'print',
      	          text: 'Imprimir'
      	      }, {
      	          extend: 'colvis',
      	          text: 'Columnas'
      	      }],
      	      "language": {    	
      		      "search": "Buscar:",
      		      "zeroRecords": "No hay registros disponibles.",
      		      "info": "Mostrando _START_ a _END_ de _TOTAL_ registros",
      		      "infoEmpty": "Mostrando 0 de 0 registros",
      		      paginate: {
      		            previous: 'Atrás',
      		            next:     'Siguiente'
      		        }
      	      }
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

</body>

</html>