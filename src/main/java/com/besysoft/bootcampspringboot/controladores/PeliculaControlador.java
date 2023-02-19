package com.besysoft.bootcampspringboot.controladores;


import com.besysoft.bootcampspringboot.dto.request.PeliculaSerieRequestDto;

import com.besysoft.bootcampspringboot.servicios.interfaces.IPeliculaService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping(path = "/películas")

public class PeliculaControlador {

    @Autowired
    private IPeliculaService pelicula;

    @GetMapping
    public ResponseEntity<?> listaPeliculaSerie() {

        return ResponseEntity.ok().body(pelicula.getAll());
    }

    @GetMapping(path = "/titulos/{titulos}")
    public ResponseEntity<?> filtrarPeliculaTitulo(@PathVariable("titulos") String tituloPelicula) {
        return ResponseEntity.ok().body(pelicula.filtrarPeliculaTitulo(tituloPelicula));
    }


    @GetMapping(path = ("/calificaciones"))
    ResponseEntity<?> peliculaPorRangoCalificacion(@RequestParam int desde, @RequestParam int hasta) {
        return ResponseEntity.ok().body(pelicula.filtrarPeliculaPorCalificacion(desde, hasta));

    }


    @GetMapping(path = "/fechas")
    ResponseEntity<?> peliculaPorRangoFecha(
            @RequestParam String desde, @RequestParam String hasta) {

        try {
            return ResponseEntity.ok().body(pelicula.filtrarPeliculaPorFecha(desde, hasta));

        } catch (RuntimeException ex) {
            return new ResponseEntity("Ingrese fecha con el formato dd-MM-yyyy", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> crearPelicula(@Valid @RequestBody PeliculaSerieRequestDto peliculaNueva, BindingResult result) {

        if(result.hasErrors()){

            Map<String, String> validaciones = new HashMap<>();
            log.info("Ocurrio una validacion, en el metodo crearPelicula().");

            result.getFieldErrors().forEach(error -> {
                log.info("Atributo: " + error.getField() + " - Validacion: " + error.getDefaultMessage());
                validaciones.put(error.getField(), error.getDefaultMessage());
            });

            return ResponseEntity.badRequest().body(validaciones);

        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pelicula.agregarPelicula(peliculaNueva));


        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> actualizarPelicula(@PathVariable Long id,
                                                @Valid @RequestBody PeliculaSerieRequestDto peliculaNueva,BindingResult result) {

        if(result.hasErrors()){

            Map<String, String> validaciones = new HashMap<>();
            log.info("Ocurrio una validacion, en el metodo actualizarPelicula().");

            result.getFieldErrors().forEach(error -> {
                log.info("Atributo: " + error.getField() + " - Validacion: " + error.getDefaultMessage());
                validaciones.put(error.getField(), error.getDefaultMessage());
            });

            return ResponseEntity.badRequest().body(validaciones);

        }
        try {
            return ResponseEntity.ok().body(pelicula.updatePelicula(id, peliculaNueva));

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}