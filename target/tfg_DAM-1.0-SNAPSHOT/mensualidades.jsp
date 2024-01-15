<%-- 
    Document   : mensualidades
    Created on : 1 jun. 2023, 13:09:45
    Author     : sgranjot
--%>

<%@page import="servicios.implementacion.ServicioMensualidad"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.annotation.AnnotationConfigApplicationContext"%>
<%@page import="configuration.jdbc_Template_javaBased.AppConfig"%>
<%@page import="configuration.jdbc_Template_javaBased.AppConfig"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="modelos.Usuario"%>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Mensualidades</title>
        <link rel="icon" type="image/png" href="resources/img/icon.png">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/propiedades.css">
        <script src="resources/js/principal.js"></script>
    </head>

    <body>

        <%
            session = request.getSession();
            Usuario usuarioSession = (Usuario) session.getAttribute("usuarioSession");
            String listMens = (String) request.getAttribute("mensualidadesJson");
            String listProp = (String) request.getAttribute("propiedadesJson");
            String listArrend = (String) request.getAttribute("arrendatariosJson");

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


            <!--Tabla mensualidades-->
            <div class="info">
                <h2>Información de las Mensualidades</h2>
                <hr>
            </div>

            <div class="miTabla">
                <table>
                    <thead>
                        <tr>
                            <th>Propiedad</th>
                            <th>Arrendatario</th>
                            <th>Fecha</th>
                            <th>Cantidad</th>
                            <th>Pagada</th>
                        </tr>
                    </thead>
                    <tbody id="tbody"></tbody>
                    <tfoot class="footer-table">
                    </tfoot>
                </table>
            </div>

            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    let mensualidades = <%=listMens%>;
                    let mensualidadesJson = JSON.parse(JSON.stringify(mensualidades));

                    let propiedades = <%=listProp%>;
                    let propiedadesJson = JSON.parse(JSON.stringify(propiedades));

                    let arrendatarios = <%=listArrend%>;
                    let arrendatariosJson = JSON.parse(JSON.stringify(arrendatarios));

                    let tbody = document.getElementById("tbody");

                    for (let i = 0; i < mensualidades.length; i++) {
                        let mensualidad = mensualidades[i];

                        let fila = tbody.insertRow();
                        let celdaPropiedad = fila.insertCell();
                        let celdaArrendatario = fila.insertCell();
                        let celdaFecha = fila.insertCell();
                        let celdaCantidad = fila.insertCell();
                        let celdaPagada = fila.insertCell();

                        celdaPropiedad.innerHTML = propiedadesJson[i];
                        celdaArrendatario.innerHTML = arrendatariosJson[i];
                        celdaFecha.innerHTML = mensualidad.fecha;
                        celdaCantidad.innerHTML = mensualidad.cantidad;
                        celdaPagada.innerHTML = '<input id="pagada' + i + '" type="checkbox" name="pagada" data-index="' + i + '" ' + (mensualidad.estado ? 'checked' : '') + '>'
                                + '<input id="idMensualidad' + i + '" type="hidden" value="' + mensualidad.idMensualidad + '">';

                        let checkbox = document.getElementById('pagada' + i);
                        if (checkbox.checked) {
                            checkbox.disabled = true;
                        }
                    }

                });

                document.addEventListener('DOMContentLoaded', function () {
                    let checkboxes = document.querySelectorAll('input[name="pagada"]');
                    checkboxes.forEach(function (checkbox) {
                        checkbox.addEventListener('change', function () {
                            let index = this.getAttribute('data-index');
                            if (this.checked) {
                                this.disabled = true;
                                let idMensualidad = document.getElementById('idMensualidad' + index).value;
                                document.getElementById("idMensualidadH").value = idMensualidad;
                                document.getElementById("pagarMensualidad").submit();
                            }
                        });
                    });
                });
            </script>

        </div>

        <!--menu lateral-->
        <%@ include file="menu.jsp" %>


        <!--=========  RUTAS  =============-->
        <%@ include file="rutas.jsp" %>

    </body>
</html>
