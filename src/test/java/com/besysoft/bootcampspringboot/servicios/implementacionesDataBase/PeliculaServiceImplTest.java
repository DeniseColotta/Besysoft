package com.besysoft.bootcampspringboot.servicios.implementacionesDataBase;

import com.besysoft.bootcampspringboot.datos.DatosDummy;
import com.besysoft.bootcampspringboot.dominios.PeliculaSerie;
import com.besysoft.bootcampspringboot.dto.mapper.IPeliculaSerieMapper;
import com.besysoft.bootcampspringboot.dto.request.PeliculaSerieRequestDto;
import com.besysoft.bootcampspringboot.dto.response.PeliculaSerieResponseDto;
import com.besysoft.bootcampspringboot.repositorios.database.IPeliculaRepository;
import com.besysoft.bootcampspringboot.servicios.interfaces.IPeliculaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PeliculaServiceImplTest {

        @MockBean
        private IPeliculaRepository repository;

        @Autowired
        private IPeliculaSerieMapper mapper;

        @Autowired
        private IPeliculaService service;

        @BeforeEach
        void setUp(){

        }
        @AfterEach
        void tearDown(){

        }

        @Test
        void getAll() {
            when(repository.findAll())
                    .thenReturn(DatosDummy.getPeliculas());
            List<PeliculaSerieResponseDto> peliculas= service.getAll();

            assertThat(peliculas.size()).isEqualTo(3);
            verify(repository,times(1)).findAll();
        }


   @Test
    void testFiltrarPeliculaPorFecha() {
        LocalDate desde = LocalDate.of(2022, 1, 1);
        LocalDate hasta = LocalDate.of(2022, 12, 31);


        when(repository.findPeliculaByFechaBetween(desde, hasta)).thenReturn(DatosDummy.getPeliculas());

        List<PeliculaSerieResponseDto> result = service.filtrarPeliculaPorFecha("01-01-2022", "31-12-2022");

        assertThat(result).hasSize(3);
       verify(repository).findPeliculaByFechaBetween(desde,hasta);

}

    @Test
    void filtrarPeliculaPorCalificacion() {
        Integer calificacion1= 4;
        Integer calificacion2= 5;


        when(repository.findPeliculaByCalificacionBetween(calificacion1, calificacion2)).thenReturn(DatosDummy.getPeliculas());

        List<PeliculaSerieResponseDto> result = service.filtrarPeliculaPorCalificacion(4,5);

        assertThat(result).hasSize(3);
        verify(repository).findPeliculaByCalificacionBetween(calificacion1,calificacion2);
    }

    @Test
    void filtrarPeliculaTitulo() {
        String nombre= "Pelicula1";

        when(repository.findPeliculaByTitulo(nombre)).thenReturn(Optional.of(DatosDummy.getPeliculaUno()));

        PeliculaSerieResponseDto peliculaDto = new PeliculaSerieResponseDto();
        peliculaDto.setId(DatosDummy.getPeliculaUno().getId());
        peliculaDto.setTitulo(DatosDummy.getPeliculaUno().getTitulo());
        peliculaDto.setCalificacion(DatosDummy.getPeliculaUno().getCalificacion());
        peliculaDto.setFechaDeCreacion(DatosDummy.getPeliculaUno().getFechaDeCreacion());

        mapper.mapToDto(DatosDummy.getPeliculaUno());

        PeliculaSerieResponseDto result = service.filtrarPeliculaTitulo(nombre);

        assertEquals(result,peliculaDto);
        verify(repository).findPeliculaByTitulo(nombre);



    }
//si
    @Test
    void agregarPelicula() {

            PeliculaSerieRequestDto requestDto = new PeliculaSerieRequestDto();
            requestDto.setTitulo("Los minions");
            requestDto.setFechaDeCreacion(null);
            requestDto.setCalificacion(3);
           PeliculaSerie peliculaEntity = new PeliculaSerie(1L, requestDto.getTitulo(), null,requestDto.getCalificacion());

           PeliculaSerieResponseDto responseDto = new PeliculaSerieResponseDto();
           responseDto.setId(peliculaEntity.getId());
           responseDto.setTitulo(peliculaEntity.getTitulo());
           responseDto.setFechaDeCreacion(null);
           responseDto.setCalificacion(peliculaEntity.getCalificacion());

            when(repository.save(any(PeliculaSerie.class))).thenReturn(peliculaEntity);
            mapper.mapToEntity(requestDto);
            mapper.mapToDto(peliculaEntity);

            PeliculaSerieResponseDto resultado = service.agregarPelicula(requestDto);

            assertEquals(responseDto, resultado);
        }


    @Test
    void updatePelicula() {
        PeliculaSerie peliculaExistente = DatosDummy.getPeliculaUno();
        when(repository.findById(1L)).thenReturn(Optional.of(peliculaExistente));

        PeliculaSerieRequestDto peliculaAct = new PeliculaSerieRequestDto();
        peliculaAct.setId(peliculaExistente.getId());
        peliculaAct.setTitulo("Pelicula5");
        peliculaAct.setFechaDeCreacion(peliculaExistente.getFechaDeCreacion());
        peliculaAct.setCalificacion(peliculaExistente.getCalificacion());

        PeliculaSerie peliculaModificado = new PeliculaSerie();
        peliculaModificado.setId(1L);
        peliculaModificado.setTitulo(peliculaAct.getTitulo());
        peliculaModificado.setFechaDeCreacion(peliculaAct.getFechaDeCreacion());
        peliculaModificado.setCalificacion(peliculaAct.getCalificacion());

        mapper.mapToEntity(peliculaAct);

        PeliculaSerieResponseDto peliculaResponseDto = new PeliculaSerieResponseDto();
        peliculaResponseDto.setId(peliculaModificado.getId());
        peliculaResponseDto.setTitulo(peliculaModificado.getTitulo());
        peliculaResponseDto.setFechaDeCreacion(peliculaModificado.getFechaDeCreacion());
        peliculaResponseDto.setCalificacion(peliculaModificado.getCalificacion());

        mapper.mapToDto(peliculaModificado);

        PeliculaSerieResponseDto resultado = service.updatePelicula(1L, peliculaAct);

        assertEquals(resultado, peliculaResponseDto);
    }
}