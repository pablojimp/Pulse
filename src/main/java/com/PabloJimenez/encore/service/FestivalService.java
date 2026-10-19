package com.PabloJimenez.encore.service;

import java.util.List;
import java.util.Optional;

import com.PabloJimenez.encore.entity.Festival;

public interface FestivalService {
    List<Festival> findAll();
    Optional<Festival> findById(Long id);
    List<Festival> buscarPorPrecioRango(double minmo, double maximo);
    List<Festival> buscarPorDiasRango(int minmo, int maximo);
    List<Festival> findByNumeroEscenarios(int numeroEscenarios);
    List<Festival> findByEdadMinima(int edadMinima);
    Festival save(Festival festival);
    void deleteById(Long id);

}