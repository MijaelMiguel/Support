package com.proyecto.support.Model;

import java.util.Date;

public abstract class UsuarioModel {
    String usu_codigo;
    String usu_nombres;
    String usu_apellidos;
    String usu_correo;
    String usu_telefono;
    Date usu_fecreg;
    boolean usu_estcod;


    public String getUsu_codigo() {
        return usu_codigo;
    }

    public void setUsu_codigo(String usu_codigo) {
        this.usu_codigo = usu_codigo;
    }

    public String getUsu_nombres() {
        return usu_nombres;
    }

    public void setUsu_nombres(String usu_nombres) {
        this.usu_nombres = usu_nombres;
    }

    public String getUsu_apellidos() {
        return usu_apellidos;
    }

    public void setUsu_apellidos(String usu_apellidos) {
        this.usu_apellidos = usu_apellidos;
    }

    public String getUsu_correo() {
        return usu_correo;
    }

    public void setUsu_correo(String usu_correo) {
        this.usu_correo = usu_correo;
    }

    public String getUsu_telefono() {
        return usu_telefono;
    }

    public void setUsu_telefono(String usu_telefono) {
        this.usu_telefono = usu_telefono;
    }

    public Date getUsu_fecreg() {
        return usu_fecreg;
    }

    public void setUsu_fecreg(Date usu_fecreg) {
        this.usu_fecreg = usu_fecreg;
    }

    public boolean isUsu_estcod() {
        return usu_estcod;
    }

    public void setUsu_estcod(boolean usu_estcod) {
        this.usu_estcod = usu_estcod;
    }
}
