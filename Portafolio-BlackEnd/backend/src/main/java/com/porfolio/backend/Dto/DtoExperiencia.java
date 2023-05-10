package com.porfolio.backend.Dto;

import javax.validation.constraints.NotBlank;

public class DtoExperiencia {
    @NotBlank
    private String empresa;
    @NotBlank
    private String descripcion;
     @NotBlank
    private int ingreso;
    @NotBlank
    private int egreso;  
             

    public DtoExperiencia(String empresa, String descripcion, int ingreso, int egreso) {
        this.empresa = empresa;
        this.descripcion = descripcion;
        this.ingreso = ingreso;
        this.egreso = egreso;
  
    }

    public DtoExperiencia() {
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIngreso() {
        return ingreso;
    }

    public void setIngreso(int ingreso) {
        this.ingreso = ingreso;
    }

    public int getEgreso() {
        return egreso;
    }

    public void setEgreso(int egreso) {
        this.egreso = egreso;
    }
}