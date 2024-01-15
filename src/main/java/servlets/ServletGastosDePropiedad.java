/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import adapter.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import configuration.jdbc_Template_javaBased.AppConfig;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Gasto;
import modelos.Propiedad;
import modelos.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import servicios.implementacion.ServicioGasto;
import servicios.implementacion.ServicioPropiedad;
import servicios.implementacion.ServicioTipoGasto;

/**
 *
 * @author sgranjot
 */
public class ServletGastosDePropiedad extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioSession");

        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioGasto servicioGasto = (ServicioGasto) contenedor.getBean("servicioGasto");
        ServicioTipoGasto servicioTipoGasto = (ServicioTipoGasto) contenedor.getBean("servicioTipoGasto");
        ServicioPropiedad servicioPropiedad = (ServicioPropiedad) contenedor.getBean("servicioPropiedad");

        int idPropiedad = Integer.valueOf(request.getParameter("propiedadId"));
        request.setAttribute("idPropiedad", idPropiedad);

        Gson gson =new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .create();

        //lista de gastos de propiedad
        Propiedad propiedad = servicioPropiedad.getPropiedad(idPropiedad);
        List<Gasto> listGastos = servicioGasto.getGastosDePropiedad(propiedad);
        String gastosJson = gson.toJson(listGastos);
        request.setAttribute("gastosJson", gastosJson);

        //lista de tipo de gastos
        List<String> listTipoGasto = new ArrayList();
        for (Gasto g : listGastos) {
            listTipoGasto.add(servicioTipoGasto.getTipoGasto(g.getIdTipoGasto()));
        }
        String tipoGastosJson = gson.toJson(listTipoGasto);
        request.setAttribute("tipoGastosJson", tipoGastosJson);

        RequestDispatcher rd = request.getRequestDispatcher("gastosDePropiedad.jsp");
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
