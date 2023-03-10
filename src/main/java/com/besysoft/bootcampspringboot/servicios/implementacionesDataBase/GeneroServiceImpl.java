package com.besysoft.bootcampspringboot.servicios.implementacionesDataBase;

import com.besysoft.bootcampspringboot.dto.mapper.IGeneroMapper;
import com.besysoft.bootcampspringboot.dto.request.GeneroRequestDto;
import com.besysoft.bootcampspringboot.dto.response.GeneroResponseDto;
import com.besysoft.bootcampspringboot.dominios.Genero;

import com.besysoft.bootcampspringboot.excepciones.ExistException;
import com.besysoft.bootcampspringboot.excepciones.NotFoundException;
import com.besysoft.bootcampspringboot.repositorios.database.IGeneroRepository;

import com.besysoft.bootcampspringboot.servicios.interfaces.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
public class GeneroServiceImpl implements IGeneroService {


    private final IGeneroRepository repository;

    public GeneroServiceImpl(IGeneroRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private IGeneroMapper generoMapper;

    @Transactional(readOnly = true)
    @Override
    public List<GeneroResponseDto> getAll() {
        List<Genero> generos = repository.findAll();
        List<GeneroResponseDto> generoDto = generos.stream()
                .map(generoMapper::mapToDto)
                .collect(Collectors.toList());

        return generoDto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<GeneroResponseDto> filtrarPeliculaPorGenero(String nombreGenero) {

        Optional<Genero> oGenero = repository.findByNombre(nombreGenero);

        if (!oGenero.isPresent()) {
            throw new NotFoundException(String.format("el genero %s no existe", nombreGenero),
                    new RuntimeException("Causa Original"));
        }
        List<Genero> generos = repository.findGeneroByPelicula(nombreGenero);
        List<GeneroResponseDto> generoDtoByPelicula = generos.stream()
                .map(generoMapper::mapToDto)
                .collect(Collectors.toList());
        return generoDtoByPelicula;

    }

    @Transactional(readOnly = false)
    @Override
    public GeneroResponseDto agregarGenero(GeneroRequestDto nuevoGenero) {
        Optional<Genero> oGenero = repository.findByNombre(nuevoGenero.getNombre());

        if (oGenero.isPresent()) {
            throw new ExistException(String.format("el genero %s ya existe", nuevoGenero.getNombre()),
                    new RuntimeException("Causa Original"));
        }


        Genero genero = repository.save(generoMapper.mapToEntity(nuevoGenero));
        return generoMapper.mapToDto(genero);

    }

    @Transactional(readOnly = false)
    @Override
    public GeneroResponseDto updateGenero(long id, GeneroRequestDto generoAct) {
        Optional<Genero> oGenero = repository.findById(id);

        if (!oGenero.isPresent()) {
            throw new NotFoundException(String.format("el id %s ingresado no existe", id),
                    new RuntimeException("Causa Original"));

        }
        generoAct.setNombre(generoAct.getNombre());
        Genero genero = repository.save(generoMapper.mapToEntity(generoAct));
        return generoMapper.mapToDto(genero);
    }


}
