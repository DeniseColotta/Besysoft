package com.besysoft.bootcampspringboot.repositorios.database;

import com.besysoft.bootcampspringboot.datos.DatosDummy;
import com.besysoft.bootcampspringboot.dominios.Genero;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class IGeneroRepositoryTest {

    @Autowired
    private IGeneroRepository repository;

    @BeforeEach
    void setUp() {
        repository.save( DatosDummy.getGeneroUno());
        repository.save(DatosDummy.getGeneroDos());

    }
    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void findByNombre() {
        String nombre="comedia";
        List<Genero> generos = repository.findAll()
                .stream()
                .filter(p -> p.getNombre().equals(nombre))
                .collect(Collectors.toList());

        Optional<Genero> oGenero= repository.findByNombre(nombre);

        assertThat(oGenero.isPresent()).isTrue();
        assertThat(oGenero.get().getNombre()).isEqualTo(nombre);
    }


    @Test
    void findGeneroByPelicula() {
        String nombre="comedia";
        List<Genero>generos=repository.findAll()
                .stream().filter(genero -> genero.getNombre().equalsIgnoreCase(nombre)).collect(Collectors.toList());
        List<Genero> genero= repository.findGeneroByPelicula(DatosDummy.getGeneroUno().getNombre());
        assertThat(genero.size()).isEqualTo(1);
        assertThat(genero.get(0).getNombre()).isEqualTo(nombre);
    }
}