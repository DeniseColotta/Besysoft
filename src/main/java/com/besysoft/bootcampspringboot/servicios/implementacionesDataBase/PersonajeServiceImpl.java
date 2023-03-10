package com.besysoft.bootcampspringboot.servicios.implementacionesDataBase;

import com.besysoft.bootcampspringboot.dto.mapper.IPersonajeMapper;
import com.besysoft.bootcampspringboot.dto.request.PersonajeRequestDto;
import com.besysoft.bootcampspringboot.dto.response.PersonajeResponseDto;
import com.besysoft.bootcampspringboot.dominios.Personaje;
import com.besysoft.bootcampspringboot.excepciones.ExistException;
import com.besysoft.bootcampspringboot.excepciones.NotFoundException;

import com.besysoft.bootcampspringboot.repositorios.database.IPersonajeRepository;
import com.besysoft.bootcampspringboot.servicios.interfaces.IPersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
public class PersonajeServiceImpl implements IPersonajeService {

    @Autowired
    private IPersonajeRepository repository;

    @Autowired
    private IPersonajeMapper personajeMapper;

    @Transactional(readOnly = true)
    @Override
    public List<PersonajeResponseDto> getAll() {

        List<Personaje> personajes = repository.findAll();
        List<PersonajeResponseDto> personajeDto = personajes.stream()
                .map(personajeMapper::mapToDto)
                .collect(Collectors.toList());

        return personajeDto;
    }

    @Transactional(readOnly = true)
    @Override
    public PersonajeResponseDto filtrarPersonajePorNombre(String nombre) {
        Optional<Personaje>personaje= repository.findPersonajeByNombre(nombre);
        PersonajeResponseDto personajes= personajeMapper.mapToDto(personaje.get());
        return personajes;

    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonajeResponseDto> filtrarPersonajesPorEdad(int edad) {
        List<Personaje> personajes = repository.findPersonajesByEdad(edad);

        List<PersonajeResponseDto> personajeDto = personajes.stream()
                .map(personajeMapper::mapToDto)
                .collect(Collectors.toList());

        return personajeDto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonajeResponseDto> filtrarPersonajesPorRangoEdad(int desde, int hasta) {
        List<Personaje> personajes = repository.findPersonajesByRangoEdadBetween(desde,hasta);

        List<PersonajeResponseDto> personajeDto = personajes.stream()
                .map(personajeMapper::mapToDto)
                .collect(Collectors.toList());

        return personajeDto;
    }

    @Transactional(readOnly = false)
    @Override
    public PersonajeResponseDto agregarPersonaje(PersonajeRequestDto nuevoPersonaje) {

        Optional<Personaje> oPersonaje = repository.findPersonajeByNombre(nuevoPersonaje.getNombre());

        if (oPersonaje.isPresent()) {
            throw new ExistException(String.format("el personaje %s ya existe", nuevoPersonaje.getNombre()),
                    new RuntimeException("Causa Original"));
        }
        Personaje personaje= repository.save(personajeMapper.mapToEntity(nuevoPersonaje));
        return personajeMapper.mapToDto(personaje);

    }

    @Transactional(readOnly = false)
    @Override
    public PersonajeResponseDto updatePersonaje(long id, PersonajeRequestDto personajeAct) {
        Optional<Personaje> oPersonaje = repository.findById(id);

        if (!oPersonaje.isPresent()) {
            throw new NotFoundException(String.format("el id %s ingresado no existe", id),
                    new RuntimeException("Causa Original"));

        }
        personajeAct.setNombre(personajeAct.getNombre());
        personajeAct.setEdad(personajeAct.getEdad());
        personajeAct.setPeso(personajeAct.getPeso());
        personajeAct.setHistoria(personajeAct.getHistoria());

        Personaje personaje=repository.save(personajeMapper.mapToEntity(personajeAct));
        return personajeMapper.mapToDto(personaje);

    }
}