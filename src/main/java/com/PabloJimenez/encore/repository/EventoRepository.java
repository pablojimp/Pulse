package com.PabloJimenez.encore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.PabloJimenez.encore.entity.Evento;
import com.PabloJimenez.encore.entity.LugarEvento;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByLugar(LugarEvento lugar);
    List<Evento> findByPromotor(String promotor);
}
