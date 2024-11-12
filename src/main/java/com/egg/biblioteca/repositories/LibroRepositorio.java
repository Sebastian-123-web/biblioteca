package com.egg.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.biblioteca.entity.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    public Libro buscarLibroPorTitulo(@Param("titulo") String titulo);

    @Query("SELECT l  FROM Libro l WHERE l.autor = :autor")
    public Libro buscarLibroPorAutor(@Param("autor") String autor);
}
