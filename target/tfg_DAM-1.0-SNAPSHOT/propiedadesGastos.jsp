<%-- 
    Document   : propiedadesGastos
    Created on : 2 jun. 2023, 20:42:54
    Author     : sgranjot
--%>

<%@page import="servicios.implementacion.ServicioMensualidad"%>
<%@page import="modelos.TipoPropiedad"%>
<%@page import="modelos.Direccion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="modelos.Propiedad"%>
<%@page import="servicios.implementacion.ServicioPropiedad"%>
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
        <title>PropiedadesGastos</title>
        <link rel="icon" type="image/png" href="resources/img/icon.png">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/propiedades.css">

    </head>

    <body>


        <%
            session = request.getSession();
            Usuario usuarioSession = (Usuario) session.getAttribute("usuarioSession");
            String listProp = (String) request.getAttribute("propiedadesJson");
            String listDirec = (String) request.getAttribute("direccionesJson");
            String listTipo = (String) request.getAttribute("tipoPropiedadesJson");

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
                <h2>Elige una propiedad</h2>
                <hr>
            </div>

            <div class="miTabla">
                <table>
                    <thead>
                        <tr>
                            <th>Tipo</th>
                            <th>Calle</th>
                            <th>Numero</th>
                            <th>Piso</th>
                            <th>Puerta</th>
                            <th>Población</th>
                            <th>Libre</th>
                        </tr>
                    </thead>
                    <tbody id="tbody"></tbody>
                    <tfoot class="footer-table">

                    </tfoot>
                </table>
            </div>


            <script>
                let propiedades = <%=listProp%>;
                let propiedadesJson = JSON.parse(JSON.stringify(propiedades));

                let direcciones = <%=listDirec%>;
                let direccionesJson = JSON.parse(JSON.stringify(direcciones));

                let tipoPropiedades = <%=listTipo%>;
                let tipoPropiedadesJson = JSON.parse(JSON.stringify(tipoPropiedades));

                let tbody = document.getElementById("tbody");

                for (let i = 0; i < propiedades.length; i++) {
                    let propiedad = propiedades[i];
                    let direccion = direcciones[i];

                    let fila = tbody.insertRow();
                    let celdaTipo = fila.insertCell();
                    let celdaCalle = fila.insertCell();
                    let celdaNum = fila.insertCell();
                    let celdaPiso = fila.insertCell();
                    let celdaPuerta = fila.insertCell();
                    let celdaPoblacion = fila.insertCell();

                    let celdaLibre = fila.insertCell();

                    let libre;
                    if (propiedad.libre === true) {
                        libre = "Si";
                    } else {
                        libre = "No";
                    }

                    celdaTipo.innerHTML = tipoPropiedadesJson[i];
                    celdaCalle.innerHTML = direccion.calle;
                    celdaNum.innerHTML = direccion.numero;
                    celdaPiso.innerHTML = direccion.piso;
                    celdaPuerta.innerHTML = direccion.puerta;
                    celdaPoblacion.innerHTML = direccion.poblacion;
                    celdaLibre.innerHTML = libre;

                    let idSeleccion = propiedad.idPropiedad;
                    fila.addEventListener("click", function () {
                        window.location.href = "servletGastosDePropiedad?propiedadId=" + idSeleccion;
                    });

                }

            </script>

        </div>


        <!--menu lateral-->
        <%@ include file="menu.jsp" %>


        <!--=========  RUTAS  =============-->
        <%@ include file="rutas.jsp" %>


        <script src="resources/js/principal.js"></script>

    </body>

</html>