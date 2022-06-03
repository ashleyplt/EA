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
.text-blue {
    color: #28b4c9!important;
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
    <title>Actividad</title>
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
<link rel="stylesheet" href="../dist/sweetalert2.min.css">


<script src="../dist/sweetalert2.all.min.js"></script> 

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
	String estadoAgenda = (String) usuario.getAttribute("estado_agenda");
	if (estadoAgenda == null || estadoAgenda == "") {
	%>
	<%
		} else {
	if (estadoAgenda == "error") {
		session.setAttribute("estado_agenda", "");
	%><script>
	
	 
	   Swal.fire(
			   '¡ Guarde en el carrito al menos 4 actividades!',
			   'De click para finalizar!',
			   'error'
			 ) 
 
	</script>
	<%
	}
		}
%>
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
                                    Información de todas las actividades dentro del sistema
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <iframe id="iframe" name="iframe" style="display:none;"></iframe>
                    
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header">
                                Información
                                <div class="input-group rounded mt-3 mb-3" style="position:relative;right:-68%;">
								</div>
								<div style="margin-left:auto">
									<button type="button" class="btn btn-secondary btn-sm" onclick="window.location.href='crear-actividad.jsp'">
				          				<i class="fas fa-plus"></i>
				          				Crear nueva actividad
				          			</button>
			          			</div> 
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                        <div class="row" id="lista-actividad">	
                                        <%
                                        //Actividades
	                                     DTVActividad dtva = new DTVActividad();
	        			                 ArrayList<VW_Actividad> listAct;
                                       	 listAct = dtva.listarHistorico();
                                       	 int cont = 1;
                                       	 
                                       	 //Valoracion de Actividades
                                       	 DTValoracionActividad dtvaa = new DTValoracionActividad();
                                       	 ValoracionActividad va = new ValoracionActividad();
                                       	 for (VW_Actividad a : listAct) {
                                        	int valor = dtvaa.obtenerValoracion(a.getId_actividad());
                                           %>
                                            <div class="col-12 col-md-4 " id="actividad">
                                            	<div id="sidenav-3" class="text-center card-box border">
													<div class="member-card pt-2 pb-2">
														<div class="thumb-lg member-thumb mx-auto">
															<img src="../dist/assets/images/actividades.png"
																class="rounded-circle img-thumbnail"
																alt="foto">
														</div>
														<div>
														<h4 class="sidenav-link"><a href="vista-det-actividad.jsp?id_actividad=<%=a.getId_actividad()%>"><%=a.getNombre()%></a></h4>									
															<div class="text-muted">
																<%=a.getDescripcion()%>
															</div>
															<p class="text-muted">
																Tipo: <%=a.getTipo()%>
															</p>
															<p class="text-muted">
																Modalidad <span>| </span><span><a
																	class="text-pink"><%=a.getModalidad() %></a></span>
																	<span>| </span> <a class="text-blue"><%=a.getEvaluacion()%></a>
															</p>
															<p class="text-muted">
															<span title="Editar"> <a
															href="editar-actividad.jsp?id_actividad=<%=a.getId_actividad()%>">
															<i class="fas fa-edit"></i>
															</a>
															</span>	 
															</p>
														</div>
														<div tabindex="-1" class="dropdown-divider"></div>
														<div class="mt-2 mb-5">
														
														<%for(int i = 0; i < valor; i++){ %>
														<a  class="fa fa-star" style=" color: orange;"></a>
														<%} %>
														<p> </p>
														<a href="valorar-actividad.jsp?id_actividad=<%=a.getId_actividad()%>"> Valorar Actividad </a>
														<button type="button" class="btn btn-primary btn-sm" onclick="SAlertagregar('<%=a.getId_actividad()%>','<%=a.getNombre()%>')"  >
														Agregar
														
														 </button>	
														</div>							 
														
														
														
														
													</div>
												</div>
											</div>
               								<%
                                        	 }
                                            %>
											</div>
											<script>
											
											function agregar_carrito() {
 
												 
												document.form.target = "iframe";
						                        document.form.action = "../SL_Carrito_Actividad";
						                        document.form.submit();
												
											}
											
											
											
											
											   function SAlertagregar(id, nombre){
										    	   
										    	   Swal.fire({
										    		   title: 'Añade elementos a tu carrito de actividades',   html:
										    		     '<form name=\"form\" method=\"post\" class=\"container\"' 
										    		     + '<div class=\" container col-md-12\">'  
										    		     + '<label>¿Estas Seguro de añadir la actividad?</label>' 
														 + '<label class=\"text-center mt-2\">'+ nombre +'</label>' 
														 + '<input type=\"hidden\" id=\"id\" name=\"id\" value=\"'+ id + '\">'
										    		     + '</div>'
										    		     + '</form>',
										    		   inputAttributes: {
										    		     autocapitalize: 'off'
										    		   },
										    		   showCancelButton: true,
										    		   confirmButtonText: 'Agregar',
										    		   showLoaderOnConfirm: true,
										    		   preConfirm: (login) => {
										    			   agregar_carrito()
										    		   },
										    		   allowOutsideClick: () => !Swal.isLoading()
										    		 }).then((result) => {
										    		   if (result.isConfirmed) {
										    		     Swal.fire({
										    		       title: `${result.value.login}Se ha agregado al carrito exitosamente`
										    		     })
										    		   }
										    		 })  	  
										       }
										    </script> 
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
    	    $("#lista-actividad #actividad").filter(function() {
    	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    	    });
    	  });
    	});

	$("#oculto-div").addClass("d-block");
    </script>

</body>

</html>