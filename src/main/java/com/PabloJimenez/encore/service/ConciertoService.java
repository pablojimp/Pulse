package com.PabloJimenez.encore.service;

import java.util.List;
import java.util.Optional;

import com.PabloJimenez.encore.entity.Concierto;

public interface ConciertoService {
    List<Concierto> findAll();
    Optional<Concierto> findById(Long id);
    List<Concierto> buscarPorPrecioRango(double minmo, double maximo);
    List<Concierto> findByEdadMinima(int edadMinima);
    Concierto save(Concierto concierto);
    void deleteById(Long id);
}
