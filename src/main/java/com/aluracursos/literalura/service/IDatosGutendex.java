package com.aluracursos.literalura.service;

public interface IDatosGutendex {
    <T> T obtenerDatos(String json, Class<T> clase);
}
