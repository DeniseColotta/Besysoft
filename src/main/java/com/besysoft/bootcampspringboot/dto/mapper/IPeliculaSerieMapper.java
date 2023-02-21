package com.besysoft.bootcampspringboot.dto.mapper;


import com.besysoft.bootcampspringboot.dominios.PeliculaSerie;
import com.besysoft.bootcampspringboot.dto.request.PeliculaSerieRequestDto;
import com.besysoft.bootcampspringboot.dto.response.PeliculaSerieResponseDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface IPeliculaSerieMapper {

   PeliculaSerie mapToEntity(PeliculaSerieRequestDto dto);

   PeliculaSerieResponseDto mapToDto(PeliculaSerie entity);


}

