<%-- 
    Document   : mostrarContrato
    Created on : 30 may. 2023, 23:46:23
    Author     : sgranjot
--%>

<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.annotation.AnnotationConfigApplicationContext"%>
<%@page import="configuration.jdbc_Template_javaBased.AppConfig"%>
<%@page import="servicios.implementacion.ServicioMensualidad"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mostrra contrato</title>
        <link rel="icon" type="image/png" href="resources/img/icon.png">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/propiedades.css">
    </head>

    <body>

        <%
            session = request.getSession();
            Usuario usuarioSession = (Usuario) session.getAttribute("usuarioSession");
            String tipo = (String) request.getAttribute("tipo");
            String idContrato = (String) request.getAttribute("idContrato");
            String vigorBot = (String) request.getAttribute("vigor");

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
                <h2>Información del Contrato</h2>
                <hr>
            </div>



            <table class="tablaMostrarPropiedad">
                <thead>
                    <tr>
                        <th id="vigor">
                            Contrato en vigor: ${vigor}
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><b>Tipo de propiedad: </b></td><td id="tipo">${tipo}</td>
                        <td><b>Calle: </b></td><td id="calle">${calle}</td>
                        <td><b>Número: </b></td><td id="numero">${numero}</td>
                        <td><b>Población: </b></td><td id="poblacion">${poblacion}</td>
                    </tr>
                    <tr>   
                        <td><b>Nombre del arrendatario: </b></td><td id="nombre">${nombre}</td>
                        <td><b>Apellidos: </b></td><td id="apellidos">${apellidos}</td>
                    </tr>
                    <tr>
                        <td><b>Fecha de inicio: </b></td><td id="fechaInicio">${fechaInicio}</td>
                        <td><b>Fecha de fin: </b></td><td id="fechaFin">${fechaFin}</td>
                        <td><b>Fianza: </b></td><td id="fianza">${fianza}</td>
                        <td><b>Mensualidad: </b></td><td id="mensualidad">${mensualidad}</td>
                    </tr>
                </tbody>
                <tfoot class="footer-table">
                    <tr>
                        <td><button id="finalizarContr" onclick="javascript:finalizarContrato()" class="button" name="finalizar">finalizar contrato</button></td>
                        <td><button id="eliminarContr" class="button" name="eliminar" onclick="javascript:eliminarContrato()">eliminar</button></td> 
                    </tr>
                </tfoot>

            </table>

        </div>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                let boton = document.getElementById("finalizarContr");
                let vigor = "<%= vigorBot%>";
                if (vigor.toLowerCase() === "si") {
                    boton.disabled = false;
                } else {
                    boton.disabled = true;
                }
            });
        </script>


        <!--menu lateral-->
        <%@ include file="menu.jsp" %>


        <!--=========  RUTAS  =============-->
        <%@ include file="rutas.jsp" %>


        <form id="finalizarContrato" action="servletFinalizarContrato" method="post" style="display: none;" accept-charset="UTF-8">
            <input type="hidden" id="contratoId" name="contratoId" value=<%=idContrato%>>
            <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletFinalizarContrato").toString(), "UTF-8")%>">
        </form>

        <form id="eliminarContrato" action="servletEliminarContrato" method="post" style="display: none;" accept-charset="UTF-8">
            <input type="hidden" id="contratoId" name="contratoId" value=<%=idContrato%>>
            <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletEliminarContrato").toString(), "UTF-8")%>">
        </form>


        <script type="text/javascript" src="resources/js/principal.js"></script>

    </body>
</html>
