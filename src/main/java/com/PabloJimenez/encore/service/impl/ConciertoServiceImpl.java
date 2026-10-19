package com.PabloJimenez.encore.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PabloJimenez.encore.entity.Concierto;
import com.PabloJimenez.encore.repository.ConciertoRepository;
import com.PabloJimenez.encore.service.ConciertoService;

/**
 * Implementacion de {@link ConciertoService} que gestiona la logica de negocio
 * relacionada con los conciertos.
 * <p>
 * Delega las operaciones de persistencia en {@link ConciertoRepository}.
 * </p>
 */
@Service
public class ConciertoServiceImpl implements ConciertoService {

    /** Repositorio de conciertos inyectado por Spring. */
    @Autowired
    ConciertoRepository conciertoRepository;

    /**
     * Recupera todos los conciertos almacenados en la base de datos.
     *
     * @return {@link List} con todos los {@link Concierto} existentes.
     */
    @Override
    public List<Concierto> findAll() {
        return conciertoRepository.findAll();
    }

    /**
     * Busca un concierto por su identificador unico.
     *
     * @param id identificador del concierto a buscar.
     * @return {@link Optional} con el {@link Concierto} si existe, o vacio si no se encuentra.
     */
    @Override
    public Optional<Concierto> findById(Long id) {
        return conciertoRepository.findById(id);
    }

    /**
     * Busca conciertos cuyo precio de entrada este dentro de un rango dado.
     * <p>
     * Solo realiza la consulta si {@code minimo} es mayor o igual a 0
     * y {@code maximo} es estrictamente mayor que {@code minimo}.
     * En caso contrario, devuelve una lista vacia.
     * </p>
     *
     * @param minimo precio minimo del rango de busqueda (inclusive).
     * @param maximo precio maximo del rango de busqueda (inclusive).
     * @return {@link List} de {@link Concierto} con precio comprendido entre {@code minimo} y {@code maximo},
     *         o una lista vacia si los parametros no son validos.
     */
    @Override
    public List<Concierto> buscarPorPrecioRango(double minimo, double maximo) {
        List<Concierto> lista = Collections.emptyList();
        if (minimo >= 0 && maximo > minimo) {
            lista = conciertoRepository.buscarPorPrecioRango(minimo, maximo);
        }
        return lista;
    }

    /**
     * Busca conciertos cuya edad minima de acceso sea menor o igual a la indicada.
     * <p>
     * Permite encontrar todos los conciertos a los que puede asistir
     * una persona de la edad especificada.
     * </p>
     *
     * @param edadMinima edad del usuario para filtrar los conciertos accesibles.
     * @return {@link List} de {@link Concierto} con edad minima menor o igual a {@code edadMinima}.
     */
    @Override
    public List<Concierto> findByEdadMinima(int edadMinima) {
        return conciertoRepository.findByEdadMinimaLessThanEqual(edadMinima);
    }

    /**
     * Persiste un concierto en la base de datos.
     * <p>
     * Si el concierto ya existe (tiene un {@code id} asignado), actualiza sus datos;
     * de lo contrario, crea un nuevo registro.
     * </p>
     *
     * @param concierto objeto {@link Concierto} a guardar o actualizar.
     * @return el {@link Concierto} persistido con su identificador generado o actualizado.
     */
    @Override
    public Concierto save(Concierto concierto) {
        return conciertoRepository.save(concierto);
    }

    /**
     * Elimina un concierto de la base de datos por su identificador.
     *
     * @param id identificador del concierto a eliminar.
     */
    @Override
    public void deleteById(Long id) {
        conciertoRepository.deleteById(id);
    }
}