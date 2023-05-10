
package com.porfolio.backend.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String empresa;
    @NotNull
    private String descripcion;
    @NotNull
    private int ingreso;
    @NotNull
    private int egreso;
    

    public Experiencia(String empresa, String descripcion, int ingreso, int egreso) {
        this.empresa = empresa;
        this.descripcion = descripcion;
        this.ingreso = ingreso;
        this.egreso = egreso;
    }

    public Experiencia() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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