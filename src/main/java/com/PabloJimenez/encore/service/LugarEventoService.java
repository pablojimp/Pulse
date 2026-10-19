package com.PabloJimenez.encore.service;

import java.util.List;
import java.util.Optional;

import com.PabloJimenez.encore.entity.*;

public interface LugarEventoService {
    List<LugarEvento> findAll();
    Optional<LugarEvento> findById(Long id);
    LugarEvento findByNombre(String nombre);
    LugarEvento save(LugarEvento lugarEvento);
    List<Evento> findEventos(Long id);
    void deleteById(Long id);

}
