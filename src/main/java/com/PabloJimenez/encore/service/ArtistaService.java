package com.PabloJimenez.encore.service;

import java.util.List;
import java.util.Optional;

import com.PabloJimenez.encore.entity.Artista;

public interface ArtistaService {
    List<Artista> findAll();
    Optional<Artista> findById(Long id);
    List<Artista> findByCiudadNacimiento(String ciudad);
    Artista findByNombre(String nombre);
    Artista save(Artista artista);
    void deleteById(Long id);
}
