package com.PabloJimenez.encore;

import com.PabloJimenez.encore.entity.Artista;
import com.PabloJimenez.encore.entity.Concierto;
import com.PabloJimenez.encore.entity.Evento;
import com.PabloJimenez.encore.entity.LugarEvento;
import com.PabloJimenez.encore.service.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EventoServiceImplTest {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private LugarEventoService lugarEventoService;

    private Artista artista;
    private LugarEvento lugar;

    @BeforeEach
    void inicio() {
        artista = artistaService.save(new Artista("Fito", "img.jpg", 2000000, "Bilbao", "Rock"));
        lugar = lugarEventoService.save(new LugarEvento("Estadio", 40000, "Valencia"));
    }

    private Concierto crearConcierto(String nombre, String promotor) {
        return new Concierto(nombre, promotor, lugar,
                LocalDateTime.of(2025, 9, 15, 20, 0),
                55.0, 16, 90, artista);
    }

    @Test
    @DisplayName("Guardar un evento y recuperarlo por id")
    void save_y_findById() {
        Concierto guardado = (Concierto) eventoService.save(crearConcierto("Fito Live", "Promotor A"));

        Optional<Evento> resultado = eventoService.findById(guardado.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Fito Live", resultado.get().getNombre());
    }

    @Test
    @DisplayName("findAll devuelve todos los eventos guardados")
    void findAll_devuelveEventos() {
        eventoService.save(crearConcierto("Evento 1", "Promotor A"));
        eventoService.save(crearConcierto("Evento 2", "Promotor B"));

        List<Evento> lista = eventoService.findAll();

        assertTrue(lista.size() >= 2);
    }

    @Test
    @DisplayName("findById devuelve vacío si el id no existe")
    void findById_noExistente() {
        Optional<Evento> resultado = eventoService.findById(999999L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("findByLugarEvento devuelve los eventos de ese lugar")
    void findByLugarEvento_conResultados() {
        eventoService.save(crearConcierto("Concierto en el lugar", "Promotor X"));

        List<Evento> resultado = eventoService.findByLugarEvento(lugar);

        assertFalse(resultado.isEmpty());
    }

    @Test
    @DisplayName("findByLugarEvento devuelve lista vacía para un lugar sin eventos")
    void findByLugarEvento_sinEventos() {
        LugarEvento lugarVacio = lugarEventoService.save(new LugarEvento("Sin eventos", 100, "Burgos"));

        List<Evento> resultado = eventoService.findByLugarEvento(lugarVacio);

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("findByPromotor devuelve eventos con ese promotor")
    void findByPromotor_conResultados() {
        eventoService.save(crearConcierto("Concierto Promotor", "Live Nation"));

        List<Evento> resultado = eventoService.findByPromotor("Live Nation");

        assertFalse(resultado.isEmpty());
    }

    @Test
    @DisplayName("findByPromotor con promotor null devuelve lista vacía")
    void findByPromotor_null() {
        List<Evento> resultado = eventoService.findByPromotor(null);

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("findByPromotor con promotor en blanco devuelve lista vacía")
    void findByPromotor_blanco() {
        List<Evento> resultado = eventoService.findByPromotor("   ");

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("deleteById elimina el evento correctamente")
    void deleteById_eliminaEvento() {
        Evento guardado = eventoService.save(crearConcierto("Borrar", "Promotor"));
        Long id = guardado.getId();

        eventoService.deleteById(id);

        assertTrue(eventoService.findById(id).isEmpty());
    }

    @Test
    @DisplayName("findRandom devuelve exactamente n eventos distintos")
    void findRandom_devuelveNEventos() {
        eventoService.save(crearConcierto("Random 1", "Prom A"));
        eventoService.save(crearConcierto("Random 2", "Prom B"));
        eventoService.save(crearConcierto("Random 3", "Prom C"));

        List<Evento> resultado = eventoService.findRandom(2);
        assertEquals(2, resultado.size());
    }
}