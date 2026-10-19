package com.PabloJimenez.encore.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PabloJimenez.encore.entity.Evento;
import com.PabloJimenez.encore.entity.LugarEvento;
import com.PabloJimenez.encore.repository.*;
import com.PabloJimenez.encore.service.*;

/**
 * Implementacion de {@link EventoService} que gestiona la logica de negocio
 * relacionada con los eventos.
 * <p>
 * Delega las operaciones de persistencia en {@link EventoRepository}.
 * </p>
 */
@Service
public class EventoServiceImpl implements EventoService {

    /** Repositorio de eventos inyectado por Spring. */
    @Autowired
    private EventoRepository eventoRepository;

    /**
     * Recupera todos los eventos almacenados en la base de datos.
     *
     * @return {@link List} con todos los {@link Evento} existentes.
     */
    @Override
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    /**
     * Busca un evento por su identificador unico.
     *
     * @param id identificador del evento a buscar.
     * @return {@link Optional} con el {@link Evento} si existe, o vacio si no se encuentra.
     */
    @Override
    public Optional<Evento> findById(Long id) {
        return eventoRepository.findById(id);
    }

    /**
     * Busca todos los eventos celebrados en un lugar concreto.
     *
     * @param lugarEvento objeto {@link LugarEvento} por el que filtrar.
     * @return {@link List} de {@link Evento} asociados al lugar indicado.
     */
    @Override
    public List<Evento> findByLugarEvento(LugarEvento lugarEvento) {
        return eventoRepository.findByLugar(lugarEvento);
    }

    /**
     * Persiste un evento en la base de datos.
     * <p>
     * Si el evento ya existe (tiene un {@code id} asignado), actualiza sus datos;
     * de lo contrario, crea un nuevo registro.
     * </p>
     *
     * @param evento objeto {@link Evento} a guardar o actualizar.
     * @return el {@link Evento} persistido con su identificador generado o actualizado.
     */
    @Override
    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    /**
     * Elimina un evento de la base de datos por su identificador.
     *
     * @param id identificador del evento a eliminar.
     */
    @Override
    public void deleteById(Long id) {
        eventoRepository.deleteById(id);
    }

    /**
     * Busca todos los eventos organizados por un promotor concreto.
     * <p>
     * Si el parametro {@code promotor} es {@code null} o esta en blanco,
     * devuelve una lista vacia sin realizar ninguna consulta.
     * </p>
     *
     * @param promotor nombre del promotor por el que filtrar.
     * @return {@link List} de {@link Evento} asociados al promotor indicado,
     *         o una lista vacia si el parametro no es valido.
     */
    @Override
    public List<Evento> findByPromotor(String promotor) {
        List<Evento> lista = Collections.emptyList();
        if (promotor != null && !promotor.isBlank()) {
            lista = eventoRepository.findByPromotor(promotor);
        }
        return lista;
    }

    /**
     * Devuelve una seleccion de eventos elegidos de forma aleatoria.
     * <p>
     * Recupera todos los eventos disponibles y selecciona {@code randoms} de ellos
     * sin repeticion usando {@link Math#random()}. El bucle continua hasta completar
     * la cantidad solicitada, descartando duplicados en cada iteracion.
     * </p>
     *
     * @param randoms numero de eventos aleatorios a devolver.
     * @return {@link List} de {@code randoms} {@link Evento} seleccionados sin repeticion.
     */
    @Override
    public List<Evento> findRandom(int randoms) {
        List<Evento> seleccionados = new ArrayList<>();
        List<Evento> todosLosEventos = findAll();

        while (seleccionados.size() < randoms) {
            int numero = (int) (Math.random() * todosLosEventos.size());

            Evento candidato = todosLosEventos.get(numero);

            if (!seleccionados.contains(candidato)) {
                seleccionados.add(candidato);
            }
        }

        return seleccionados;
    }
}