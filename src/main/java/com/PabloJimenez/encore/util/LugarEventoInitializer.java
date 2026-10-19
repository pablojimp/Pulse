package com.PabloJimenez.encore.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import com.github.javafaker.Faker;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.PabloJimenez.encore.config.InitializationConfig;
import com.PabloJimenez.encore.entity.*;
import com.PabloJimenez.encore.service.*;

@Component
public class LugarEventoInitializer {
    @Autowired
    private LugarEventoService lugarEventoService;

    @Autowired
    private InitializationConfig config;

    private final Faker faker = new Faker(new Locale("es"));

    @Transactional
    public void inicializarLugaresEventos() {
        for (int i = 0; i < config.getNuemeroDeLugares(); i++) {
            LugarEvento lugarEvento = crearLugarEventoAleatorio();
            guardarLugarEvento(lugarEvento);
        }
    }

    public LugarEvento crearLugarEventoAleatorio() {
        LugarEvento lugarEvento = new LugarEvento(generarNombre(), faker.number().numberBetween(2000, 200000),
                "Granada");
        return lugarEvento;
    }

    private String generarNombre() {
        int numeroSeleccionado = faker.number().numberBetween(0, lugares.size());
        String nombre = lugares.get(numeroSeleccionado);
        lugares.remove(nombre);
        return nombre;
    }

    static ArrayList<String> lugares = new ArrayList<>(Arrays.asList(
            "Palacio de los Deportes de Granada",
            "Estadio Nuevo Los Cármenes",
            "Teatro Isabel la Católica",
            "Teatro del Generalife",
            "Auditorio Manuel de Falla",
            "Sala El Tren",
            "Sala Vogue",
            "Sala Industrial Copera",
            "Planta Baja",
            "Sala Aliatar",
            "El Garage Beat Club",
            "Sala Maui",
            "Estadio de la Juventud",
            "Recinto Ferial de Granada",
            "Plaza de Toros de Granada",
            "Parque García Lorca",
            "Jardines del Triunfo",
            "Centro Cultural CajaGranada",
            "Auditorio del Parque de las Ciencias",
            "Sala Boom"));

    private void guardarLugarEvento(LugarEvento lugarEvento) {
        try {
            lugarEventoService.save(lugarEvento);
        } catch (Exception e) {
            System.err.println("Error al crear lugar" +
                    lugarEvento.getNombreLugar() + ": " + e.getMessage());
        }
    }

}
