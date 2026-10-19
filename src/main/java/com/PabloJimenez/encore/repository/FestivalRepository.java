package com.PabloJimenez.encore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PabloJimenez.encore.entity.Festival;

public interface FestivalRepository extends JpaRepository<Festival, Long> {
    @Query("SELECT festival FROM Festival festival WHERE festival.precio >= :precioMinimo AND festival.precio <= :precioMaximo")
    List<Festival> buscarPorPrecioRango(@Param("precioMinimo") double minimo, @Param("precioMaximo") double maximo);
    List<Festival> findByEdadMinimaLessThanEqual(int edadMinima);
    @Query("SELECT festival FROM Festival festival WHERE festival.numeroDias >= :diaMinimo AND festival.numeroDias <= :diaMaximo")
    List<Festival> buscarPorDiasRango(@Param("diaMinimo") int minimo, @Param("diaMaximo") int maximo);
    List<Festival> findByNumeroEscenarios(int numeroEscenarios);
}
