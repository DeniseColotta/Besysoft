package com.besysoft.bootcampspringboot.servicios.implementaciones;

import com.besysoft.bootcampspringboot.dto.mapper.IGeneroMapper;
import com.besysoft.bootcampspringboot.dto.request.GeneroRequestDto;
import com.besysoft.bootcampspringboot.dto.response.GeneroResponseDto;
import com.besysoft.bootcampspringboot.modelos.Genero;

import com.besysoft.bootcampspringboot.repositorios.database.IGeneroRepository;

import com.besysoft.bootcampspringboot.servicios.interfaces.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeneroServiceImpl implements IGeneroService {

    @Autowired
    private IGeneroRepository repository;

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
            throw new RuntimeException("el genero ingresado no existe");
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
            throw new RuntimeException("el genero ingresado ya existe");
        }


      Genero genero= repository.save(generoMapper.mapToEntity(nuevoGenero));
        return generoMapper.mapToDto(genero);

    }

    @Transactional(readOnly = false)
    @Override
    public GeneroResponseDto updateGenero(long id, GeneroRequestDto generoAct) {
        Optional<Genero> oGenero = repository.findById(id);

        if (!oGenero.isPresent()) {
            throw new RuntimeException("El Id ingresado no existe");

        }
        generoAct.setNombre(generoAct.getNombre());
        Genero genero=repository.save(generoMapper.mapToEntity(generoAct));
        return generoMapper.mapToDto(genero);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Genero> findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existePorNombre(String nombre) {
        return this.repository.existsByNombre(nombre);
    }
}

