package com.ejemplo.carmenuy.model;

public class Detective {
    
    private String nombre;
    private String apellido;
    private Rango rango;
    private int movimientos;

    public Detective(String nombre, String apellido, Rango rango) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.rango = rango;
        this.movimientos = calcularMovimientosIniciales(rango);
    }

    private int calcularMovimientosIniciales(Rango rango) {
        
        switch (rango) {
            case DETECTIVE_JUNIOR:
                return 5;
            case DETECTIVE_APRENDIZ:
                return 4;
            case DETECTIVE_EFICIENTE:
                return 3;
            case DETECTIVE_JEFE:
                return 2;
            case INSPECTOR:
                return 1;
            default:
                return 5;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Rango getRango() {
        return rango;
    }

    public void setRango(Rango rango) {
        this.rango = rango;
        this.movimientos = calcularMovimientosIniciales(rango);
    }

    public int getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }

    public void incrementarMovimientos(int incremento) {
        this.movimientos += incremento;
    }

    public void decrementarMovimientos(int decremento) {
        this.movimientos -= decremento;
    }

    @Override
    public String toString() {
        
        return "Detective{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", rango=" + rango +
                ", movimientos=" + movimientos +
                '}';
    }
}
