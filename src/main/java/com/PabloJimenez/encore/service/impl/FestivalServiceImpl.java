package com.PabloJimenez.encore.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PabloJimenez.encore.entity.Festival;
import com.PabloJimenez.encore.repository.FestivalRepository;
import com.PabloJimenez.encore.service.FestivalService;

/**
 * Implementacion de {@link FestivalService} que gestiona la logica de negocio
 * relacionada con los festivales.
 * <p>
 * Delega las operaciones de persistencia en {@link FestivalRepository}.
 * </p>
 */
@Service
public class FestivalServiceImpl implements FestivalService {

    /** Repositorio de festivales inyectado por Spring. */
    @Autowired
    FestivalRepository festivalRepository;

    /**
     * Recupera todos los festivales almacenados en la base de datos.
     *
     * @return {@link List} con todos los {@link Festival} existentes.
     */
    @Override
    public List<Festival> findAll() {
        return festivalRepository.findAll();
    }

    /**
     * Busca un festival por su identificador unico.
     *
     * @param id identificador del festival a buscar.
     * @return {@link Optional} con el {@link Festival} si existe, o vacio si no se encuentra.
     */
    @Override
    public Optional<Festival> findById(Long id) {
        return festivalRepository.findById(id);
    }

    /**
     * Busca festivales cuyo precio de entrada este dentro de un rango dado.
     * <p>
     * Solo realiza la consulta si {@code minimo} es mayor o igual a 0
     * y {@code maximo} es estrictamente mayor que {@code minimo}.
     * En caso contrario, devuelve una lista vacia.
     * </p>
     *
     * @param minimo precio minimo del rango de busqueda (inclusive).
     * @param maximo precio maximo del rango de busqueda (inclusive).
     * @return {@link List} de {@link Festival} con precio comprendido entre {@code minimo} y {@code maximo},
     *         o una lista vacia si los parametros no son validos.
     */
    @Override
    public List<Festival> buscarPorPrecioRango(double minimo, double maximo) {
        List<Festival> lista = Collections.emptyList();
        if (minimo >= 0 && maximo > minimo) {
            lista = festivalRepository.buscarPorPrecioRango(minimo, maximo);
        }
        return lista;
    }

    /**
     * Busca festivales cuya duracion en dias este dentro de un rango dado.
     * <p>
     * Solo realiza la consulta si {@code minimo} es mayor o igual a 0
     * y {@code maximo} es estrictamente mayor que {@code minimo}.
     * En caso contrario, devuelve una lista vacia.
     * </p>
     *
     * @param minimo numero minimo de dias del rango de busqueda (inclusive).
     * @param maximo numero maximo de dias del rango de busqueda (inclusive).
     * @return {@link List} de {@link Festival} con duracion comprendida entre {@code minimo} y {@code maximo} dias,
     *         o una lista vacia si los parametros no son validos.
     */
    @Override
    public List<Festival> buscarPorDiasRango(int minimo, int maximo) {
        List<Festival> lista = Collections.emptyList();
        if (minimo >= 0 && maximo > minimo) {
            lista = festivalRepository.buscarPorDiasRango(minimo, maximo);
        }
        return lista;
    }

    /**
     * Busca festivales por su numero de escenarios.
     *
     * @param numeroEscenarios numero exacto de escenarios por el que filtrar.
     * @return {@link List} de {@link Festival} que tienen exactamente {@code numeroEscenarios} escenarios.
     */
    @Override
    public List<Festival> findByNumeroEscenarios(int numeroEscenarios) {
        return festivalRepository.findByNumeroEscenarios(numeroEscenarios);
    }

    /**
     * Busca festivales cuya edad minima de acceso sea menor o igual a la indicada.
     * <p>
     * Permite encontrar todos los festivales a los que puede asistir
     * una persona de la edad especificada.
     * </p>
     *
     * @param edadMinima edad del usuario para filtrar los festivales accesibles.
     * @return {@link List} de {@link Festival} con edad minima menor o igual a {@code edadMinima}.
     */
    @Override
    public List<Festival> findByEdadMinima(int edadMinima) {
        return festivalRepository.findByEdadMinimaLessThanEqual(edadMinima);
    }

    /**
     * Persiste un festival en la base de datos.
     * <p>
     * Si el festival ya existe (tiene un {@code id} asignado), actualiza sus datos;
     * de lo contrario, crea un nuevo registro.
     * </p>
     *
     * @param festival objeto {@link Festival} a guardar o actualizar.
     * @return el {@link Festival} persistido con su identificador generado o actualizado.
     */
    @Override
    public Festival save(Festival festival) {
        return festivalRepository.save(festival);
    }

    /**
     * Elimina un festival de la base de datos por su identificador.
     *
     * @param id identificador del festival a eliminar.
     */
    @Override
    public void deleteById(Long id) {
        festivalRepository.deleteById(id);
    }
}