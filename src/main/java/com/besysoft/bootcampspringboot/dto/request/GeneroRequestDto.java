package com.besysoft.bootcampspringboot.dto.request;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class GeneroRequestDto {

    private Long id;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]+$", message = "solamente permite caracteres de la A - Z")
    @Size(min = 1, max = 50)
    private String nombre;

    private List<PeliculaSerieRequestDto> pelicula;
}
