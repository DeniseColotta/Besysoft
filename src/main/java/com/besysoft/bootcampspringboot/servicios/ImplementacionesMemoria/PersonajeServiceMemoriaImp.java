package com.besysoft.bootcampspringboot.servicios.ImplementacionesMemoria;

import com.besysoft.bootcampspringboot.dominios.Personaje;
import com.besysoft.bootcampspringboot.dto.mapper.IPersonajeMapper;
import com.besysoft.bootcampspringboot.dto.request.PersonajeRequestDto;
import com.besysoft.bootcampspringboot.dto.response.PersonajeResponseDto;
import com.besysoft.bootcampspringboot.repositorios.memory.interfaces.IPersonajeRepository;
import com.besysoft.bootcampspringboot.servicios.interfaces.IPersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "memory")
public class PersonajeServiceMemoriaImp implements IPersonajeService {

    @Autowired
    private IPersonajeRepository repository;

    @Autowired
    private IPersonajeMapper personajeMapper;


    @Override
    public List<PersonajeResponseDto> getAll() {

        List<Personaje> personajes = repository.obtenerTodos();
        List<PersonajeResponseDto> personajeDto = personajes.stream()
                .map(personajeMapper::mapToDto)
                .collect(Collectors.toList());

        return personajeDto;
    }


    @Override
    public PersonajeResponseDto filtrarPersonajePorNombre(String nombre) {
        Optional<Personaje> personaje = repository.filtrarPersonajePorNombre(nombre);
        PersonajeResponseDto personajes = personajeMapper.mapToDto(personaje.get());
        return personajes;

    }


    @Override
    public List<PersonajeResponseDto> filtrarPersonajesPorEdad(int edad) {
        List<Personaje> personajes = repository.filtrarPersonajesPorEdad(edad);

        List<PersonajeResponseDto> personajeDto = personajes.stream()
                .map(personajeMapper::mapToDto)
                .collect(Collectors.toList());

        return personajeDto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonajeResponseDto> filtrarPersonajesPorRangoEdad(int desde, int hasta) {
        List<Personaje> personajes = repository.filtrarPersonajesPorRangoEdad(desde, hasta);

        List<PersonajeResponseDto> personajeDto = personajes.stream()
                .map(personajeMapper::mapToDto)
                .collect(Collectors.toList());

        return personajeDto;
    }

    @Transactional(readOnly = false)
    @Override
    public PersonajeResponseDto agregarPersonaje(PersonajeRequestDto nuevoPersonaje) {

        Optional<Personaje> oPersonaje = repository.filtrarPersonajePorNombre(nuevoPersonaje.getNombre());

        if (oPersonaje.isPresent()) {
            throw new RuntimeException("El personaje ingresado ya existe");
        }
        Personaje personaje = repository.agregarPersonaje(personajeMapper.mapToEntity(nuevoPersonaje));
        return personajeMapper.mapToDto(personaje);

    }

    @Transactional(readOnly = false)
    @Override
    public PersonajeResponseDto updatePersonaje(long id, PersonajeRequestDto personajeAct) {
        Optional<Personaje> oPersonaje = repository.obtenerTodos().stream().
                filter(pr -> pr.getId() == id)
                .findAny();

        if (!oPersonaje.isPresent()) {
            throw new RuntimeException("El Id ingresado no existe");

        }
        personajeAct.setNombre(personajeAct.getNombre());
        personajeAct.setEdad(personajeAct.getEdad());
        personajeAct.setPeso(personajeAct.getPeso());
        personajeAct.setHistoria(personajeAct.getHistoria());

        Personaje personaje = repository.updatePersonaje(id, personajeMapper.mapToEntity(personajeAct));
        return personajeMapper.mapToDto(personaje);

    }
}

