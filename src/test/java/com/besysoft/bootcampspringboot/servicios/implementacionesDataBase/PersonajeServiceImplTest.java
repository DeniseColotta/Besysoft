package com.besysoft.bootcampspringboot.servicios.implementacionesDataBase;

import com.besysoft.bootcampspringboot.datos.DatosDummy;
import com.besysoft.bootcampspringboot.dominios.Personaje;
import com.besysoft.bootcampspringboot.dto.mapper.IPersonajeMapper;
import com.besysoft.bootcampspringboot.dto.request.PersonajeRequestDto;
import com.besysoft.bootcampspringboot.dto.response.PersonajeResponseDto;
import com.besysoft.bootcampspringboot.repositorios.database.IPersonajeRepository;
import com.besysoft.bootcampspringboot.servicios.interfaces.IPersonajeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonajeServiceImplTest {

    @MockBean
    private IPersonajeRepository repository;

    @Autowired
    private IPersonajeMapper mapper;

    @Autowired
    private IPersonajeService service;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll() {
            when(repository.findAll())
                    .thenReturn(DatosDummy.getPersonajes());
            List<PersonajeResponseDto> personajes= service.getAll();

            assertThat(personajes.size()).isEqualTo(2);
            verify(repository,times(1)).findAll();
        }


    @Test
    void filtrarPersonajePorNombre() {
        String nombre= "Personaje1";

        when(repository.findPersonajeByNombre(nombre)).thenReturn(Optional.of(DatosDummy.getPersonajeUno()));

        PersonajeResponseDto personajeDto = new PersonajeResponseDto();
        personajeDto.setId(DatosDummy.getPersonajeUno().getId());
        personajeDto.setNombre(DatosDummy.getPersonajeUno().getNombre());
        personajeDto.setEdad(DatosDummy.getPersonajeUno().getEdad());
        personajeDto.setPeso(DatosDummy.getPersonajeUno().getPeso());
        personajeDto.setHistoria(DatosDummy.getPersonajeUno().getHistoria());

        mapper.mapToDto(DatosDummy.getPersonajeUno());

        PersonajeResponseDto result = service.filtrarPersonajePorNombre(nombre);

        assertEquals(result,personajeDto);
        verify(repository).findPersonajeByNombre(nombre);
    }

    @Test
    void filtrarPersonajesPorEdad() {
        Integer edad1= 10;

        when(repository.findPersonajesByEdad(edad1)).thenReturn(DatosDummy.getPersonajes());

        List<PersonajeResponseDto> result = service.filtrarPersonajesPorEdad(10);

        assertThat(result).hasSize(2);
        verify(repository).findPersonajesByEdad(edad1);
    }
//si
    @Test
    void filtrarPersonajesPorRangoEdad() {
        Integer edad1= 10;
        Integer edad2=60;


        when(repository.findPersonajesByRangoEdadBetween(edad1,edad2)).thenReturn(DatosDummy.getPersonajes());

        List<PersonajeResponseDto> result = service.filtrarPersonajesPorRangoEdad(10,60);

        assertThat(result).hasSize(2);
        verify(repository).findPersonajesByRangoEdadBetween(edad1,edad2);
    }

    //si
    @Test
    void agregarPersonaje() {
        PersonajeRequestDto requestDto = new PersonajeRequestDto();
        requestDto.setNombre("Gru");
        requestDto.setEdad(10);
        requestDto.setPeso(30D);
        requestDto.setHistoria(null);
        Personaje personajeEntity = new Personaje(1L, requestDto.getNombre(),requestDto.getEdad(),requestDto.getPeso(),null);

        PersonajeResponseDto responseDto = new PersonajeResponseDto();
        responseDto.setId(personajeEntity.getId());
        responseDto.setEdad(personajeEntity.getEdad());
        responseDto.setPeso(personajeEntity.getPeso());
        responseDto.setNombre(personajeEntity.getNombre());
        responseDto.setHistoria(personajeEntity.getHistoria());

        when(repository.save(any(Personaje.class))).thenReturn(personajeEntity);
        mapper.mapToEntity(requestDto);
        mapper.mapToDto(personajeEntity);

        PersonajeResponseDto resultado = service.agregarPersonaje(requestDto);

        assertEquals(responseDto, resultado);
    }

    @Test
    void updatePersonaje() {
        Personaje personajeExistente = DatosDummy.getPersonajeUno();
        when(repository.findById(1L)).thenReturn(Optional.of(personajeExistente));

        PersonajeRequestDto personajeAct = new PersonajeRequestDto();
        personajeAct.setId(1L);
        personajeAct.setNombre("Personaje5");
        personajeAct.setEdad(70);
        personajeAct.setPeso(60D);
        personajeAct.setHistoria(null);

        mapper.mapToEntity(personajeAct);

        personajeExistente.setNombre(personajeAct.getNombre());
        personajeExistente.setEdad(personajeAct.getEdad());
        personajeExistente.setPeso(personajeAct.getPeso());
        personajeExistente.setHistoria(personajeAct.getHistoria());

        when(repository.save(any(Personaje.class))).thenReturn(personajeExistente);

        mapper.mapToDto(personajeExistente);
        PersonajeResponseDto personajeResponseDto = new PersonajeResponseDto();
        personajeResponseDto.setId(personajeExistente.getId());
        personajeResponseDto.setNombre(personajeExistente.getNombre());
        personajeResponseDto.setEdad(personajeExistente.getEdad());
        personajeResponseDto.setPeso(personajeExistente.getPeso());
        personajeResponseDto.setHistoria(personajeExistente.getHistoria());

        PersonajeResponseDto resultado = service.updatePersonaje(1L, personajeAct);

        assertEquals(personajeResponseDto, resultado);

    }

}