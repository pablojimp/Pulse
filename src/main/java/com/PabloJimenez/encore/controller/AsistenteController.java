package com.PabloJimenez.encore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PabloJimenez.encore.DTO.AsistenteDTO;
import com.PabloJimenez.encore.entity.Asistente;
import com.PabloJimenez.encore.service.AsistenteService;

@RestController
@RequestMapping("/api/v1/asistentes")
public class AsistenteController {

    @Autowired
    AsistenteService asistenteService;

    /**
     * Obtiene la lista completa de asistentes registrados.
     *
     * @return Lista con todos los {@link Asistente} existentes en la base de datos.
     */
    @GetMapping
    public List<Asistente> getAllArtistas() {
        return asistenteService.findAll();
    }

    /**
     * Busca un asistente por su identificador único.
     *
     * @param id identificador del asistente a buscar.
     * @return Optional que contiene el {@link Asistente} si existe,
     *         o vacío si no se encuentra.
     */
    @GetMapping("/{id}")
    public Optional<Asistente> getAsistenteById(@PathVariable Long id) {
        return asistenteService.findById(id);
    }

    /**
     * Busca un asistente por su dirección de correo electrónico.
     *
     * @param email dirección de email del asistente a buscar.
     * @return el {@link Asistente} cuyo email coincide con el parámetro,
     *         o {@code null} si no existe.
     */
    @GetMapping("/email")
    public Asistente getAsistenteByEmail(@RequestParam("email") String email) {
        return asistenteService.findByEmail(email);
    }

    /**
     * Busca un asistente por su número de documento de identidad (DNI).
     *
     * @param documento número de documento del asistente a buscar.
     * @return el {@link Asistente} cuyo documento coincide con el parámetro,
     *         o {@code null} si no existe.
     */
    @GetMapping("/dni")
    public Asistente getAsistenteByDocumento(@RequestParam("documento") String documento) {
        return asistenteService.findByDocumento(documento);
    }

    /**
     * Obtiene todos los asistentes inscritos en un evento concreto.
     *
     * @param id identificador del evento cuyos asistentes se quieren recuperar.
     * @return Lista de {@link Asistente} asociados al evento indicado.
     */
    @GetMapping("/evento")
    public List<Asistente> findByIdEvento(@RequestParam("id") Long id) {
        return asistenteService.findByEventoId(id);
    }

    /**
     * Actualiza los datos de un asistente existente.
     * <p>
     * Solo realiza la actualización si el asistente con el {@code id} indicado
     * existe en la base de datos.
     * </p>
     *
     * @param id      identificador del asistente a actualizar.
     * @param artista objeto {@link Asistente} con los nuevos datos.
     * @return el {@link Asistente} actualizado, o null si no se encontró.
     */
    @PutMapping("/{id}")
    public Asistente updateEvento(@PathVariable Long id, @RequestBody Asistente artista) {
        Asistente result = null;
        if (asistenteService.findById(id).isPresent()) {
            artista.setId(id);
            result = asistenteService.save(artista);
        }
        return result;
    }

    /**
     * Crea un nuevo asistente a partir de los datos recibidos en el cuerpo de la
     * petición.
     * <p>
     * Este método es utilizado internamente por el generador de datos con Faker.
     * </p>
     *
     * @param asistente objeto {@link Asistente} con los datos del nuevo asistente.
     * @return el {@link Asistente} persistido con su identificador generado.
     */
    @PostMapping
    public Asistente createAsistente(@RequestBody Asistente asistente) {
        return asistenteService.save(asistente);
    }

    /**
     * Elimina un asistente de la base de datos por su identificador.
     * <p>
     * Si el asistente no existe, la operación no tiene ningún efecto.
     * </p>
     *
     * @param id identificador del asistente a eliminar.
     * @return el {@code id} del asistente eliminado.
     */
    @DeleteMapping("/{id}")
    public Long deleteAsistente(@PathVariable Long id) {
        if (asistenteService.findById(id).isPresent()) {
            asistenteService.deleteById(id);
        }
        return id;
    }

    /**
     * Crea un nuevo asistente y lo vincula directamente a un evento existente.
     * <p>
     * Recibe un {@link AsistenteDTO} con los datos personales del asistente
     * y el identificador del evento al que se va a inscribir.
     * </p>
     *
     * @param dto objeto de transferencia de datos que contiene nombre, documento,
     *            email, edad e {@code eventoId} del evento al que se asociará el
     *            asistente.
     * @return el {@link Asistente} creado y asociado al evento indicado.
     */
    @PostMapping("/crear")
    public Asistente createAsistenteConEvento(@RequestBody AsistenteDTO dto) {
        Asistente asistente = new Asistente(
                dto.getNombre(),
                dto.getDocumento(),
                dto.getEmail(),
                dto.getEdad());
        return asistenteService.saveWithEvento(asistente, dto.getEventoId());
    }
}