<%-- 
    Document   : cambiarContrasenia
    Created on : 7 jun. 2023, 11:39:08
    Author     : sgranjot
--%>

<%@page import="java.net.URLEncoder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cambiar contrasenia</title>
        <link rel="icon" type="image/png" href="resources/img/icon.png">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="resources/css/login.css"/>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
        <script src="resources/js/login.js"></script>
    </head>

    <body>

        <section class="flex-container">
            <div class="imgLogo" id="logo">
                <img src= "resources/img/logo.png" width="100" alt="mi_logo">
            </div>

        </section>


        <section class="flex-container" id="registForm">
            <div class="login" id="regist">
                <form action="servletCambiarContrasenia" method="post" accept-charset="UTF-8">
                    <table  cellspacing="10" cellpadding="4">
                        <tbody>
                            <tr>
                                <td><img src= "resources/img/logo.png" width="350" alt="mi_logo"></td>
                            </tr>
                            <tr>
                                <td><img src="resources/img/userAdd.png" alt="user" height="20" style="vertical-align: -25%;"> &nbsp;&nbsp; 
                                    <input type="text" id="usuarioReg" name="usuario" value="" class="inputTextLogin" placeholder="nombre de usuario" onkeyup="javascript:compruebaCampos('usuarioReg', 'contrasenaReg', 'confirmContrasenaReg')"></td>
                            </tr>
                            <tr>
                                <td><img src="resources/img/pass.png" alt="password" height="20" style="vertical-align: -25%;"> &nbsp;&nbsp; 
                                    <input type="password" id="contrasenaReg" name="contrasena" value="" class="inputTextLogin" placeholder="contraseña" onkeyup="javascript:compruebaCampos('usuarioReg', 'contrasenaReg', 'confirmContrasenaReg')"></td>
                            </tr>
                            <tr>
                                <td><img src="resources/img/pass.png" alt="password" height="20" style="vertical-align: -25%;"> &nbsp;&nbsp; 
                                    <input type="password" id="confirmContrasenaReg" name="confirmContrasena" value="" class="inputTextLogin" placeholder="confirme contraseña" onkeyup="javascript:compruebaCampos('usuarioReg', 'contrasenaReg', 'confirmContrasenaReg')"></td>
                            </tr>
                            <tr>
                                <td><button type="submit" class="buttonVerde" id="btnConfirmRegist">Confirmar</button> &nbsp;&nbsp;&nbsp; 
                                    <button type="button" class="buttonVerde" onclick="window.location.href = 'paginaPrincipal.jsp'">Cancelar</button></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </section>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                document.getElementById('regist').style.display = 'inline';
                document.getElementById('usuarioReg').value = "";
                document.getElementById('usuarioReg').focus();
                document.getElementById('contrasenaReg').value = "";
                document.getElementById('confirmContrasenaReg').value = "";
            });
        </script>


    </body>
</html>
