package com.PabloJimenez.encore.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Clase abstracta base que representa un evento.
 * <p>
 * Mapeada a la tabla {@code Evento} usando la estrategia de herencia
 * {@link InheritanceType#JOINED}, por lo que cada subclase ({@link Concierto},
 * {@link Festival}) tiene su propia tabla con una clave foranea hacia esta.
 * </p>
 * <p>
 * Un evento tiene un nombre, un promotor, un {@link LugarEvento} donde se celebra
 * y una lista de {@link Asistente} inscritos mediante una tabla intermedia
 * {@code asistente_evento}.
 * </p>
 */
@Entity
@Table(name = "Evento")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Evento {

    /**
     * Identificador unico del evento, generado automaticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del evento. No puede ser nulo.
     */
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /**
     * Nombre del promotor responsable del evento.
     */
    @Column(name = "promotor")
    private String promotor;

    /**
     * Lugar donde se celebra el evento. No puede ser nulo.
     * Relacion {@code @ManyToOne} con {@link LugarEvento}.
     */
    @ManyToOne
    @JoinColumn(name = "lugarId", nullable = false)
    private LugarEvento lugar;

    /**
     * Lista de asistentes inscritos en el evento.
     * Relacion {@code @ManyToMany} propietaria, gestionada a traves de la
     * tabla intermedia {@code asistente_evento}.
     */
    @ManyToMany
    @JoinTable(name = "asistente_evento",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "asistente_id"))
    private List<Asistente> asistentes;

    /**
     * Constructor por defecto. Inicializa la lista de asistentes vacia.
     */
    public Evento() {
        this.asistentes = new ArrayList<>();
    }

    /**
     * Constructor con los campos basicos del evento. La lista de asistentes
     * se inicializa vacia.
     *
     * @param nombre   nombre del evento.
     * @param promotor nombre del promotor.
     * @param lugar    lugar donde se celebra el evento.
     */
    public Evento(String nombre, String promotor, LugarEvento lugar) {
        this();
        this.nombre = nombre;
        this.promotor = promotor;
        this.lugar = lugar;
    }

    /**
     * Constructor con todos los campos del evento, incluyendo la lista de asistentes.
     *
     * @param nombre     nombre del evento.
     * @param promotor   nombre del promotor.
     * @param lugar      lugar donde se celebra el evento.
     * @param asistentes lista inicial de {@link Asistente} inscritos.
     */
    public Evento(String nombre, String promotor, LugarEvento lugar, ArrayList<Asistente> asistentes) {
        this.asistentes = asistentes;
        this.nombre = nombre;
        this.promotor = promotor;
        this.lugar = lugar;
    }

    /**
     * Devuelve el nombre del evento.
     *
     * @return nombre del evento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del evento.
     *
     * @param nombre nuevo nombre del evento.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el nombre del promotor del evento.
     *
     * @return promotor del evento.
     */
    public String getPromotor() {
        return promotor;
    }

    /**
     * Establece el nombre del promotor del evento.
     *
     * @param promotor nuevo promotor del evento.
     */
    public void setPromotor(String promotor) {
        this.promotor = promotor;
    }

    /**
     * Devuelve el lugar donde se celebra el evento.
     *
     * @return {@link LugarEvento} del evento.
     */
    public LugarEvento getLugar() {
        return lugar;
    }

    /**
     * Establece el lugar donde se celebra el evento.
     *
     * @param lugar nuevo {@link LugarEvento} del evento.
     */
    public void setLugar(LugarEvento lugar) {
        this.lugar = lugar;
    }

    /**
     * Devuelve el identificador unico del evento.
     *
     * @return id del evento.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador unico del evento.
     *
     * @param id nuevo id del evento.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve la lista de asistentes inscritos en el evento.
     *
     * @return lista de {@link Asistente}.
     */
    public List<Asistente> getAsistentes() {
        return asistentes;
    }

    /**
     * Establece la lista de asistentes inscritos en el evento.
     *
     * @param asistentes nueva lista de {@link Asistente}.
     */
    public void setAsistentes(List<Asistente> asistentes) {
        this.asistentes = asistentes;
    }
}