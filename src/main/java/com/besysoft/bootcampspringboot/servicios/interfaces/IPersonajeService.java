package com.besysoft.bootcampspringboot.servicios.interfaces;

import com.besysoft.bootcampspringboot.Entidades.Personaje;

import java.util.List;


public interface IPersonajeService {
    List<Personaje> getAll();

    List<Personaje> filtrarPersonajePorNombre(String nombre);

    List<Personaje> filtrarPersonajesPorEdad(int edad);

    List<Personaje> filtrarPersonajesPorRangoEdad(int desde, int hasta);

    Personaje agregarPersonaje(Personaje nuevoPersonaje);

    Personaje updatePersonaje(long id, Personaje personaje);

}
