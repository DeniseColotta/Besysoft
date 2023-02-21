package com.besysoft.bootcampspringboot.dto.mapper;

import com.besysoft.bootcampspringboot.dominios.Genero;
import com.besysoft.bootcampspringboot.dto.request.GeneroRequestDto;
import com.besysoft.bootcampspringboot.dto.response.GeneroResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IGeneroMapper {

    @Mapping(source="pelicula",target = "peliculaSerie")
    Genero mapToEntity(GeneroRequestDto dto);

    @Mapping(source="peliculaSerie",target = "pelicula")
    GeneroResponseDto mapToDto(Genero genero);


}
