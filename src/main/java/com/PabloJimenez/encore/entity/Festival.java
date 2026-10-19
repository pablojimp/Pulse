package com.PabloJimenez.encore.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Entidad que representa un festival musical, subclase de {@link Evento}.
 * <p>
 * Mapeada a la tabla {@code Festival} mediante herencia {@code JOINED},
 * usando {@code eventoId} como clave primaria y foranea hacia {@link Evento}.
 * Un festival puede contar con multiples {@link Artista} y se celebra
 * durante varios dias.
 * </p>
 */
@Entity
@Table(name = "Festival")
@PrimaryKeyJoinColumn(name = "eventoId")
public class Festival extends Evento {

    /**
     * Fecha y hora de inicio del festival. No puede ser nula.
     */
    @Column(name = "fechaHora", nullable = false)
    private LocalDateTime fechaHora;

    /**
     * Precio de la entrada del festival. No puede ser nulo.
     */
    @Column(name = "precio", nullable = false)
    private double precio;

    /**
     * Edad minima requerida para acceder al festival.
     */
    @Column(name = "edadMinima")
    private int edadMinima;

    /**
     * Numero de escenarios del festival.
     */
    @Column(name = "numeroEscenarios")
    private int numeroEscenarios;

    /**
     * Numero de dias de duracion del festival.
     */
    @Column(name = "numeroDias")
    private int numeroDias;

    /**
     * Lista de artistas que participan en el festival.
     * Relacion {@code @ManyToMany} propietaria, gestionada a traves de
     * la tabla intermedia {@code festivalArtista}.
     */
    @ManyToMany
    @JoinTable(name = "festivalArtista",
            joinColumns = @JoinColumn(name = "festivalId"),
            inverseJoinColumns = @JoinColumn(name = "artistaId"))
    private List<Artista> artistas;

    /**
     * Constructor por defecto. Inicializa la lista de artistas vacia.
     */
    public Festival() {
        this.artistas = new ArrayList<>();
    }

    /**
     * Constructor completo que incluye la lista inicial de asistentes y artistas.
     *
     * @param nombre           nombre del festival.
     * @param promotor         nombre del promotor.
     * @param lugar            lugar donde se celebra el festival.
     * @param asistentes       lista inicial de {@link Asistente} inscritos.
     * @param fechaHora        fecha y hora de inicio.
     * @param precio           precio de la entrada.
     * @param edadMinima       edad minima de acceso.
     * @param numeroEscenarios numero de escenarios del festival.
     * @param numeroDias       numero de dias de duracion.
     * @param artistas         lista inicial de {@link Artista} participantes.
     */
    public Festival(String nombre, String promotor, LugarEvento lugar, ArrayList<Asistente> asistentes,
            LocalDateTime fechaHora, double precio,
            int edadMinima, int numeroEscenarios,
            int numeroDias, List<Artista> artistas) {
        super(nombre, promotor, lugar, asistentes);
        this.fechaHora = fechaHora;
        this.precio = precio;
        this.edadMinima = edadMinima;
        this.numeroEscenarios = numeroEscenarios;
        this.numeroDias = numeroDias;
        this.artistas = artistas;
    }

    /**
     * Constructor sin lista de asistentes ni artistas.
     * La lista de artistas se inicializa vacia.
     *
     * @param nombre           nombre del festival.
     * @param promotor         nombre del promotor.
     * @param lugar            lugar donde se celebra el festival.
     * @param fechaHora        fecha y hora de inicio.
     * @param precio           precio de la entrada.
     * @param edadMinima       edad minima de acceso.
     * @param numeroEscenarios numero de escenarios del festival.
     * @param numeroDias       numero de dias de duracion.
     */
    public Festival(String nombre, String promotor, LugarEvento lugar,
            LocalDateTime fechaHora, double precio,
            int edadMinima, int numeroEscenarios,
            int numeroDias) {
        super(nombre, promotor, lugar);
        this.fechaHora = fechaHora;
        this.precio = precio;
        this.edadMinima = edadMinima;
        this.numeroEscenarios = numeroEscenarios;
        this.numeroDias = numeroDias;
        this.artistas = new ArrayList<>();
    }

    /**
     * Devuelve la fecha y hora de inicio del festival.
     *
     * @return fecha y hora del festival.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora de inicio del festival.
     *
     * @param fechaHora nueva fecha y hora del festival.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Devuelve el precio de la entrada del festival.
     *
     * @return precio del festival.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de la entrada del festival.
     *
     * @param precio nuevo precio del festival.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Devuelve la edad minima requerida para acceder al festival.
     *
     * @return edad minima de acceso.
     */
    public int getEdadMinima() {
        return edadMinima;
    }

    /**
     * Establece la edad minima requerida para acceder al festival.
     *
     * @param edadMinima nueva edad minima de acceso.
     */
    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    /**
     * Devuelve el numero de escenarios del festival.
     *
     * @return numero de escenarios.
     */
    public int getNumeroEscenarios() {
        return numeroEscenarios;
    }

    /**
     * Establece el numero de escenarios del festival.
     *
     * @param numeroEscenarios nuevo numero de escenarios.
     */
    public void setNumeroEscenarios(int numeroEscenarios) {
        this.numeroEscenarios = numeroEscenarios;
    }

    /**
     * Devuelve el numero de dias de duracion del festival.
     *
     * @return numero de dias.
     */
    public int getNumeroDias() {
        return numeroDias;
    }

    /**
     * Establece el numero de dias de duracion del festival.
     *
     * @param numeroDias nuevo numero de dias.
     */
    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    /**
     * Devuelve la lista de artistas participantes en el festival.
     *
     * @return lista de {@link Artista}.
     */
    public List<Artista> getArtistas() {
        return artistas;
    }

    /**
     * Establece la lista de artistas participantes en el festival.
     *
     * @param artistas nueva lista de {@link Artista}.
     */
    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }
}