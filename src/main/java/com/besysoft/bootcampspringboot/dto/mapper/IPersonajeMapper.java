package com.besysoft.bootcampspringboot.dto.mapper;

import com.besysoft.bootcampspringboot.modelos.Personaje;

import com.besysoft.bootcampspringboot.dto.request.PersonajeRequestDto;

import com.besysoft.bootcampspringboot.dto.response.PersonajeResponseDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface IPersonajeMapper {

    Personaje mapToEntity(PersonajeRequestDto dto);
    PersonajeResponseDto mapToDto(Personaje personaje);
}