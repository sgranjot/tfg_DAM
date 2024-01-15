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
import modelos.Contrato;
import modelos.Propiedad;
import modelos.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import servicios.implementacion.ServicioContrato;
import servicios.implementacion.ServicioPropiedad;

/**
 *
 * @author sgranjot
 */
public class ServletFinalizarContrato extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioSession");
        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioContrato servicioContrato = (ServicioContrato) contenedor.getBean("servicioContrato");
        ServicioPropiedad servicioPropiedad = (ServicioPropiedad) contenedor.getBean("servicioPropiedad");

        int idc = Integer.parseInt(request.getParameter("contratoId"));
        Contrato contrato = servicioContrato.getContrato(idc);
        contrato.setFechaFin(LocalDate.now());
        servicioContrato.actualizaContrato(contrato);

        int idPropiedad = contrato.getIdPropiedad();
        Propiedad propiedad = servicioPropiedad.getPropiedad(idPropiedad);
        propiedad.setLibre(true);
        servicioPropiedad.actualizaPropiedad(propiedad);

        RequestDispatcher rd = request.getRequestDispatcher("servletContratos");
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
