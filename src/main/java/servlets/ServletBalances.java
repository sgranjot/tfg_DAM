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
import modelos.Gasto;
import modelos.Propiedad;
import modelos.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import servicios.implementacion.ServicioArrendatario;
import servicios.implementacion.ServicioContrato;
import servicios.implementacion.ServicioDireccion;
import servicios.implementacion.ServicioGasto;
import servicios.implementacion.ServicioMensualidad;
import servicios.implementacion.ServicioPropiedad;
import servicios.implementacion.ServicioTipoGasto;
import servicios.implementacion.ServicioTipoPropiedad;

/**
 *
 * @author sgranjot
 */
public class ServletBalances extends HttpServlet {

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
        ServicioGasto servicioGasto = (ServicioGasto) contenedor.getBean("servicioGasto");
        ServicioTipoGasto servicioTipoGastp = (ServicioTipoGasto) contenedor.getBean("servicioTipoGasto");

        Gson gson = new Gson();

        //lista de propiedades
        List<Propiedad> listPropiedades = servicioPropiedad.getPropiedadesDeUsuario(usuarioSesion);

        //lista de direcciones
        List<String> listDirecciones = new ArrayList();
        for (Propiedad p : listPropiedades) {
            int idTipoPropiedad = p.getIdTipoPropiedad();
            int idDireccion = p.getIdDireccion();
            Direccion direccion = servicioDireccion.getDireccion(idDireccion);
            String tipo = servicioTipoPropiedad.getTipoPropiedad(idTipoPropiedad);
            String calle = direccion.getCalle();
            String numero = direccion.getNumero();
            String poblacion = direccion.getPoblacion();
            String plaza = direccion.getNumeroPlaza();
            if (tipo.equalsIgnoreCase("garaje")) {
                listDirecciones.add(tipo + " plaza nº " + plaza + " en " + calle + " " + numero + ", " + poblacion);
            } else {
                listDirecciones.add(tipo + " en " + calle + " " + numero + ", " + poblacion);
            }
        }
        String direccionesJson = gson.toJson(listDirecciones);
        request.setAttribute("direccionesJson", direccionesJson);

        //lista de ingresos
        List<Integer> listIngresos = new ArrayList();
        for (Propiedad p : listPropiedades) {
            listIngresos.add(servicioMensualidad.getMensualidadesCobradasDePropiedad(p));
        }
        String ingresosJson = gson.toJson(listIngresos);
        request.setAttribute("ingresosJson", ingresosJson);

        //lista de gastos
        List<Double> listGastos = new ArrayList();
        for (Propiedad p : listPropiedades) {
            double gastosPropiedad = 0;
            List<Gasto> listGastosPropiedad = servicioGasto.getGastosDePropiedad(p);
            for (Gasto g : listGastosPropiedad) {
                gastosPropiedad += g.getImporte();
            }
            listGastos.add(gastosPropiedad);
        }
        String gastosJson = gson.toJson(listGastos);
        request.setAttribute("gastosJson", gastosJson);

        //total ingresos
        int totalIngresos = 0;
        for (Propiedad p : listPropiedades) {
            totalIngresos += servicioMensualidad.getMensualidadesCobradasDePropiedad(p);
        }
        request.setAttribute("totalIngresos", totalIngresos);

        //total gastos
        double totalGastos = 0;
        for (Propiedad p : listPropiedades) {
            List<Gasto> listGastosPropiedad = servicioGasto.getGastosDePropiedad(p);
            for (Gasto g : listGastosPropiedad) {
                totalGastos += g.getImporte();
            }
        }
        request.setAttribute("totalGastos", totalGastos);

        RequestDispatcher rd = request.getRequestDispatcher("balances.jsp");
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
