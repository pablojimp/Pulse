package com.PabloJimenez.encore.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.github.javafaker.Faker;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.PabloJimenez.encore.config.InitializationConfig;
import com.PabloJimenez.encore.entity.*;
import com.PabloJimenez.encore.service.*;

@Component
public class FestivalInitializer {

    @Autowired
    private FestivalService festivalService;

    @Autowired
    private LugarEventoService lugarEventoService;

    @Autowired
    private ArtistaService artistaService;

    @Autowired
    private AsistenteService asistenteService;
    @Autowired
    private InitializationConfig config;

    private final Faker faker = new Faker(new Locale("es"));

    @Transactional
    public void inicializarFestival() {
        for (int i = 0; i < config.getNumeroDeFestivales(); i++) {
            Festival festival = crearFestivalAleatorio();
            guardarFestival(festival);
        }
    }

    public Festival crearFestivalAleatorio() {
        List<LugarEvento> lugares = lugarEventoService.findAll();
        List<Artista> artistas = artistaService.findAll();
        ArrayList<Asistente> asistentes = new ArrayList<>();
        List<Asistente> posiblesAsistentes = asistenteService.findAll();
        List<Artista> artistasFestival = new ArrayList<>();
        LugarEvento lugar = lugares.get(faker.number().numberBetween(0, lugares.size()));

        for (int i = 0; i < faker.number().numberBetween(5, posiblesAsistentes.size()); i++) {
            Asistente asistente = posiblesAsistentes.get(faker.number().numberBetween(0, posiblesAsistentes.size()));
            asistentes.add(asistente);
        }
        for (int i = 0; i < faker.number().numberBetween(5, 20); i++) {
            Artista artista = artistas.get(faker.number().numberBetween(0, artistas.size()));

            if (artistasFestival.isEmpty()) {
                artistasFestival.add(artista);
            } else {
                if (!artistasFestival.contains(artista)) {
                    artistasFestival.add(artista);
                }
            }

        }

        Festival festival = new Festival(generarNombre(), generarPromotor(), lugar, asistentes,
                LocalDateTime.now().plusDays(faker.number().numberBetween(1, 365)),
                faker.number().numberBetween(50, 400), generarEdad(), faker.number().numberBetween(1, 5),
                faker.number().numberBetween(3, 7), artistasFestival);
        return festival;

    }

    private int generarEdad() {
        int[] edades = { 0, 16, 18 };
        return edades[faker.number().numberBetween(0, edades.length)];
    }

    private String generarNombre() {
        String[] festivales = {
                "Granada Sound",
                "Festival de Música del Generalife",
                "Alhambra Festival",
                "Granada en Vivo",
                "Festival del Sacromonte",
                "Sierra Nevada Festival",
                "Granada Urban Music Fest",
                "Festival de la Vega",
                "Noches del Botánico Granada",
                "Granada Hip Hop Festival",
                "Festival Flamenco Granada",
                "Granada Rock Fest",
                "Festival de las Artes Granada",
                "Cármenes Music Festival",
                "Granada Indie Fest",
                "Festival del Darro",
                "Granada Electronic Music Fest",
                "Festival de la Alcaicería",
                "Genil Festival",
                "Granada Latin Fest"
        };
        return festivales[faker.number().numberBetween(0, festivales.length)];
    }

    private String generarPromotor() {
        String[] promotores = {
                "Live Nation", "La Vendicion", "El Drogas Producciones",
                "Universal Music Events", "Warner Live", "Sony Music Live",
                "Primavera Sound SL", "Mad Cool Festival", "Doctor Music",
                "Pagano", "Producciones Animadas", "Eventim Spain"
        };
        return promotores[faker.number().numberBetween(0, promotores.length)];
    }

    private void guardarFestival(Festival festival) {
        try {
            festivalService.save(festival);

        } catch (Exception e) {
            System.err.println("Error al crear el festival" +
                    festival.getNombre() + ": " + e.getMessage());
        }
    }

}
