package com.PabloJimenez.encore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.PabloJimenez.encore.entity.Evento;
import com.PabloJimenez.encore.service.EventoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/eventos")
public class EventoController {

    @Autowired
    EventoService eventoService;

    /**
     * Obtiene la lista completa de eventos registrados.
     *
     * @return {@link List} con todos los {@link Evento} existentes en la base de datos.
     */
    @GetMapping
    public List<Evento> getAllEventos() {
        return eventoService.findAll();
    }

    /**
     * Obtiene una selección aleatoria de eventos.
     *
     * @param cantidad número de eventos aleatorios a devolver.
     * @return {@link List} con {@code cantidad} {@link Evento} seleccionados aleatoriamente.
     */
    @GetMapping("/random")
    public List<Evento> getRandom(@RequestParam("cantidad") int cantidad) {
        return eventoService.findRandom(cantidad);
    }

    /**
     * Busca un evento por su identificador único.
     *
     * @param id identificador del evento a buscar.
     * @return {@link Optional} que contiene el {@link Evento} si existe,
     *         o vacio si no se encuentra.
     */
    @GetMapping("/{id}")
    public Optional<Evento> getEventoById(@PathVariable Long id) {
        return eventoService.findById(id);
    }

    /**
     * Busca todos los eventos organizados por un promotor concreto.
     *
     * @param promotor nombre del promotor por el que filtrar.
     * @return {@link List} de {@link Evento} asociados al promotor indicado.
     */
    @GetMapping("/promotor/{promotor}")
    public List<Evento> getEventosByPromotor(@PathVariable String promotor) {
        return eventoService.findByPromotor(promotor);
    }

    /**
     * Actualiza los datos de un evento existente.
     * <p>
     * Solo realiza la actualización si el evento con el {@code id} indicado
     * existe en la base de datos.
     * </p>
     *
     * @param id     identificador del evento a actualizar.
     * @param evento objeto {@link Evento} con los nuevos datos.
     * @return el {@link Evento} actualizado, o {@code null} si no se encontró.
     */
    @PutMapping("/{id}")
    public Evento updateEvento(@PathVariable Long id, @RequestBody Evento evento) {
        Evento result = null;
        if (eventoService.findById(id).isPresent()) {
            evento.setId(id);
            result = eventoService.save(evento);
        }
        return result;
    }

    /**
     * Elimina un evento de la base de datos por su identificador.
     * <p>
     * Si el evento no existe, la operación no tiene ningún efecto.
     * </p>
     *
     * @param id identificador del evento a eliminar.
     */
    @DeleteMapping("/{id}")
    public void deleteEvento(@PathVariable Long id) {
        if (eventoService.findById(id).isPresent()) {
            eventoService.deleteById(id);
        }
    }
}