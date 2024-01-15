/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import configuration.jdbc_Template_javaBased.AppConfig;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Gasto;
import modelos.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import servicios.implementacion.ServicioGasto;
import servicios.implementacion.ServicioTipoGasto;

/**
 *
 * @author sgranjot
 */
public class ServletMostrarGasto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioSession");

        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioGasto servicioGasto = (ServicioGasto) contenedor.getBean("servicioGasto");
        ServicioTipoGasto servicioTipoGasto = (ServicioTipoGasto) contenedor.getBean("servicioTipoGasto");

        int idGasto = Integer.valueOf(request.getParameter("gastoId"));
        request.setAttribute("idGasto", idGasto);
        Gasto gasto = servicioGasto.getGasto(idGasto);
        int idTipoGasto = gasto.getIdTipoGasto();
        request.setAttribute("tipoGasto", servicioTipoGasto.getTipoGasto(idTipoGasto));
        int idPropiedad = gasto.getIdPropiedad();
        request.setAttribute("idPropiedad", idPropiedad);
        double importe = gasto.getImporte();
        request.setAttribute("importe", importe);
        LocalDate fecha = gasto.getFecha();
        request.setAttribute("fecha", fecha);
        String descripcion = gasto.getDescripcion();
        request.setAttribute("descripcion", descripcion);

        RequestDispatcher rd = request.getRequestDispatcher("mostrarGasto.jsp");
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
