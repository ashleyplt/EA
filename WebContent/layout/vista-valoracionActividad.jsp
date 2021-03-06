<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" 
    import="entidades.*,datos.*, java.util.*, vistas.*"%>
    
<%
    response.setHeader("Cache-Control","no-cache,post-check=0,pre-check=0");
	response.setHeader("Pragma","no-cache");
	response.setHeader("Expires","Thu,01 Dec 1994 16:00:00 GMT");
	response.setDateHeader("Expires", -1);
%>	
	
    
<!DOCTYPE html>

<html lang="es">

<style>

body{
    background:#DCDCDC;
    margin-top:20px;
}
.card-box {
    padding: 30px;
    border-radius: 3px;
    margin-bottom: 2%;
    background-color: #fff;
}
.card-box-det {
    padding: 30px;
    border-radius: 3px;
    margin-bottom: 2%;
    background-color: #fff;
}
.social-links li a {
    border-radius: 50%;
    color: rgba(121, 121, 121, .8);
    display: inline-block;
    height: 30%;
    line-height: 27%;
    border: 2px solid rgba(121, 121, 121, .5);
    text-align: center;
    width: 100px;
}

.social-links li a:hover {
    color: #797979;
    border: 2px solid #797979;
}
.thumb-lg {
    height: 45%;
    width: 45%;
}
.thumb-lg2 {
    height: 40%;
    width: 40%;
}
.img-thumbnail {
    padding: .25rem;
    background-color: #fff;
    border: 1px solid #dee2e6;
    border-radius: .25rem;
    max-width: 100%;
    width: 100%; 
    height: 100%;
}
.text-pink {
    color: #ff679b!important;
}
.text-blue {
    color: #28b4c9!important;
}
.text-green {
    color: #42b82e!important;
}
.btn-rounded {
    border-radius: 2em;
}
.text-muted {
    color: #98a6ad!important;
}
h4 {
    line-height: 22%;
    font-size: 60px;
}
</style>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Detalle Actividad</title>
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

            <div class="app-main__outer">
                <div class="app-main__inner">
                    <div class="app-page-title">
                        <div class="page-title-wrapper">

                            <!--comentario inicia la card debajo del header-->
                            <div class="page-title-heading">
                                <div class="page-title-icon">
                                    <img src="https://i.imgur.com/IZRXXvB.png"
										style="border-radius: 20px 20px; width: 40px; height: 40px"
										alt="icono.jpg">
                                </div>
                                <div>Actividades
                                    <div class="page-title-subheading">
                                    Informaci??n de completa de cada las actividades
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-20">
                            <div class="card">
                                <div class="card-header">
                                Informaci??n
                                <div class="input-group rounded mt-3 mb-3" style="position:relative;right:auto;">
								</div>
								 
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                        <div class="row">	
                                        <%
                                        String id_actividad = request.getParameter("id_actividad");
                                        //Actividades
	                                     DTVActividad dtva = new DTVActividad();
	        			                 VW_Actividad a = new VW_Actividad();
                                       	 a = dtva.getActividad(Integer.parseInt(id_actividad));
                                       	 int cont = 1;
                                       	 
                                       	 //Valoracion de Actividades
                                       	 DTValoracionActividad dtvaa = new DTValoracionActividad();
                                       	 ValoracionActividad va = new ValoracionActividad();
                                       	 ArrayList<VW_ValoracionActividad> listVAct;
                                       	 listVAct = dtvaa.obtenerDatosValoracionlist(a.getId_actividad());
                                       	 int valor = dtvaa.obtenerValoracion(a.getId_actividad());
                                       	 int ida = a.getId_actividad();

											for(VW_ValoracionActividad vva : listVAct){
											
											%>
											<div>
                                            	<div id="sidenav-3" class="text-center card-box mt-2">
                                            	<h4 class="sidenav-link" style="font-weight:bold; font-size:200%"><%=vva.getDocente()%></h4> 
													<div class="member-card pt-1 pb-1 mt-2">
														<div class="thumb-lg2   col-12">
															<img src="../<%=vva.getImagen()==null?"dist/imagen/user.png":vva.getImagen()%>"
																class="rounded-circle "
																alt="foto" style="height:200px">
																
														</div>
														
														<div class="pt-1 pb-1 mt-2">
																<%for(int i = 0; i < vva.getValor(); i++){ %>
																<a  class="fa fa-star" style=" color: orange;font-size:20px"></a>
																<%} %>
																</div>														
															<div class="text-muted">
																<p style="font-size:20px"> <%=vva.getComentario()%></p>
															</div>
															
														<div tabindex="-1" class="dropdown-divider">
														
																
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


</body>

</html>