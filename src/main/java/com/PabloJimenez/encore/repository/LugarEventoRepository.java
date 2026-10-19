package com.PabloJimenez.encore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PabloJimenez.encore.entity.Evento;
import com.PabloJimenez.encore.entity.LugarEvento;

public interface LugarEventoRepository extends JpaRepository<LugarEvento, Long> {
    LugarEvento findByNombreLugar(String nombre);

    @Query("SELECT e FROM Evento e WHERE e.lugar.id = :id")
    List<Evento> findEventosByLugarId(@Param("id") Long id);
}