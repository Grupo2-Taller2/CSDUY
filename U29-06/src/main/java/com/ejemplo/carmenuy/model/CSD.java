package com.ejemplo.carmenuy.model;

public class CSD {
    
    private String nombre;
    private int pasosPorDelante;
    private int capacidadMovimiento;

    public CSD(String nombre, int pasosPorDelante, int capacidadMovimiento) {
        this.nombre = nombre;
        this.pasosPorDelante = pasosPorDelante;
        this.capacidadMovimiento = capacidadMovimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPasosPorDelante() {
        return pasosPorDelante;
    }

    public void setPasosPorDelante(int pasosPorDelante) {
        this.pasosPorDelante = pasosPorDelante;
    }

    public int getCapacidadMovimiento() {
        return capacidadMovimiento;
    }

    public void setCapacidadMovimiento(int capacidadMovimiento) {
        this.capacidadMovimiento = capacidadMovimiento;
    }

    public void reducirCapacidadMovimiento() {
        this.capacidadMovimiento--;
    }

    @Override
    public String toString() {
        
        return "CSD{" +
                "nombre='" + nombre + '\'' +
                ", pasosPorDelante=" + pasosPorDelante +
                ", capacidadMovimiento=" + capacidadMovimiento +
                '}';
    }
}
