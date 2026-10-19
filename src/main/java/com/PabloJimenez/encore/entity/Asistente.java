package com.PabloJimenez.encore.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

/**
 * Entidad que representa a un asistente a eventos.
 * <p>
 * Mapeada a la tabla {@code asistente} de la base de datos.
 * Un asistente puede estar inscrito en varios {@link Evento} mediante
 * una relacion {@code @ManyToMany}.
 * </p>
 */
@Entity
@Table(name = "asistente")
public class Asistente {

    /**
     * Identificador unico del asistente, generado automaticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre completo del asistente. No puede ser nulo.
     */
    @Column(nullable = false)
    private String nombre;

    /**
     * Numero de documento de identidad del asistente. No puede ser nulo y debe ser unico.
     */
    @Column(nullable = false, unique = true)
    private String documento;

    /**
     * Direccion de correo electronico del asistente. No puede ser nula y debe ser unica.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Edad del asistente. No puede ser nula.
     */
    @Column(nullable = false)
    private int edad;

    /**
     * Lista de eventos a los que esta inscrito el asistente.
     * Relacion {@code @ManyToMany} inversa; excluida de la serializacion JSON.
     */
    @ManyToMany(mappedBy = "asistentes")
    @JsonIgnore
    private List<Evento> eventosAsistente;

    /**
     * Constructor por defecto. Inicializa la lista de eventos vacia.
     */
    public Asistente() {
        this.eventosAsistente = new ArrayList<>();
    }

    /**
     * Constructor con todos los campos del asistente.
     *
     * @param nombre    nombre completo del asistente.
     * @param documento numero de documento de identidad.
     * @param email     direccion de correo electronico.
     * @param edad      edad del asistente.
     */
    public Asistente(String nombre, String documento, String email, int edad) {
        this();
        this.nombre = nombre;
        this.documento = documento;
        this.email = email;
        this.edad = edad;
    }

    /**
     * Devuelve el identificador unico del asistente.
     *
     * @return id del asistente.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador unico del asistente.
     *
     * @param id nuevo id del asistente.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre completo del asistente.
     *
     * @return nombre del asistente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre completo del asistente.
     *
     * @param nombre nuevo nombre del asistente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el numero de documento de identidad del asistente.
     *
     * @return documento del asistente.
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Establece el numero de documento de identidad del asistente.
     *
     * @param documento nuevo documento del asistente.
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Devuelve la edad del asistente.
     *
     * @return edad del asistente.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad del asistente.
     *
     * @param edad nueva edad del asistente.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Devuelve la lista de eventos a los que esta inscrito el asistente.
     *
     * @return lista de {@link Evento}.
     */
    public List<Evento> getEventosAsistente() {
        return eventosAsistente;
    }

    /**
     * Establece la lista de eventos a los que esta inscrito el asistente.
     *
     * @param eventosAsistente nueva lista de {@link Evento}.
     */
    public void setEventosAsistente(List<Evento> eventosAsistente) {
        this.eventosAsistente = eventosAsistente;
    }

    /**
     * Devuelve la direccion de correo electronico del asistente.
     *
     * @return email del asistente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece la direccion de correo electronico del asistente.
     *
     * @param email nuevo email del asistente.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}