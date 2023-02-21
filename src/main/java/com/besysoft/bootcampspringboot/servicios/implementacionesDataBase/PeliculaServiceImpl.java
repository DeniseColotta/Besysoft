package com.besysoft.bootcampspringboot.servicios.implementacionesDataBase;

import com.besysoft.bootcampspringboot.dto.mapper.IPeliculaSerieMapper;
import com.besysoft.bootcampspringboot.dto.request.PeliculaSerieRequestDto;
import com.besysoft.bootcampspringboot.dto.response.PeliculaSerieResponseDto;
import com.besysoft.bootcampspringboot.dominios.PeliculaSerie;

import com.besysoft.bootcampspringboot.repositorios.database.IPeliculaRepository;
import com.besysoft.bootcampspringboot.servicios.interfaces.IPeliculaService;
import com.besysoft.bootcampspringboot.utilidades.Fecha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
public class PeliculaServiceImpl implements IPeliculaService {

    @Autowired
    private IPeliculaRepository repository;

    @Autowired
    private IPeliculaSerieMapper peliculaMapper;

    @Transactional(readOnly = true)
    public List<PeliculaSerieResponseDto> getAll() {
        List<PeliculaSerie> peliculas = repository.findAll();
        List<PeliculaSerieResponseDto> peliculaDto = peliculas.stream()
                .map(peliculaMapper::mapToDto)
                .collect(Collectors.toList());



        return peliculaDto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PeliculaSerieResponseDto> filtrarPeliculaPorFecha(String desde, String hasta) {

        LocalDate fecha1 = Fecha.formatear(desde);
        LocalDate fecha2 = Fecha.formatear(hasta);
        List<PeliculaSerie> peliculas = repository.findPeliculaByFechaBetween(fecha1, fecha2);

        List<PeliculaSerieResponseDto> peliculaDto = peliculas.stream()
                .map(peliculaMapper::mapToDto)
                .collect(Collectors.toList());

        return peliculaDto;

    }

    @Transactional(readOnly = true)
    @Override
    public List<PeliculaSerieResponseDto> filtrarPeliculaPorCalificacion(int desde, int hasta) {

        List<PeliculaSerie> peliculas = repository.findPeliculaByCalificacionBetween(desde,hasta);

        List<PeliculaSerieResponseDto> peliculaDto = peliculas.stream()
                .map(peliculaMapper::mapToDto)
                .collect(Collectors.toList());

        return peliculaDto;

    }

    @Transactional(readOnly = true)
    @Override
    public PeliculaSerieResponseDto filtrarPeliculaTitulo(String titulo) {
      Optional<PeliculaSerie>pelicula= repository.findPeliculaByTitulo(titulo);
      PeliculaSerieResponseDto peliculas= peliculaMapper.mapToDto(pelicula.get());
      return peliculas;


    }

    @Transactional(readOnly = false)
    @Override
    public PeliculaSerieResponseDto agregarPelicula(PeliculaSerieRequestDto nuevaPelicula) {
        Optional<PeliculaSerie> oPelicula = repository.findPeliculaByTitulo(nuevaPelicula.getTitulo());

        if (oPelicula.isPresent()) {
            throw new RuntimeException("La película ingresada ya existe");
        }
        PeliculaSerie pelicula= repository.save(peliculaMapper.mapToEntity(nuevaPelicula));
        return peliculaMapper.mapToDto(pelicula);

    }

    @Transactional(readOnly = false)
    @Override
    public PeliculaSerieResponseDto updatePelicula(long id, PeliculaSerieRequestDto pelicula) {
        Optional<PeliculaSerie> oPelicula = repository.findById(id);

        if (!oPelicula.isPresent()) {
            throw new RuntimeException("El Id ingresado no existe");
        }
        pelicula.setTitulo(pelicula.getTitulo());
        pelicula.setCalificacion(pelicula.getCalificacion());
        pelicula.setFechaDeCreacion(pelicula.getFechaDeCreacion());

        PeliculaSerie peliculaSerie=repository.save(peliculaMapper.mapToEntity(pelicula));
        return peliculaMapper.mapToDto(peliculaSerie);

    }
}
