package com.aluracursos.literalura.modelo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String nombre;
    private Integer autorNace;
    private Integer autorMuere;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor() {

    }

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.autores();
        this.autorNace = datosAutor.autorNace();
        this.autorMuere = datosAutor.autorMuere();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor{" +
                ", nombre='" + nombre + '\'' +
                ", Fecha de nacimiento=" + autorNace +
                ", Fecha de muerte=" + autorMuere +
                ", libros=" + libros;
    }
}