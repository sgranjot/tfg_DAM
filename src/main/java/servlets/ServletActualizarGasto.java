/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import configuration.jdbc_Template_javaBased.AppConfig;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import servicios.implementacion.ServicioGasto;

/**
 *
 * @author sgranjot
 */
public class ServletActualizarGasto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioSession");

        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioGasto servicioGasto = (ServicioGasto) contenedor.getBean("servicioGasto");

        String idPropiedad = request.getParameter("idPropiedadAe");
        request.setAttribute("idPropiedad", idPropiedad);
        String idGasto = request.getParameter("idGastoAe");
        request.setAttribute("idGasto", idGasto);

        String tipoGasto = request.getParameter("tipoGastoAe");
        request.setAttribute("tipoGasto", tipoGasto);
        String fecha = request.getParameter("fechaAe");
        request.setAttribute("fecha", fecha);
        String importe = request.getParameter("importeAe");
        request.setAttribute("importe", importe);
        String descripcion = request.getParameter("descripcionAe");
        request.setAttribute("descripcion", descripcion);

        RequestDispatcher rd = request.getRequestDispatcher("actualizarGasto.jsp");
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
