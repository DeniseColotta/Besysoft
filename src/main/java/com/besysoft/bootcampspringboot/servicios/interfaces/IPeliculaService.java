package com.besysoft.bootcampspringboot.servicios.interfaces;


import com.besysoft.bootcampspringboot.dto.request.PeliculaSerieRequestDto;
import com.besysoft.bootcampspringboot.dto.response.PeliculaSerieResponseDto;
import com.besysoft.bootcampspringboot.modelos.PeliculaSerie;

import java.util.List;
import java.util.Optional;

public interface IPeliculaService {

    List<PeliculaSerieResponseDto> getAll();

    List<PeliculaSerieResponseDto> filtrarPeliculaPorFecha(String desde, String hasta);

    List<PeliculaSerieResponseDto> filtrarPeliculaPorCalificacion(int desde, int hasta);

    PeliculaSerieResponseDto filtrarPeliculaTitulo(String titulo);

    PeliculaSerieResponseDto agregarPelicula(PeliculaSerieRequestDto nuevaPelicula);

    PeliculaSerieResponseDto updatePelicula(long id, PeliculaSerieRequestDto pelicula);


}
