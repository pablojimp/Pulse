package com.PabloJimenez.encore;

import com.PabloJimenez.encore.entity.Asistente;
import com.PabloJimenez.encore.service.AsistenteService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AsistenteServiceImplTest {

    @Autowired
    private AsistenteService asistenteService;

    // Faker nunca genera DNIs con este formato ni emails con este dominio
    private static final String DNI = "78451203x";
    private static final String MAIL_DOMAIN = "@test.com";

    @Test
    @DisplayName("Guardar un asistente y recuperarlo por id")
    void save_y_findById() {
        Asistente a = new Asistente("Juan Test", DNI, "juan" + MAIL_DOMAIN, 25);
        Asistente guardado = asistenteService.save(a);

        Optional<Asistente> resultado = asistenteService.findById(guardado.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Juan Test", resultado.get().getNombre());
    }

    @Test
    @DisplayName("findByDocumento devuelve el asistente correcto")
    void findByDocumento_existente() {
        String dni = DNI + System.currentTimeMillis();
        asistenteService.save(new Asistente("María Test", dni, "maria" + MAIL_DOMAIN, 28));

        Asistente resultado = asistenteService.findByDocumento(dni);

        assertNotNull(resultado);
        assertEquals(dni, resultado.getDocumento());
    }

    @Test
    @DisplayName("findByDocumento devuelve null si no existe")
    void findByDocumento_noExistente() {
        assertNull(asistenteService.findByDocumento("DNI_IMPOSIBLE_XYZ_99999"));
    }

    @Test
    @DisplayName("findByEmail devuelve el asistente correcto")
    void findByEmail_existente() {
        String email = "luis" + System.currentTimeMillis() + MAIL_DOMAIN;
        asistenteService.save(new Asistente("Luis Test", DNI, email, 35));

        Asistente resultado = asistenteService.findByEmail(email);

        assertNotNull(resultado);
        assertEquals(email, resultado.getEmail());
    }

    @Test
    @DisplayName("findByEmail devuelve null si no existe")
    void findByEmail_noExistente() {
        assertNull(asistenteService.findByEmail("noexiste@imposible-xyz.local"));
    }

    @Test
    @DisplayName("deleteById elimina el asistente correctamente")
    void deleteById_eliminaAsistente() {
        Asistente guardado = asistenteService.save(
                new Asistente("Borrar Test", DNI + "005", "borrar" + MAIL_DOMAIN, 20));
        Long id = guardado.getId();

        asistenteService.deleteById(id);

        assertTrue(asistenteService.findById(id).isEmpty());
    }

    @Test
    @DisplayName("Actualizar un asistente con save sobrescribe los datos")
    void save_actualizaAsistente() {
        Asistente guardado = asistenteService.save(
                new Asistente("Original Test", DNI + "006", "orig" + MAIL_DOMAIN, 20));
        guardado.setNombre("Actualizado Test");
        asistenteService.save(guardado);

        Optional<Asistente> resultado = asistenteService.findById(guardado.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Actualizado Test", resultado.get().getNombre());
    }
}