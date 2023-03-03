package com.besysoft.bootcampspringboot.servicios.implementacionesDataBase;

import com.besysoft.bootcampspringboot.datos.DatosDummy;
import com.besysoft.bootcampspringboot.dominios.Genero;
import com.besysoft.bootcampspringboot.dominios.Personaje;
import com.besysoft.bootcampspringboot.dto.mapper.IGeneroMapper;
import com.besysoft.bootcampspringboot.dto.request.GeneroRequestDto;
import com.besysoft.bootcampspringboot.dto.request.PersonajeRequestDto;
import com.besysoft.bootcampspringboot.dto.response.GeneroResponseDto;
import com.besysoft.bootcampspringboot.dto.response.PersonajeResponseDto;
import com.besysoft.bootcampspringboot.repositorios.database.IGeneroRepository;
import com.besysoft.bootcampspringboot.servicios.interfaces.IGeneroService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class GeneroServiceImplTest {

    @MockBean
    private IGeneroRepository repository;

    @Autowired
    private IGeneroMapper mapper;

    @Autowired
    private IGeneroService service;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getAll() {
        when(repository.findAll())
                .thenReturn(DatosDummy.getGeneros());
        List<GeneroResponseDto> iterableGeneros = service.getAll();

        assertThat(iterableGeneros.size()).isEqualTo(3);
        verify(repository, times(1)).findAll();
    }


    @Test
    void filtrarPeliculaPorGenero() {
        String nombreGenero = "comedia";
        Genero generoBuscado = DatosDummy.getGeneroUno();
        Optional<Genero> optionalGenero = Optional.of(generoBuscado);
        when(repository.findByNombre(nombreGenero)).thenReturn(optionalGenero);
        List<Genero> generos = Arrays.asList(DatosDummy.getGeneroUno(), DatosDummy.getGeneroDos());
        when(repository.findGeneroByPelicula(nombreGenero)).thenReturn(generos);
        List<GeneroResponseDto> resultado = service.filtrarPeliculaPorGenero(nombreGenero);
        List<GeneroResponseDto> generoEsperadoPorPelicula = generos.stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
        assertEquals(generoEsperadoPorPelicula, resultado);
    }


    @Test
    void agregarGenero() {
        GeneroRequestDto requestDto = new GeneroRequestDto(4L, "Drama");
        Genero generoEntity = new Genero(4L, requestDto.getNombre(), null);

        GeneroResponseDto responseDto = new GeneroResponseDto(generoEntity.getId(), generoEntity.getNombre());

        when(repository.save(any(Genero.class))).thenReturn(generoEntity);
        mapper.mapToEntity(requestDto);
        mapper.mapToDto(generoEntity);

        GeneroResponseDto resultado = service.agregarGenero(requestDto);

        assertEquals(responseDto, resultado);
    }


    @Test
    void updateGenero() {

        Genero generoExistente = DatosDummy.getGeneroUno();
        when(repository.findById(1L)).thenReturn(Optional.of(generoExistente));

        GeneroRequestDto generoAct = new GeneroRequestDto();
        generoAct.setId(1L);
        generoAct.setNombre("Terror");

        mapper.mapToEntity(generoAct);

        generoExistente.setNombre(generoAct.getNombre());

        when(repository.save(any(Genero.class))).thenReturn(generoExistente);

        mapper.mapToDto(generoExistente);
        GeneroResponseDto generoResponseDto = new GeneroResponseDto();
        generoResponseDto.setId(generoExistente.getId());
        generoResponseDto.setNombre(generoExistente.getNombre());

        GeneroResponseDto resultado = service.updateGenero(1L, generoAct);

        assertEquals(generoResponseDto, resultado);
    }


}