package com.egg.biblioteca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.biblioteca.entity.Autor;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositories.AutorRepositorio;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    // CREAR AUTOR
    @Transactional
    public void crearAutor(String nombre) throws MiException {
        validar(nombre);
        Autor autor = new Autor();
        autor.setNombre(nombre);
        System.out.println(nombre);

        autorRepositorio.save(autor);
    }

    // LISTAR AUTORES
    @Transactional(readOnly = true)
    public List<Autor> listarAutor(){
        List<Autor> autor = new ArrayList<>();
        autor = autorRepositorio.findAll();
        return autor;
    }

    // EDITAR UN AUTOR
    @Transactional
    public void modificarAutor(String id, String nombre) throws MiException {
        validar(nombre);
        Optional<Autor> autorRespuesta = autorRepositorio.findById(id);
        if (autorRespuesta.isPresent()) {
            Autor autor = autorRespuesta.get();
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        }
    }

    // VALIDAR LOS PARAMETOS DE LOS METODOS
    public void validar(String nombre) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede ser nulo o vacio");
        }
    }

    // BUSCAR UN REGISTRO POR ID
    @Transactional(readOnly = true)
    public Autor getOneAutor(String id) {
        return autorRepositorio.getReferenceById(id);
    }
}
