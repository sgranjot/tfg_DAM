<%-- 
    Document   : arrendatarios
    Created on : 28 may. 2023, 20:30:18
    Author     : sgranjot
--%>

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
        <title>Arrendatarios</title>
        <link rel="icon" type="image/png" href="resources/img/icon.png">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/propiedades.css">
        <script src="resources/js/principal.js"></script>
    </head>

    <body>

        <%
            session = request.getSession();
            Usuario usuarioSession = (Usuario) session.getAttribute("usuarioSession");
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


            <!--Tabla arrendatarios-->
            <div class="info">
                <h2>Información de los Arrendatarios</h2>
                <hr>
            </div>

            <div class="miTabla">
                <table>
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Apellidos</th>
                            <th>DNI</th>
                            <th>Telefono</th>
                            <th>Correo electrónico</th>
                        </tr>
                    </thead>
                    <tbody id="tbody"></tbody>
                    <tfoot class="footer-table">
                        <tr>
                            <td><button id="aniadirArrend" onclick="aniadirArrendatario()" class="button" name="añadir" >añadir</button></td>
                        </tr>
                    </tfoot>
                </table>
            </div>


            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    let arrendatarios = <%=listArrend%>;
                    let arrendatariosJson = JSON.parse(JSON.stringify(arrendatarios));

                    let tbody = document.getElementById("tbody");

                    for (let i = 0; i < arrendatarios.length; i++) {
                        let arrendatario = arrendatarios[i];

                        let fila = tbody.insertRow();
                        let celdaNombre = fila.insertCell();
                        let celdaApellidos = fila.insertCell();
                        let celdaDNI = fila.insertCell();
                        let celdaTelefono = fila.insertCell();
                        let celdaCorreo = fila.insertCell();

                        celdaNombre.innerHTML = arrendatario.nombre;
                        celdaApellidos.innerHTML = arrendatario.apellidos;
                        celdaDNI.innerHTML = arrendatario.dni;
                        celdaTelefono.innerHTML = arrendatario.telefono;
                        celdaCorreo.innerHTML = arrendatario.email;

                        let idSeleccion = arrendatario.idArrendatario;
                        fila.addEventListener("click", function () {
                            window.location.href = "servletMostrarArrendatario?arrendatarioId=" + idSeleccion;
                        });

                    }
                });


            </script>

        </div>

        <!--menu lateral-->
        <%@ include file="menu.jsp" %>


        <!--=========  RUTAS  =============-->
        <%@ include file="rutas.jsp" %>

    </body>
</html>
