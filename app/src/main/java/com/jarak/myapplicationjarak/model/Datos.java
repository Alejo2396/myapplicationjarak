package com.jarak.myapplicationjarak.model;

public class Datos {
    private String nombre;

    private String apellido;
    private String correo;

    public Datos(String nom, String ape, String co){
        this.nombre = nom;
        this.apellido = ape;
        this.correo = co;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public  void setApellido( String apellido){
        this.apellido = apellido;
    }
    public void setCorreo( String correo){
        this.correo = correo;
    }

    public String getNombre(){

        return this.nombre;
    }
    public String getApellido(){

        return this.apellido;
    }
    public String getCorreo(){

        return this.correo;
    }


}