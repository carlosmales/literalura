package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.modelo.DatosGutendex;
import com.aluracursos.literalura.modelo.DatosLibro;
import com.aluracursos.literalura.service.ConsumoAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
//    private final String API_KEY = "modern%20prometheus";
//    private final String BUSCA_GENERAL = "search=";
//    private final String BUSCA_TEMA = "topic=";
    private DatosGutendex gutendex = new DatosGutendex();
    private List<DatosLibro> datosLibros = new ArrayList<>();
//    private SerieRepository repositorio;
//    private List<Serie> series;
//    private Optional<Serie> serieBuscada;
    public void muestraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elige la acción que deseas realizar
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en determinado año
                    5 - Listar libros por idiomas
                                  
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarVivos();
                    break;
                case 5:
                    listarIdiomas();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

            private DatosLibro getDatosLibro() {
                System.out.println("Escribe el título de libro que buscas");
                var nombreLibro = teclado.nextLine();
                var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
                System.out.println(json);
                DatosLibro datos = gutendex.obtenerDatos(json, DatosLibro.class);
                return datos;
            }


            private void buscarLibro() {
                DatosLibro datos = getDatosLibro();
//                    Libros serie = new Libros(datos);
//                    repositorio.save(serie);
                System.out.println(datos);
            }

            private void listarLibros(){}
            private void listarAutores(){}
            private void listarVivos(){}
            private void listarIdiomas(){}
        }