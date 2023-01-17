package com.besysoft.bootcampspringboot.controlador;
import com.besysoft.bootcampspringboot.dominio.PeliculaSerie;
import com.besysoft.bootcampspringboot.utilidades.Datos;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/peliculas")

public class PeliculaController {

Datos datos =new Datos();

    @GetMapping
    public @ResponseBody
    List<PeliculaSerie> listarPeliculaSerie() {
        return datos.listarPeliculaSerie();}

    @GetMapping(path="/titulo/{tituloPelicula}")
    public @ResponseBody
    PeliculaSerie filtrarPersonajeTitulo(@PathVariable("tituloPelicula") String titulo) {
       return datos.filtrarPeliculaTitulo(titulo);}

    @GetMapping(path="/genero/{genero}")
    public @ResponseBody
    List<PeliculaSerie> filtrarPeliculaGenero(@PathVariable("genero") String nombreGenero) {
        return datos.peliculaPorGenero(nombreGenero);
    }}