
function fecha() {
    var fechaActual = new Date();
    var dia = fechaActual.getDate();
    var mes = fechaActual.getMonth() + 1;
    var año = fechaActual.getFullYear();

    var fechaFormateada = dia + " de " + mes + " de " + año;

    document.getElementById("fecha").innerText = fechaFormateada;
}

function aniadirPropiedad() {
    document.getElementById("aniadirPropiedad").submit();
}

function cerrarSesion() {
     if (confirm("¿Seguro que quieres cerrar la sesión?")) {
       document.getElementById("cerrarSesion").submit();  
     }
}

function darBajaUsuario() {
     if (confirm("¿Seguro que quieres darte de baja? Podrás recuperar tus datos si vuelves a registrarte con el mismo nombre y contraseña")) {
         document.getElementById("darBajaUsuario").submit();
     }
}

function cambiarContrasenia() {
    document.getElementById("cambiarContrasenia").submit();
}

function propiedades() {
    document.getElementById("propiedades").submit();
}

function actualizarPropiedad() {
    let calle = document.getElementById("calle").textContent;
    let numero = document.getElementById("numero").textContent;
    let piso = document.getElementById("piso").textContent;
    let puerta = document.getElementById("puerta").textContent;
    let poblacion = document.getElementById("poblacion").textContent;
    let codigoPostal = document.getElementById("codigoPostal").textContent;
    let provincia = document.getElementById("provincia").textContent;
    let pais = document.getElementById("pais").textContent;
    let plaza = document.getElementById("plaza").textContent;

    document.getElementById("calleH").value = calle;
    document.getElementById("numeroH").value = numero;
    document.getElementById("pisoH").value = piso;
    document.getElementById("puertaH").value = puerta;
    document.getElementById("poblacionH").value = poblacion;
    document.getElementById("codigoPostalH").value = codigoPostal;
    document.getElementById("provinciaH").value = provincia;
    document.getElementById("paisH").value = pais;
    document.getElementById("plazaH").value = plaza;

    document.getElementById("actualizarPropiedad").submit();
}

function eliminarPropiedad() {
    let calle = document.getElementById("calle").textContent;
    let numero = document.getElementById("numero").textContent;
    let piso = document.getElementById("piso").textContent;
    let puerta = document.getElementById("puerta").textContent;
    let poblacion = document.getElementById("poblacion").textContent;
    let codigoPostal = document.getElementById("codigoPostal").textContent;
    let provincia = document.getElementById("provincia").textContent;
    let pais = document.getElementById("pais").textContent;
    let plaza = document.getElementById("plaza").textContent;

    document.getElementById("calleHe").value = calle;
    document.getElementById("numeroHe").value = numero;
    document.getElementById("pisoHe").value = piso;
    document.getElementById("puertaHe").value = puerta;
    document.getElementById("poblacionHe").value = poblacion;
    document.getElementById("codigoPostalHe").value = codigoPostal;
    document.getElementById("provinciaHe").value = provincia;
    document.getElementById("paisHe").value = pais;
    document.getElementById("plazaHe").value = plaza;

    if (confirm("Esto eliminará la propiedad de la base de datos ¿estás seguro?")) {
        document.getElementById("eliminarPropiedad").submit();
    }
}

function arrendatarios() {
    document.getElementById("arrendatarios").submit();
}

function aniadirArrendatario() {
    document.getElementById("aniadirArrendatario").submit();
}

function actualizarArrendatario() {
    let nombre = document.getElementById("nombre").textContent;
    let apellidos = document.getElementById("apellidos").textContent;
    let dni = document.getElementById("dni").textContent;
    let telefono = document.getElementById("telefono").textContent;
    let email = document.getElementById("email").textContent;

    document.getElementById("nombreH").value = nombre;
    document.getElementById("apellidosH").value = apellidos;
    document.getElementById("dniH").value = dni;
    document.getElementById("telefonoH").value = telefono;
    document.getElementById("emailH").value = email;

    document.getElementById("actualizarArrendatario").submit();
}

function eliminarArrendatario() {
    let nombre = document.getElementById("nombre").textContent;
    let apellidos = document.getElementById("apellidos").textContent;
    let dni = document.getElementById("dni").textContent;
    let telefono = document.getElementById("telefono").textContent;
    let email = document.getElementById("email").textContent;

    document.getElementById("nombreHe").value = nombre;
    document.getElementById("apellidosHe").value = apellidos;
    document.getElementById("dniHe").value = dni;
    document.getElementById("telefonoHe").value = telefono;
    document.getElementById("emailHe").value = email;

    if (confirm("Esto eliminará el arrendatario de la base de datos ¿estás seguro?")) {
        document.getElementById("eliminarArrendatario").submit();
    }
}

function contratos() {
    document.getElementById("contratos").submit();
}

function aniadirContrato() {
    document.getElementById("aniadirContrato").submit();
}

function finalizarContrato() {
    if (confirm("¿Seguro que quiere finalizar el contrato?")) {
        let vigor = document.getElementById("vigor");
        vigor.innerHTML = "Contrato en vigor: No";
        document.getElementById("finalizarContrato").submit();
    }
    
}

function eliminarContrato() {
    if (confirm("Esto eliminará el contrato de la base de datos ¿estás seguro?")) {
        document.getElementById("eliminarContrato").submit();
    }
}

function crearContrato() {
    document.getElementById("crearContrato").submit();
}

function mensualidades() {
    document.getElementById("mensualidades").submit();
}

function gastos() {
    document.getElementById("gastos").submit();
}

function aniadirGasto() {
    document.getElementById("aniadirGasto").submit();
}

function actualizarGasto() {
    let tipoGasto = document.getElementById("tipoGasto").textContent;
    let fecha = document.getElementById("fechaG").textContent;
    let importe = document.getElementById("importe").textContent;
    let descripcion = document.getElementById("descripcion").textContent;
    let idGasto = document.getElementById("idGasto").value;
    let idPropiedad = document.getElementById("idPropiedad").value;

    document.getElementById("tipoGastoA").value = tipoGasto;
    document.getElementById("fechaA").value = fecha;
    document.getElementById("importeA").value = importe;
    document.getElementById("descripcionA").value = descripcion;
    document.getElementById("idGastoA").value = idGasto;
    document.getElementById("idPropiedadA").value = idPropiedad;

    document.getElementById("actualizarGasto").submit();
}

function eliminarGasto() {
    if (confirm("Esto eliminará el gasto de la base de datos ¿estás seguro?")) {
        document.getElementById("eliminarGasto").submit();
    }
}

function balances() {
    document.getElementById("balances").submit();
}




