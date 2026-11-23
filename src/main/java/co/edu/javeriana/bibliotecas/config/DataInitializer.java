package co.edu.javeriana.bibliotecas.config;

import co.edu.javeriana.bibliotecas.entity.Biblioteca;
import co.edu.javeriana.bibliotecas.entity.Libro;
import co.edu.javeriana.bibliotecas.repository.BibliotecaRepository;
import co.edu.javeriana.bibliotecas.repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Carga datos de prueba al arrancar la aplicación usando los repositorios.
 * Solo inserta si la base de datos está vacía, para no duplicar datos
 * cada vez que se reinicia el backend.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BibliotecaRepository bibliotecaRepository;
    private final LibroRepository libroRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (bibliotecaRepository.count() > 0) {
            log.info("Datos de prueba ya existentes: no se vuelven a cargar.");
            return;
        }

        //bibliotecas - se guardan primero para que tengan id
        Biblioteca central = bibliotecaRepository.save(
                new Biblioteca("Biblioteca Central", "Carrera 7 # 40-62", "Bogotá"));
        Biblioteca norte = bibliotecaRepository.save(
                new Biblioteca("Biblioteca Norte", "Calle 170 # 54-30", "Bogotá"));
        Biblioteca poblado = bibliotecaRepository.save(
                new Biblioteca("Biblioteca El Poblado", "Carrera 43A # 10-15", "Medellín"));

        //libros: cada uno queda asociado a una única biblioteca
        List<Libro> libros = List.of(
                libro("Cien años de soledad", "Gabriel García Márquez", 1967, central),
                libro("La vorágine", "José Eustasio Rivera", 1924, central),
                libro("María", "Jorge Isaacs", 1867, central),
                libro("El coronel no tiene quien le escriba", "Gabriel García Márquez", 1961, norte),
                libro("Crónica de una muerte anunciada", "Gabriel García Márquez", 1981, norte),
                libro("Delirio", "Laura Restrepo", 2004, norte),
                libro("El olvido que seremos", "Héctor Abad Faciolince", 2006, poblado),
                libro("Rosario Tijeras", "Jorge Franco", 1999, poblado)
        );
        libroRepository.saveAll(libros);

        log.info("Datos de prueba cargados: {} bibliotecas y {} libros.",
                bibliotecaRepository.count(), libroRepository.count());
    }

    private Libro libro(String titulo, String autor, int anio, Biblioteca biblioteca) {
        Libro libro = new Libro(titulo, autor, anio);
        biblioteca.agregarLibro(libro); // asigna libro.biblioteca y mantiene la lista sincronizada
        return libro;
    }
}
