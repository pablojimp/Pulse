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
public class ConciertoInitializer {

    @Autowired
    private ConciertoService conciertoService;

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
    public void inicializarConcierto() {
        for (int i = 0; i < config.getNumeroDeConciertos(); i++) {
            Concierto concierto = crearConciertoAleatorio();
            guardarConcierto(concierto);
        }
    }

    private Concierto crearConciertoAleatorio() {
        ArrayList<Asistente> asistentes = new ArrayList<>();
        List<Artista> artistas = artistaService.findAll();
        List<LugarEvento> lugares = lugarEventoService.findAll();

        List<Asistente> posiblesAsistentes = asistenteService.findAll();

        int edad = generarEdad();
        Artista artista = artistas.get(faker.number().numberBetween(0, artistas.size()));
        LugarEvento lugar = lugares.get(faker.number().numberBetween(0, lugares.size()));

        for (int i = 0; i < faker.number().numberBetween(5, posiblesAsistentes.size()); i++) {
            Asistente asistente = posiblesAsistentes.get(faker.number().numberBetween(0, posiblesAsistentes.size()));
            if (asistente.getEdad() >= edad) {
                asistentes.add(asistente);
            }
        }

        Concierto concierto = new Concierto(
                generarNombre(artista.getNombre()),
                generarPromotor(),
                lugar,
                asistentes,
                LocalDateTime.now().plusDays(faker.number().numberBetween(1, 365)),
                faker.number().numberBetween(20, 150),
                edad,
                faker.number().numberBetween(60, 180),
                artista);

        return concierto;
    }

    private int generarEdad() {
        int[] edades = { 0, 16, 18 };
        return edades[faker.number().numberBetween(0, edades.length)];
    }

    private String generarNombre(String nombreArtista) {
        String[] formatos = {
                nombreArtista + " en Granada",
                nombreArtista + " - Noche Única en Granada",
                nombreArtista + " - Tour Primavera en Granada",
                nombreArtista + " Unplugged en Granada",
                nombreArtista + " - Gira Verano Granada",
                nombreArtista + " en Directo - Granada",
                nombreArtista + " - La Gran Despedida en Granada",
                nombreArtista + " - Comeback en Granada",
                nombreArtista + " - Noche de Éxitos en Granada",
                nombreArtista + " - 25 Aniversario en Granada",
        };
        return formatos[faker.number().numberBetween(0, formatos.length)];
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

    private void guardarConcierto(Concierto concierto) {
        try {
            conciertoService.save(concierto);

        } catch (Exception e) {
            System.err.println("Error al crear concierto con nombre" +
                    concierto.getNombre() + ": " + e.getMessage());
        }
    }
}
