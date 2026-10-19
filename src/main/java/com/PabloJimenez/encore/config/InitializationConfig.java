package com.PabloJimenez.encore.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.PabloJimenez.encore.util.ArtistaInitializer;

@Configuration
@ConfigurationProperties(prefix = "stem.inicializacion")
public class InitializationConfig {

    private int numeroDeConciertos = 70;
    private int numeroDeArtistas = ArtistaInitializer.getNumeroArtistas();
    private int nuemeroDeLugares = 10;
    private int numeroMaximoOyentes = 99000000;
    private int numeroDeFestivales = 30;
    private int numeroDeAsistentes = 1000;

    public int getNumeroDeArtistas() {
        return numeroDeArtistas;
    }

    public int getNumeroDeAsistentes() {
        return numeroDeAsistentes;
    }

    public void setNumeroDeAsistentes(int numeroDeAsistentes) {
        this.numeroDeAsistentes = numeroDeAsistentes;
    }

    public void setNumeroDeArtistas(int numeroDeArtistas) {
        this.numeroDeArtistas = numeroDeArtistas;
    }

    public int getNuemeroDeLugares() {
        return nuemeroDeLugares;
    }

    public void setNuemeroDeLugares(int nuemeroDeLugares) {
        this.nuemeroDeLugares = nuemeroDeLugares;
    }

    public int getNumeroDeConciertos() {
        return numeroDeConciertos;
    }

    public void setNumeroDeConciertos(int numeroDeConciertos) {
        this.numeroDeConciertos = numeroDeConciertos;
    }

    public int getNumeroMaximoOyentes() {
        return numeroMaximoOyentes;
    }

    public void setNumeroMaximoOyentes(int numeroMaximoOyentes) {
        this.numeroMaximoOyentes = numeroMaximoOyentes;
    }

    public int getNumeroDeFestivales() {
        return numeroDeFestivales;
    }

    public void setNumeroDeFestivales(int numeroDeFestivales) {
        this.numeroDeFestivales = numeroDeFestivales;
    }

}
