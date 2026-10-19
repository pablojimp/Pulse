package com.PabloJimenez.encore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.PabloJimenez.encore.entity.Concierto;

public interface ConciertoRepository extends JpaRepository<Concierto, Long> {
    @Query(value = "SELECT concierto FROM Concierto concierto where precio >= :precioMinimo AND precio <= :precioMaximo")
    List<Concierto> buscarPorPrecioRango(@Param("precioMinimo") double minimo, @Param("precioMaximo") double maximo);

    List<Concierto> findByEdadMinimaLessThanEqual(int edad);
}
