package com.proyecto.support.View;

public class SupportModel {
    String nombre;
    int foto;
    public SupportModel(String nombre, int foto) {
        this.nombre = nombre;
        this.foto = foto;
    }

    public SupportModel() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
