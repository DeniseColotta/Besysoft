package com.besysoft.bootcampspringboot.dto.request;


import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class PersonajeRequestDto {
    private Long id;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "solamente permite caracteres de la A - Z y números")
    @Size(min = 1, max = 50)

    private String nombre;
    private int edad;
    private double peso;

    @Size(min = 10, max = 200)
    private String historia;
}
