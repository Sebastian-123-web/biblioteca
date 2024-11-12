package com.egg.biblioteca.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.biblioteca.entity.Editorial;
import com.egg.biblioteca.excepciones.MiException;
import com.egg.biblioteca.repositories.EditorialRepositorio;


@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    // CREAR EDITORIAL
    @Transactional
    public void crearEditorial(String nombre) throws MiException {
        validar(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);
    }

    // LISTAR EDITORIAL
    @Transactional(readOnly = true)
    public List<Editorial> listarEditorial() {
        List<Editorial> editorial = new ArrayList<>();
        editorial = editorialRepositorio.findAll();
        return editorial;
    }

    // EDITAR UN EDITORIAL
    @Transactional
    public void modificarEditorial(String id, String nombre) throws MiException {
        validar(nombre);
        Optional<Editorial> editorialRespuesta = editorialRepositorio.findById(id);
        if (editorialRespuesta.isPresent()) {
            Editorial editorial = editorialRespuesta.get();
            editorial.setNombre(nombre);
            editorialRepositorio.save(editorial);
        }
    }

    // VALIDAR LOS PARAMETROS DE LOS METODOS
    public void validar(String nombre) throws MiException {
        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre no puede ser nulo o vacio");
        }
    }
}
