package com.aluracursos.literalura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("name") String autores,
        @JsonAlias("birth_year") int autorNace,
        @JsonAlias("death_year") int autorMuere,
        @JsonAlias("languages") String idioma,
        @JsonAlias("download_count") int descargas
) {
}