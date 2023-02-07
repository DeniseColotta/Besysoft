package com.besysoft.bootcampspringboot.Entidades;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "personajes")
public class Personaje implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    private int edad;

    private double peso;

    @Column(nullable = false, length = 200)
    private String historia;

    @ManyToMany
    @JoinTable(
    name = "peliculasSeries_personajes",
    joinColumns = @JoinColumn(name = "personaje_id"),
    inverseJoinColumns = @JoinColumn(name = "peliculaSerie_id"))
    private List<PeliculaSerie> peliculaSerie;


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public List<PeliculaSerie> getPeliculaSerie() {
        return peliculaSerie;
    }

    public void setPeliculaSerie(List<PeliculaSerie> peliculaSerie) {
        this.peliculaSerie = peliculaSerie;
    }


    public Personaje() {
    }


    public Personaje(Long id, String nombre, int edad, double peso, String historia) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
        this.id = id;
        this.peliculaSerie = new ArrayList<>();

    }
}
