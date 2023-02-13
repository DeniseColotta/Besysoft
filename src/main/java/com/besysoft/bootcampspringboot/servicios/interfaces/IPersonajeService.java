package com.besysoft.bootcampspringboot.servicios.interfaces;

import com.besysoft.bootcampspringboot.Entidades.Personaje;

import java.util.List;
import java.util.Optional;


public interface IPersonajeService {
    List<Personaje> getAll();

    Optional<Personaje> filtrarPersonajePorNombre(String nombre);

    List<Personaje> filtrarPersonajesPorEdad(int edad);

    List<Personaje> filtrarPersonajesPorRangoEdad(int desde, int hasta);

    Personaje agregarPersonaje(Personaje nuevoPersonaje);

    Personaje updatePersonaje(long id, Personaje personaje);

}
