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
    height: 25%;
    width: 25%;
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
    <link rel="stylesheet"
	href="../dist/plugins/sweetalert2/dist/sweetalert2.min.css">

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
                                    Información de completa de cada las actividades
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-20">
                            <div class="card">
                                <div class="card-header">
                                Información
                                <div class="input-group rounded mt-3 mb-3" style="position:relative;right:-68%;">
								</div>
								<div style="margin-left:auto">
									<button type="button" class="btn btn-secondary btn-sm" onclick="window.location.href='crear-actividad.jsp'">
				          				<i class="fas fa-plus"></i>
				          				Crear una nueva edición
				          			</button>
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
                                       	 VW_ValoracionActividad vva = new VW_ValoracionActividad();
                                       	 vva = dtvaa.obtenerDatosValoracion(a.getId_actividad());
                                       	 int valor = dtvaa.obtenerValoracion(a.getId_actividad());
                                       	 int ida = a.getId_actividad();
                                           %>
                                            <div class="col-12 col-md-6" id="actividad">
                                            	<div id="sidenav-3" class="text-center card-box border">
													<div class="member-card pt-5 pb-2">
														<div class="thumb-lg member-thumb mx-auto">
															<img src="../dist/assets/images/actividades.png"
																class="rounded-circle img-thumbnail"
																alt="foto">
														</div>
														<div class="">
															<h4 class="sidenav-link" style="font-weight:bold; font-size:200%"> <%=a.getNombre()%></h4>
															<div class="text-muted">
																<p style="font-size:100%"> <%=a.getDescripcion()%></p>
															</div>
															<p class="text-muted" style="font-weight:bold; font-size:100%">
																Tipo: | <a class="text-green"><%= a.getTipo()%></a>
															</p>
															<p class="text-muted" style="font-weight:bold;">
																Modalidad <span>| </span><span><a
																	class="text-pink"><%=a.getModalidad()%></a></span>
															</p>
															<p class="text-muted" style="font-weight:bold;">
																Tipo de Evaluación: <span>| </span><a
																	class="text-blue"><%=a.getEvaluacion()%></a>
															</p>
															<p class="text-muted">
															
															</p>
														</div>
														<div tabindex="-1" class="dropdown-divider"></div>
														<%for(int i = 0; i < valor; i++){ %>
														<a  class="fa fa-star" style=" color: orange;"></a>
														<%} %>
														<p></p>
														<div> 
														<h3 style="font-size:140%">Creado por: </h3>
														<p><%=a.getCreador()%></p>
														</div>
														<div class="mb-2" style="margin-left:80%; font-weight:bold; color:#bf1c1c">Horas: <%=a.getCant_horas()%></div>
														</div>
												</div>												
											</div>
											<div class="col-12 col-md-6">
											
                                            	<div id="sidenav-3" class="text-left card-box-det">
                                            	<h4 class="sidenav-link" style="font-weight:bold; font-size:200%">Valoraciones:</h4> 
													<div class="member-card pt-1 pb-1">
														<div style="font-weight:bold; font-size:150%;"> <label style="margin-top:5%"><%=vva.getDocente()==null?"Ningun Docente a valorado esta actividad":vva.getDocente()%></label>
															
																<div class="pt-1 pb-1 mt-5">
																<%for(int i = 0; i < valor; i++){ %>
																<a  class="fa fa-star" style=" color: orange;"></a>
																<%} %>
																<img src="../<%=vva.getImagen()==null?"dist/imagen/user.png":vva.getImagen()%>"
																class="rounded-circle thumb-lg2"
																alt="foto" style="margin-left:75%;margin-top:-20%">
																</div>
																
																
																
														</div>														
															<div class="text-muted">
																<p style="font-size:100%"> <%=vva.getComentario()==null?"":vva.getComentario()%></p>
															</div>
													</div>
												</div>
												<div>
													<a href="vista-valoracionActividad.jsp?id_actividad=<%=a.getId_actividad()%>"> Ver todas las valoraciones de esta actividad</a>
												 
												</div>											
											</div>
											<%
											DTVMaterialesActividad dtvma = new DTVMaterialesActividad();
											//VW_MaterialesActividad vma = new VW_MaterialesActividad();
											ArrayList<VW_MaterialesActividad> listMaterialesAct;
											listMaterialesAct = dtvma.listarActMateriales(a.getId_actividad());
											%>
											<div style="margin-left:auto">
												<button type="button" class="btn btn-secondary btn-sm" onclick="material(<%=a.getId_actividad()%>)">
							          				<i class="fas fa-plus"></i>
							          				Agregar Materiales
							          			</button>
						          			</div> 
											<div class="col-12 col-md-12">
											
                                            	<div id="sidenav-3" class="text-left card-box-det"> Materiales:
													<div class="member-card pt-5 pb-2">
														<div class="thumb-lg member-thumb mx-auto">
															
														</div>
														<%
														for (VW_MaterialesActividad m : listMaterialesAct) {
														%>
														<div class="">
															<h4 class="sidenav-link" style="font-weight:bold; font-size:200%"></h4>
															<div class="text-muted">
															
															<p style="font-size:100%"> <%=m.getMaterial()%></p>
															</div>
															<p class="text-muted"><%=m.getDetalle_material() %>
															</p>
															<p class="text-muted"><a href="<%=m.getEnlace()%>"><i class="fas fa-link"></i></a>
															</p>
															<p class="text-muted">
															</p>
															<p class="text-muted">
															
															</p>
														</div>
														<%
														} 
														%> 
														<div tabindex="-1" class="dropdown-divider"></div>
														
														</div>
												</div>												
											</div>											                								
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
    
    <!-- JS de Agregar Material con el Alert mamalon -->
    <script src="../dist/plugins/sweetalert2/dist/sweetalert2.min.js"></script>
    
    <script>
    function aMaterial(){

    	var texto = $("#id").val();
    	document.formulario.action = "../SLMaterial";
    	document.formulario.submit();	
    }
    </script>
    
    
    <script>
    function material(id){
 	   
 	   Swal.fire({
 		   title: 'Añade materiales para tu actividad',   html:
 		     '<form name=\"formulario\" method=\"post\" class=\"container\"' 
 		     + '<div class=\"col-md-12\">' 
 		     + '<div class="form-group">' 
 		     + '<label style="position:relative; left:-40%;">Nombre: </label>'
 		     + '<input type="text" class="form-control " style="width: 100%;"'
 		     + 'id="nMaterial" name="nMaterial" placeholder="Escriba un nombre para el Material" required>'
 		     + '<input type=\"hidden\" name=\"id\" id=\"id\" value =\"' + id + '\">'
 		     + '<div class="valid-feedback">'
 		     + 'Nombre válido'
 		     + '</div>'
 		     + '<div class="invalid-feedback">'
 		     + 'Por favor ingrese un nombre válido'
 		     + '</div>'
 		     + '</div>'
 		     + '</div>'
 		     + '<div class=\"col-md-12\">' 
 		     + '<div class="form-group">' 
 		     + '<label style="position:relative; left:-40%;">Descripción: </label>'
 		     + '<input type="text" class="form-control " style="width: 100%;"'
 		     + 'id="dMaterial" name="dMaterial" placeholder="Escriba una descripción para el Material" required>'
 		     + '<div class="valid-feedback">'
 		     + 'Descripción válida '
 		     + '</div>'
 		     + '<div class="invalid-feedback">'
 		     + 'Por favor ingrese una descripción válida'
 		     + '</div>'
 		     + '</div>'
 		     + '</div>'
 		     + '<div class=\"col-md-12\">' 
 		     + '<div class="form-group">' 
 		     + '<label style="position:relative; left:-48%;">URL: </label>'
 		     + '<input type="text" class="form-control " style="width: 100%;"'
 		     + 'id="urlMaterial" name="urlMaterial" placeholder="Escriba una url para el Material" required>'
 		     + '<div class="valid-feedback">'
 		     + 'Url válido '
 		     + '</div>'
 		     + '<div class="invalid-feedback">'
 		     + 'Por favor ingrese una url válida ' 
 		     + '</div>'
 		     + '</div>'
 		     + '</div>'
 		     + '</form>',
 		   inputAttributes: {
 		     autocapitalize: 'off'
 		   },
 		   showCancelButton: true,
 		   confirmButtonText: 'Guardar',
 		   showLoaderOnConfirm: true,
 		   preConfirm: (login) => {
 			   aMaterial()
 		   },
 		   allowOutsideClick: () => !Swal.isLoading()
 		 }).then((result) => {
 		   if (result.isConfirmed) {
 		     Swal.fire({
 		       title: `${result.value.login}Se ha Guardado en la Base de datos`
 		     })
 		   }
 		 })  	  
    }  
    </script>

   
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