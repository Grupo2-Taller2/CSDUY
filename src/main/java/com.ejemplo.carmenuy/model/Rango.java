//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/model/Rango.java
package com.ejemplo.carmenuy.model;

/**
 * Enumeración que representa los diferentes rangos de detective en el juego Carmen Sandiego Uruguay.
 */
public enum Rango {
    DETECTIVE_JUNIOR("Detective Junior"),
    DETECTIVE_APRENDIZ("Detective Aprendiz"),
    DETECTIVE_EFICIENTE("Detective Eficiente"),
    DETECTIVE_JEFE("Detective Jefe"),
    INSPECTOR("Inspector");

    private final String descripcion;

    /**
     * Constructor privado para asignar una descripción a cada rango.
     *
     * @param descripcion La descripción del rango.
     */
    Rango(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la descripción del rango.
     *
     * @return La descripción del rango.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Devuelve una representación en cadena del rango.
     *
     * @return La descripción del rango.
     */
    @Override
    public String toString() {
        return this.descripcion;
    }

    /**
     * Ejemplo de uso de la enumeración Rango.
     */
    public static void main(String[] args) {
        Rango rangoActual = Rango.DETECTIVE_APRENDIZ;
        System.out.println("Tu rango actual es: " + rangoActual.getDescripcion());
        
        // Iterar sobre todos los rangos
        System.out.println("\nTodos los rangos disponibles:");
        for (Rango rango : Rango.values()) {
            System.out.println(rango.name() + ": " + rango.getDescripcion());
        }
    }
}
