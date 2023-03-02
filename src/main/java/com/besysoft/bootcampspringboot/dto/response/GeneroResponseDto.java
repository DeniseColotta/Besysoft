package com.besysoft.bootcampspringboot.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GeneroResponseDto {

    private Long id;
    private String nombre;
    private List<PeliculaSerieResponseDto>peliculas;

    public GeneroResponseDto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
