package com.besysoft.bootcampspringboot.repositorios.memory.interfaces;

import com.besysoft.bootcampspringboot.Entidades.PeliculaSerie;


import java.util.List;
import java.util.Optional;

public interface PeliculaRepository {
    List<PeliculaSerie> crearPelicula();

    List<PeliculaSerie> filtrarPeliculaPorFecha(String desde, String hasta);

    List<PeliculaSerie> filtrarPeliculaPorCalificacion(int desde, int hasta);

    Optional<PeliculaSerie> filtrarPeliculaTitulo(String titulo);

    List<PeliculaSerie> agregarPelicula(PeliculaSerie nuevaPelicula);

    PeliculaSerie updatePelicula(long id, PeliculaSerie pelicula);


}
