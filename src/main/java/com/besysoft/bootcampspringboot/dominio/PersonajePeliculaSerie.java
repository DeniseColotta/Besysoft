package com.besysoft.bootcampspringboot.dominio;

public class PersonajePeliculaSerie {
    private long id;
    private Personaje personaje;
    private PeliculaSerie peliculaSerie;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public PeliculaSerie getPeliculaSerie() {
        return peliculaSerie;
    }

    public void setPeliculaSerie(PeliculaSerie peliculaSerie) {
        this.peliculaSerie = peliculaSerie;
    }



    public PersonajePeliculaSerie(long id, Personaje personaje, PeliculaSerie peliculaSerie) {
        this.id=id;
        this.personaje = personaje;
        this.peliculaSerie = peliculaSerie;

    }
}
