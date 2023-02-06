package com.besysoft.bootcampspringboot.servicios.interfaces;


import com.besysoft.bootcampspringboot.Entidades.PeliculaSerie;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IPeliculaService {

    List<PeliculaSerie> getAll();
    List<PeliculaSerie> filtrarPeliculaPorFecha(String desde, String hasta);

    List<PeliculaSerie> filtrarPeliculaPorCalificacion(int desde, int hasta);

    Optional<PeliculaSerie> filtrarPeliculaTitulo(String titulo);

    PeliculaSerie agregarPelicula(PeliculaSerie nuevaPelicula);

    PeliculaSerie updatePelicula(long id, PeliculaSerie pelicula);
    List<PeliculaSerie> filtrarPeliculaPorGenero(String nombreGenero);

}
