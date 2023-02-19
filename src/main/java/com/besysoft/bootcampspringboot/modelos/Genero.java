package com.besysoft.bootcampspringboot.modelos;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "generos")
public class Genero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_id")
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

    public List<PeliculaSerie> getPeliculaSerie() {
        return peliculaSerie;
    }

    public void setPeliculaSerie(List<PeliculaSerie> peliculaSerie) {
        this.peliculaSerie = peliculaSerie;
    }

    public Genero() {
    }

    public Genero(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.peliculaSerie = new ArrayList<>();
    }
}