package com.besysoft.bootcampspringboot.servicios.ImplementacionesMemoria;


import com.besysoft.bootcampspringboot.dominios.Genero;

import com.besysoft.bootcampspringboot.dto.mapper.IGeneroMapper;
import com.besysoft.bootcampspringboot.dto.request.GeneroRequestDto;
import com.besysoft.bootcampspringboot.dto.response.GeneroResponseDto;
import com.besysoft.bootcampspringboot.repositorios.memory.interfaces.IGeneroRepository;
import com.besysoft.bootcampspringboot.servicios.interfaces.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "memory")
    public class GeneroServiceMemoriaImpl implements IGeneroService {

        @Autowired
        private IGeneroRepository repositoryGenero;

        @Autowired
        private IGeneroMapper generoMapper;

    @Override
    public List<GeneroResponseDto> getAll() {
        List<Genero> generos = repositoryGenero.obtenerTodos();
        List<GeneroResponseDto> generoDto = generos.stream()
                .map(generoMapper::mapToDto)
                .collect(Collectors.toList());

        return generoDto;
    }
    @Override
    public List<GeneroResponseDto> filtrarPeliculaPorGenero(String nombreGenero) {

        Optional<Genero> oGenero = repositoryGenero.buscarGenero(nombreGenero);

        if (!oGenero.isPresent()) {
            throw new RuntimeException("el genero ingresado no existe");
        }
        List<Genero> generos = repositoryGenero.filtrarPeliculaPorGenero(nombreGenero);
        List<GeneroResponseDto> generoDtoByPelicula = generos.stream()
                .map(generoMapper::mapToDto)
                .collect(Collectors.toList());
        return generoDtoByPelicula;
    }



    @Override
    public GeneroResponseDto agregarGenero(GeneroRequestDto nuevoGenero) {
        Optional<Genero> oGenero = repositoryGenero.buscarGenero(nuevoGenero.getNombre());

        if (oGenero.isPresent()) {
            throw new RuntimeException("el genero ingresado ya existe");
        }


        Genero genero= repositoryGenero.agregarGenero(generoMapper.mapToEntity(nuevoGenero));
        return generoMapper.mapToDto(genero);

    }


    @Override
    public GeneroResponseDto updateGenero(long id, GeneroRequestDto generoAct) {
        Optional<Genero> oGenero =  repositoryGenero.obtenerTodos().stream().
                filter(pr -> pr.getId() == id)
                .findAny();

        if (!oGenero.isPresent()) {
            throw new RuntimeException("El Id ingresado no existe");

        }
        generoAct.setNombre(generoAct.getNombre());
        Genero genero=repositoryGenero.updateGenero(id,generoMapper.mapToEntity(generoAct));
        return generoMapper.mapToDto(genero);
    }}

    /*    @Override
        public Optional<Genero> buscarGenero(String nombre) {
            return repositoryGenero.buscarGenero(nombre);
        }
    }
}
*/