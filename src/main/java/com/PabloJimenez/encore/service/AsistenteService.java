package com.PabloJimenez.encore.service;

import java.util.List;
import java.util.Optional;

import com.PabloJimenez.encore.entity.*;

public interface AsistenteService {
    List<Asistente> findAll();

    Optional<Asistente> findById(Long id);

    Asistente findByDocumento(String documento);

    List<Asistente> findByEventoId(Long eventoId);

    Asistente findByEmail(String email);

    Asistente save(Asistente asistente);

    Asistente saveWithEvento(Asistente asistente, Long eventoId);

    void deleteById(Long id);

}