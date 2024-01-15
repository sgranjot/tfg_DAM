/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import modelos.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import configuration.jdbc_Template_javaBased.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import servicios.implementacion.ServicioUsuario;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sgranjot
 */
public class ServletUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        ApplicationContext contenedor = new AnnotationConfigApplicationContext(AppConfig.class);
        ServicioUsuario servicioUsuario = (ServicioUsuario) contenedor.getBean("servicioUsuario");

        String nombreRecibido = request.getParameter("usuario");
        String contrasenaRecibida = request.getParameter("contrasena");
        String contrasenaConfirmRecibida = request.getParameter("confirmContrasena");
        Usuario usuarioRecibido = new Usuario(0, nombreRecibido, contrasenaRecibida, true);

        RequestDispatcher rd;

        //registrar usuario
        if (contrasenaRecibida.equals(contrasenaConfirmRecibida)) {
            
            if (servicioUsuario.recuperarUsuario(usuarioRecibido.getNombre()) != null) {
                response.sendRedirect("index.html");
            }
            else {
                servicioUsuario.altaUsuario(usuarioRecibido);
                Usuario usuarioSession = servicioUsuario.recuperarUsuario(nombreRecibido);
                HttpSession session = request.getSession();
                session.setAttribute("usuarioSession", usuarioSession);
                session.setMaxInactiveInterval(1800);
                usuarioSession.setActivado(true);
                servicioUsuario.actualizaContrasenia(usuarioSession);
                rd = request.getRequestDispatcher("paginaPrincipal.jsp");
                rd.forward(request, response);
            }

        } else {
            //iniciar sesion
            Usuario userRecogido = servicioUsuario.recuperarUsuario(usuarioRecibido.getNombre());
            if (userRecogido == null) {
                response.sendRedirect("index.html");
            } else {
                if (userRecogido.isActivado()
                        && usuarioRecibido.getContrasenia().equals(userRecogido.getContrasenia())) {
                    Usuario usuarioSession = servicioUsuario.recuperarUsuario(nombreRecibido);
                    HttpSession session = request.getSession();
                    session.setAttribute("usuarioSession", usuarioSession);
                    session.setMaxInactiveInterval(1800);
                    rd = request.getRequestDispatcher("paginaPrincipal.jsp");
                    rd.forward(request, response);
                } else {
                    response.sendRedirect("index.html");
                }
            }
        }
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
    }// </editor-fold>

}
