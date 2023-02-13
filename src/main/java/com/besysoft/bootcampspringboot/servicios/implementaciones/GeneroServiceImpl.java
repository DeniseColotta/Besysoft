package com.besysoft.bootcampspringboot.servicios.implementaciones;

import com.besysoft.bootcampspringboot.Entidades.Genero;

import com.besysoft.bootcampspringboot.repositorios.database.IGeneroRepository;

import com.besysoft.bootcampspringboot.servicios.interfaces.IGeneroService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroServiceImpl implements IGeneroService {

    @Autowired
    private IGeneroRepository repository;


    @Override
    public List<Genero> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Genero> filtrarPeliculaPorGenero(String nombreGenero) {

        Optional<Genero> oGenero = repository.findByNombre(nombreGenero);

        if (!oGenero.isPresent()) {
            throw new RuntimeException("el genero ingresado no existe");
        }

        return repository.findGeneroByPelicula(nombreGenero);

    }

    @Override
    public Genero agregarGenero(Genero nuevoGenero) {
        Optional<Genero> oGenero = repository.findByNombre(nuevoGenero.getNombre());

        if (oGenero.isPresent()) {
            throw new RuntimeException("el genero ingresado ya existe");
        }

        return repository.save(nuevoGenero);


    }


    @Override
    public Genero updateGenero(long id, Genero genero) {
        Optional<Genero> oGenero = repository.findById(id);

        if (!oGenero.isPresent()) {
            throw new RuntimeException("El Id ingresado no existe");

        }
        genero.setNombre(genero.getNombre());
        return repository.save(genero);
    }


    @Override
    public Optional<Genero> findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return this.repository.existsByNombre(nombre);
    }
}

