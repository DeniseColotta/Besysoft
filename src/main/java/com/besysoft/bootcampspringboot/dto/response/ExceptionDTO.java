package com.besysoft.bootcampspringboot.dto.response;

import lombok.*;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDTO {
    private int estado;
    private String mensaje;
    private Map<String, String> detalle;
}
