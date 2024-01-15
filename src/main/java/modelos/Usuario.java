package modelos;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private int idUsuario;
    private String nombre;
    private String contrasenia;
    private boolean activado;
    private List<Propiedad> listaPropiedades;
    private List<Arrendatario> listaArrendatarios;

    public Usuario() {
    }

    public Usuario(String nombre, String contrasenia, boolean activado) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.activado = activado;
        listaPropiedades = new ArrayList();
    }

    public Usuario(int idUsuario, String nombre, String contrasenia, boolean activado) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.activado = activado;
        listaPropiedades = new ArrayList();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int id) {
        this.idUsuario = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }

    public List<Propiedad> getListaPropiedades() {
        return listaPropiedades;
    }

    public void setListaPropiedades(List<Propiedad> listaPropiedades) {
        this.listaPropiedades = listaPropiedades;
    }

    public List<Arrendatario> getListaArrendatarios() {
        return listaArrendatarios;
    }

    public void setListaArrendatarios(List<Arrendatario> listaArrendatarios) {
        this.listaArrendatarios = listaArrendatarios;
    }

}
