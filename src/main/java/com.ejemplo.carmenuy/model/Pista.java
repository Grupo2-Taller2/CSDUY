// CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/model/Pista.java
package com.ejemplo.carmenuy.model;

import java.util.Objects;

/**
 * Representa una pista en el juego Carmen Sandiego Uruguay.
 * Contiene información sobre el texto de la pista y si es correcta.
 */
public class Pista {
    private final int id;
    private String texto;
    private boolean esCorrecta;

    /**
     * Constructor para crear una instancia de Pista.
     *
     * @param id El identificador único de la pista.
     * @param texto El texto de la pista.
     * @param esCorrecta Indica si la pista es correcta o no.
     * @throws IllegalArgumentException si el texto es nulo o vacío.
     */
    public Pista(int id, String texto, boolean esCorrecta) {
        this.id = id;
        setTexto(texto);
        this.esCorrecta = esCorrecta;
    }

    /**
     * Obtiene el identificador de la pista.
     *
     * @return El identificador de la pista.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el texto de la pista.
     *
     * @return El texto de la pista.
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Establece el texto de la pista.
     *
     * @param texto El nuevo texto de la pista.
     * @throws IllegalArgumentException si el texto es nulo o vacío.
     */
    public void setTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("El texto de la pista no puede ser nulo o vacío");
        }
        this.texto = texto.trim();
    }

    /**
     * Verifica si la pista es correcta.
     *
     * @return true si la pista es correcta, false en caso contrario.
     */
    public boolean esCorrecta() {
        return esCorrecta;
    }

    /**
     * Establece si la pista es correcta o no.
     *
     * @param esCorrecta true si la pista es correcta, false en caso contrario.
     */
    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    @Override
    public String toString() {
        return String.format("Pista{id=%d, texto='%s', esCorrecta=%b}", id, texto, esCorrecta);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pista pista = (Pista) o;
        return id == pista.id &&
               esCorrecta == pista.esCorrecta &&
               Objects.equals(texto, pista.texto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, texto, esCorrecta);
    }
}
