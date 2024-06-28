//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/model/Secuaz.java
package com.ejemplo.carmenuy.model;

import java.util.Objects;

/**
 * Representa un secuaz en el juego Carmen Sandiego Uruguay.
 * Contiene información sobre el nombre, habilidad y nivel de peligrosidad del secuaz.
 */
public class Secuaz {
    private String nombre;
    private String habilidad;
    private int peligrosidad;

    /**
     * Constructor para crear una instancia de Secuaz.
     *
     * @param nombre El nombre del secuaz.
     * @param habilidad La habilidad especial del secuaz.
     * @param peligrosidad El nivel de peligrosidad del secuaz (0-100).
     * @throws IllegalArgumentException si algún parámetro es inválido.
     */
    public Secuaz(String nombre, String habilidad, int peligrosidad) {
        setNombre(nombre);
        setHabilidad(habilidad);
        setPeligrosidad(peligrosidad);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        this.nombre = nombre.trim();
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        if (habilidad == null || habilidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La habilidad no puede ser nula o vacía");
        }
        this.habilidad = habilidad.trim();
    }

    public int getPeligrosidad() {
        return peligrosidad;
    }

    public void setPeligrosidad(int peligrosidad) {
        if (peligrosidad < 0 || peligrosidad > 100) {
            throw new IllegalArgumentException("La peligrosidad debe estar entre 0 y 100");
        }
        this.peligrosidad = peligrosidad;
    }

    @Override
    public String toString() {
        return String.format("Secuaz{nombre='%s', habilidad='%s', peligrosidad=%d}", 
                             nombre, habilidad, peligrosidad);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Secuaz secuaz = (Secuaz) o;
        return peligrosidad == secuaz.peligrosidad &&
               Objects.equals(nombre, secuaz.nombre) &&
               Objects.equals(habilidad, secuaz.habilidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, habilidad, peligrosidad);
    }
}
