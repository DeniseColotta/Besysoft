package com.besysoft.bootcampspringboot.dominio;


public class Genero {

    private String nombre;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Genero() {
    }

    public Genero(String nombre) {
        this.nombre = nombre;

    }
}