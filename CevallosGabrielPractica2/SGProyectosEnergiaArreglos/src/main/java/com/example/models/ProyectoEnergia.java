package com.example.models;

public class ProyectoEnergia {
    private Integer idProyecto;
    
    private String nombre;
    private String ubicacion;
    private double montoTotalInversion;
    private double cantidadElectricidad;
    private String fechaInicio;
    private String fechaFin;

    private Integer idInversionista;


    public ProyectoEnergia(){}
    
    public Integer getIdProyecto() {
        return this.idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getMontoTotalInversion() {
        return this.montoTotalInversion;
    }

    public void setMontoTotalInversion(double montoTotalInversion) {
        this.montoTotalInversion = montoTotalInversion;
    }

    public double getCantidadElectricidad() {
        return this.cantidadElectricidad;
    }

    public void setCantidadElectricidad(double cantidadElectricidad) {
        this.cantidadElectricidad = cantidadElectricidad;
    }

    public String getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return this.fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getIdInversionista() {
        return this.idInversionista;
    }

    public void setIdInversionista(Integer idInversionista) {
        this.idInversionista = idInversionista;
    }
}
