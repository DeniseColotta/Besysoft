package com.besysoft.bootcampspringboot.repositorios.database;

import com.besysoft.bootcampspringboot.datos.DatosDummy;
import com.besysoft.bootcampspringboot.dominios.PeliculaSerie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IPeliculaRepositoryTest {

    @Autowired
    private IPeliculaRepository repository;

    @BeforeEach
    void setUp() {

        repository.save(DatosDummy.getPeliculaUno());
        repository.save( DatosDummy.getPeliculaDos());
        repository.save(DatosDummy.getPeliculaTres());

    }
    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void findPeliculaByFechaBetween() {

            LocalDate fecha1 = LocalDate.of(2000,01,20);
            LocalDate fecha2 = LocalDate.of(2010,01,20);

            List<PeliculaSerie> peliculas = repository.findAll()
                .stream()
                .filter(p -> p.getFechaDeCreacion().isAfter(fecha1) && p.getFechaDeCreacion().isBefore(fecha2))
                .collect(Collectors.toList());

            List<PeliculaSerie> peliculasPorFecha = repository.findPeliculaByFechaBetween(fecha1, fecha2);

            assertNotNull(peliculas);
            assertThat(assertThat(peliculas.size()).isEqualTo(0));
            assertEquals(peliculas,peliculasPorFecha);


    }


    @Test
    void findPeliculaByCalificacionBetween() {

        Integer desde = 4;
        Integer hasta = 5;
        List<PeliculaSerie> peliculas = repository.findAll()
                .stream()
                .filter(p -> p.getCalificacion() >= desde && p.getCalificacion() <= hasta)
                .collect(Collectors.toList());


        List<PeliculaSerie> pelisPorCalif = repository.findPeliculaByCalificacionBetween(4,5);

        assertThat(peliculas.size()).isEqualTo(2);
        assertEquals(peliculas,pelisPorCalif);
        }



    @Test
    void findPeliculaByTitulo() {
        String nombre="Pelicula1";
        List<PeliculaSerie> pelicula = repository.findAll()
                .stream()
                .filter(p -> p.getTitulo().equals(nombre))
                .collect(Collectors.toList());
        Optional<PeliculaSerie> oPelicula= repository.findPeliculaByTitulo(nombre);

        assertThat(oPelicula.isPresent()).isTrue();
        assertThat(oPelicula.get().getTitulo()).isEqualTo(nombre);
    }
}