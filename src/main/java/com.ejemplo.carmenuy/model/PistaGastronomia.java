//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/model/PistaGastronomia.java
package com.ejemplo.carmenuy.model;

/**
 * Representa una pista gastronómica en el juego Carmen Sandiego Uruguay.
 * Extiende la clase Pista con características específicas de gastronomía.
 */
public class PistaGastronomia extends Pista {

    /**
     * Constructor para crear una instancia de PistaGastronomia.
     *
     * @param id El identificador único de la pista.
     * @param texto El texto de la pista gastronómica.
     * @param esCorrecta Indica si la pista es correcta o no.
     * @throws IllegalArgumentException si el texto es nulo o vacío.
     */
    public PistaGastronomia(int id, String texto, boolean esCorrecta) {
        super(id, texto, esCorrecta);
    }

    /**
     * Proporciona información adicional específica de la gastronomía.
     * Este método puede ser sobrescrito para añadir detalles gastronómicos.
     *
     * @return Una cadena con información gastronómica adicional.
     */
    public String getInformacionGastronomica() {
        return "Esta es una pista relacionada con la gastronomía local.";
    }

    @Override
    public String toString() {
        return String.format("PistaGastronomia{id=%d, texto='%s', esCorrecta=%b, infoGastronomica='%s'}",
                             getId(), getTexto(), esCorrecta(), getInformacionGastronomica());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PistaGastronomia)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
