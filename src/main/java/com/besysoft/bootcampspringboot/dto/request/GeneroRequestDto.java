package com.besysoft.bootcampspringboot.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeneroRequestDto {

    private Long id;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z ]+$", message = "solamente permite caracteres de la A - Z")
    @Size(min = 1, max = 50)
    private String nombre;

    private List<PeliculaSerieRequestDto> peliculas;

    public GeneroRequestDto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
