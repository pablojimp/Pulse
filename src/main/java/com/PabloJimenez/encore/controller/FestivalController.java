package com.PabloJimenez.encore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.PabloJimenez.encore.entity.Festival;
import com.PabloJimenez.encore.service.FestivalService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/festivales")
public class FestivalController {

    @Autowired
    FestivalService festivalService;

    /**
     * Obtiene la lista completa de festivales registrados.
     *
     * @return {@link List} con todos los {@link Festival} existentes en la base de
     *         datos.
     */
    @GetMapping
    public List<Festival> getAllEventos() {
        return festivalService.findAll();
    }

    /**
     * Busca un festival por su identificador único.
     *
     * @param id identificador del festival a buscar.
     * @return {@link Optional} que contiene el {@link Festival} si existe,
     *         o vacío si no se encuentra.
     */
    @GetMapping("/{id}")
    public Optional<Festival> getFestivalById(@PathVariable Long id) {
        return festivalService.findById(id);
    }

    /**
     * Busca festivales cuyo precio de entrada se encuentre dentro de un rango dado.
     *
     * @param min precio mínimo del rango de búsqueda (inclusive).
     * @param max precio maximo del rango de búsqueda (inclusive).
     * @return {@link List} de {@link Festival} con precio comprendido entre
     *         {@code min} y {@code max}.
     */
    @GetMapping("/precios")
    public List<Festival> buscarPorPrecioRango(@RequestParam("min") double min, @RequestParam("max") double max) {
        return festivalService.buscarPorPrecioRango(min, max);
    }

    /**
     * Busca festivales aptos para una edad mínima concreta.
     *
     * @param edad edad mínima requerida para acceder al festival.
     * @return {@link List} de {@link Festival} cuya edad mínima coincide con el
     *         valor indicado.
     */
    @GetMapping("/edad/{edad}")
    public List<Festival> buscarPorEdad(@PathVariable int edad) {
        return festivalService.findByEdadMinima(edad);
    }

    /**
     * Busca festivales cuya duracion en días se encuentre dentro de un rango dado.
     *
     * @param min número mínimo de días del rango de búsqueda (inclusive).
     * @param max número maximo de días del rango de búsqueda (inclusive).
     * @return {@link List} de {@link Festival} con duracion comprendida entre
     *         {@code min} y {@code max} días.
     */
    @GetMapping("/dias")
    public List<Festival> buscarPorDias(@RequestParam("min") int min, @RequestParam("max") int max) {
        return festivalService.buscarPorDiasRango(min, max);
    }

    /**
     * Actualiza los datos de un festival existente.
     * <p>
     * Solo realiza la actualizacion si el festival con el {@code id} indicado
     * existe en la base de datos.
     * </p>
     *
     * @param id       identificador del festival a actualizar.
     * @param festival objeto {@link Festival} con los nuevos datos.
     * @return el {@link Festival} actualizado, o {@code null} si no se encontro.
     */
    @PutMapping("/{id}")
    public Festival updateEvento(@PathVariable Long id, @RequestBody Festival festival) {
        Festival result = null;
        if (festivalService.findById(id).isPresent()) {
            festival.setId(id);
            result = festivalService.save(festival);
        }
        return result;
    }

    /**
     * Elimina un festival de la base de datos por su identificador.
     * <p>
     * Si el festival no existe, la operacion no tiene ningún efecto.
     * </p>
     *
     * @param id identificador del festival a eliminar.
     */
    @DeleteMapping("/{id}")
    public void deleteEvento(@PathVariable Long id) {
        if (festivalService.findById(id).isPresent()) {
            festivalService.deleteById(id);
        }
    }
}