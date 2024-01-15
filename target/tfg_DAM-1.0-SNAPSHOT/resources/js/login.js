/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function habilitaLogin() {
    document.getElementById('login').style.display = 'inline';
    document.getElementById('acceder').style.display = 'none';
    document.getElementById('logo').style.display = 'none';
    document.getElementById('usuario').value = "";
    document.getElementById('usuario').focus();
    document.getElementById('contrasena').value = "";
}


function registrarUsuario() {
    document.getElementById('login').style.display = 'none';
    document.getElementById('regist').style.display = 'inline';
    document.getElementById('usuarioReg').value = "";
    document.getElementById('usuarioReg').focus();
    document.getElementById('contrasenaReg').value = "";
    document.getElementById('confirmContrasenaReg').value = "";
}

function cancelarRegistrar() {
    document.getElementById('login').style.display = 'inline';
    document.getElementById('regist').style.display = 'none';
    document.getElementById('usuario').value = "";
    document.getElementById('contrasena').value = "";
    document.getElementById('usuarioReg').value = "";
    document.getElementById('contrasenaReg').value = "";
    document.getElementById('confirmContrasenaReg').value = "";
}

function compruebaCampos(...args) {
    const numArgs = args.length;
    let cont = 0;
    let input = null;
    let valor = null;
    for (let i = 0; i < numArgs; i++) {
        input = document.getElementById(args[i]);
        valor = input.value;
        if (valor.length > 0) {
            cont++;
        } else {
            cont = 0;
        }
    }

    if (cont === 2 & numArgs === 2) {
        document.getElementById('btnIniciar').style.pointerEvents = 'auto';
    } else if (cont < 2 & numArgs === 2) {
        document.getElementById('btnIniciar').style.pointerEvents = 'none';
    }

    let contr = document.getElementById('contrasenaReg').value;
    let contConf = document.getElementById('confirmContrasenaReg').value;
    if (cont === 3 & numArgs === 3) {
        if (contr === contConf) {
            document.getElementById('btnConfirmRegist').style.pointerEvents = 'auto';
        } else {
            document.getElementById('btnConfirmRegist').style.pointerEvents = 'none';
        }

    } else {
        document.getElementById('btnConfirmRegist').style.pointerEvents = 'none';
    }

}


function invitado() {
    window.location.href = "paginaPrincipal.jsp";
}


