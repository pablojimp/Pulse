package com.PabloJimenez.encore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PabloJimenez.encore.entity.Asistente;

public interface AsistenteRepository extends JpaRepository<Asistente, Long> {
    Asistente findByDocumento(String documento);

    Asistente findByEmail(String email);

    @Query("SELECT evento.asistentes FROM Evento evento WHERE evento.id = :eventoId")
    List<Asistente> findByEventoId(@Param("eventoId") Long eventoId);
}
