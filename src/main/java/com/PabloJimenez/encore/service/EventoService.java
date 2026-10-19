package com.PabloJimenez.encore.service;

import java.util.*;

import com.PabloJimenez.encore.entity.Evento;
import com.PabloJimenez.encore.entity.LugarEvento;

public interface EventoService {
    List<Evento> findAll();

    Optional<Evento> findById(Long id);

    List<Evento> findByLugarEvento(LugarEvento lugarEvento);

    Evento save(Evento evento);

    void deleteById(Long id);

    List<Evento> findByPromotor(String promotor);

    List<Evento> findRandom(int randoms);
}