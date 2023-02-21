package com.besysoft.bootcampspringboot.servicios.interfaces;


import com.besysoft.bootcampspringboot.dto.request.PeliculaSerieRequestDto;
import com.besysoft.bootcampspringboot.dto.response.PeliculaSerieResponseDto;

import java.util.List;

public interface IPeliculaService {

    List<PeliculaSerieResponseDto> getAll();

    List<PeliculaSerieResponseDto> filtrarPeliculaPorFecha(String desde, String hasta);

    List<PeliculaSerieResponseDto> filtrarPeliculaPorCalificacion(int desde, int hasta);

    PeliculaSerieResponseDto filtrarPeliculaTitulo(String titulo);

    PeliculaSerieResponseDto agregarPelicula(PeliculaSerieRequestDto nuevaPelicula);

    PeliculaSerieResponseDto updatePelicula(long id, PeliculaSerieRequestDto pelicula);


}
