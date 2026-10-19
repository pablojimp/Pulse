package com.PabloJimenez.encore;

import com.PabloJimenez.encore.entity.Evento;
import com.PabloJimenez.encore.entity.LugarEvento;
import com.PabloJimenez.encore.service.LugarEventoService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class LugarEventoServiceImplTest {

    @Autowired
    private LugarEventoService lugarEventoService;

    private static final String PREFIJO = "TEST-";

    @Test
    @DisplayName("Guardar un lugar y recuperarlo por id")
    void save_y_findById() {
        LugarEvento lugar = new LugarEvento(PREFIJO + "Wizink", 18000, "Madrid");
        LugarEvento guardado = lugarEventoService.save(lugar);

        Optional<LugarEvento> resultado = lugarEventoService.findById(guardado.getId());

        assertTrue(resultado.isPresent());
        assertEquals(PREFIJO + "Wizink", resultado.get().getNombreLugar());
    }

    @Test
    @DisplayName("findAll devuelve todos los lugares guardados")
    void findAll_devuelveLugares() {
        int antes = lugarEventoService.findAll().size();

        lugarEventoService.save(new LugarEvento(PREFIJO + "A", 5000, "Sevilla"));
        lugarEventoService.save(new LugarEvento(PREFIJO + "B", 8000, "Valencia"));

        assertEquals(antes + 2, lugarEventoService.findAll().size());
    }

    @Test
    @DisplayName("findByNombre devuelve el lugar correcto")
    void findByNombre_existente() {
        String nombre = PREFIJO + System.currentTimeMillis();
        lugarEventoService.save(new LugarEvento(nombre, 20000, "Barcelona"));

        LugarEvento resultado = lugarEventoService.findByNombre(nombre);

        assertNotNull(resultado);
        assertEquals("Barcelona", resultado.getLocalizacion());
    }

    @Test
    @DisplayName("findByNombre devuelve null si no existe")
    void findByNombre_noExistente() {
        assertNull(lugarEventoService.findByNombre("LUGAR_IMPOSIBLE_XYZ_99999"));
    }

    @Test
    @DisplayName("findById devuelve vacío si el id no existe")
    void findById_noExistente() {
        assertTrue(lugarEventoService.findById(999999L).isEmpty());
    }

    @Test
    @DisplayName("deleteById elimina el lugar correctamente")
    void deleteById_eliminaLugar() {
        LugarEvento guardado = lugarEventoService.save(new LugarEvento(PREFIJO + "Borrar", 1000, "Ciudad"));
        Long id = guardado.getId();

        lugarEventoService.deleteById(id);

        assertTrue(lugarEventoService.findById(id).isEmpty());
    }

    @Test
    @DisplayName("Actualizar un lugar con save sobrescribe los datos")
    void save_actualizaLugar() {
        LugarEvento guardado = lugarEventoService.save(new LugarEvento(PREFIJO + "Original", 5000, "Madrid"));
        guardado.setNombreLugar(PREFIJO + "Actualizado");
        lugarEventoService.save(guardado);

        Optional<LugarEvento> resultado = lugarEventoService.findById(guardado.getId());

        assertTrue(resultado.isPresent());
        assertEquals(PREFIJO + "Actualizado", resultado.get().getNombreLugar());
    }

    @Test
    @DisplayName("findEventos devuelve lista vacía para un lugar recién creado sin eventos")
    void findEventos_sinEventos() {
        LugarEvento guardado = lugarEventoService.save(new LugarEvento(PREFIJO + "SinEventos", 1000, "Burgos"));

        List<Evento> eventos = lugarEventoService.findEventos(guardado.getId());

        assertTrue(eventos.isEmpty());
    }
}