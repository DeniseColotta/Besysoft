package com.besysoft.bootcampspringboot.servicios.interfaces;

import com.besysoft.bootcampspringboot.dto.request.PersonajeRequestDto;
import com.besysoft.bootcampspringboot.dto.response.PersonajeResponseDto;
import com.besysoft.bootcampspringboot.modelos.Personaje;

import java.util.List;
import java.util.Optional;


public interface IPersonajeService {
    List<PersonajeResponseDto> getAll();

    PersonajeResponseDto filtrarPersonajePorNombre(String nombre);

    List<PersonajeResponseDto> filtrarPersonajesPorEdad(int edad);

    List<PersonajeResponseDto> filtrarPersonajesPorRangoEdad(int desde, int hasta);

    PersonajeResponseDto agregarPersonaje(PersonajeRequestDto nuevoPersonaje);

    PersonajeResponseDto updatePersonaje(long id, PersonajeRequestDto personaje);

}
