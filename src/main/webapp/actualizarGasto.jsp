<%-- 
    Document   : actualizarGasto
    Created on : 3 jun. 2023, 18:05:50
    Author     : sgranjot
--%>

<%@page import="servicios.implementacion.ServicioMensualidad"%>
<%@page import="servicios.implementacion.ServicioGasto"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.annotation.AnnotationConfigApplicationContext"%>
<%@page import="configuration.jdbc_Template_javaBased.AppConfig"%>
<%@page import="modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Actualizar Gasto</title>
        <link rel="icon" type="image/png" href="resources/img/icon.png">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/propiedades.css">

    </head>

    <body>

        <%
            session = request.getSession();
            Usuario usuarioSession = (Usuario) session.getAttribute("usuarioSession");
            int idUsuario = usuarioSession.getIdUsuario();
            String idPropiedad = (String) request.getAttribute("idPropiedad");

            ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
            ServicioGasto servicioGasto = (ServicioGasto) contenedor.getBean("servicioGasto");
            String idGasto = (String) request.getAttribute("idGasto");
            String tipoGasto = (String) request.getAttribute("tipoGasto");
            String fecha = (String) request.getAttribute("fecha");
            String importe = (String) request.getAttribute("importe");
            String descripcion = (String) request.getAttribute("descripcion");
            ServicioMensualidad servicioMensualidad = (ServicioMensualidad) contenedor.getBean("servicioMensualidad");
            int mensualidadesPendientes = servicioMensualidad.getMensualidadesDeUsuarioPendientes(usuarioSession);
        %>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                let mensualidadesPendientes = <%= mensualidadesPendientes%>;
                var pendientesDiv = document.getElementById("pendientesDiv");
                pendientesDiv.innerHTML = "Pendientes <br>" + mensualidadesPendientes;
            });
        </script>

        <!--cuerpo-->
        <div class="content">
            <div class="yellow-box">
                <p id="fecha" style="font-weight: bold;"></p>
            </div>
            <script>
                var fechaActual = new Date();
                var dia = fechaActual.getDate();
                var mes = fechaActual.getMonth() + 1;
                var año = fechaActual.getFullYear();

                var fechaFormateada = dia + "/" + mes + "/" + año;

                document.getElementById("fecha").innerText = fechaFormateada;
            </script>
            <div class="dropdown-menu">
                <nav>
                    <ul>
                        <li class="align-right">
                            <div class="usuario">
                                <a href="#">
                                    <span style="color: #006666; font-weight: bold;"><%= usuarioSession.getNombre()%> &nbsp;</span>
                                    <img src="resources/img/user.png" width="25" alt="usuario">&nbsp;
                                </a>
                            </div>
                            <ul class="sub-menu">
                                <li class="align-right">
                                    <div>
                                        <a href="#" onclick="javascript:cambiarContrasenia();">
                                            <span class="sub-text"><br>Cambiar contraseña</span>
                                        </a>
                                    </div>
                                </li>
                                <li class="align-right">
                                    <div>
                                        <a href="#" onclick="javascript:darBajaUsuario();">
                                            <span class="sub-text">Darse de baja</span>
                                        </a>
                                    </div>
                                </li>
                                <li class="align-right">
                                    <div>
                                        <a href="#" onclick="javascript:cerrarSesion();">
                                            <span class="sub-text">Cerrar sesión</span>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
            <div id="pendientesDiv" class="white-box" style="font-weight: bold;">
            </div>


            <!--TITULO-->
            <div class="info">
                <h2>Actualizar Gasto</h2>
                <hr>
            </div>


            <!--============FORMULARIO ACTIUALIZAR GASTO===========0-->
            <div id="miDivForm">
                <form id="miFormulario" action="servletConfirmarActualizarGasto" method="post" accept-charset="UTF-8">
                    <label for="tipoGasto">Tipo de gasto:</label>
                    <select id="tipoGastoAct" name="tipoGasto" required>
                        <option value="1">Impuesto</option>
                        <option value="2">Comunidad</option>
                        <option value="3">Suministro</option>
                        <option value="4">Incidencia</option>
                        <option value="5">Hipoteca</option>
                    </select><br><br>

                    <label for="fecha">Fecha:</label>
                    <input type="date" id="fechaAct" name="fecha" value="<%=fecha%>" required><br><br><br>

                    <label for="importe">Importe:</label>
                    <input type="number" step="0.01" min="0" id="importeAct" name="importe" value="<%=importe%>" required><br><br>

                    <label for="descripcion">Descripción:</label>
                    <textarea  id="descripcionAct" name="descripcion" rows="6" cols="50" style="resize: none;" placeholder="Escribe tu descripción aqui..."><%=descripcion%></textarea><br>      
                    <br><br><br>
                    <input type="hidden" id="idPropiedadAct" name="idPropiedad" value="<%=idPropiedad%>">
                    <input type="hidden" id="idGastoAct" name="idGasto" value="<%=idGasto%>">
                    <input type="submit" value="actualizar">
                </form>
            </div>

        </div>


        <!--=========MENU LATERAL===========-->
        <%@ include file="menu.jsp" %>


        <!--=========  RUTAS  =============-->
        <%@ include file="rutas.jsp" %>


        <script src="resources/js/principal.js"></script> 

    </body>

</html>
