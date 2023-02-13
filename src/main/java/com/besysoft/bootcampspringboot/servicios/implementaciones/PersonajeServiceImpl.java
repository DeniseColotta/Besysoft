package com.besysoft.bootcampspringboot.servicios.implementaciones;

import com.besysoft.bootcampspringboot.Entidades.Personaje;
import com.besysoft.bootcampspringboot.repositorios.database.IPersonajeRepository;
import com.besysoft.bootcampspringboot.servicios.interfaces.IPersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
public class PersonajeServiceImpl implements IPersonajeService {

    @Autowired
    private IPersonajeRepository personajeRepository;

    @Override
    public List<Personaje> getAll() {
        return personajeRepository.findAll();
    }

    @Override
    public Optional<Personaje> filtrarPersonajePorNombre(String nombre) {
        return personajeRepository.filtrarPersonajePorNombre(nombre);
    }

    @Override
    public List<Personaje> filtrarPersonajesPorEdad(int edad) {
        return personajeRepository.filtrarPersonajesPorEdad(edad);
    }

    @Override
    public List<Personaje> filtrarPersonajesPorRangoEdad(int desde, int hasta) {
        return personajeRepository.filtrarPersonajesPorRangoEdad(desde, hasta);
    }

    @Override
    public Personaje agregarPersonaje(Personaje nuevoPersonaje) {

        Optional<Personaje> oPersonaje = personajeRepository.filtrarPersonajePorNombre(nuevoPersonaje.getNombre());

        if (oPersonaje.isPresent()) {
            throw new RuntimeException("El personaje ingresado ya existe");
        }

        return personajeRepository.save(nuevoPersonaje);
    }

    @Override
    public Personaje updatePersonaje(long id, Personaje personajeAct) {
        Optional<Personaje> oPersonaje = personajeRepository.findById(id);

        if (!oPersonaje.isPresent()) {
            throw new RuntimeException("El Id ingresado no existe");

        }
        personajeAct.setNombre(personajeAct.getNombre());
        personajeAct.setEdad(personajeAct.getEdad());
        personajeAct.setPeso(personajeAct.getPeso());
        personajeAct.setHistoria(personajeAct.getHistoria());


        return personajeRepository.save(personajeAct);
    }
}