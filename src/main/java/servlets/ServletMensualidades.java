/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.google.gson.Gson;
import configuration.jdbc_Template_javaBased.AppConfig;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Contrato;
import modelos.Direccion;
import modelos.Mensualidad;
import modelos.Propiedad;
import modelos.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import servicios.implementacion.ServicioArrendatario;
import servicios.implementacion.ServicioContrato;
import servicios.implementacion.ServicioDireccion;
import servicios.implementacion.ServicioMensualidad;
import servicios.implementacion.ServicioPropiedad;
import servicios.implementacion.ServicioTipoPropiedad;

/**
 *
 * @author sgranjot
 */
public class ServletMensualidades extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioSession");

        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioMensualidad servicioMensualidad = (ServicioMensualidad) contenedor.getBean("servicioMensualidad");
        ServicioContrato servicioContrato = (ServicioContrato) contenedor.getBean("servicioContrato");
        ServicioPropiedad servicioPropiedad = (ServicioPropiedad) contenedor.getBean("servicioPropiedad");
        ServicioArrendatario servicioArrendatario = (ServicioArrendatario) contenedor.getBean("servicioArrendatario");
        ServicioDireccion servicioDireccion = (ServicioDireccion) contenedor.getBean("servicioDireccion");
        ServicioTipoPropiedad servicioTipoPropiedad = (ServicioTipoPropiedad) contenedor.getBean("servicioTipoPropiedad");

        Gson gson = new Gson();

        //actualizar lista de mensualidades 
        List<Contrato> contratos = servicioContrato.getContratosDeUsuario(usuarioSesion);

        LocalDate actual = LocalDate.now();
        String mes = actual.getMonth().toString();
        String month = null;
        switch (mes) {
            case "JANUARY":
                month = "01";
                break;
            case "FEBRUARY":
                month = "02";
                break;
            case "MARCH":
                month = "03";
                break;
            case "APRIL":
                month = "04";
                break;
            case "MAY":
                month = "05";
                break;
            case "JUNE":
                month = "06";
                break;
            case "JULY":
                month = "07";
                break;
            case "AUGUST":
                month = "08";
                break;
            case "SEPTEMBER":
                month = "09";
                break;
            case "OCTOBER":
                month = "10";
                break;
            case "NOVEMBER":
                month = "11";
                break;
            case "DECEMBER":
                month = "12";
                break;
        }

        for (Contrato c : contratos) {
            LocalDate fechaInicio = c.getFechaInicio();
            LocalDate fechaFin = c.getFechaFin();
            //LocalDate fechaContador = fechaInicio;
            String fecha = actual.getYear() + "-" + month + "-01";
            while (fechaInicio.isBefore(actual)) {
                if (fechaFin.isAfter(fechaInicio)) {
                    String f = fechaInicio.getYear() + "-" + fechaInicio.getMonthValue() + "-01";
                    System.out.println(f);
                    servicioMensualidad.altaMensualidad(new Mensualidad(0, usuarioSesion.getIdUsuario(), c.getIdContrato(), c.getMensualidad(), Date.valueOf(f), false));
                }
                fechaInicio = fechaInicio.plusMonths(1);
            }
            if (fechaFin.isAfter(actual)) {
                servicioMensualidad.altaMensualidad(new Mensualidad(0, usuarioSesion.getIdUsuario(), c.getIdContrato(), c.getMensualidad(), Date.valueOf(fecha), false));
            }
        }

        //lista de mensualidades
        List<Mensualidad> listMensualidades = servicioMensualidad.getMensualidadesDeUsuario(usuarioSesion);
        String mensualidadesJson = gson.toJson(listMensualidades);
        request.setAttribute("mensualidadesJson", mensualidadesJson);

        //lista de propiedades
        List<String> listPropiedades = new ArrayList();
        for (Mensualidad m : listMensualidades) {
            int idContrato = m.getIdContrato();
            Contrato contrato = servicioContrato.getContrato(idContrato);
            int idPropiedad = contrato.getIdPropiedad();
            Propiedad propiedad = servicioPropiedad.getPropiedad(idPropiedad);
            int idTipoPropiedad = propiedad.getIdTipoPropiedad();
            int idDireccion = propiedad.getIdDireccion();
            Direccion direccion = servicioDireccion.getDireccion(idDireccion);
            String tipo = servicioTipoPropiedad.getTipoPropiedad(idTipoPropiedad);
            String calle = direccion.getCalle();
            String numero = direccion.getNumero();
            String poblacion = direccion.getPoblacion();
            String plaza = direccion.getNumeroPlaza();
            if (tipo.equalsIgnoreCase("garaje")) {
                listPropiedades.add(tipo + " plaza nº " + plaza + " en " + calle + " " + numero + ", " + poblacion);
            } else {
                listPropiedades.add(tipo + " en " + calle + " " + numero + ", " + poblacion);
            }
        }
        String propiedadesJson = gson.toJson(listPropiedades);
        request.setAttribute("propiedadesJson", propiedadesJson);

        //lista de arrendatarios
        List<String> listArrendatarios = new ArrayList();
        for (Mensualidad m : listMensualidades) {
            int idContrato = m.getIdContrato();
            Contrato contrato = servicioContrato.getContrato(idContrato);
            int idArrendatario = contrato.getIdArrendatario();
            String nombre = servicioArrendatario.getArrendatario(idArrendatario).getNombre();
            String apellidos = servicioArrendatario.getArrendatario(idArrendatario).getApellidos();
            listArrendatarios.add(apellidos + ", " + nombre);
        }
        String arrendatariosJson = gson.toJson(listArrendatarios);
        request.setAttribute("arrendatariosJson", arrendatariosJson);

        RequestDispatcher rd = request.getRequestDispatcher("mensualidades.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
