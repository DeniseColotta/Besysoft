package com.besysoft.bootcampspringboot.repositorios.memory.interfaces;

import com.besysoft.bootcampspringboot.Entidades.Genero;

import java.util.List;
import java.util.Optional;

public interface GeneroRepository {
    List<Genero> obtenerTodos();

    List<Genero> filtrarPeliculaPorGenero(String nombreGenero);

    List<Genero> agregarGenero(Genero nuevoGenero);

    Genero updateGenero(long id, Genero genero);

    Optional<Genero> buscarGenero(String nombre);


}
