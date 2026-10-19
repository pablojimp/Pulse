package com.PabloJimenez.encore;

import com.PabloJimenez.encore.entity.Artista;
import com.PabloJimenez.encore.entity.Concierto;
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
public class ConciertoServiceImplTest {

    @Autowired
    private ConciertoService conciertoService;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private LugarEventoService lugarEventoService;

    private Artista artista;
    private LugarEvento lugar;

    @BeforeEach
    void inicio() {
        artista = artistaService.save(new Artista("Duki", "img.jpg", 10000000, "Buenos Aires", "Trap"));
        lugar = lugarEventoService.save(new LugarEvento("Wizink Center", 18000, "Madrid"));
    }

    private Concierto crearConcierto(String nombre, double precio, int edadMinima) {
        return new Concierto(nombre, "Promotor SA", lugar,
                LocalDateTime.of(2025, 8, 10, 21, 0),
                precio, edadMinima, 120, artista);
    }

    @Test
    @DisplayName("Guardar un concierto y recuperarlo por id")
    void save_y_findById() {
        Concierto guardado = conciertoService.save(crearConcierto("Duki Live", 60.0, 16));

        Optional<Concierto> resultado = conciertoService.findById(guardado.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Duki Live", resultado.get().getNombre());
    }

    @Test
    @DisplayName("findAll devuelve todos los conciertos guardados")
    void findAll_devuelveConciertos() {
        conciertoService.save(crearConcierto("Concierto 1", 50.0, 16));
        conciertoService.save(crearConcierto("Concierto 2", 70.0, 18));

        List<Concierto> lista = conciertoService.findAll();

        assertTrue(lista.size() >= 2);
    }

    @Test
    @DisplayName("buscarPorPrecioRango devuelve conciertos dentro del rango")
    void buscarPorPrecioRango_rangoValido() {
        conciertoService.save(crearConcierto("Barato", 30.0, 16));
        conciertoService.save(crearConcierto("Caro", 150.0, 16));

        List<Concierto> resultado = conciertoService.buscarPorPrecioRango(20.0, 50.0);

        assertFalse(resultado.isEmpty());
    }

    @Test
    @DisplayName("buscarPorPrecioRango con máximo menor que mínimo devuelve lista vacía")
    void buscarPorPrecioRango_invalido() {
        List<Concierto> resultado = conciertoService.buscarPorPrecioRango(100.0, 10.0);

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("buscarPorPrecioRango con mínimo negativo devuelve lista vacía")
    void buscarPorPrecioRango_negativo() {
        List<Concierto> resultado = conciertoService.buscarPorPrecioRango(-10.0, 50.0);

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("findByEdadMinima devuelve conciertos accesibles para esa edad")
    void findByEdadMinima_conResultados() {
        conciertoService.save(crearConcierto("Para todos", 40.0, 0));
        conciertoService.save(crearConcierto("Solo mayores", 80.0, 18));

        List<Concierto> resultado = conciertoService.findByEdadMinima(16);

        assertFalse(resultado.isEmpty());
    }

    @Test
    @DisplayName("deleteById elimina el concierto correctamente")
    void deleteById_eliminaConcierto() {
        Concierto guardado = conciertoService.save(crearConcierto("Borrar", 50.0, 16));
        Long id = guardado.getId();

        conciertoService.deleteById(id);

        assertTrue(conciertoService.findById(id).isEmpty());
    }

    @Test
    @DisplayName("Actualizar un concierto con save sobrescribe los datos")
    void save_actualizaConcierto() {
        Concierto guardado = conciertoService.save(crearConcierto("Original", 50.0, 16));
        guardado.setPrecio(99.0);
        conciertoService.save(guardado);

        Optional<Concierto> resultado = conciertoService.findById(guardado.getId());

        assertTrue(resultado.isPresent());
        assertEquals(99.0, resultado.get().getPrecio());
    }
}