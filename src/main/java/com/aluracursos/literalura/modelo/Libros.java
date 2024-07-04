package com.aluracursos.literalura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

public class Libros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private String autores;
    private Integer autorNace;
    private Integer autorMuere;
    private String idioma;
    private Integer descargas;

    public Libros(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.autores = datosLibro.autores();
        this.autorNace = datosLibro.autorNace();
        this.autorMuere = datosLibro.autorMuere();
        this.idioma = datosLibro.idioma();
        this.descargas = datosLibro.descargas();
    }
}
