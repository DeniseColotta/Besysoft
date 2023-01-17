package com.besysoft.bootcampspringboot.utilidades;

import com.besysoft.bootcampspringboot.dominio.PeliculaSerie;
import com.besysoft.bootcampspringboot.dominio.Personaje;

import java.util.List;

public interface iDatos {

    public void crear();

    public List<Personaje> listarPersonajes();

    public Personaje filtrarPersonajePorNombre(String nombre);

    public List<Personaje> filtrarPersonajesPorEdad(int edad);

    public List<PeliculaSerie> listarPeliculaSerie();

    public PeliculaSerie filtrarPeliculaTitulo(String titulo);

    public List<PeliculaSerie> peliculaPorGenero(String nombreGenero);
}

