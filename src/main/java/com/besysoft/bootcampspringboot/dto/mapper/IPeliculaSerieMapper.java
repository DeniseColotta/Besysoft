package com.besysoft.bootcampspringboot.dto.mapper;


import com.besysoft.bootcampspringboot.dto.response.GeneroResponseDto;
import com.besysoft.bootcampspringboot.modelos.Genero;
import com.besysoft.bootcampspringboot.modelos.PeliculaSerie;
import com.besysoft.bootcampspringboot.dto.request.PeliculaSerieRequestDto;
import com.besysoft.bootcampspringboot.dto.response.PeliculaSerieResponseDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IPeliculaSerieMapper {

   PeliculaSerie mapToEntity(PeliculaSerieRequestDto dto);

   PeliculaSerieResponseDto mapToDto(PeliculaSerie entity);


}

