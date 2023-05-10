package com.porfolio.backend.Dto;


import javax.validation.constraints.NotBlank;

public class DtoCertificaciones {

    @NotBlank
    private String titulo;
    @NotBlank
    private String institucion;
    @NotBlank
    private int anio;

    public DtoCertificaciones() {
    }

    public DtoCertificaciones(String titulo, String institucion, int anio) {
        this.titulo = titulo;
        this.institucion = institucion;
        this.anio = anio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
    
}
