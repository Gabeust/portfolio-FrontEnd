package com.porfolio.backend.Dto;

import javax.validation.constraints.NotBlank;


public class DtoCompetencias {
    @NotBlank
    private String nombre;
    @NotBlank
    private int porcentaje;
    
    private String url;

    public DtoCompetencias() {
    }

    public DtoCompetencias(String nombre, int porcentaje, String url) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
}