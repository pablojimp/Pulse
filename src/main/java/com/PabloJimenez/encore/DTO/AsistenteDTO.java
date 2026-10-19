package com.PabloJimenez.encore.DTO;

public class AsistenteDTO {
    private String nombre;
    private String documento;
    private String email;
    private int edad;
    private Long eventoId;

    public String getNombre() {
        return nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public int getEdad() {
        return edad;
    }

    public Long getEventoId() {
        return eventoId;
    }
}