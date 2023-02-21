package com.besysoft.bootcampspringboot.controladores;

import com.besysoft.bootcampspringboot.dto.request.GeneroRequestDto;

import com.besysoft.bootcampspringboot.servicios.interfaces.IGeneroService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(path = "/generos")
@Slf4j
public class GeneroControlador {


    @Autowired
    private IGeneroService generoServicie;

    @GetMapping
    public ResponseEntity<?> listarGeneros() {
        return ResponseEntity.ok().body(generoServicie.getAll());
    }

    @GetMapping(path = "/{generos}")
    public ResponseEntity<?> filtrarPeliculaGenero(@PathVariable("generos") String nombreGenero) {

        try {
            return ResponseEntity.ok().body(generoServicie.filtrarPeliculaPorGenero(nombreGenero));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> crearGenero(@Valid @RequestBody GeneroRequestDto generoNuevo, BindingResult result) {
        Map<String, Object> validaciones = new HashMap<>();
        if (result.hasErrors()) {
            log.info("Ocurrio una validacion en el metodo crearGenero");
            result.getFieldErrors()
                    .forEach(error -> {
                        log.info("Campo: " + error.getField() + " validacion: " + error.getDefaultMessage());
                        validaciones.put(error.getField(), error.getDefaultMessage());
                    });
            return ResponseEntity.badRequest().body(validaciones);
        }
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(generoServicie.agregarGenero(generoNuevo));

            } catch (Exception e) {
                log.info("Ocurrio una validacion personalizada, en el metodo crearGenero: " + e.getMessage());
                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> actualizarGenero(@PathVariable Long id, @Valid@RequestBody GeneroRequestDto genero,BindingResult result) {
        if(result.hasErrors()){

            Map<String, String> validaciones = new HashMap<>();
            log.info("Ocurrio una validacion, en el metodo actualizarGenero().");

            result.getFieldErrors().forEach(error -> {
                log.info("Atributo: " + error.getField() + " - Validacion: " + error.getDefaultMessage());
                validaciones.put(error.getField(), error.getDefaultMessage());
            });

            return ResponseEntity.badRequest().body(validaciones);

        }
        try {
            return ResponseEntity.ok(generoServicie.updateGenero(id, genero));

        } catch (Exception e) {
            log.info("Ocurrio una validacion personalizada, en el metodo actualizarGenero: " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}


