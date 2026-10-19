package com.PabloJimenez.encore.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private ArtistaInitializer artistaInitializer;

    @Autowired
    private LugarEventoInitializer lugarEventoInitializer;

    @Autowired
    private ConciertoInitializer conciertoInitializer;

    @Autowired
    private FestivalInitializer festivalInitializer;

    @Autowired
    private AsistenteInitializer asistenteInitializer;

    @Override
    public void run(String... args) {
        inicializarBaseDeDatos();
    }

    private void inicializarBaseDeDatos() {
        System.out.println("Iniciando la carga de datos iniciales...");
        asistenteInitializer.inicializarAsistentes();
        artistaInitializer.inicializarArtistas();
        lugarEventoInitializer.inicializarLugaresEventos();
        conciertoInitializer.inicializarConcierto();
        festivalInitializer.inicializarFestival();
        System.out.println("Carga de datos completada.");
    }

}
