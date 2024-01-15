<%-- 
    Document   : balances
    Created on : 4 jun. 2023, 1:12:15
    Author     : sgranjot
--%>

<%@page import="java.text.DecimalFormat"%>
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
        <title>Balances</title>
        <link rel="icon" type="image/png" href="resources/img/icon.png">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link rel="stylesheet" type="text/css" href="resources/css/propiedades.css">
        <script src="resources/js/principal.js"></script>
    </head>

    <body>

        <%
            session = request.getSession();
            Usuario usuarioSession = (Usuario) session.getAttribute("usuarioSession");
            String listDirec = (String) request.getAttribute("direccionesJson");
            String listIngr = (String) request.getAttribute("ingresosJson");
            String listGast = (String) request.getAttribute("gastosJson");
            int totalIngresos = (int) request.getAttribute("totalIngresos");
            double totalGastos = (double) request.getAttribute("totalGastos");
            double balanceTotal = totalIngresos - totalGastos;
            DecimalFormat df = new DecimalFormat("#.##");
            String balanceTotalString = df.format(balanceTotal);

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
                <h2>Balances de las Propiedades</h2>
                <hr>
            </div>

            <div class="miTabla">
                <table>
                    <thead>
                        <tr>
                            <th>Propiedad</th>
                            <th>Ingresos</th>
                            <th>Gastos</th>
                            <th>Balance</th>
                        </tr>
                    </thead>
                    <tbody id="tbody"></tbody>
                    <tfoot class="footer-table">
                    </tfoot>
                </table>
            </div>

            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    let direcciones = <%=listDirec%>;
                    let direccionesJson = JSON.parse(JSON.stringify(direcciones));

                    let ingresos = <%=listIngr%>;
                    let ingresosJson = JSON.parse(JSON.stringify(ingresos));

                    let gastos = <%=listGast%>;
                    let gastosJson = JSON.parse(JSON.stringify(gastos));

                    let tbody = document.getElementById("tbody");

                    for (let i = 0; i < direcciones.length; i++) {
                        let direccion = direcciones[i];

                        let fila = tbody.insertRow();
                        let celdaPropiedad = fila.insertCell();
                        let celdaIngresos = fila.insertCell();
                        let celdaGastos = fila.insertCell();
                        let celdaBalance = fila.insertCell();

                        celdaPropiedad.innerHTML = direccionesJson[i];
                        celdaIngresos.innerHTML = ingresosJson[i];
                        celdaGastos.innerHTML = gastosJson[i];

                        let balance = ingresosJson[i] - gastosJson[i];
                        celdaBalance.innerHTML = balance.toFixed(2);

                        if (balance > 0) {
                            celdaBalance.style.color = "green";
                            celdaBalance.style.fontWeight = "bold";
                        } else if (balance < 0) {
                            celdaBalance.style.color = "red";
                            celdaBalance.style.fontWeight = "bold";
                        }
                    }
                });
            </script>

        </div>

        <div class="content">
            <div class="info">
                <h2>Balance Total</h2>
                <hr>
            </div>

            <div class="miTabla">
                <table>
                    <thead>
                        <tr>
                            <th>Ingresos</th>
                            <th>Gastos</th>
                            <th>Balance</th>
                        </tr>
                    </thead>
                    <tbody id="tbody2">
                        <tr>
                            <td><%=totalIngresos%></td>
                            <td><%=totalGastos%></td>
                            <td><%=balanceTotalString%></td>
                        </tr>
                    </tbody>
                    <tfoot class="footer-table">
                    </tfoot>
                </table>
            </div>
        </div>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                let balanceTotal = <%= balanceTotalString%>;
                let balanceCell = document.getElementById("tbody2").rows[0].cells[2];

                if (balanceTotal > 0) {
                    balanceCell.style.color = "green";
                    balanceCell.style.fontWeight = "bold";
                } else if (balanceTotal < 0) {
                    balanceCell.style.color = "red";
                    balanceCell.style.fontWeight = "bold";
                }
            });
        </script>

        <!--menu lateral-->
        <%@ include file="menu.jsp" %>


        <!--=========  RUTAS  =============-->
        <%@ include file="rutas.jsp" %>


    </body>
</html>
