package com.besysoft.bootcampspringboot.dto.mapper;

import com.besysoft.bootcampspringboot.dto.request.PeliculaSerieRequestDto;
import com.besysoft.bootcampspringboot.dto.response.PeliculaSerieResponseDto;
import com.besysoft.bootcampspringboot.modelos.Genero;
import com.besysoft.bootcampspringboot.dto.request.GeneroRequestDto;
import com.besysoft.bootcampspringboot.dto.response.GeneroResponseDto;
import com.besysoft.bootcampspringboot.modelos.PeliculaSerie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IGeneroMapper {

    @Mapping(source="pelicula",target = "peliculaSerie")
    Genero mapToEntity(GeneroRequestDto dto);

    @Mapping(source="peliculaSerie",target = "pelicula")
    GeneroResponseDto mapToDto(Genero genero);


}
