package com.proyecto.support.Model;

public class AlertasModel {
    public String nombre;
    public int foto;
    public AlertasModel(String nombre, int foto) {
        this.nombre = nombre;
        this.foto = foto;
    }

    public AlertasModel() {

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
