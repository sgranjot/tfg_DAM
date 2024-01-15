<%-- 
    Document   : gastosDePropiedad
    Created on : 2 jun. 2023, 23:26:14
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
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gastos de propiedad</title>
        <link rel="icon" type="image/png" href="resources/img/icon.png">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/propiedades.css">
        <script src="resources/js/principal.js"></script>
    </head>

    <body>

        <%
            session = request.getSession();
            Usuario usuarioSession = (Usuario) session.getAttribute("usuarioSession");
            String listGast = (String) request.getAttribute("gastosJson");
            String listTipo = (String) request.getAttribute("tipoGastosJson");
            int idProp = (int) request.getAttribute("idPropiedad");

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
                <h2>Información de los Gastos</h2>
                <hr>
            </div>

            <div class="miTabla">
                <table>
                    <thead>
                        <tr>
                            <th>Tipo</th>
                            <th>Fecha</th>
                            <th>Importe</th>
                            <th>Descripción</th>
                        </tr>
                    </thead>
                    <tbody id="tbody"></tbody>
                    <tfoot class="footer-table">
                        <tr>
                            <td><button id="aniadirGast" class="button" name="añadir" onclick="javascript:aniadirGasto()">añadir</button></td>
                        </tr>
                    </tfoot>
                </table>
            </div>


            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    let gastos = <%=listGast%>;
                    console.log(gastos);
                    let gastosJson = JSON.parse(JSON.stringify(gastos));

                    let tipoGastos = <%=listTipo%>;
                    let tipoGastosJson = JSON.parse(JSON.stringify(tipoGastos));

                    let tbody = document.getElementById("tbody");

                    for (let i = 0; i < gastos.length; i++) {
                        let gasto = gastos[i];

                        let fila = tbody.insertRow();
                        let celdaTipo = fila.insertCell();
                        let celdaFecha = fila.insertCell();
                        let celdaImporte = fila.insertCell();
                        let celdaDescripcion = fila.insertCell();

                        celdaTipo.innerHTML = tipoGastosJson[i];
                        celdaFecha.innerHTML = formatDate(gasto.fecha);
                        celdaImporte.innerHTML = gasto.importe;
                        celdaDescripcion.innerHTML = gasto.descripcion;

                        let idSeleccion = gasto.idGasto;
                        fila.addEventListener("click", function () {
                            window.location.href = "servletMostrarGasto?gastoId=" + idSeleccion;
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


        <form id="aniadirGasto" action="crearGasto.jsp" method="post" style="display: none;" accept-charset="UTF-8">
            <input type="hidden" id="idPropiedad" name="idPropiedad" value="<%=idProp%>">
            <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletAniadirGasto").toString(), "UTF-8")%>">
        </form>

    </body>
</html>
