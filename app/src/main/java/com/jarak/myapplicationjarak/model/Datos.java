package com.jarak.myapplicationjarak.model;

public class Datos {

    private String nombre;
    private String apellido;
    private String correo;

    public Datos(String nombre, String apellido, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }
}
