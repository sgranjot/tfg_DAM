/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.google.gson.Gson;
import configuration.jdbc_Template_javaBased.AppConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Direccion;
import modelos.Propiedad;
import modelos.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import servicios.implementacion.ServicioDireccion;
import servicios.implementacion.ServicioPropiedad;
import servicios.implementacion.ServicioTipoPropiedad;

/**
 *
 * @author sgranjot
 */
public class ServletGastos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioSession");

        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioPropiedad servicioPropiedad = (ServicioPropiedad) contenedor.getBean("servicioPropiedad");
        ServicioDireccion servicioDireccion = (ServicioDireccion) contenedor.getBean("servicioDireccion");
        ServicioTipoPropiedad servicioTipoPropiedad = (ServicioTipoPropiedad) contenedor.getBean("servicioTipoPropiedad");

        Gson gson = new Gson();

        //lista de propiedades
        List<Propiedad> listPropiedades = servicioPropiedad.getPropiedadesDeUsuario(usuarioSesion);
        String propiedadesJson = gson.toJson(listPropiedades);
        request.setAttribute("propiedadesJson", propiedadesJson);

        //lista de direcciones
        List<Direccion> listDirecciones = new ArrayList();
        for (Propiedad p : listPropiedades) {
            listDirecciones.add(servicioDireccion.getDireccion(p.getIdDireccion()));
        }
        String direccionesJson = gson.toJson(listDirecciones);
        request.setAttribute("direccionesJson", direccionesJson);

        //lista de tipo de propiedad
        List<String> listTipoPropiedad = new ArrayList();
        for (Propiedad p : listPropiedades) {
            listTipoPropiedad.add(servicioTipoPropiedad.getTipoPropiedad(p.getIdTipoPropiedad()));
        }
        String tipoPropiedadesJson = gson.toJson(listTipoPropiedad);
        request.setAttribute("tipoPropiedadesJson", tipoPropiedadesJson);

        RequestDispatcher rd = request.getRequestDispatcher("propiedadesGastos.jsp");
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
