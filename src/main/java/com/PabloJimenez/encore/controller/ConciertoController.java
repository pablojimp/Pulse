package com.PabloJimenez.encore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PabloJimenez.encore.entity.Concierto;
import com.PabloJimenez.encore.service.ConciertoService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/conciertos")
public class ConciertoController {

    @Autowired
    ConciertoService conciertoService;

    /**
     * Obtiene la lista completa de conciertos registrados.
     *
     * @return {@link List} con todos los {@link Concierto} existentes en la base de datos.
     */
    @GetMapping
    public List<Concierto> getAllConciertos() {
        return conciertoService.findAll();
    }

    /**
     * Busca un concierto por su identificador unico.
     *
     * @param id identificador del concierto a buscar.
     * @return {@link Optional} que contiene el {@link Concierto} si existe,
     *         o vacio si no se encuentra.
     */
    @GetMapping("/{id}")
    public Optional<Concierto> getEventoById(@PathVariable Long id) {
        return conciertoService.findById(id);
    }

    /**
     * Busca conciertos cuyo precio de entrada se encuentre dentro de un rango dado.
     *
     * @param min precio minimo del rango de busqueda (inclusive).
     * @param max precio maximo del rango de busqueda (inclusive).
     * @return {@link List} de {@link Concierto} con precio comprendido entre {@code min} y {@code max}.
     */
    @GetMapping("/precios")
    public List<Concierto> buscarPorPrecioRango(@RequestParam("min") double min, @RequestParam("max") double max) {
        return conciertoService.buscarPorPrecioRango(min, max);
    }

    /**
     * Busca conciertos aptos para una edad minima concreta.
     *
     * @param edad edad minima requerida para acceder al concierto.
     * @return {@link List} de {@link Concierto} cuya edad minima coincide con el valor indicado.
     */
    @GetMapping("/edad/{edad}")
    public List<Concierto> buscarPorEdad(@PathVariable int edad) {
        return conciertoService.findByEdadMinima(edad);
    }

    /**
     * Actualiza los datos de un concierto existente.
     * <p>
     * Solo realiza la actualizacion si el concierto con el {@code id} indicado
     * existe en la base de datos.
     * </p>
     *
     * @param id        identificador del concierto a actualizar.
     * @param concierto objeto {@link Concierto} con los nuevos datos.
     * @return el {@link Concierto} actualizado, o {@code null} si no se encontro.
     */
    @PutMapping("/{id}")
    public Concierto updateEvento(@PathVariable Long id, @RequestBody Concierto concierto) {
        Concierto result = null;
        if (conciertoService.findById(id).isPresent()) {
            concierto.setId(id);
            result = conciertoService.save(concierto);
        }
        return result;
    }

    /**
     * Elimina un concierto de la base de datos por su identificador.
     * <p>
     * Si el concierto no existe, la operacion no tiene ningun efecto.
     * </p>
     *
     * @param id identificador del concierto a eliminar.
     */
    @DeleteMapping("/{id}")
    public void deleteEvento(@PathVariable Long id) {
        if (conciertoService.findById(id).isPresent()) {
            conciertoService.deleteById(id);
        }
    }
}