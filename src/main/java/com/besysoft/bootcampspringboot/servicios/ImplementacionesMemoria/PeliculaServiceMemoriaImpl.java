package com.besysoft.bootcampspringboot.servicios.ImplementacionesMemoria;

import com.besysoft.bootcampspringboot.dominios.PeliculaSerie;
import com.besysoft.bootcampspringboot.dto.mapper.IPeliculaSerieMapper;
import com.besysoft.bootcampspringboot.dto.request.PeliculaSerieRequestDto;
import com.besysoft.bootcampspringboot.dto.response.PeliculaSerieResponseDto;
import com.besysoft.bootcampspringboot.excepciones.ExistException;
import com.besysoft.bootcampspringboot.excepciones.NotFoundException;
import com.besysoft.bootcampspringboot.repositorios.memory.interfaces.IPeliculaRepository;
import com.besysoft.bootcampspringboot.servicios.interfaces.IPeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "memory")
public class PeliculaServiceMemoriaImpl implements IPeliculaService {

    @Autowired
    private IPeliculaRepository repositoryPelicula;

    @Autowired
    private IPeliculaSerieMapper peliculaMapper;


    public List<PeliculaSerieResponseDto> getAll() {
        List<PeliculaSerie> peliculas = repositoryPelicula.obtenerTodos();
        List<PeliculaSerieResponseDto> peliculaDto = peliculas.stream()
                .map(peliculaMapper::mapToDto)
                .collect(Collectors.toList());


        return peliculaDto;
    }


    @Override
    public List<PeliculaSerieResponseDto> filtrarPeliculaPorFecha(String desde, String hasta) {


        List<PeliculaSerie> peliculas = repositoryPelicula.filtrarPeliculaPorFecha(desde, hasta);

        List<PeliculaSerieResponseDto> peliculaDto = peliculas.stream()
                .map(peliculaMapper::mapToDto)
                .collect(Collectors.toList());

        return peliculaDto;

    }


    @Override
    public List<PeliculaSerieResponseDto> filtrarPeliculaPorCalificacion(int desde, int hasta) {

        List<PeliculaSerie> peliculas = repositoryPelicula.filtrarPeliculaPorCalificacion(desde, hasta);

        List<PeliculaSerieResponseDto> peliculaDto = peliculas.stream()
                .map(peliculaMapper::mapToDto)
                .collect(Collectors.toList());

        return peliculaDto;

    }


    @Override
    public PeliculaSerieResponseDto filtrarPeliculaTitulo(String titulo) {
        Optional<PeliculaSerie> pelicula = repositoryPelicula.filtrarPeliculaTitulo(titulo);
        PeliculaSerieResponseDto peliculas = peliculaMapper.mapToDto(pelicula.get());
        return peliculas;


    }

    @Override
    public PeliculaSerieResponseDto agregarPelicula(PeliculaSerieRequestDto nuevaPelicula) {
        Optional<PeliculaSerie> oPelicula = repositoryPelicula.filtrarPeliculaTitulo(nuevaPelicula.getTitulo());

        if (oPelicula.isPresent()) {
            throw new ExistException(String.format("la pelicula %s ya existe", nuevaPelicula.getTitulo()),
                    new RuntimeException("Causa Original"));
        }
        PeliculaSerie pelicula = repositoryPelicula.agregarPelicula(peliculaMapper.mapToEntity(nuevaPelicula));
        return peliculaMapper.mapToDto(pelicula);

    }


    @Override
    public PeliculaSerieResponseDto updatePelicula(long id, PeliculaSerieRequestDto pelicula) {
        Optional<PeliculaSerie> oPelicula = repositoryPelicula.obtenerTodos().stream().
                filter(pr -> pr.getId() == id)
                .findAny();

        if (!oPelicula.isPresent()) {
            throw new NotFoundException(String.format("el id %s ingresado no existe", id),
                    new RuntimeException("Causa Original"));
        }
        pelicula.setTitulo(pelicula.getTitulo());
        pelicula.setCalificacion(pelicula.getCalificacion());
        pelicula.setFechaDeCreacion(pelicula.getFechaDeCreacion());

        PeliculaSerie peliculaSerie = repositoryPelicula.updatePelicula(id, peliculaMapper.mapToEntity(pelicula));
        return peliculaMapper.mapToDto(peliculaSerie);

    }
}


