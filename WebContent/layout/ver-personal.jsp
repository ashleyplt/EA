<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" 
    import="entidades.*,datos.*, java.util.*, vistas.*"%>
<!DOCTYPE html>

<html lang="es">

<style>
body{
    background:#DCDCDC;
    margin-top:20px;
}
.card-box {
    padding: 20px;
    border-radius: 3px;
    margin-bottom: 30px;
    background-color: #fff;
    height: 410px;
}

.social-links li a {
    border-radius: 50%;
    color: rgba(121, 121, 121, .8);
    display: inline-block;
    height: 30px;
    line-height: 27px;
    border: 2px solid rgba(121, 121, 121, .5);
    text-align: center;
    width: 30px;
}

.social-links li a:hover {
    color: #797979;
    border: 2px solid #797979;
}
.thumb-lg {
    height: 88px;
    width: 88px;
}
.img-thumbnail {
    padding: .25rem;
    background-color: #fff;
    border: 1px solid #dee2e6;
    border-radius: .25rem;
    max-width: 100%;
    width: 100px; 
    height: 100px;
}
.text-pink {
    color: #ff679b!important;
}
.btn-rounded {
    border-radius: 2em;
}
.text-muted {
    color: #98a6ad!important;
}
h4 {
    line-height: 22px;
    font-size: 18px;
}
</style>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Personal</title>
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

        <div class="app-main">
            <!--comentario include de los menus-->
            <jsp:include page="./component/movil-menu.jsp"></jsp:include>
            <jsp:include page="./component/menu.jsp"></jsp:include>
            <%
			HttpSession usuario = request.getSession();
			String roladmin = session.getAttribute("rol").toString();
			if(roladmin.equals("")){
			%>
			<script>
			window.location.href = "index.jsp";
			</script>
			<%
			}
			%>

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
                                <div>Personales
                                    <div class="page-title-subheading">
                                    Información de todos los personales dentro del sistema
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                Información
                                <div class="input-group rounded mt-3 mb-3" style="position:relative;right:-68%;">
									<div class="form-outline">
										<input id="myInput" type="search" id="form1"
											class="form-control" placeholder="Buscar"/> 
									</div>
								</div> 
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                        <div class="row" id="lista-personal">	
                                        <%
	                                        DTVExpedienteDocente dtved = new DTVExpedienteDocente();
	        			                    ArrayList<VW_Expediente_Docente> listExp;
	        			                    DTVPermisosExpediente dtvpe = new DTVPermisosExpediente();
	        			                    ArrayList<VW_Permisos_Expediente> listPermisos;
	        			                    
        			                    
                                             DTVDatosPersonal dtvdp = new  DTVDatosPersonal();
                                        	 ArrayList<VW_Datos_Personal> listadatos = new ArrayList<VW_Datos_Personal> ();
                                        	 listadatos = dtvdp.listarDatosPersonal();
                                        	 int cont = 1;
                                        	 for (VW_Datos_Personal dp : listadatos) {
                                        		 
                                            %>
                                            <div class="col-12 col-md-4" id="personal">
                                            	<div id="sidenav-3" class="text-center card-box border">
													<div class="member-card pt-2 pb-2">
														<div class="thumb-lg member-thumb mx-auto">
															<img
																src="../<%=dp.getImagen()==null?"dist/imagen/user.png":dp.getImagen()%>"
																class="rounded-circle img-thumbnail"
																alt="foto">
														</div>
														<div class="">
															<h4 class="sidenav-link"><%=dp.getNombre()%></h4>
															<div class="text-muted">
																<%=dp.getCorreo()%>
															</div>
															<p class="text-muted">
																Teléfono: <%=dp.getTelefono()%>
															</p>
															<p class="text-muted">
																Usuario <span>| </span><span><a
																	class="text-pink"><%=dp.getUsuario() %></a></span>
															</p>
															
														</div>
														<div tabindex="-1" class="dropdown-divider"></div>
														<div class="mt-1">
															<div class="row">
																<div class="col-6">
																	<div class="mt-3">
																	<%listExp = dtved.listarExpedienteSegunDocente(dp.getId_personal());%>
																		<h4><%=listExp.size()%></h4>
																		<p class="mb-0 text-muted">Expedientes asignados</p>
																	</div>
																</div>
																<div class="col-6">
																	<div class="mt-3">
																	<%listPermisos = dtvpe.listarPermisosSegunDocente(dp.getId_personal());%>
																		<h4><%=listPermisos.size()%></h4>
																		<p class="mb-0 text-muted">Permisos expedientes</p>
																	</div>
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
    $(document).ready(function(){
    	  $("#myInput").on("keyup", function() {
    	    var value = $(this).val().toLowerCase();
    	    $("#lista-personal #personal").filter(function() {
    	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    	    });
    	  });
    	});
    
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

    	$("#oculto-div").addClass("d-block");
    </script>

</body>

</html>