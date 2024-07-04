package com.aluracursos.literalura.modelo;

public interface IDatosGutendex {
    <T> T obtenerDatos(String json, Class<T> clase);
}
