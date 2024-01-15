<%-- 
    Document   : actualizarArrendatario
    Created on : 30 may. 2023, 10:05:16
    Author     : sgranjot
--%>

<%@page import="servicios.implementacion.ServicioMensualidad"%>
<%@page import="servicios.implementacion.ServicioArrendatario"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.annotation.AnnotationConfigApplicationContext"%>
<%@page import="configuration.jdbc_Template_javaBased.AppConfig"%>
<%@page import="configuration.jdbc_Template_javaBased.AppConfig"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="modelos.Usuario"%>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page  language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Actualizar Arrendatario</title>
        <link rel="icon" type="image/png" href="resources/img/icon.png">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/propiedades.css">
    </head>
    <body>

        <%
            session = request.getSession();
            Usuario usuarioSession = (Usuario) session.getAttribute("usuarioSession");
            String nombre = (String) request.getAttribute("nombre");
            String apellidos = (String) request.getAttribute("apellidos");
            String dni = (String) request.getAttribute("dni");
            String telefono = (String) request.getAttribute("telefono");
            String email = (String) request.getAttribute("email");
            int idUsuario = usuarioSession.getIdUsuario();

            ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
            ServicioArrendatario servicioArrendatario = (ServicioArrendatario) contenedor.getBean("servicioArrendatario");
            int idArrendatario = servicioArrendatario.getId(idUsuario, nombre, apellidos, dni, telefono, email);
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
                <h2>Actualizar Arrendatario</h2>
                <hr>
            </div>


            <!--============FORMULARIO ACTIUALIZAR ARRENDATARIOS===========0-->
            <div id="miDivForm">
                <form id="miFormulario" action="servletConfirmarActualizarArrendatario" method="post" accept-charset="UTF-8">
                    <label for="nombre">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" value="<%=nombre%>" required>

                    <label for="apellidos">Apellidos:</label>
                    <input type="text" id="apellidos" name="apellidos" value="<%=apellidos%>" required><br>

                    <label for="dni">DNI:</label>
                    <input type="text" id="dni" name="dni" value="<%=dni%>" required>

                    <label for="telefono">Teléfono:</label>
                    <input type="text" id="telefono" name="telefono" value="<%=telefono%>" required><br>

                    <label for="email">Correo electrónico:</label>
                    <input type="text" id="email" name="email" value="<%=email%>" required>
                    <input type="hidden" name="idArrendatario" value="<%=idArrendatario%>">
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
