package com.besysoft.bootcampspringboot.repositorios.memory.interfaces;

import com.besysoft.bootcampspringboot.dominios.Personaje;

import java.util.List;
import java.util.Optional;

public interface IPersonajeRepository {

    List<Personaje> obtenerTodos();

    Optional<Personaje> filtrarPersonajePorNombre(String nombre);

    List<Personaje> filtrarPersonajesPorEdad(int edad);

    List<Personaje> filtrarPersonajesPorRangoEdad(int desde, int hasta);

    Personaje agregarPersonaje(Personaje nuevoPersonaje);

    Personaje updatePersonaje(long id, Personaje personaje);


}
