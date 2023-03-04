package com.besysoft.bootcampspringboot.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeneroResponseDto {

    private Long id;
    private String nombre;
    private List<PeliculaSerieResponseDto>peliculas;

    public GeneroResponseDto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}

