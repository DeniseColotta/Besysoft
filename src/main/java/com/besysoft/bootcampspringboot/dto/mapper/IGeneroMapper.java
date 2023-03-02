package com.besysoft.bootcampspringboot.dto.mapper;

import com.besysoft.bootcampspringboot.dominios.Genero;
import com.besysoft.bootcampspringboot.dto.request.GeneroRequestDto;
import com.besysoft.bootcampspringboot.dto.response.GeneroResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IGeneroMapper {

    @Mapping(source="peliculas",target = "peliculaSerie")
    Genero mapToEntity(GeneroRequestDto dto);

    @Mapping(source="peliculaSerie",target = "peliculas")
    GeneroResponseDto mapToDto(Genero genero);


}
