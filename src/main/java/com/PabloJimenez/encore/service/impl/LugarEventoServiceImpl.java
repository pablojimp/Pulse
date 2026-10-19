package com.PabloJimenez.encore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PabloJimenez.encore.entity.Evento;
import com.PabloJimenez.encore.entity.LugarEvento;
import com.PabloJimenez.encore.repository.LugarEventoRepository;
import com.PabloJimenez.encore.service.LugarEventoService;

/**
 * Implementacion de {@link LugarEventoService} que gestiona la logica de negocio
 * relacionada con los lugares de eventos.
 */
@Service
public class LugarEventoServiceImpl implements LugarEventoService {

    /** Repositorio de lugares de eventos inyectado por Spring. */
    @Autowired
    LugarEventoRepository lugarEventoRepository;

    /**
     * Recupera todos los lugares de eventos almacenados en la base de datos.
     *
     * @return {@link List} con todos los {@link LugarEvento} existentes.
     */
    @Override
    public List<LugarEvento> findAll() {
        return lugarEventoRepository.findAll();
    }

    /**
     * Busca un lugar de evento por su identificador unico.
     *
     * @param id identificador del lugar a buscar.
     * @return {@link Optional} con el {@link LugarEvento} si existe, o vacio si no se encuentra.
     */
    @Override
    public Optional<LugarEvento> findById(Long id) {
        return lugarEventoRepository.findById(id);
    }

    /**
     * Busca un lugar de evento por su nombre.
     *
     * @param nombre nombre del lugar a buscar.
     * @return el {@link LugarEvento} cuyo nombre coincide, o {@code null} si no existe.
     */
    @Override
    public LugarEvento findByNombre(String nombre) {
        return lugarEventoRepository.findByNombreLugar(nombre);
    }

    /**
     * Persiste un lugar de evento en la base de datos.
     * <p>
     * Si el lugar ya existe (tiene un {@code id} asignado), actualiza sus datos;
     * de lo contrario, crea un nuevo registro.
     * </p>
     *
     * @param lugarEvento objeto {@link LugarEvento} a guardar o actualizar.
     * @return el {@link LugarEvento} persistido con su identificador generado o actualizado.
     */
    @Override
    public LugarEvento save(LugarEvento lugarEvento) {
        return lugarEventoRepository.save(lugarEvento);
    }

    /**
     * Elimina un lugar de evento de la base de datos por su identificador.
     *
     * @param id identificador del lugar a eliminar.
     */
    @Override
    public void deleteById(Long id) {
        lugarEventoRepository.deleteById(id);
    }

    /**
     * Obtiene todos los eventos celebrados en un lugar concreto.
     *
     * @param id identificador del lugar cuyos eventos se quieren recuperar.
     * @return {@link List} de {@link Evento} asociados al lugar indicado.
     */
    @Override
    public List<Evento> findEventos(Long id) {
        return lugarEventoRepository.findEventosByLugarId(id);
    }
}