package com.besysoft.bootcampspringboot.controladores;
import com.besysoft.bootcampspringboot.dto.request.PeliculaSerieRequestDto;
import com.besysoft.bootcampspringboot.servicios.interfaces.IPeliculaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/películas")
@Api(value = "Pelicula-Serie Controlador", tags = "Acciones para la entidad PeliculaSerie")
@Slf4j

public class PeliculaControlador {

    @Autowired
    private IPeliculaService pelicula;

    @GetMapping
    @ApiOperation(value = "Consulta todos las peliculas y series disponibles de la base de datos")
    public ResponseEntity<?> listaPeliculaSerie() {

        return ResponseEntity.ok().body(pelicula.getAll());
    }

    @GetMapping(path = "/titulos/{titulos}")
    @ApiOperation(value = "Consulta la película o serie por el título ingresado")
    public ResponseEntity<?> filtrarPeliculaTitulo(@PathVariable("titulos") String tituloPelicula) {
        return ResponseEntity.ok().body(pelicula.filtrarPeliculaTitulo(tituloPelicula));
    }


    @GetMapping(path = ("/calificaciones"))
    @ApiOperation(value = "Consulta todas las películas o series por el rango de calificación ingresado")
    ResponseEntity<?> peliculaPorRangoCalificacion(@RequestParam int desde, @RequestParam int hasta) {
        return ResponseEntity.ok().body(pelicula.filtrarPeliculaPorCalificacion(desde, hasta));

    }

    @GetMapping(path = "/fechas")
    @ApiOperation(value = "Consulta todas las películas o series por el rango de fechas ingresado")
    ResponseEntity<?> peliculaPorRangoFecha(
            @RequestParam String desde, @RequestParam String hasta) {
        return ResponseEntity.ok().body(pelicula.filtrarPeliculaPorFecha(desde, hasta));

    }

    @PostMapping
    @ApiOperation(value = "Permite crear una nueva película o serie")
    public ResponseEntity<?> crearPelicula(@Valid @RequestBody PeliculaSerieRequestDto peliculaNueva) {

        return ResponseEntity.status(HttpStatus.CREATED).body(pelicula.agregarPelicula(peliculaNueva));
    }


    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Permite actualizar una película o serie")
    public ResponseEntity<?> actualizarPelicula(@PathVariable Long id,
                                                @Valid @RequestBody PeliculaSerieRequestDto peliculaNueva) {

        return ResponseEntity.ok().body(pelicula.updatePelicula(id, peliculaNueva));


    }
}