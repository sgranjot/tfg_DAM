<%-- 
    Document   : mostrarPropiedad
    Created on : 24 may. 2023, 23:20:37
    Author     : sgranjot
--%>

<%@page import="modelos.Propiedad"%>
<%@page import="servicios.implementacion.ServicioPropiedad"%>
<%@page import="servicios.implementacion.ServicioMensualidad"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.annotation.AnnotationConfigApplicationContext"%>
<%@page import="configuration.jdbc_Template_javaBased.AppConfig"%>
<%@page import="configuration.jdbc_Template_javaBased.AppConfig"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mostrar propiedad</title>
        <link rel="icon" type="image/png" href="resources/img/icon.png">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/propiedades.css">

    </head>

    <body>

        <%
            session = request.getSession();
            Usuario usuarioSession = (Usuario) session.getAttribute("usuarioSession");
            String tipo = (String) request.getAttribute("tipo");
            StringBuilder sb = new StringBuilder(tipo);
            String plaza = (String) request.getAttribute("plaza");
            int idPropiedad = (int) request.getAttribute("idPropiedad");

            ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
            ServicioMensualidad servicioMensualidad = (ServicioMensualidad) contenedor.getBean("servicioMensualidad");
            int mensualidadesPendientes = servicioMensualidad.getMensualidadesDeUsuarioPendientes(usuarioSession);

            ServicioPropiedad servicioPropiedad = (ServicioPropiedad) contenedor.getBean("servicioPropiedad");
            Propiedad propiedad = servicioPropiedad.getPropiedad(idPropiedad);
            boolean libre;
            libre = propiedad.isLibre();
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
                <h2>Información de la Propiedad</h2>
                <hr>
            </div>



            <table class="tablaMostrarPropiedad">
                <thead>
                    <tr>
                        <th id="plaza"><%

                            if (tipo.equalsIgnoreCase("garaje")) {
                                sb.append(" plaza: ");
                                sb.append(plaza);
                            }
                            %>
                            <%=sb.toString()%>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><b>Calle: </b></td><td id="calle">${calle}</td>
                        <td><b>Número: </b></td><td id="numero">${numero}</td>
                        <td><b>Piso: </b></td><td id="piso">${piso}</td>
                        <td><b>Puerta: </b></td><td id="puerta">${puerta}</td>
                    </tr>
                    <tr>
                        <td><b>Población: </b></td><td id="poblacion">${poblacion}</td>
                        <td><b>Código Postal: </b></td><td id="codigoPostal">${codigoPostal}</td>
                    </tr>
                    <tr>
                        <td><b>Provincia: </b></td><td id="provincia">${provincia}</td>
                        <td><b>País: </b></td><td id="pais">${pais}</td>
                    </tr>
                </tbody>
                <tfoot class="footer-table">
                    <tr>
                        <td><button id="actualizarProp" onclick="javascript:actualizarPropiedad()" class="button" name="actualizar">actualizar</button></td>
                        <td><button id="contrato" onclick="javascript:crearContrato()" class="button" name="contrato">contrato</button></td>
                        <td><button id="eliminarPrp" onclick="javascript:eliminarPropiedad()" class="button" name="eliminar">eliminar</button></td>
                    </tr>
                </tfoot>
            </table>

            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    let libre = <%=libre%>;
                    let boton = document.getElementById("contrato");
                    if (libre === false) {
                        boton.disabled = true;
                    } else {
                        boton.disabled = false;
                    }
                });
            </script>

        </div>


        <!--menu lateral-->
        <%@ include file="menu.jsp" %>

        <!--=========  RUTAS  =============-->
        <%@ include file="rutas.jsp" %>


        <form id="crearContrato" action="crearContrato.jsp" method="post" style="display: none;" accept-charset="UTF-8">
            <input type="hidden" id="idPropiedad" name="idPropiedad" value=<%=idPropiedad%>>
            <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletCrearContrato").toString(), "UTF-8")%>">
        </form>


        <script type="text/javascript" src="resources/js/principal.js"></script>

    </body>
</html>
