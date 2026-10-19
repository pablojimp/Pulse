package com.PabloJimenez.encore.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.persistence.*;

/**
 * Entidad que representa un concierto, subclase de {@link Evento}.
 * <p>
 * Mapeada a la tabla {@code Concierto} mediante herencia {@code JOINED},
 * usando {@code eventoId} como clave primaria y foranea hacia {@link Evento}.
 * Un concierto esta protagonizado por un unico {@link Artista}.
 * </p>
 */
@Entity
@Table(name = "Concierto")
@PrimaryKeyJoinColumn(name = "eventoId")
public class Concierto extends Evento {

    /**
     * Fecha y hora de celebracion del concierto. No puede ser nula.
     */
    @Column(name = "fechaHora", nullable = false)
    private LocalDateTime fechaHora;

    /**
     * Precio de la entrada del concierto. No puede ser nulo.
     */
    @Column(name = "precio", nullable = false)
    private double precio;

    /**
     * Edad minima requerida para acceder al concierto.
     */
    @Column(name = "edadMinima")
    private int edadMinima;

    /**
     * Duracion del concierto en minutos.
     */
    @Column(name = "duracion")
    private int duracion;

    /**
     * Artista protagonista del concierto. No puede ser nulo.
     * Relacion {@code @ManyToOne} con {@link Artista}.
     */
    @ManyToOne
    @JoinColumn(name = "artistaId", nullable = false)
    private Artista artista;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Concierto() {
    }

    /**
     * Constructor completo que incluye la lista inicial de asistentes.
     *
     * @param nombre      nombre del concierto.
     * @param promotor    nombre del promotor.
     * @param lugar       lugar donde se celebra el concierto.
     * @param asistentes  lista inicial de {@link Asistente} inscritos.
     * @param fechaHora   fecha y hora de celebracion.
     * @param precio      precio de la entrada.
     * @param edadMinima  edad minima de acceso.
     * @param duracion    duracion en minutos.
     * @param artista     artista protagonista del concierto.
     */
    public Concierto(String nombre, String promotor, LugarEvento lugar, ArrayList<Asistente> asistentes,
            LocalDateTime fechaHora, double precio,
            int edadMinima, int duracion, Artista artista) {
        super(nombre, promotor, lugar, asistentes);
        this.fechaHora = fechaHora;
        this.precio = precio;
        this.edadMinima = edadMinima;
        this.duracion = duracion;
        this.artista = artista;
    }

    /**
     * Constructor sin lista de asistentes. La lista se inicializa vacia.
     *
     * @param nombre     nombre del concierto.
     * @param promotor   nombre del promotor.
     * @param lugar      lugar donde se celebra el concierto.
     * @param fechaHora  fecha y hora de celebracion.
     * @param precio     precio de la entrada.
     * @param edadMinima edad minima de acceso.
     * @param duracion   duracion en minutos.
     * @param artista    artista protagonista del concierto.
     */
    public Concierto(String nombre, String promotor, LugarEvento lugar,
            LocalDateTime fechaHora, double precio,
            int edadMinima, int duracion, Artista artista) {
        super(nombre, promotor, lugar);
        this.fechaHora = fechaHora;
        this.precio = precio;
        this.edadMinima = edadMinima;
        this.duracion = duracion;
        this.artista = artista;
    }

    /**
     * Devuelve la fecha y hora de celebracion del concierto.
     *
     * @return fecha y hora del concierto.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora de celebracion del concierto.
     *
     * @param fechaHora nueva fecha y hora del concierto.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Devuelve el precio de la entrada del concierto.
     *
     * @return precio del concierto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de la entrada del concierto.
     *
     * @param precio nuevo precio del concierto.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Devuelve la edad minima requerida para acceder al concierto.
     *
     * @return edad minima de acceso.
     */
    public Integer getEdadMinima() {
        return edadMinima;
    }

    /**
     * Establece la edad minima requerida para acceder al concierto.
     *
     * @param edadMinima nueva edad minima de acceso.
     */
    public void setEdadMinima(Integer edadMinima) {
        this.edadMinima = edadMinima;
    }

    /**
     * Devuelve la duracion del concierto en minutos.
     *
     * @return duracion en minutos.
     */
    public Integer getDuracion() {
        return duracion;
    }

    /**
     * Establece la duracion del concierto en minutos.
     *
     * @param duracion nueva duracion en minutos.
     */
    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    /**
     * Devuelve el artista protagonista del concierto.
     *
     * @return {@link Artista} del concierto.
     */
    public Artista getArtista() {
        return artista;
    }

    /**
     * Establece el artista protagonista del concierto.
     *
     * @param artista nuevo {@link Artista} del concierto.
     */
    public void setArtista(Artista artista) {
        this.artista = artista;
    }
}