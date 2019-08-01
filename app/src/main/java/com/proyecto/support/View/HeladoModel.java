package com.proyecto.support.View;

public class HeladoModel {
    // 2
    // despues de crear el loyout crear esta carpeta
    String nombre;
    int foto;
    //constructores ALT +INSERT


    public HeladoModel(String nombre, int foto) {
        this.nombre = nombre;
        this.foto = foto;
    }

    public HeladoModel() {

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