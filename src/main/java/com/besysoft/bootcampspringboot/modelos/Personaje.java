package com.besysoft.bootcampspringboot.modelos;


import com.fasterxml.jackson.annotation.JsonIgnore;

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

    private Integer edad;

    private Double peso;

    @Column(length = 200)
    private String historia;

   // @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "personajes")
    private List<PeliculaSerie> peliculasSeries;


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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public List<PeliculaSerie> getPeliculaSeries() {
        return peliculasSeries;
    }

    public void setPeliculaSeries(List<PeliculaSerie> peliculaSeries) {
        this.peliculasSeries = peliculaSeries;
    }


    public Personaje() {
    }


    public Personaje(Long id, String nombre, Integer edad, Double peso, String historia) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
        this.id = id;
        this.peliculasSeries = new ArrayList<>();

    }
}
