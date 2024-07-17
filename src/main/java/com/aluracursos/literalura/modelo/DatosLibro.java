package com.aluracursos.literalura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Deque;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosLibro(
        @JsonAlias("results") List<DatosLibro> datosDeLibro,
        @JsonAlias("title") String titulo,
        @JsonAlias("name") String autores,
        @JsonAlias("birth_year") int autorNace,
        @JsonAlias("death_year") int autorMuere,
        @JsonAlias("languages") String idioma,
        @JsonAlias("download_count") int descargas
) {
}
public Object datosAutor() {
}