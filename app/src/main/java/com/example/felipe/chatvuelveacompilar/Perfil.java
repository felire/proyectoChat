package com.example.felipe.chatvuelveacompilar;

import java.util.List;

/**
 * Created by felipe on 31/08/16.
 */
public class Perfil implements PersonaDescargadora {
    private String nombre;
    private String id;
    private String imagen;
    private List<Contacto> contactos;

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }



    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getImagen() {
        return imagen;
    }

    @Override
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
