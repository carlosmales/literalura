package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.modelo.Autor;
import com.aluracursos.literalura.modelo.DatosAutor;
import com.aluracursos.literalura.modelo.Idioma;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.DatosGutendex;
import com.aluracursos.literalura.modelo.Libro;
import com.aluracursos.literalura.service.ConsumoAPI;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private DatosGutendex gutendex = new DatosGutendex();
    private List<DatosLibro> datosLibros = new ArrayList<>();
    private LibroRepository repositorio;

    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }

    public Principal() {

    }

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
                    agregarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarPorAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarPorIdiomas();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

            private DatosLibro buscarDatosLibros() {
                System.out.println("Escribe el título de libro que buscas");
                var nombreLibro = teclado.nextLine();
                var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
                System.out.println(json);
                DatosLibro datos = gutendex.obtenerDatos(json, DatosLibro.class);
                return datos;
            }

    private <DatosLibro> void agregarLibro() {
        DatosLibro datosResultado = datosLibros();
        Object datos = null;
        if (!datos.toString().isEmpty()) {
            DatosLibro datosLibro = datosResultado;
            Class<? extends String> datosAutor = datosLibro.toString().getClass();
            var tituloDeLibro = libroRepository.findLibroByTitulo(datosLibro.titulo());
            if (tituloDeLibro != null) {
                System.out.println("No se puede registrar el mismo libro más de una vez.");
            } else {
                var autorDeLibro = autorRepository.findAutorByNombreIgnoreCase(datosAutor.nombreAutor());
                Libro libro;
                if (autorDeLibro != null) {
                    libro = new Libro(datosLibro, autorDeLibro);
                } else {
                    Autor autor = new Autor(datosAutor);
                    autorRepository.save(autor);
                    libro = new Libro(datosLibro, autor);
                }
                libroRepository.save(libro);
                System.out.println("--------- LIBRO AGREGADO ---------");
                System.out.println(libro);
            }
        } else {
            System.out.println("Este libro no existe aún, intentelo de nuevo.");
        }
    }
            private void listarLibros(){
                List<Autor> autores = autorRepository.findAll();
                if (autores.isEmpty()) {
                    System.out.println("No hay autores registrados.");
                } else {
                    autores.forEach(System.out::println);
                }
            }
            private void listarPorAutores(){
                List<Autor> autores = autorRepository.findAll();
                if (autores.isEmpty()) {
                    System.out.println("No hay autores registrados.");
                } else {
                    autores.forEach(System.out::println);
                }
            }
            private void listarAutoresVivos(){
                String menu = """
                Ingrese el idioma para buscar los libros:
                es - español
                en - inglés
                fr - francés
                pt - portugués
                """;
                System.out.println(menu);
                String idioma = teclado.nextLine();
                if (!idioma.equals("es") && !idioma.equals("en") && !idioma.equals("fr") && !idioma.equals("pt")) {
                    System.out.println("Idioma no valido, intentalo de nuevo.");
                } else {
                    List<Libro> librosPorIdioma = libroRepository.findLibrosByIdiomasContaining(idioma);
                    if (librosPorIdioma.isEmpty()) {
                        System.out.println("No hay libros registrados en este idioma.");
                    } else {
                        int cantidadLibros = librosPorIdioma.size();
                        System.out.println("Total libros registrados en %s: ".formatted(Idioma.fromString(idioma)) + cantidadLibros);
                        librosPorIdioma.forEach(System.out::println);
                    }
                }
            }
            private void listarPorIdiomas(){
                System.out.println("¿De donde quiere obtener las estadísticas?");
                String menu = """
                1 - Gutendex
                2 - Base de datos
                """;
                System.out.println(menu);
                var opcion = teclado.nextInt();
                teclado.nextLine();
                if (opcion == 1) {
                    System.out.println("----- ESTADÍSTICAS DE DESCARGAS EN GUTENDEX -----");
                    String json = consumoApi.obtenerDatos(URL_BASE);
                    DatosLibro datosResultado = DatosGutendex.obtenerDatos(json, DatosLibro.class);
                    DoubleSummaryStatistics estadisticas = datosResultado.datosLibro()
                            .stream()
                            .collect(Collectors.summarizingDouble(DatosLibro::cantidadDescargas));
                    System.out.println("Libro con más descargas: " + estadisticas.getMax());
                    System.out.println("Libro con menos descargas: " + estadisticas.getMin());
                    System.out.println("Promedio de descargas: " + estadisticas.getAverage());
                } else if (opcion == 2) {
                    System.out.println("----- ESTADÍSTICAS DE DESCARGAS EN BASE DE DATOS -----");
                    List<Libro> libros = libroRepository.findAll();
                    if (libros.isEmpty()) {
                        System.out.println("No hay libros registrados en la base de datos.");
                    } else {
                        DoubleSummaryStatistics estadisticas = libros
                                .stream()
                                .collect(Collectors.summarizingDouble(Libro::getDescargas));
                        System.out.println("Libro con más descargas: " + estadisticas.getMax());
                        System.out.println("Libro con menos descargas: " + estadisticas.getMin());
                        System.out.println("Promedio de descargas: " + estadisticas.getAverage());
                    }
                } else {
                    System.out.println("Opción no válida, intentelo de nuevo.");
                }
            }
            }
        }