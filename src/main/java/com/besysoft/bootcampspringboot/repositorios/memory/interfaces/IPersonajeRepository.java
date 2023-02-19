package com.besysoft.bootcampspringboot.repositorios.memory.interfaces;

import com.besysoft.bootcampspringboot.modelos.Personaje;

import java.util.List;

public interface IPersonajeRepository {

    List<Personaje> crearPersonaje();

    List<Personaje> filtrarPersonajePorNombre(String nombre);

    List<Personaje> filtrarPersonajesPorEdad(int edad);

    List<Personaje> filtrarPersonajesPorRangoEdad(int desde, int hasta);

    List<Personaje> agregarPersonaje(Personaje nuevoPersonaje);

    Personaje updatePersonaje(long id, Personaje personaje);


}
