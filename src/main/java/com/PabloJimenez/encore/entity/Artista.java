package com.PabloJimenez.encore.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

/**
 * Entidad que representa a un artista musical.
 * <p>
 * Mapeada a la tabla {@code artista} de la base de datos.
 * Un artista puede estar asociado a varios {@link Concierto} y varios
 * {@link Festival}.
 * </p>
 */
@Entity
@Table(name = "artista")
public class Artista {

    /**
     * Identificador unico del artista, generado automaticamente por la base de
     * datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre artistico del artista. No puede ser nulo.
     */
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /**
     * URL o ruta de la imagen del artista. No puede ser nula.
     */
    @Column(name = "imagenArtista", nullable = false)
    private String imagen;

    /**
     * Numero de oyentes mensuales del artista.
     */
    @Column(name = "numeroOyentes")
    private int numeroOyentes;

    /**
     * Ciudad de nacimiento del artista.
     */
    @Column(name = "ciudadNacimiento")
    private String ciudadNacimiento;

    /**
     * Descripcion breve del artista. Longitud maxima de 500 caracteres.
     */
    @Column(name = "descripcion", length = 500)
    private String descripcion;

    /**
     * Lista de conciertos en los que participa el artista.
     * Relacion {@code @OneToMany} inversa; excluida de la serializacion JSON.
     */
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Concierto> conciertos;

    /**
     * Lista de festivales en los que participa el artista.
     * Relacion {@code @ManyToMany} inversa; excluida de la serializacion JSON.
     */
    @ManyToMany(mappedBy = "artistas", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Festival> festivales;

    /**
     * Constructor por defecto. Inicializa las listas de festivales y conciertos
     * vacias.
     */
    public Artista() {
        this.festivales = new ArrayList<>();
        this.conciertos = new ArrayList<>();
    }

    /**
     * Constructor con todos los campos del artista.
     *
     * @param nombre           nombre artistico del artista.
     * @param imagen           URL o ruta de la imagen del artista.
     * @param numeroOyentes    numero de oyentes mensuales.
     * @param ciudadNacimiento ciudad de nacimiento del artista.
     * @param descripcion      descripcion breve del artista.
     */
    public Artista(String nombre, String imagen, int numeroOyentes, String ciudadNacimiento, String descripcion) {
        this();
        this.imagen = imagen;
        this.nombre = nombre;
        this.numeroOyentes = numeroOyentes;
        this.ciudadNacimiento = ciudadNacimiento;
        this.descripcion = descripcion;
    }

    /**
     * Devuelve la URL o ruta de la imagen del artista.
     *
     * @return imagen del artista.
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Establece la URL o ruta de la imagen del artista.
     *
     * @param imagen nueva imagen del artista.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Devuelve el nombre artistico del artista.
     *
     * @return nombre del artista.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre artistico del artista.
     *
     * @param nombre nuevo nombre del artista.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el numero de oyentes mensuales del artista.
     *
     * @return numero de oyentes.
     */
    public int getNumeroOyentes() {
        return numeroOyentes;
    }

    /**
     * Establece el numero de oyentes mensuales del artista.
     *
     * @param numeroOyentes nuevo numero de oyentes.
     */
    public void setNumeroOyentes(int numeroOyentes) {
        this.numeroOyentes = numeroOyentes;
    }

    /**
     * Devuelve la ciudad de nacimiento del artista.
     *
     * @return ciudad de nacimiento.
     */
    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    /**
     * Establece la ciudad de nacimiento del artista.
     *
     * @param ciudadNacimiento nueva ciudad de nacimiento.
     */
    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    /**
     * Devuelve la descripcion del artista.
     *
     * @return descripcion del artista.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripcion del artista.
     *
     * @param descripcion nueva descripcion.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Devuelve la lista de conciertos asociados al artista.
     *
     * @return lista de {@link Concierto}.
     */
    public List<Concierto> getConciertos() {
        return conciertos;
    }

    /**
     * Establece la lista de conciertos asociados al artista.
     *
     * @param conciertos nueva lista de {@link Concierto}.
     */
    public void setConciertos(List<Concierto> conciertos) {
        this.conciertos = conciertos;
    }

    /**
     * Devuelve la lista de festivales en los que participa el artista.
     *
     * @return lista de {@link Festival}.
     */
    public List<Festival> getFestivales() {
        return festivales;
    }

    /**
     * Establece la lista de festivales en los que participa el artista.
     *
     * @param festivales nueva lista de {@link Festival}.
     */
    public void setFestivales(List<Festival> festivales) {
        this.festivales = festivales;
    }

    /**
     * Devuelve el identificador unico del artista.
     *
     * @return id del artista.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador unico del artista.
     *
     * @param id nuevo id del artista.
     */
    public void setId(Long id) {
        this.id = id;
    }
}