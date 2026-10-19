package com.PabloJimenez.encore;

import com.PabloJimenez.encore.entity.Artista;
import com.PabloJimenez.encore.service.ArtistaService;

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
public class ArtistaServiceImplTest {

    @Autowired
    private ArtistaService artistaService;

    @Test
    @DisplayName("Guardar un artista y recuperarlo por id")
    void save_y_findById() {
        Artista artista = new Artista("Rosaliaa", "img.jpg", 5000000, "Barcelona", "Flamenco moderno");
        Artista guardado = artistaService.save(artista);

        Optional<Artista> resultado = artistaService.findById(guardado.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Rosaliaa", resultado.get().getNombre());
    }

    @Test
    @DisplayName("findAll devuelve todos los artistas guardados")
    void findAll_devuelveArtistas() {
        int antes = artistaService.findAll().size();

        artistaService.save(new Artista( "A1", "img1.jpg", 100, "Madrid", "Desc"));
        artistaService.save(new Artista( "A2", "img2.jpg", 200, "Sevilla", "Desc"));

        assertEquals(antes + 2, artistaService.findAll().size());
    }

    @Test
    @DisplayName("findByNombre devuelve el artista correcto")
    void findByNombre_existente() {
        String nombre = "Pablooo";
        artistaService.save(new Artista(nombre, "img.jpg", 3000000, "Madrid", "Urban"));

        Artista resultado = artistaService.findByNombre(nombre);

        assertNotNull(resultado);
        assertEquals(nombre, resultado.getNombre());
    }

    @Test
    @DisplayName("findByNombre devuelve null si no existe")
    void findByNombre_noExistente() {
        Artista resultado = artistaService.findByNombre("NOMBRE_IMPOSIBLE_XYZ_99999");

        assertNull(resultado);
    }

    @Test
    @DisplayName("findByCiudadNacimiento devuelve artistas de esa ciudad")
    void findByCiudadNacimiento_conResultados() {
        artistaService.save(new Artista( "BCN", "img.jpg", 100, "CIUDAD_TEST_UNICA", "Desc"));

        List<Artista> resultado = artistaService.findByCiudadNacimiento("CIUDAD_TEST_UNICA");

        assertFalse(resultado.isEmpty());
        assertTrue(resultado.stream().allMatch(a -> "CIUDAD_TEST_UNICA".equals(a.getCiudadNacimiento())));
    }

    @Test
    @DisplayName("findByCiudadNacimiento con ciudad nula devuelve lista vacía")
    void findByCiudadNacimiento_ciudadNula() {
        assertTrue(artistaService.findByCiudadNacimiento(null).isEmpty());
    }

    @Test
    @DisplayName("findByCiudadNacimiento con ciudad en blanco devuelve lista vacía")
    void findByCiudadNacimiento_ciudadBlanco() {
        assertTrue(artistaService.findByCiudadNacimiento("   ").isEmpty());
    }

    @Test
    @DisplayName("deleteById elimina el artista correctamente")
    void deleteById_eliminaArtista() {
        Artista guardado = artistaService.save(new Artista( "Borrar", "img.jpg", 1, "Ciudad", "Desc"));
        Long id = guardado.getId();

        artistaService.deleteById(id);

        assertTrue(artistaService.findById(id).isEmpty());
    }

    @Test
    @DisplayName("Actualizar un artista con save sobrescribe los datos")
    void save_actualizaArtista() {
        Artista guardado = artistaService.save(new Artista( "Original", "img.jpg", 100, "Madrid", "Desc"));
        guardado.setNombre( "Actualizado");
        artistaService.save(guardado);

        Optional<Artista> resultado = artistaService.findById(guardado.getId());

        assertTrue(resultado.isPresent());
        assertEquals( "Actualizado", resultado.get().getNombre());
    }
}