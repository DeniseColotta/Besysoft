package com.besysoft.bootcampspringboot.controladores;
import com.besysoft.bootcampspringboot.dto.request.GeneroRequestDto;
import com.besysoft.bootcampspringboot.servicios.interfaces.IGeneroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/generos")
@Slf4j
@Api(value="Genero Controlador", tags="Acciones para la entidad Genero")
public class GeneroControlador {


    @Autowired
    private IGeneroService generoServicie;

    @GetMapping
    @ApiOperation(value="Consulta todos los generos disponibles de la base de datos")
    public ResponseEntity<?> listarGeneros() {
        return ResponseEntity.ok().body(generoServicie.getAll());
    }

    @GetMapping(path = "/{generos}")
    @ApiOperation(value="Consulta las películas según el genero ingresado")
    public ResponseEntity<?> filtrarPeliculaGenero(@PathVariable("generos") String nombreGenero) {
            return ResponseEntity.ok().body(generoServicie.filtrarPeliculaPorGenero(nombreGenero));
    }

    @PostMapping
    @ApiOperation(value="Permite crear un nuevo género")
    public ResponseEntity<?> crearGenero(@Valid @RequestBody GeneroRequestDto generoNuevo) {
            return ResponseEntity.status(HttpStatus.CREATED).body(generoServicie.agregarGenero(generoNuevo));

        }

    @PutMapping(path = "/{id}")
    @ApiOperation(value="Permite actualizar un género")
    public ResponseEntity<?> actualizarGenero(@PathVariable Long id, @Valid @RequestBody GeneroRequestDto genero){
            return ResponseEntity.ok(generoServicie.updateGenero(id, genero));

    }
}


