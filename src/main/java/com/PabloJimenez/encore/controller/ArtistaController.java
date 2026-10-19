package com.PabloJimenez.encore.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.PabloJimenez.encore.entity.Artista;
import com.PabloJimenez.encore.service.ArtistaService;
import com.github.javafaker.Artist;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/artistas")
public class ArtistaController {
    @Autowired
    ArtistaService artistaService;

    /**
     * Obtiene la lista completa de artistas registrados.
     *
     * @return Lista con todos los {@link Artista} existentes en la base de datos.
     */
    @GetMapping
    public List<Artista> getAllArtistas() {
        return artistaService.findAll();
    }

    /**
     * Busca un artista por su identificador unico.
     *
     * @param id identificador del artista a buscar.
     * @return Optional que contiene el {@link Artista} si existe,
     *         o vacío si no se encuentra.
     */
    @GetMapping("/{id}")
    public Optional<Artista> getArtistaById(@PathVariable Long id) {
        return artistaService.findById(id);
    }

    /**
     * Busca artistas filtrando por ciudad de nacimiento.
     *
     * @param ciudad nombre de la ciudad de nacimiento por la que filtrar.
     * @return Lista de {@link Artista} nacidos en la ciudad indicada.
     */
    @GetMapping("/ciudad")
    public List<Artista> getArtistasByCiudad(@RequestParam("ciudad") String ciudad) {
        return artistaService.findByCiudadNacimiento(ciudad);
    }

    /**
     * Busca un artista por su nombre artistico.
     *
     * @param nombre nombre artistico del artista a buscar.
     * @return el {@link Artista} cuyo nombre coincide con el parámetro
     */
    @GetMapping("/nombre")
    public Artista getArtistasByNombre(@RequestParam("nombre") String nombre) {
        return artistaService.findByNombre(nombre);
    }

    /**
     * Actualiza los datos de un artista existente.
     * <p>
     * Solo realiza la actualización si el artista con el {@code id} indicado
     * existe en la base de datos.
     * </p>
     *
     * @param id      identificador del artista a actualizar.
     * @param artista objeto {@link Artista} con los nuevos datos.
     * @return el {@link Artista} actualizado, o {@code null} si no se encontró.
     */
    @PutMapping("/{id}")
    public Artista updateEvento(@PathVariable Long id, @RequestBody Artista artista) {
        Artista result = null;
        if (artistaService.findById(id).isPresent()) {
            artista.setId(id);
            result = artistaService.save(artista);
        }
        return result;
    }

    /**
     * Elimina un artista de la base de datos por su identificador.
     * <p>
     * Si el artista no existe, la operación no tiene ningún efecto.
     * </p>
     *
     * @param id identificador del artista a eliminar.
     */
    @DeleteMapping("/{id}")
    public void deleteEvento(@PathVariable Long id) {
        if (artistaService.findById(id).isPresent()) {
            artistaService.deleteById(id);
        }
    }
}