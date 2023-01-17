package com.besysoft.bootcampspringboot.controlador;
import com.besysoft.bootcampspringboot.dominio.Personaje;
import com.besysoft.bootcampspringboot.utilidades.Datos;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/personajes")

public class PersonajeControlador {

    Datos datos =new Datos();

    @GetMapping
    public @ResponseBody
    List<Personaje> filtrarPersonaje() {
        return datos.listarPersonajes();
    }

    @GetMapping(path="/nombre/{nombre}")
     Personaje personajeNombre(@PathVariable("nombre") String nombrePersonaje) {
        return datos.filtrarPersonajePorNombre(nombrePersonaje);}

    @GetMapping(path="/edad/{edad}")
    List<Personaje> personajeEdad(@PathVariable("edad") int edad){
    return datos.filtrarPersonajesPorEdad(edad);


}}


