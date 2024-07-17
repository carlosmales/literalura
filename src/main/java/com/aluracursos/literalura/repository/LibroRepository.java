package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
Libro findLibroByTitulo(String titulo);
List<Libro> findLibrosByIdiomasContaining(String idiomas);

}
