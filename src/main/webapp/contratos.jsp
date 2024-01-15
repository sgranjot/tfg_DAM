<%-- 
    Document   : contratos
    Created on : 30 may. 2023, 14:07:29
    Author     : sgranjot
--%>


<%@page import="servicios.implementacion.ServicioMensualidad"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.annotation.AnnotationConfigApplicationContext"%>
<%@page import="configuration.jdbc_Template_javaBased.AppConfig"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="modelos.Usuario"%>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Contratos</title>
        <link rel="icon" type="image/png" href="resources/img/icon.png">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/propiedades.css">
        <script src="resources/js/principal.js"></script>
    </head>
    <body>

        <%
            session = request.getSession();
            Usuario usuarioSession = (Usuario) session.getAttribute("usuarioSession");
            String listContr = (String) request.getAttribute("contratosJson");
            String listProp = (String) request.getAttribute("propiedadesJson");
            String listArrend = (String) request.getAttribute("arrendatariosJson");
            String listVig = (String) request.getAttribute("vigorJson");

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


            <!--Tabla contratos-->
            <div class="info">
                <h2>Información de los Contratos</h2>
                <hr>
            </div>

            <div class="miTabla">
                <table>
                    <thead>
                        <tr>
                            <th>Propiedad</th>
                            <th>Arrendatario</th>
                            <th>Fecha de Inicio</th>
                            <th>Fecha de Fin</th>
                            <th>En vigor</th>
                        </tr>
                    </thead>
                    <tbody id="tbody"></tbody>
                    <tfoot class="footer-table">
                    </tfoot>
                </table>
            </div>


            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    let contratos = <%=listContr%>;
                    let contratosJson = JSON.parse(JSON.stringify(contratos));

                    let propiedades = <%=listProp%>;
                    let propiedadesJson = JSON.parse(JSON.stringify(propiedades));

                    let arrendatarios = <%=listArrend%>;
                    let arrendatariosJson = JSON.parse(JSON.stringify(arrendatarios));

                    let vigores = <%=listVig%>;
                    let vigoresJson = JSON.parse(JSON.stringify(vigores));

                    let tbody = document.getElementById("tbody");

                    for (let i = 0; i < contratosJson.length; i++) {
                        let contrato = contratosJson[i];

                        let fila = tbody.insertRow();
                        let celdaPropiedad = fila.insertCell();
                        let celdaArrendatario = fila.insertCell();
                        let celdaFechaInicio = fila.insertCell();
                        let celdaFechaFin = fila.insertCell();
                        let celdaVigor = fila.insertCell();

                        celdaPropiedad.innerHTML = propiedadesJson[i];
                        celdaArrendatario.innerHTML = arrendatariosJson[i];
                        celdaFechaInicio.innerHTML = formatDate(contrato.fechaInicio);
                        celdaFechaFin.innerHTML = formatDate(contrato.fechaFin);
                        celdaVigor.innerHTML = vigoresJson[i];

                        let idSeleccion = contrato.idContrato;
                        fila.addEventListener("click", function () {
                            window.location.href = "servletMostrarContrato?contratoId=" + idSeleccion;
                        });

                    }
                });
                
                function formatDate(dateString) {
                    const options = {year: 'numeric', month: '2-digit', day: '2-digit'};
                    return new Date(dateString).toLocaleDateString(undefined, options);
                }

            </script>

        </div>

        <!--menu lateral-->
        <%@ include file="menu.jsp" %>


        <!--=========  RUTAS  =============-->
        <%@ include file="rutas.jsp" %>


    </body>
</html>
