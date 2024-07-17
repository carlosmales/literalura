package com.aluracursos.literalura.modelo;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

@Repository

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true, nullable = false)
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private String autores;
    private Integer autorNace;
    private Integer autorMuere;
    private String idioma;
    private Integer descargas;

    public Libro(){}

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.autores = datosLibro.autores();
        this.autorNace = datosLibro.autorNace();
        this.autorMuere = datosLibro.autorMuere();
        this.idioma = datosLibro.idioma();
        this.descargas = datosLibro.descargas();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public Integer getAutorNace() {
        return autorNace;
    }

    public void setAutorNace(Integer autorNace) {
        this.autorNace = autorNace;
    }

    public Integer getAutorMuere() {
        return autorMuere;
    }

    public void setAutorMuere(Integer autorMuere) {
        this.autorMuere = autorMuere;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                ", Titulo='" + titulo + '\'' +
                ", Autores='" + autores + '\'' +
                ", Idioma='" + idioma + '\'' +
                ", Numero de descargas=" + descargas;
    }
}
