package com.PabloJimenez.encore;

import com.PabloJimenez.encore.entity.Festival;
import com.PabloJimenez.encore.entity.LugarEvento;
import com.PabloJimenez.encore.service.FestivalService;
import com.PabloJimenez.encore.service.LugarEventoService;

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
public class FestivalServiceImplTest {

    @Autowired
    private FestivalService festivalService;

    @Autowired
    private LugarEventoService lugarEventoService;

    private LugarEvento lugar;

    @BeforeEach
    void inicio() {
        lugar = lugarEventoService.save(new LugarEvento("Recinto Ferial", 50000, "Madrid"));
    }

    private Festival crearFestival(String nombre, double precio, int edadMinima, int escenarios, int dias) {
        
        return new Festival(nombre, "Promotor Festival", lugar,
                LocalDateTime.of(2025, 7, 1, 12, 0),
                precio, edadMinima, escenarios, dias);
    }

    @Test
    @DisplayName("Guardar un festival y recuperarlo por id")
    void save_y_findById() {
        Festival guardado = festivalService.save(crearFestival("Mad Cool", 120.0, 18, 4, 3));

        Optional<Festival> resultado = festivalService.findById(guardado.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Mad Cool", resultado.get().getNombre());
    }

    @Test
    @DisplayName("findAll devuelve todos los festivales guardados")
    void findAll_devuelveFestivales() {
        festivalService.save(crearFestival("Festival 1", 80.0, 16, 3, 2));
        festivalService.save(crearFestival("Festival 2", 150.0, 18, 5, 4));

        List<Festival> lista = festivalService.findAll();

        assertTrue(lista.size() >= 2);
    }

    @Test
    @DisplayName("buscarPorPrecioRango devuelve festivales dentro del rango")
    void buscarPorPrecioRango_rangoValido() {
        festivalService.save(crearFestival("Barato", 50.0, 16, 2, 1));
        festivalService.save(crearFestival("Caro", 200.0, 18, 6, 5));

        List<Festival> resultado = festivalService.buscarPorPrecioRango(40.0, 100.0);

        assertFalse(resultado.isEmpty());
    }

    @Test
    @DisplayName("buscarPorPrecioRango con rango inválido devuelve lista vacía")
    void buscarPorPrecioRango_invalido() {
        List<Festival> resultado = festivalService.buscarPorPrecioRango(200.0, 50.0);

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("buscarPorPrecioRango con mínimo negativo devuelve lista vacía")
    void buscarPorPrecioRango_negativo() {
        List<Festival> resultado = festivalService.buscarPorPrecioRango(-10.0, 100.0);

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("buscarPorDiasRango devuelve festivales dentro del rango de días")
    void buscarPorDiasRango_rangoValido() {
        festivalService.save(crearFestival("Corto", 60.0, 16, 2, 1));
        festivalService.save(crearFestival("Largo", 180.0, 18, 5, 7));

        List<Festival> resultado = festivalService.buscarPorDiasRango(1, 3);

        assertFalse(resultado.isEmpty());
    }

    @Test
    @DisplayName("buscarPorDiasRango con rango inválido devuelve lista vacía")
    void buscarPorDiasRango_invalido() {
        List<Festival> resultado = festivalService.buscarPorDiasRango(7, 1);

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("findByNumeroEscenarios devuelve festivales con ese número exacto")
    void findByNumeroEscenarios_conResultados() {
        festivalService.save(crearFestival("4 Escenarios", 100.0, 18, 4, 3));

        List<Festival> resultado = festivalService.findByNumeroEscenarios(4);

        assertFalse(resultado.isEmpty());
    }

    @Test
    @DisplayName("findByEdadMinima devuelve festivales accesibles para esa edad")
    void findByEdadMinima_conResultados() {
        festivalService.save(crearFestival("Para todos", 60.0, 0, 3, 2));
        festivalService.save(crearFestival("Solo adultos", 120.0, 18, 4, 3));

        List<Festival> resultado = festivalService.findByEdadMinima(16);

        assertFalse(resultado.isEmpty());
    }

    @Test
    @DisplayName("deleteById elimina el festival correctamente")
    void deleteById_eliminaFestival() {
        Festival guardado = festivalService.save(crearFestival("Borrar", 50.0, 16, 2, 1));
        Long id = guardado.getId();

        festivalService.deleteById(id);

        assertTrue(festivalService.findById(id).isEmpty());
    }

    @Test
    @DisplayName("Actualizar un festival con save sobrescribe los datos")
    void save_actualizaFestival() {
        Festival guardado = festivalService.save(crearFestival("Original", 100.0, 18, 4, 3));
        guardado.setPrecio(150.0);
        festivalService.save(guardado);

        Optional<Festival> resultado = festivalService.findById(guardado.getId());

        assertTrue(resultado.isPresent());
        assertEquals(150.0, resultado.get().getPrecio());
    }
}