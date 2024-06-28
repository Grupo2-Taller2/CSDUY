//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/model/Jugador.java
package com.ejemplo.carmenuy.model;
public class Jugador {
    private String nombre;
    private String contraseña;
    private Detective detective;

    public Jugador(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.detective = new Detective(nombre, "", Rango.DETECTIVE_JUNIOR);
    }

    // Getters y setters
}
