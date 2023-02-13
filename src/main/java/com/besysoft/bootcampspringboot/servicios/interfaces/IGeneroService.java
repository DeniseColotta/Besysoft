package com.besysoft.bootcampspringboot.servicios.interfaces;

import com.besysoft.bootcampspringboot.Entidades.Genero;


import java.util.List;
import java.util.Optional;

public interface IGeneroService {
    List<Genero> getAll();

    List<Genero> filtrarPeliculaPorGenero(String nombreGenero);

    Genero agregarGenero(Genero nuevoGenero);

    Genero updateGenero(long id, Genero genero);

    Optional<Genero> findByNombre(String nombre);

    boolean existePorNombre(String nombre);
}
