package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.modelo.Autor;

import java.util.List;

public interface AutorRepository {
    Autor findAutorByNombreIgnoreCase(String nombre);
    List<Autor> findAutorByFechaDeNacimientoLessThanEqualAndFechaDeFallecimientoGreaterThanEqual(int fechaDeNacimiento, int fechaDeFallecimiento);

}
