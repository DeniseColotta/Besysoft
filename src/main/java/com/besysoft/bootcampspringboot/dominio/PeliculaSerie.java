package com.besysoft.bootcampspringboot.dominio;
import java.time.LocalDate;


public class PeliculaSerie {
public String titulo;
private LocalDate fechaDeCreacion;
private int calificacion; //1 al 5
private Genero genero;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public PeliculaSerie() {
    }

    public PeliculaSerie(String titulo, LocalDate fechaDeCreacion, int calificacion, Genero genero) {
        this.titulo = titulo;
        this.fechaDeCreacion = fechaDeCreacion;
        this.calificacion = calificacion;
        this.genero=genero;

    }
}

