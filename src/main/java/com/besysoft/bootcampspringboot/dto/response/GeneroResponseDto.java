package com.besysoft.bootcampspringboot.dto.response;

import com.besysoft.bootcampspringboot.modelos.PeliculaSerie;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GeneroResponseDto {

    private Long id;
    private String nombre;
    private List<PeliculaSerieResponseDto>pelicula;

}
