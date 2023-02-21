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


    public Genero(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.peliculaSerie = new ArrayList<>();
    }
}