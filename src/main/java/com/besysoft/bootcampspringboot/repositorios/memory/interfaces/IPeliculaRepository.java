package com.besysoft.bootcampspringboot.repositorios.memory.interfaces;

import com.besysoft.bootcampspringboot.dominios.PeliculaSerie;


import java.util.List;
import java.util.Optional;

public interface IPeliculaRepository {
    List<PeliculaSerie> obtenerTodos();

    List<PeliculaSerie> filtrarPeliculaPorFecha(String desde, String hasta);

    List<PeliculaSerie> filtrarPeliculaPorCalificacion(int desde, int hasta);

    Optional<PeliculaSerie> filtrarPeliculaTitulo(String titulo);

    PeliculaSerie agregarPelicula(PeliculaSerie nuevaPelicula);

    PeliculaSerie updatePelicula(long id, PeliculaSerie pelicula);


}
