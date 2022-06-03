<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    import="entidades.*,datos.*,
	 java.util.*;"%>

<!DOCTYPE html>

<html lang="es">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Ver syllabus</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />

    <!--comentario Estilos de bootstrap 5 -->
    <link rel="stylesheet" href="../dist/plugins/Bootstrap/css/bootstrap.min.css">

    <!--comentario estilos tabla-->
    <link rel="stylesheet" href="../dist/plugins/table/datatables-bs4/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="../dist/plugins/table/datatables-responsive/css/responsive.bootstrap4.min.css">
    <link rel="stylesheet" href="../dist/plugins/table/datatables-buttons/css/buttons.bootstrap4.min.css">

    <!--comentario Font awesome-->
    <link href="../dist/plugins/FontAwesome/css/all.min.css" rel="stylesheet">

    <!--comentario Estilos Principales-->
    <link href="../dist/main.css" rel="stylesheet">


</head>

<body>

    <div class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">

        <!--comentario include de header y settings-->
        <jsp:include page="./component/header.jsp"></jsp:include>
        <jsp:include page="./component/settings.jsp"></jsp:include>
        
        <%
        	int expediente = 0;
        	String idSyllabus = request.getParameter("idsyllabus");
        	int syllabus = Integer.parseInt(idSyllabus);
        	if(request.getParameter("idexpediente").isEmpty() || request.getParameter("idexpediente").isBlank()){
        		expediente = 0;
        	}
        	
        	DTSyllabus dts = new DTSyllabus();
    		ArrayList<Syllabus> listaSyllabus = new ArrayList<Syllabus>();
    		listaSyllabus = dts.listarSyllabus(expediente, syllabus);
    		int exp = Integer.parseInt(request.getParameter("idexpediente"));
    		
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
                                    <i class="fas fa-scroll icon-gradient bg-mean-fruit">
                                    </i>
                                </div>
                                <div>Ver syllabus de asignaturas
                                    <div class="page-title-subheading">Syllabus de asignatura activos
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                    <h3 class="card-title">Syllabus</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
	                                <div class= "row ">
	                                	<div class="col-sm-12 mt-0 mb-2">
	                                		<div class="dt-buttons btn-group flex-wrap">
	                                			<div class="buttons-html5">
	                                				<a class="btn btn-primary" href="estructura.jsp?idexpediente=<%=exp%>">Regresar al expediente</a>
	                                			</div>
	                                
	                                		</div>
	                               		 </div>
	                                </div>
                                
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>Id</th>
                                                <th>Expediente</th>
                                                <th>Fecha de creación</th>
                                                <th>Última modificación</th>
                                                <th>Objetivos</th>
                                                <th>Agendas</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<%
                                        		for(Syllabus s : listaSyllabus){
                                        	%>
                                        		<tr>
                                        			<td><%=s.getId_syllabus()%></td>
                                        			<td><%=s.getId_expediente_asignatura()%></td>
                                        			<td><%=s.getFecha_creacion()%></td>
                                        			<%
                                        				if(s.getFecha_modificacion()==null){
                                        			%>
                                        				<td>Este syllabus no ha sido modificado hasta el momento</td>
                                        			<%
                                        			}else{
                                        			%>
                                        				<td><%=s.getFecha_modificacion()%></td>
                                        			<%
                                        				}
                                        			%>
                                        			<td>
                                        			<span title= "Gestionar Objetivos">
                                        				<a href="crear-objetivo.jsp?idexpediente=<%=s.getId_expediente_asignatura()%>&idsyllabus=<%=s.getId_syllabus()%>"><i class="fas fa-cogs" style= "color:blue;"></i></a>
                                        			</span>
                                        			</td>
                                        			
                                        			<td>
                                        			<span title= "Añadir nueva agenda">
                                        				<a href="crear-detalleSyllabus.jsp?idsyllabus=<%=s.getId_syllabus()%>"><i class="fas fa-plus" style= "color:blue;"></i></a>
                                        			</span>
                                        			<span title= "Ver agendas">
                                        				<a href="create-Syllabus.jsp"><i class="far fa-eye" style= "color:blue;"></i></a>
                                        			</span>		
                                        			</td>
                                        			
                                        		</tr>
                                        	<%
                                        		}
                                        	%>
                                        </tbody>
                                        <tfoot>
                                            <tr>
                                               <th>Id</th>
                                                <th>Expediente</th>
                                                <th>Fecha de creación</th>
                                                <th>Última modificación</th>
                                                <th>Objetivos</th>
                                                <th>Agendas</th>
                                            </tr>
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
                "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
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