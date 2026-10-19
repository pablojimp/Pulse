package com.PabloJimenez.encore.util;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.PabloJimenez.encore.config.*;
import com.PabloJimenez.encore.entity.Asistente;
import com.PabloJimenez.encore.service.*;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;

@Component
public class AsistenteInitializer {

    @Autowired
    private AsistenteService asistenteService;

    @Autowired
    private InitializationConfig config;

    private final Faker faker = new Faker(new Locale("es"));

    @Transactional
    public void inicializarAsistentes() {
        for (int i = 0; i < config.getNumeroDeAsistentes(); i++) {
            Asistente asistente = crearAsistenteAleatorio();
            guardarAsistente(asistente);
        }
    }

    private Asistente crearAsistenteAleatorio() {
        String nombre = generarNombre();
        String email = generarEmail(nombre);
        String documento = generarDocumento();
        int edad = faker.number().numberBetween(0, 99);

        Asistente asistente = new Asistente(nombre, documento, email, edad);

        return asistente;
    }

    private String generarDocumento() {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = faker.number().numberBetween(10000000, 99999999);
        char letra = letras.charAt(numero % 23);
        return numero + String.valueOf(letra);
    }

    private String generarEmail(String nombre) {

        String nombreSinEspacios = nombre.replace(" ","");

        String email = "";

        int numeroAletorio = (int) (Math.random() * 999);

        String[] dominios = {
                "gmail.com", "hotmail.com", "outlook.com", "yahoo.es",
                "yahoo.com", "icloud.com", "live.com", "msn.com",
                "telefonica.net", "movistar.es", "orange.es", "vodafone.es",
                "ono.com", "jazztel.es", "masmovil.es", "yoigo.com",
                "correo.es", "terra.es", "wanadoo.es", "hispavista.com"
        };

        email = nombreSinEspacios + numeroAletorio +"@"+dominios[faker.number().numberBetween(0, dominios.length)];

        return email;
    }

    private String generarNombre() {

        String nombre = "";

        String[] nombres = {
                "Pablo", "María", "Carlos", "Laura", "Javier",
                "Ana", "Miguel", "Sara", "David", "Elena",
                "Alejandro", "Lucía", "Daniel", "Marta", "Jorge",
                "Carmen", "Sergio", "Patricia", "Andrés", "Cristina",
                "Rubén", "Nuria", "Iván", "Raquel", "Alberto",
                "Silvia", "Fernando", "Beatriz", "Marcos", "Natalia",
                "Víctor", "Irene", "Diego", "Sonia", "Álvaro",
                "Verónica", "Adrián", "Lorena", "Roberto", "Mónica",
                "Antonio", "Rebeca", "Guillermo", "Esther", "Rodrigo",
                "Alicia", "Enrique", "Pilar", "Francisco", "Claudia",
                "Héctor", "Rocío", "Ignacio", "Miriam", "Óscar",
                "Teresa", "Manuel", "Inés", "Rafael", "Sofía",
                "Tomás", "Valentina", "Ernesto", "Jimena", "Gonzalo",
                "Amparo", "Salvador", "Noelia", "Emilio", "Ainhoa",
                "Joaquín", "Vanesa", "Agustín", "Yolanda", "César",
                "Paloma", "Borja", "Leire", "Jaime", "Olga",
                "Ismael", "Concha", "Nicolás", "Fátima", "Hugo",
                "Montserrat", "Mateo", "Blanca", "Iker", "Lola",
                "Unai", "Macarena", "Jon", "Remedios", "Xavi",
                "Dolores", "Arnau", "Esperanza", "Pau", "Encarna"
        };

        nombre += nombres[faker.number().numberBetween(0, nombres.length)] + " ";

        String[] apellidos = {
                "García", "Martínez", "López", "Sánchez", "González",
                "Rodríguez", "Fernández", "Pérez", "Gómez", "Martín",
                "Jiménez", "Ruiz", "Hernández", "Díaz", "Moreno",
                "Muñoz", "Álvarez", "Romero", "Alonso", "Gutiérrez",
                "Navarro", "Torres", "Domínguez", "Vázquez", "Ramos",
                "Gil", "Ramírez", "Serrano", "Blanco", "Molina",
                "Morales", "Suárez", "Ortega", "Delgado", "Castro",
                "Ortiz", "Rubio", "Marín", "Sanz", "Iglesias",
                "Núñez", "Medina", "Garrido", "Cortés", "Castillo",
                "Santos", "Lozano", "Guerrero", "Cano", "Prieto",
                "Méndez", "Cruz", "Calvo", "Gallego", "Vidal",
                "León", "Herrera", "Marcos", "Lorenzo", "Cabrera",
                "Fuentes", "Lara", "Aguilar", "Arias", "Mora",
                "Ibáñez", "Vargas", "Carmona", "Crespo", "Bravo",
                "Tovar", "Velasco", "Pascual", "Reyes", "Herrero",
                "Pons", "Soler", "Ferrer", "Vila", "Puig",
                "Mir", "Bosch", "Valls", "Mas", "Font",
                "Expósito", "Montoya", "Esteban", "Carrasco", "Pardo",
                "Vera", "Caballero", "Campos", "Vega", "Reina",
                "Arranz", "Benítez", "Montes", "Nieto", "Solís"
        };

        nombre += apellidos[faker.number().numberBetween(0, apellidos.length)] + " ";
        nombre += apellidos[faker.number().numberBetween(0, apellidos.length)];

        return nombre;
    }

    private void guardarAsistente(Asistente asistente) {
        try {
            asistenteService.save(asistente);
        } catch (Exception e) {
            System.err.println("Error al asistente con nombre: " +
                    asistente.getNombre() + ": " + e.getMessage());
        }
    }
}