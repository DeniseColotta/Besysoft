package com.besysoft.bootcampspringboot.dominios;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "personajes")
    private List<PeliculaSerie> peliculasSeries;


    public Personaje(Long id, String nombre, Integer edad, Double peso, String historia) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
        this.id = id;
        this.peliculasSeries = new ArrayList<>();

    }
}
