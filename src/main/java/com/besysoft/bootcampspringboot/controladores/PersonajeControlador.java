package com.besysoft.bootcampspringboot.controladores;

import com.besysoft.bootcampspringboot.dto.request.PersonajeRequestDto;
import com.besysoft.bootcampspringboot.servicios.interfaces.IPersonajeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/personajes")
@Slf4j
@Api(value = "Personaje Controlador", tags = "Acciones para la entidad Personaje")
public class PersonajeControlador {

    @Autowired
    private IPersonajeService servicePersonaje;

    @GetMapping
    @ApiOperation(value = "Consulta todos los personajes disponibles de la base de datos")
    public @ResponseBody
    ResponseEntity<?> filtrarPersonaje() {
        return ResponseEntity.ok().body(servicePersonaje.getAll());
    }

    @GetMapping(path = "/nombres/{nombres}")
    @ApiOperation(value = "Consulta el personaje según el nombre ingresado")
    ResponseEntity<?> filtrarPersonajePorNombre(@PathVariable("nombres") String nombrePersonaje) {
        return ResponseEntity.ok().body(servicePersonaje.filtrarPersonajePorNombre(nombrePersonaje));
    }


    @GetMapping(path = "/edades/{edades}")
    @ApiOperation(value = "Consulta todos los personajes por la edad ingresada")
    ResponseEntity<?> filtrarPersonajePorEdad(@PathVariable("edades") int edad) {

        return ResponseEntity.ok().body(servicePersonaje.filtrarPersonajesPorEdad(edad));
    }


    @GetMapping(path = ("/edades"))
    @ApiOperation(value = "Consulta todos los personajes por el rango de edad ingresado")
    ResponseEntity<?> filtrarPersonajePorRangoEdad(@RequestParam int desde, @RequestParam int hasta) {

        return ResponseEntity.ok().body(servicePersonaje.filtrarPersonajesPorRangoEdad(desde, hasta));
    }


    @PostMapping()
    @ApiOperation(value = "Permite crear un nuevo personaje")
    public ResponseEntity<?> altaPersonaje(@Valid @RequestBody PersonajeRequestDto personajeNuevo) {

        return ResponseEntity.status(HttpStatus.CREATED).body(servicePersonaje.agregarPersonaje(personajeNuevo));

    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Permite actualizar un personaje")
    public ResponseEntity<?> actualizarPersonaje(@PathVariable Long id,
                                                 @Valid @RequestBody PersonajeRequestDto personaje) {
        return ResponseEntity.ok(servicePersonaje.updatePersonaje(id, personaje));

    }

}

