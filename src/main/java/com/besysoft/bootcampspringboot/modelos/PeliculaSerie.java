package com.besysoft.bootcampspringboot.modelos;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "peliculas_series")
public class PeliculaSerie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(name = "fecha_de_creacion")
    private LocalDate fechaDeCreacion;

    private Integer calificacion;

    //@JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "personajes_peliculas_series",
            joinColumns = @JoinColumn(name = "pelicula_serie_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id")
    )
    private List<Personaje> personajes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public List<Personaje> getPersonaje() {
        return personajes;
    }

    public void setPersonaje(List<Personaje> personaje) {
        this.personajes = personaje;
    }


    public PeliculaSerie() {
    }

    public PeliculaSerie(Long id, String titulo, LocalDate fechaDeCreacion, Integer calificacion) {
        this.id = id;
        this.titulo = titulo;
        this.fechaDeCreacion = fechaDeCreacion;
        this.calificacion = calificacion;
        this.personajes = new ArrayList<>();


    }
}

