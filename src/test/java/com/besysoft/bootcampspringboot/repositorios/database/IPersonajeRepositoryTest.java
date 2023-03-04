package com.besysoft.bootcampspringboot.repositorios.database;

import com.besysoft.bootcampspringboot.datos.DatosDummy;
import com.besysoft.bootcampspringboot.dominios.Personaje;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IPersonajeRepositoryTest {

    @Autowired
    private IPersonajeRepository repository;

    @BeforeEach
    void setUp() {
        repository.save(DatosDummy.getPersonajeUno());
        repository.save( DatosDummy.getPersonajeDos());


    }
    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }


    @Test
    void findPersonajeByNombre() {
        String nombre="Personaje1";
        List<Personaje> personajes = repository.findAll()
                .stream()
                .filter(p -> p.getNombre().equals(nombre))
                .collect(Collectors.toList());

        Optional<Personaje> oPersonaje= repository.findPersonajeByNombre(nombre);

        assertThat(oPersonaje.isPresent()).isTrue();
        assertThat(oPersonaje.get().getNombre()).isEqualTo(nombre);
    }



    @Test
    void findPersonajesByEdad() {
        Integer edad=45;
        List<Personaje> personajes = repository.findAll()
                .stream()
                .filter(p -> p.getEdad().equals(edad))
                .collect(Collectors.toList());

       List<Personaje> personajesPorEdad= repository.findPersonajesByEdad(edad);

        assertThat(personajesPorEdad.size()).isEqualTo(1);
        assertThat(personajesPorEdad.get(0).getEdad()).isEqualTo(edad);
    }

    @Test
    void findPersonajesByRangoEdadBetween() {
        Integer desde=40;
        Integer hasta=60;

        List<Personaje> personajes = repository.findAll()
                .stream()
                .filter(p -> p.getEdad() >= desde && p.getEdad() <= hasta)
                .collect(Collectors.toList());
        List<Personaje> personajesPorEdad=repository.findPersonajesByRangoEdadBetween(desde,hasta);

        assertThat(personajesPorEdad.size()).isEqualTo(1);
        assertEquals(personajes,personajesPorEdad);
    }
}