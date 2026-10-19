package com.PabloJimenez.encore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PabloJimenez.encore.entity.Asistente;
import com.PabloJimenez.encore.entity.Evento;
import com.PabloJimenez.encore.repository.*;
import com.PabloJimenez.encore.service.AsistenteService;

/**
 * Implementacion de {@link AsistenteService} que gestiona la logica de negocio
 * relacionada con los asistentes a eventos.
 */
@Service
public class AsistenteServiceImpl implements AsistenteService {

    /** Repositorio de eventos inyectado por Spring, usado para gestionar relaciones. */
    @Autowired
    EventoRepository eventoRepository;

    /** Repositorio de asistentes inyectado por Spring. */
    @Autowired
    AsistenteRepository asistenteRepository;

    /**
     * Recupera todos los asistentes almacenados en la base de datos.
     *
     * @return {@link List} con todos los {@link Asistente} existentes.
     */
    @Override
    public List<Asistente> findAll() {
        return asistenteRepository.findAll();
    }

    /**
     * Busca un asistente por su identificador unico.
     *
     * @param id identificador del asistente a buscar.
     * @return {@link Optional} con el {@link Asistente} si existe, o vacio si no se encuentra.
     */
    @Override
    public Optional<Asistente> findById(Long id) {
        return asistenteRepository.findById(id);
    }

    /**
     * Busca un asistente por su numero de documento de identidad.
     *
     * @param documento numero de documento del asistente a buscar.
     * @return el {@link Asistente} cuyo documento coincide, o {@code null} si no existe.
     */
    @Override
    public Asistente findByDocumento(String documento) {
        return asistenteRepository.findByDocumento(documento);
    }

    /**
     * Busca un asistente por su direccion de correo electronico.
     *
     * @param email direccion de email del asistente a buscar.
     * @return el {@link Asistente} cuyo email coincide, o {@code null} si no existe.
     */
    @Override
    public Asistente findByEmail(String email) {
        return asistenteRepository.findByEmail(email);
    }

    /**
     * Obtiene todos los asistentes inscritos en un evento concreto.
     *
     * @param eventoId identificador del evento cuyos asistentes se quieren recuperar.
     * @return {@link List} de {@link Asistente} asociados al evento indicado.
     */
    public List<Asistente> findByEventoId(Long eventoId) {
        return asistenteRepository.findByEventoId(eventoId);
    }

    /**
     * Persiste un asistente en la base de datos.
     * <p>
     * Si el asistente ya existe (tiene un {@code id} asignado), actualiza sus datos;
     * de lo contrario, crea un nuevo registro.
     * </p>
     *
     * @param asistente objeto {@link Asistente} a guardar o actualizar.
     * @return el {@link Asistente} persistido con su identificador generado o actualizado.
     */
    @Override
    public Asistente save(Asistente asistente) {
        return asistenteRepository.save(asistente);
    }

    /**
     * Elimina un asistente de la base de datos y desvincula todas sus relaciones con eventos.
     * <p>
     * Antes de eliminar el registro, recorre todos los eventos a los que estaba inscrito
     * el asistente y lo elimina de sus listas de asistentes, garantizando la integridad
     * de la relacion.
     * </p>
     *
     * @param id identificador del asistente a eliminar.
     */
    @Override
    public void deleteById(Long id) {
        Asistente asistente = asistenteRepository.findById(id).orElseThrow();

        for (Evento evento : asistente.getEventosAsistente()) {
            evento.getAsistentes().remove(asistente);
        }
        asistente.getEventosAsistente().clear();

        asistenteRepository.deleteById(id);
    }

    /**
     * Crea un nuevo asistente y lo inscribe en un evento existente.
     * <p>
     * Primero persiste el asistente, luego recupera el evento por su {@code eventoId}
     * y anade el asistente a su lista de asistentes, guardando finalmente el evento
     * con la relacion actualizada.
     * </p>
     *
     * @param asistente objeto {@link Asistente} con los datos del nuevo asistente.
     * @param eventoId  identificador del evento al que se va a inscribir el asistente.
     * @return el {@link Asistente} persistido y vinculado al evento indicado.
     */
    @Override
    public Asistente saveWithEvento(Asistente asistente, Long eventoId) {
        Asistente savedAsistente = asistenteRepository.save(asistente);
        Evento evento = eventoRepository.findById(eventoId).orElseThrow();
        evento.getAsistentes().add(savedAsistente);
        eventoRepository.save(evento);
        return savedAsistente;
    }
}