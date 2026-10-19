package com.PabloJimenez.encore.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PabloJimenez.encore.entity.Artista;
import com.PabloJimenez.encore.repository.ArtistaRepository;
import com.PabloJimenez.encore.service.*;

/**
 * Implementacion de {@link ArtistaService} que gestiona la logica de negocio
 * relacionada con los artistas.
 */
@Service
public class ArtistaServiceImpl implements ArtistaService {

    /** Repositorio de artistas inyectado por Spring. */
    @Autowired
    ArtistaRepository artistaRepository;

    /**
     * Recupera todos los artistas almacenados en la base de datos.
     *
     * @return {@link List} con todos los {@link Artista} existentes.
     */
    @Override
    public List<Artista> findAll() {
        return artistaRepository.findAll();
    }

    /**
     * Busca un artista por su identificador unico.
     *
     * @param id identificador del artista a buscar.
     * @return {@link Optional} con el {@link Artista} si existe, o vacio si no se encuentra.
     */
    @Override
    public Optional<Artista> findById(Long id) {
        return artistaRepository.findById(id);
    }

    /**
     * Busca artistas por ciudad de nacimiento.
     * <p>
     * Si el parametro {@code ciudad} es {@code null} o esta en blanco,
     * devuelve una lista vacia sin realizar ninguna consulta.
     * </p>
     *
     * @param ciudad nombre de la ciudad de nacimiento por la que filtrar.
     * @return {@link List} de {@link Artista} nacidos en la ciudad indicada,
     *         o una lista vacia si el parametro no es valido.
     */
    @Override
    public List<Artista> findByCiudadNacimiento(String ciudad) {
        List<Artista> lista = Collections.emptyList();
        if (ciudad != null && !ciudad.isBlank()) {
            lista = artistaRepository.findByCiudadNacimiento(ciudad);
        }
        return lista;
    }

    /**
     * Busca un artista por su nombre artistico.
     *
     * @param nombre nombre artistico del artista a buscar.
     * @return el {@link Artista} cuyo nombre coincide, o {@code null} si no existe.
     */
    @Override
    public Artista findByNombre(String nombre) {
        return artistaRepository.findByNombre(nombre);
    }

    /**
     * Persiste un artista en la base de datos.
     * <p>
     * Si el artista ya existe (tiene un {@code id} asignado), actualiza sus datos;
     * de lo contrario, crea un nuevo registro.
     * </p>
     *
     * @param artista objeto {@link Artista} a guardar o actualizar.
     * @return el {@link Artista} persistido con su identificador generado o actualizado.
     */
    @Override
    public Artista save(Artista artista) {
        return artistaRepository.save(artista);
    }

    /**
     * Elimina un artista de la base de datos por su identificador.
     *
     * @param id identificador del artista a eliminar.
     */
    @Override
    public void deleteById(Long id) {
        artistaRepository.deleteById(id);
    }
}