package com.besysoft.bootcampspringboot.servicios.interfaces;

import com.besysoft.bootcampspringboot.dto.request.GeneroRequestDto;
import com.besysoft.bootcampspringboot.dto.response.GeneroResponseDto;
import com.besysoft.bootcampspringboot.dominios.Genero;


import java.util.List;


public interface IGeneroService {
    List<GeneroResponseDto> getAll();

    List<GeneroResponseDto> filtrarPeliculaPorGenero(String nombreGenero);

    GeneroResponseDto agregarGenero(GeneroRequestDto nuevoGenero);

    GeneroResponseDto updateGenero(long id, GeneroRequestDto genero);


}
