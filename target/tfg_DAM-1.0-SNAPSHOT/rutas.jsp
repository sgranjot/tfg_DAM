<%@page import="java.net.URLEncoder"%>

<form id="cerrarSesion" action="servletCerrarSesion" method="post" style="display: none;" accept-charset="UTF-8">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletCerrarSesion").toString(), "UTF-8")%>">
</form>

<form id="darBajaUsuario" action="servletDarBajaUsuario" method="post" style="display: none;" accept-charset="UTF-8">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletDarBajaUsuario").toString(), "UTF-8")%>">
</form>

<form id="cambiarContrasenia" action="cambiarContrasenia.jsp" method="post" style="display: none;" accept-charset="UTF-8">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletCambiarContrasenia").toString(), "UTF-8")%>">
</form>

<form id="propiedades" action="servletPropiedades" method="post" style="display: none;" accept-charset="UTF-8">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletPropiedades").toString(), "UTF-8")%>">
</form>

<form id="arrendatarios" action="servletArrendatarios" method="post" style="display: none;" accept-charset="UTF-8">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletArrendatarios").toString(), "UTF-8")%>">
</form>

<form id="contratos" action="servletContratos" method="post" style="display: none;" accept-charset="UTF-8">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletContratos").toString(), "UTF-8")%>">
</form>

<form id="mensualidades" action="servletMensualidades" method="post" style="display: none;" accept-charset="UTF-8">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletMensualidades").toString(), "UTF-8")%>">
</form>

<form id="gastos" action="servletGastos" method="post" style="display: none;" accept-charset="UTF-8">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletGastos").toString(), "UTF-8")%>">
</form>

<form id="balances" action="servletBalances" method="post" style="display: none;" accept-charset="UTF-8">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletBalances").toString(), "UTF-8")%>">
</form>

<form id="aniadirArrendatario" action="crearArrendatario.jsp" method="post" style="display: none;" accept-charset="UTF-8">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletAniadirArrendatario").toString(), "UTF-8")%>">
</form>

<form id="aniadirPropiedad" action="crearPropiedad.jsp" method="post" style="display: none;" accept-charset="UTF-8">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletAniadirPropiedad").toString(), "UTF-8")%>">
</form>

<form id="mostrarArrendatario" action="servletMostrarArrendatario" method="post" style="display: none;" accept-charset="UTF-8">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletMostrarArrendatario").toString(), "UTF-8")%>">
</form>

<form id="aniadirContrato" action="crearContrato.jsp" method="post" style="display: none;" accept-charset="UTF-8">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletAniadirContrato").toString(), "UTF-8")%>">
</form>

<form id="mostrarPropiedad" action="servletMostrarPropiedad" method="post" style="display: none;" accept-charset="UTF-8">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletMostrarPropiedad").toString(), "UTF-8")%>">
</form>

<form id="pagarMensualidad" action="servletPagarMensualidad" method="post" style ="display: none;" accept-charset="UTF-8">
    <input type="hidden" id="idMensualidadH" name="idMensualidadH">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletPagarMensualidad").toString(), "UTF-8")%>">
</form>

<form id="actualizarArrendatario" action="servletActualizarArrendatario" method="post" style ="display: none;" accept-charset="UTF-8">
    <input type="hidden" id="nombreH" name="nombreH">
    <input type="hidden" id="apellidosH" name="apellidosH">
    <input type="hidden" id="dniH" name="dniH">
    <input type="hidden" id="telefonoH" name="telefonoH">
    <input type="hidden" id="emailH" name="emailH">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletActualizarArrendatario").toString(), "UTF-8")%>">
</form>

<form id="eliminarArrendatario" action="servletEliminarArrendatario" method="post" style ="display: none;" accept-charset="UTF-8">
    <input type="hidden" id="nombreHe" name="nombreH">
    <input type="hidden" id="apellidosHe" name="apellidosH">
    <input type="hidden" id="dniHe" name="dniH">
    <input type="hidden" id="telefonoHe" name="telefonoH">
    <input type="hidden" id="emailHe" name="emailH">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletEliminarArrendatario").toString(), "UTF-8")%>">
</form>

<form id="actualizarPropiedad" action="servletActualizarPropiedad" method="post" style ="display: none;" accept-charset="UTF-8">
    <input type="hidden" id="calleH" name="calleH">
    <input type="hidden" id="numeroH" name="numeroH">
    <input type="hidden" id="pisoH" name="pisoH">
    <input type="hidden" id="puertaH" name="puertaH">
    <input type="hidden" id="poblacionH" name="poblacionH">
    <input type="hidden" id="codigoPostalH" name="codigoPostalH">
    <input type="hidden" id="provinciaH" name="provinciaH">
    <input type="hidden" id="paisH" name="paisH">
    <input type="hidden" id="plazaH" name="plazaH">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletActualizarPropiedad").toString(), "UTF-8")%>">
</form>

<form id="eliminarPropiedad" action="servletEliminarPropiedad" method="post" style ="display: none;" accept-charset="UTF-8">
    <input type="hidden" id="calleHe" name="calleH">
    <input type="hidden" id="numeroHe" name="numeroH">
    <input type="hidden" id="pisoHe" name="pisoH">
    <input type="hidden" id="puertaHe" name="puertaH">
    <input type="hidden" id="poblacionHe" name="poblacionH">
    <input type="hidden" id="codigoPostalHe" name="codigoPostalH">
    <input type="hidden" id="provinciaHe" name="provinciaH">
    <input type="hidden" id="paisHe" name="paisH">
    <input type="hidden" id="plazaHe" name="plazaH">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletEliminarPropiedad").toString(), "UTF-8")%>">
</form>

<form id="actualizarGasto" action="servletActualizarGasto" method="post" style="display: none;" accept-charset="UTF-8">
    <input type="hidden" id="tipoGastoA" name="tipoGastoAe">
    <input type="hidden" id="fechaA" name="fechaAe">
    <input type="hidden" id="importeA" name="importeAe">
    <input type="hidden" id="descripcionA" name="descripcionAe">
    <input type="hidden" id="idGastoA" name="idGastoAe">
    <input type="hidden" id="idPropiedadA" name="idPropiedadAe">
    <input type="hidden" name="dispatcher" id="dispatcher" value="<%= URLEncoder.encode(request.getRequestDispatcher("servletActualizarGasto").toString(), "UTF-8")%>">
</form>



