<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="vistas.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="datos.*"%>
<%@page import="entidades.*"%>

<%
ArrayList<Cargo> listcargo = new ArrayList<Cargo>();
DTCargo cargo = new DTCargo();
listcargo = cargo.listarCargo();

ArrayList<VW_Coordinacion_Departamento> listCD = new ArrayList<VW_Coordinacion_Departamento>();
DTVCoordinacionDepartamentos cd = new DTVCoordinacionDepartamentos();
listCD = cd.listarCoordinacionDepartamentos();

ArrayList<Rol> listRol = new ArrayList<Rol>();
DTRol rol = new DTRol();
listRol = rol.listarRol();
%>

<!DOCTYPE html>

<html lang="es">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="es">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Editar Personal</title>
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
</head>


<body>

    <!-- <jsp:include page="component/menu.jsp"></jsp:include> -->

    <div class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header">

        <!--comentario include de header y settings -->
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
                                    <i class="fas fa-user-edit icon-gradient bg-mean-fruit">
                                    </i>
                                </div>
                                <div>Administrar Personal
                                    <div class="page-title-subheading"> Actualize los campos que necesite.
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="main-card  card">
                                <div class="card-header">Editar Personal
                                    <div class="btn-actions-pane-right">
                                        <div role="group" class="btn-group-sm btn-group">
                                            <div class="page-title-actions">

                                               <!-- Acciones en el encabezado de la tarjeta -->
                                               
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <form method="post" action="../SLPersonal" class="needs-validation" novalidate>

									<input name="opcion" value=1 style="display: none;"> 
									<input name="id_usuario" value=1 style="display: none;">
										
									<div class="col-md-12 mt-2">
                                        <div class="form-group">
                                            <label>Nombres:</label> 
                                            <input type="text" class="form-control" style="width: 100%;"
                                                name="nombre_personal" id="nombre_personal" placeholder="Escriba su nombre completo" maxlength="60"required>
                                            <div class="valid-feedback"> Nombre válido </div>
                                            <div class="invalid-feedback"> Por favor ingrese un nombre válido</div>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Apellidos:</label> 
                                            <input type="text" class="form-control " style="width: 100%;"
                                                id="apellidos" name="apellidos" placeholder="Escriba su apellido completo"  maxlength="60" required>
                                            <div class="valid-feedback"> Apellidos válido</div>
                                            <div class="invalid-feedback">
                                                Por favor ingrese un apellido válido</div>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Telefono</label> 
                                            <input type="text" class="form-control mask-tel " style="width: 100%;"
                                                id="telefono" name="telefono" placeholder="Ingrese su número telefónico" maxlength="10" class="phone_with_ddd" maxlength="14" required>
                                            <div class="valid-feedback">número válido </div>
                                            <div class="invalid-feedback">Por favor ingrese un número válido</div>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Lugar:</label>
                                            <select class="select2" name="lugar" id="lugar" data-placeholder="Seleccione el lugar donde pertenece" style="width: 100%;" required>
                                                <option value=""> Selecione al menos uno</option>
                                                <%
													for (VW_Coordinacion_Departamento CD : listCD) {
												%>
												<option value="<%=CD.getNombre()%>"><%=CD.getNombre()%></option>
												<%
													}
												%>
                                            </select>
                                            <div class="valid-feedback">Válido</div>
                                            <div class="invalid-feedback">Por favor selecione una opción</div>
                                        </div>
                                    </div>
                                     <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Cargo:</label>
                                            <select class="select2" name="cargo" id="cargo"  data-placeholder="Seleccione su cargo" style="width: 100%;" required>
                                                <option value=""> Selecione al menos uno</option>
                                                <%
													for (Cargo c : listcargo) {
												%>
												<option value="<%=c.getId_cargo()%>"><%=c.getNombre()%></option>
												<%
													}
												%>
                                                
                                            </select>
                                            <div class="valid-feedback">Válido</div>
                                            <div class="invalid-feedback">Por favor selecione una opción</div>
                                        </div>
                                        <div class="form-group">
                                            <label>Rol:</label>
                                            <select class="select2" multiple="multiple" name="rol" id="rol"  data-placeholder="Seleccione un rol" style="width: 100%;" required>
                                                <option value=""> Selecione al menos uno</option>
                                                 <%
													for (Rol r : listRol) {
												%>
												<option value="<%=r.getId_rol()%>"><%=r.getNombre()%></option>
												<%
													}
												%>
                                            </select>
                                            <div class="valid-feedback">Válido</div>
                                            <div class="invalid-feedback">Por favor selecione una opción</div>
                                        </div>
                                    </div>
									<div class="d-block text-center card-footer">
                                        <button type= "submit" class="btn-wide btn btn-success">Actualizar</button>
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

    <script src="../dist/jquery.min.js"></script> 
    <script src="../dist/jquery.mask.js"></script>
	<script>
		$('.mask-tel').mask(' 0000-0000');
	</script>
	<!-- Bootstrap 5 -->
    <script src="../dist/plugins/Bootstrap/js/popper.js"></script>
    <script src="../dist/plugins/Bootstrap/js/bootstrap.min.js"></script>

    <!-- Jquery -->
    <script type="text/javascript" src="../dist/plugins/jquery/jquery.min.js"></script>

    <!-- DataPicker -->
    <script type="text/javascript" src="../dist/plugins/DatePicker/moment.min.js"></script>
    <script type="text/javascript" src="../dist/plugins/DatePicker/daterangepicker.min.js"></script>
    <!--Scripts-->
    <!-- Selectt 2 -->
    <script src="../dist/plugins/select2/js/select2.full.min.js"></script>

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
 