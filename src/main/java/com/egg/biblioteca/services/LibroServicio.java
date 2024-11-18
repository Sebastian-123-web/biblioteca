package com.egg.biblioteca.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.biblioteca.entity.Autor;
import com.egg.biblioteca.entity.Editorial;
import com.egg.biblioteca.entity.Libro;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositories.AutorRepositorio;
import com.egg.biblioteca.repositories.EditorialRepositorio;
import com.egg.biblioteca.repositories.LibroRepositorio;


@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @Transactional
    public void crearLibro(Long isbn,String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);

        Libro libro = new Libro();

        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);
    }

    @Transactional(readOnly = true)
    public List<Libro> listarLibros() {
        List<Libro> libros = new ArrayList<>();
        libros = libroRepositorio.findAll();
        return libros;
    }

    @Transactional
    public void modificarLibro(Long id, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {
        validar(id, titulo, ejemplares, idAutor, idEditorial);
        
        Optional<Libro> libroRespuesta = libroRepositorio.findById(id);
        Optional<Autor> autorRespuesta = autorRepositorio.findById(idAutor);
        Optional<Editorial> editorialRespuesta = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (autorRespuesta.isPresent()) {
            autor = autorRespuesta.get();
        }

        if (editorialRespuesta.isPresent()) {
            editorial = editorialRespuesta.get();
        }

        if (libroRespuesta.isPresent()) {
            Libro libro = libroRespuesta.get();
            libro.setTitulo(titulo);
            libro.setEjemplares(ejemplares);
            libro.setAutor(autor);
            libro.setEditorial(editorial);

            libroRepositorio.save(libro);
        }
    }

    // VALIDAR LOS PARAMETOS DE LOS METODOS
    public void validar(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {
        if (isbn == null) {
            throw new MiException("El ID no puede ser nulo o vacio");
        }
        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("El titulo no puede ser nulo o vacio");
        }
        if (ejemplares == null) {
            throw new MiException("El Ejemplar no puede ser nulo o vacio");
        }
        if (idAutor.isEmpty() || idAutor == null) {
            throw new MiException("El Autor no puede ser nulo o vacio");
        }
        if (idEditorial.isEmpty() || idEditorial == null) {
            throw new MiException("El Editorial no puede ser nulo o vacio");
        }
    }
}