package com.besysoft.bootcampspringboot.datos;

import com.besysoft.bootcampspringboot.dominios.Genero;
import com.besysoft.bootcampspringboot.dominios.PeliculaSerie;
import com.besysoft.bootcampspringboot.dominios.Personaje;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatosDummy {


    public static PeliculaSerie getPeliculaUno(){
        return  new PeliculaSerie(1L, "Pelicula1", LocalDate.of(2022, 2, 1), 4);}

    public static PeliculaSerie getPeliculaDos(){
        return  new PeliculaSerie(2L, "Pelicula2", LocalDate.of(2022, 6, 1), 5);

    }
    public static PeliculaSerie getPeliculaTres() {
        return new PeliculaSerie(3L, "Pelicula3", LocalDate.of(2023, 6, 1), 3);
    }
    public static List<PeliculaSerie> getPeliculas() {return new ArrayList<>(Arrays.asList(
            new PeliculaSerie(1L, "Pelicula1", LocalDate.of(2022, 2, 1), 4),
            new PeliculaSerie(2L, "Pelicula2", LocalDate.of(2022, 6, 1), 5),
            new PeliculaSerie(3L, "Pelicula3", LocalDate.of(2023, 6, 1), 3)));}


    public static Genero getGeneroUno() {
        return new Genero(1L, "comedia",null);
    }

    public static Genero getGeneroDos() {
        return new Genero(2L, "acción",null);
    }

    public static Genero getGeneroTres() {
        return new Genero(3L, "drama");
    }


    public static List<Genero> getGeneros(){
        return new ArrayList<>(Arrays.asList(
                new Genero(1L, "comedia"),
                new Genero(2L, "accion"),
                new Genero(3L, "drama")));
        }


public static Personaje getPersonajeUno(){
        return new Personaje(1L,"Personaje1",45,80D,null);
    }
    public static Personaje getPersonajeDos(){
        return new Personaje(2L,"Personaje2",30,75D,null);
    }

    public static List<Personaje> getPersonajes(){
        return new ArrayList<>(Arrays.asList(new Personaje(1L,"Personaje1",45,80D,null),
                new Personaje(2L,"Personaje2",30,75D,null)));
    }
}