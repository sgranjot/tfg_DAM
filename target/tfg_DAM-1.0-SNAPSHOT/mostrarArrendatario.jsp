<%-- 
    Document   : mostrarArrendatario
    Created on : 30 may. 2023, 9:13:18
    Author     : sgranjot
--%>

<%@page import="servicios.implementacion.ServicioMensualidad"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.annotation.AnnotationConfigApplicationContext"%>
<%@page import="org.springframework.context.annotation.AnnotationConfigApplicationContext"%>
<%@page import="configuration.jdbc_Template_javaBased.AppConfig"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mostrar arrendatario</title>
        <link rel="icon" type="image/png" href="resources/img/icon.png">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/propiedades.css">
    </head>

    <body>

        <%
            session = request.getSession();
            Usuario usuarioSession = (Usuario) session.getAttribute("usuarioSession");

            ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
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


            <!--Tabla propiedades-->
            <div class="info">
                <h2>Información del Arrendatario</h2>
                <hr>
            </div>



            <table class="tablaMostrarPropiedad">
                <thead>
                    <tr>
                        <th>Arrendatario</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><b>Nombre: </b></td><td id="nombre">${nombre}</td>
                        <td><b>Apellidos: </b></td><td id="apellidos">${apellidos}</td>      
                    </tr>
                    <tr>
                        <td><b>DNI: </b></td><td id="dni">${dni}</td>
                        <td><b>Telefono: </b></td><td id="telefono">${telefono}</td>
                        <td><b>Correo electrónico: </b></td><td id="email">${email}</td>
                    </tr>
                </tbody>
                <tfoot class="footer-table">
                    <tr>
                        <td><button id="actualizarArrend" onclick="javascript:actualizarArrendatario()" class="button" name="actualizar">actualizar</button></td>
                        <td><button id="eliminarArrend" onclick="javascript:eliminarArrendatario()" class="button" name="eliminar">eliminar</button></td>
                    </tr>
                </tfoot>
            </table>

        </div>

        <!--menu lateral-->
        <%@ include file="menu.jsp" %>

        <!--=========  RUTAS  =============-->
        <%@ include file="rutas.jsp" %>


        <script type="text/javascript" src="resources/js/principal.js"></script> 

    </body>
</html>
