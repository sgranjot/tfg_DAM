<%-- 
    Document   : crearPropiedad
    Created on : 23 may. 2023, 18:15:09
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
        <title>Crear Propiedades</title>
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
                <h2>Añadir Propiedad</h2>
                <hr>
            </div>


            <!--============FORMULARIO CREAR PROPIEDAD===========0-->
            <div id="miDivForm">
                <form id="miFormulario" action="servletAniadirPropiedad" method="post" accept-charset="UTF-8">
                    <label for="calle">Calle:</label>
                    <input type="text" id="calle" name="calle" required>

                    <label for="numero">Número:</label>
                    <input type="text" id="numero" name="numero" required><br>

                    <label for="piso">Piso:</label>
                    <input type="text" id="piso" name="piso">

                    <label for="puerta">Puerta:</label>
                    <input type="text" id="puerta" name="puerta"><br>

                    <label for="codigoPostal">Código Postal:</label>
                    <input type="text" id="codigoPostal" name="codigoPostal" required>

                    <label for="poblacion">Población:</label>
                    <input type="text" id="poblacion" name="poblacion" required><br>

                    <label for="provincia">Provincia:</label>
                    <input type="text" id="provincia" name="provincia" required>

                    <label for="pais">País:</label>
                    <input type="text" id="pais" name="pais" required><br>

                    <label for="tipoPropiedad">Tipo de propiedad:</label>
                    <select id="tipo-propiedad" name="tipoPropiedad" onchange="mostrarCampoPlaza()">
                        <option value="vivienda">Vivienda</option>
                        <option value="local">Local</option>
                        <option value="garaje">Garaje</option>
                        <option value="oficina">Oficina</option>
                        <option value="nave">Nave</option>
                    </select>

                    <div id="campo-plaza" style="display: none;">
                        <label for="numero-plaza">Nº Plaza:</label>
                        <input type="text" id="numero-plaza" name="numPlaza">
                    </div><br>

                    <label for="libre">Libre:</label>
                    <input type="radio" id="libreSi" name="libre" value="si" checked>
                    <label for="libreSi">Si</label>
                    <input type="radio" id="libreNo" name="libre" value="no">
                    <label for="libreNo">No</label><br>

                    <input type="submit" value="añadir">
                </form>
            </div>
            <script>
                function mostrarCampoPlaza() {
                    var tipoVivienda = document.getElementById("tipo-propiedad").value;
                    var campoPlaza = document.getElementById("campo-plaza");

                    if (tipoVivienda === "garaje") {
                        campoPlaza.style.display = "block";
                    } else {
                        campoPlaza.style.display = "none";
                    }
                }
            </script>

        </div>

        <!--=========MENU LATERAL===========-->
        <%@ include file="menu.jsp" %>


        <!--=========  RUTAS  =============-->
        <%@ include file="rutas.jsp" %>


        <script src="resources/js/principal.js"></script>

    </body>

</html>