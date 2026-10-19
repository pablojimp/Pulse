package com.PabloJimenez.encore.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

/**
 * Entidad que representa el lugar donde se celebra un evento.
 * <p>
 * Mapeada a la tabla {@code Lugares} de la base de datos.
 * Un lugar puede acoger multiples {@link Evento}; la relacion es
 * {@code @OneToMany} con cascada total, de modo que al eliminar un lugar
 * se eliminan tambien sus eventos asociados.
 * </p>
 */
@Entity
@Table(name = "Lugares")
public class LugarEvento {

    /**
     * Identificador unico del lugar, generado automaticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del lugar. No puede ser nulo.
     */
    @Column(name = "nombreLugar", nullable = false)
    private String nombreLugar;

    /**
     * Aforo maximo del lugar. No puede ser nulo.
     */
    @Column(name = "aforoLugar", nullable = false)
    private int aforoLugar;

    /**
     * Localizacion geografica o direccion del lugar.
     */
    @Column(name = "localizacion")
    private String localizacion;

    /**
     * Lista de eventos que se celebran en este lugar.
     * Relacion {@code @OneToMany} con cascada total; excluida de la serializacion JSON.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "lugar", cascade = CascadeType.ALL)
    private List<Evento> eventos;

    /**
     * Constructor por defecto. Inicializa la lista de eventos vacia.
     */
    public LugarEvento() {
        this.eventos = new ArrayList<>();
    }

    /**
     * Constructor completo que incluye la lista inicial de eventos.
     *
     * @param nombreLugar  nombre del lugar.
     * @param aforoLugar   aforo maximo del lugar.
     * @param localizacion localizacion o direccion del lugar.
     * @param eventos      lista inicial de {@link Evento} asociados.
     */
    public LugarEvento(String nombreLugar, int aforoLugar, String localizacion, List<Evento> eventos) {
        this.nombreLugar = nombreLugar;
        this.aforoLugar = aforoLugar;
        this.localizacion = localizacion;
        this.eventos = eventos;
    }

    /**
     * Constructor sin lista de eventos. La lista se inicializa vacia.
     *
     * @param nombreLugar  nombre del lugar.
     * @param aforoLugar   aforo maximo del lugar.
     * @param localizacion localizacion o direccion del lugar.
     */
    public LugarEvento(String nombreLugar, int aforoLugar, String localizacion) {
        this();
        this.nombreLugar = nombreLugar;
        this.aforoLugar = aforoLugar;
        this.localizacion = localizacion;
    }

    /**
     * Devuelve el identificador unico del lugar.
     *
     * @return id del lugar.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador unico del lugar.
     *
     * @param id nuevo id del lugar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del lugar.
     *
     * @return nombre del lugar.
     */
    public String getNombreLugar() {
        return nombreLugar;
    }

    /**
     * Establece el nombre del lugar.
     *
     * @param nombreLugar nuevo nombre del lugar.
     */
    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    /**
     * Devuelve el aforo maximo del lugar.
     *
     * @return aforo del lugar.
     */
    public Integer getAforoLugar() {
        return aforoLugar;
    }

    /**
     * Establece el aforo maximo del lugar.
     *
     * @param aforoLugar nuevo aforo del lugar.
     */
    public void setAforoLugar(int aforoLugar) {
        this.aforoLugar = aforoLugar;
    }

    /**
     * Establece el aforo maximo del lugar (sobrecarga con {@link Integer}).
     *
     * @param aforoLugar nuevo aforo del lugar.
     */
    public void setAforoLugar(Integer aforoLugar) {
        this.aforoLugar = aforoLugar;
    }

    /**
     * Devuelve la localizacion o direccion del lugar.
     *
     * @return localizacion del lugar.
     */
    public String getLocalizacion() {
        return localizacion;
    }

    /**
     * Establece la localizacion o direccion del lugar.
     *
     * @param localizacion nueva localizacion del lugar.
     */
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    /**
     * Devuelve la lista de eventos asociados al lugar.
     *
     * @return lista de {@link Evento}.
     */
    public List<Evento> getEventos() {
        return eventos;
    }

    /**
     * Establece la lista de eventos asociados al lugar.
     *
     * @param eventos nueva lista de {@link Evento}.
     */
    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}