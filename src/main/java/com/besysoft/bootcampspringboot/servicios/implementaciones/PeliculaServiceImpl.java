package com.besysoft.bootcampspringboot.servicios.implementaciones;

import com.besysoft.bootcampspringboot.Entidades.PeliculaSerie;

import com.besysoft.bootcampspringboot.repositorios.database.IPeliculaRepository;
import com.besysoft.bootcampspringboot.servicios.interfaces.IPeliculaService;
import com.besysoft.bootcampspringboot.utilidades.Fecha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements IPeliculaService {

    @Autowired
    private IPeliculaRepository repository;


    public List<PeliculaSerie> getAll() {

        return repository.findAll();
    }

    @Override
    public List<PeliculaSerie> filtrarPeliculaPorFecha(String desde, String hasta) {

        LocalDate fecha1 = Fecha.formatear(desde);
        LocalDate fecha2 = Fecha.formatear(hasta);
        return repository.filtrarPeliculaPorFecha(fecha1, fecha2);
    }

    @Override
    public List<PeliculaSerie> filtrarPeliculaPorCalificacion(int desde, int hasta) {
        return repository.filtrarPeliculaPorCalificacion(desde, hasta);
    }

    @Override
    public Optional<PeliculaSerie> filtrarPeliculaTitulo(String titulo) {
        return repository.filtrarPeliculaTitulo(titulo);
    }

    @Override
    public PeliculaSerie agregarPelicula(PeliculaSerie nuevaPelicula) {
        Optional<PeliculaSerie> oPelicula = repository.filtrarPeliculaTitulo(nuevaPelicula.getTitulo());

        if (oPelicula.isPresent()) {
            throw new RuntimeException("La película ingresada ya existe");
        }

        return repository.save(nuevaPelicula);
    }

    @Override
    public PeliculaSerie updatePelicula(long id, PeliculaSerie pelicula) {
        Optional<PeliculaSerie> oPelicula = repository.findById(id);

        if (!oPelicula.isPresent()) {
            throw new RuntimeException("El Id ingresado no existe");
        }
        pelicula.setTitulo(pelicula.getTitulo());
        pelicula.setCalificacion(pelicula.getCalificacion());
        pelicula.setFechaDeCreacion(pelicula.getFechaDeCreacion());
        return repository.save(pelicula);
    }
}
