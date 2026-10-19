package com.PabloJimenez.encore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.PabloJimenez.encore.entity.Artista;
import java.util.List;


public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Artista findByNombre(String nombre);
    List<Artista> findByCiudadNacimiento(String ciudadNacimiento);
}
