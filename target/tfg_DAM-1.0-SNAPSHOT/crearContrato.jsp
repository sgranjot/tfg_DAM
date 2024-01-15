<%-- 
    Document   : crearContrato
    Created on : 31 may. 2023, 23:17:15
    Author     : sgranjot
--%>

<%@page import="servicios.implementacion.ServicioMensualidad"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="modelos.Arrendatario"%>
<%@page import="java.util.List"%>
<%@page import="servicios.implementacion.ServicioArrendatario"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.context.annotation.AnnotationConfigApplicationContext"%>
<%@page import="org.springframework.context.annotation.AnnotationConfigApplicationContext"%>
<%@page import="configuration.jdbc_Template_javaBased.AppConfig"%>
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
        <title>Crear Contrato</title>
        <link rel="icon" type="image/png" href="resources/img/icon.png">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/propiedades.css">
    </head>

    <body>

        <%
            session = request.getSession();
            Usuario usuarioSession = (Usuario) session.getAttribute("usuarioSession");
            ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
            ServicioArrendatario servicioArrendatario = (ServicioArrendatario) contenedor.getBean("servicioArrendatario");
            List<Arrendatario> arrendatarios = servicioArrendatario.getArrendatariosDeUsuario(usuarioSession);
            Gson gson = new Gson();
            String arrendatariosJson = gson.toJson(arrendatarios);
            int idPropiedad = Integer.valueOf(request.getParameter("idPropiedad"));

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
                <h2>Crear un contrato</h2>
                <hr>
            </div>


            <!--============FORMULARIO CREAR CONTRATOS===========0-->
            <div id="miDivForm">
                <form id="miFormulario" action="servletCrearContrato" method="post" accept-charset="UTF-8">
                    <label for="fechaInicio">Fecha de inicio:</label>
                    <input type="date" id="fechaInicio" name="fechaInicio" required><br><br>

                    <label for="fechaFin">Fecha de fin:</label>
                    <input type="date" id="fechaFin" name="fechaFin" required><br><br>

                    <label for="fianza">Fianza:</label>
                    <input type="number" id="fianza" name="fianza" min="0" required><br><br>

                    <label for="mensualidad">Mensualidad:</label>
                    <input type="number" id="mensualidad" name="mensualidad" min="0" required><br><br>

                    <label for="arrendatario">Arrendatario:</label>
                    <select id="arrendatario" name="arrendatario" required>
                        <option value="" disabled selected>Selecciona un arrendatario o añadalo en la sección arrendatarios</option>
                    </select><br>
                    <input type="hidden" value="<%=idPropiedad%>" name="idPropiedad">
                    <input type="submit" value="crear">
                </form>
            </div>
            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    let arrendatarios = JSON.parse('<%= arrendatariosJson%>');
                    let selectArrendatario = document.getElementById('arrendatario');

                    for (var i = 0; i < arrendatarios.length; i++) {
                        let option = document.createElement('option');
                        option.value = arrendatarios[i].idArrendatario;
                        option.textContent = arrendatarios[i].nombre + ' ' + arrendatarios[i].apellidos;
                        selectArrendatario.appendChild(option);
                    }
                });
            </script>

        </div>


        <!--=========MENU LATERAL===========-->
        <%@ include file="menu.jsp" %>


        <!--=========  RUTAS  =============-->
        <%@ include file="rutas.jsp" %>


        <script src="resources/js/principal.js"></script>

    </body>
</html>
