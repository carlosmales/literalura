package com.aluracursos.literalura.modelo;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name") String autores,
        @JsonAlias("birth_year") Integer autorNace,
        @JsonAlias("death_year") Integer autorMuere
) {
}