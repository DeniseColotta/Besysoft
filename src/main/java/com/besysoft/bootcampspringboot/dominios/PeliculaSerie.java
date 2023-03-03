package com.besysoft.bootcampspringboot.dominios;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "personajes_peliculas_series",
            joinColumns = @JoinColumn(name = "pelicula_serie_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id")
    )
    private List<Personaje> personajes;



    public PeliculaSerie(Long id, String titulo, LocalDate fechaDeCreacion, Integer calificacion) {
        this.id = id;
        this.titulo = titulo;
        this.fechaDeCreacion = fechaDeCreacion;
        this.calificacion = calificacion;
        this.personajes = new ArrayList<>();


    }
}

