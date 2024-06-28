//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/model/Detective.java
package com.ejemplo.carmenuy.model;

import java.util.Objects;

/**
 * Representa a un Detective en el juego Carmen Sandiego Uruguay.
 * Esta clase contiene información sobre el detective y sus capacidades.
 */
public class Detective {
    private final String nombre;
    private final String apellido;
    private Rango rango;
    private int movimientos;

    /**
     * Constructor para crear una instancia de Detective.
     *
     * @param nombre El nombre del detective.
     * @param apellido El apellido del detective.
     * @param rango El rango inicial del detective.
     * @throws IllegalArgumentException si algún parámetro es nulo o vacío.
     */
    public Detective(String nombre, String apellido, Rango rango) {
        this.nombre = validarString(nombre, "El nombre no puede ser nulo o vacío");
        this.apellido = validarString(apellido, "El apellido no puede ser nulo o vacío");
        setRango(rango);
    }

    private String validarString(String valor, String mensajeError) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensajeError);
        }
        return valor.trim();
    }

    /**
     * Calcula los movimientos iniciales basados en el rango del detective.
     *
     * @param rango El rango del detective.
     * @return El número de movimientos iniciales.
     */
    private static int calcularMovimientosIniciales(Rango rango) {
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
                throw new IllegalArgumentException("Rango no reconocido");
        }
    }

    // Getters

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Rango getRango() {
        return rango;
    }

    public int getMovimientos() {
        return movimientos;
    }

    /**
     * Establece el rango del detective y recalcula sus movimientos.
     *
     * @param rango El nuevo rango del detective.
     * @throws IllegalArgumentException si el rango es nulo.
     */
    public void setRango(Rango rango) {
        this.rango = Objects.requireNonNull(rango, "El rango no puede ser nulo");
        this.movimientos = calcularMovimientosIniciales(rango);
    }

    /**
     * Incrementa el número de movimientos del detective.
     *
     * @param incremento El número de movimientos a incrementar.
     * @throws IllegalArgumentException si el incremento es negativo.
     */
    public void incrementarMovimientos(int incremento) {
        if (incremento < 0) {
            throw new IllegalArgumentException("El incremento no puede ser negativo");
        }
        this.movimientos += incremento;
    }

    /**
     * Decrementa el número de movimientos del detective.
     *
     * @param decremento El número de movimientos a decrementar.
     * @throws IllegalArgumentException si el decremento es negativo o mayor que los movimientos actuales.
     */
    public void decrementarMovimientos(int decremento) {
        if (decremento < 0) {
            throw new IllegalArgumentException("El decremento no puede ser negativo");
        }
        if (decremento > this.movimientos) {
            throw new IllegalArgumentException("No se pueden decrementar más movimientos de los disponibles");
        }
        this.movimientos -= decremento;
    }

    @Override
    public String toString() {
        return String.format("Detective{nombre='%s', apellido='%s', rango=%s, movimientos=%d}",
                             nombre, apellido, rango, movimientos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Detective detective = (Detective) o;
        return Objects.equals(nombre, detective.nombre) &&
               Objects.equals(apellido, detective.apellido) &&
               rango == detective.rango;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, rango);
    }
}
