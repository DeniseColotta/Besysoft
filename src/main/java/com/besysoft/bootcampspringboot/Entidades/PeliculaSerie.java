package com.besysoft.bootcampspringboot.Entidades;



import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "peliculasSeries")
public class PeliculaSerie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(name = "fechaDeCreacion")

    private LocalDate fechaDeCreacion;

    private int calificacion; //1 al 5


    @ManyToMany(mappedBy = "peliculaSerie",
            fetch = FetchType.LAZY)
    private List<Personaje> personaje;



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

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }


    public List<Personaje> getPersonaje() {
        return personaje;
    }

    public void setPersonaje(List<Personaje> personaje) {
        this.personaje = personaje;
    }

   /* public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }*/

    public PeliculaSerie() {
    }

    public PeliculaSerie(Long id, String titulo, LocalDate fechaDeCreacion, int calificacion) {
        this.id = id;
        this.titulo = titulo;
        this.fechaDeCreacion = fechaDeCreacion;
        this.calificacion = calificacion;
        this.personaje = new ArrayList<>();


    }
}

