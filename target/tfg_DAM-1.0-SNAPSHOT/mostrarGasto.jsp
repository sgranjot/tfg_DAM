<%-- 
    Document   : mostrarGasto
    Created on : 3 jun. 2023, 18:40:23
    Author     : sgranjot
--%>

<%@page import="servicios.implementacion.ServicioMensualidad"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.annotation.AnnotationConfigApplicationContext"%>
<%@page import="configuration.jdbc_Template_javaBased.AppConfig"%>
<%@page import="modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar gasto</title>
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


            <!--Tabla gastos-->
            <div class="info">
                <h2>Información del Gasto</h2>
                <hr>
            </div>



            <table class="tablaMostrarPropiedad">
                <thead>
                    <tr>
                        <th id="tipoGasto">${tipoGasto}</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><b>Fecha: </b></td><td id="fechaG">${fecha}</td>
                        <td><b>Importe: </b></td><td id="importe">${importe}</td>      
                    </tr>
                    <tr>
                        <td><b>Descripcion: </b></td><td id="descripcion">${descripcion}</td>
                    </tr>
                </tbody>
                <tfoot class="footer-table">
                    <tr>
                        <td><button id="actualizarGast" onclick="javascript:actualizarGasto()" class="button" name="actualizar">actualizar</button></td>
                        <td><button id="eliminarGast" onclick="javascript:eliminarGasto()" class="button" name="eliminar">eliminar</button></td>
                    </tr>
                </tfoot>
            </table>

        </div>


        <div>
            <input type="hidden" id="idGasto" value="${idGasto}">
            <input type="hidden" id="idPropiedad" value="${idPropiedad}">
        </div>


        <!--menu lateral-->
        <%@ include file="menu.jsp" %>


        <!--=========  RUTAS  =============-->
        <%@ include file="rutas.jsp" %>


        <script type="text/javascript" src="resources/js/principal.js"></script> 


        <form id="eliminarGasto" action="servletEliminarGasto" method="post" style="display: none;" accept-charset="UTF-8">
            <input type="hidden" id="gastoId" name="gastoId" value='${idGasto}'>
            <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletEliminarContrato").toString(), "UTF-8")%>">
        </form>


    </body>
</html>
