package co.edu.javeriana.bibliotecas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.bibliotecas.entity.Biblioteca;

@Repository
public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {
    @EntityGraph(attributePaths = "libros")
    List<Biblioteca> findAllByOrderByIdAsc();
}
