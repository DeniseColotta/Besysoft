package com.besysoft.bootcampspringboot.controladores;

import com.besysoft.bootcampspringboot.dto.request.PersonajeRequestDto;

import com.besysoft.bootcampspringboot.servicios.interfaces.IPersonajeService;

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
@RequestMapping(path = "/personajes")

public class PersonajeControlador {

    @Autowired
    private IPersonajeService servicePersonaje;

    @GetMapping
    public @ResponseBody
    ResponseEntity<?> filtrarPersonaje() {
        return ResponseEntity.ok().body(servicePersonaje.getAll());
    }

    @GetMapping(path = "/nombres/{nombres}")
    ResponseEntity<?> filtrarPersonajePorNombre(@PathVariable("nombres") String nombrePersonaje) {
        return ResponseEntity.ok().body(servicePersonaje.filtrarPersonajePorNombre(nombrePersonaje));
    }


    @GetMapping(path = "/edades/{edades}")
    ResponseEntity<?> filtrarPersonajePorEdad(@PathVariable("edades") int edad) {

        return ResponseEntity.ok().body(servicePersonaje.filtrarPersonajesPorEdad(edad));
    }


    @GetMapping(path = ("/edades"))
    ResponseEntity<?> filtrarPersonajePorRangoEdad(@RequestParam int desde, @RequestParam int hasta) {

        return ResponseEntity.ok().body(servicePersonaje.filtrarPersonajesPorRangoEdad(desde, hasta));
    }


    @PostMapping()
    public ResponseEntity<?> altaPersonaje(@Valid @RequestBody PersonajeRequestDto personajeNuevo, BindingResult result) {
        if(result.hasErrors()) {

            Map<String, String> validaciones = new HashMap<>();
            log.info("Ocurrio una validacion, en el metodo altaPersonaje().");

            result.getFieldErrors().forEach(error -> {
                log.info("Atributo: " + error.getField() + " - Validacion: " + error.getDefaultMessage());
                validaciones.put(error.getField(), error.getDefaultMessage());
            });


            return ResponseEntity.badRequest().body(validaciones);
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(servicePersonaje.agregarPersonaje(personajeNuevo));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> actualizarPersonaje(@PathVariable Long id,
                                                 @Valid @RequestBody PersonajeRequestDto personaje,BindingResult result) {
        if(result.hasErrors()) {

            Map<String, String> validaciones = new HashMap<>();
            log.info("Ocurrio una validacion, en el metodo actualizarPersonaje().");

            result.getFieldErrors().forEach(error -> {
                log.info("Atributo: " + error.getField() + " - Validacion: " + error.getDefaultMessage());
                validaciones.put(error.getField(), error.getDefaultMessage());
            });

            return ResponseEntity.badRequest().body(validaciones);
        }
        try {
            return ResponseEntity.ok(servicePersonaje.updatePersonaje(id, personaje));

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

}

