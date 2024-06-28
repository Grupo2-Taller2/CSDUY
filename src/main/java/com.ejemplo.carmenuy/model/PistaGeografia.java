//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/model/PistaGeografia.java
    package com.ejemplo.carmenuy.model;

/**
 * Representa una pista geográfica en el juego Carmen Sandiego Uruguay.
 * Extiende la clase Pista con características específicas de geografía.
 */
public class PistaGeografia extends Pista {

    /**
     * Constructor para crear una instancia de PistaGeografia.
     *
     * @param id El identificador único de la pista.
     * @param texto El texto de la pista geográfica.
     * @param esCorrecta Indica si la pista es correcta o no.
     * @throws IllegalArgumentException si el texto es nulo o vacío.
     */
    public PistaGeografia(int id, String texto, boolean esCorrecta) {
        super(id, texto, esCorrecta);
    }

    /**
     * Proporciona información adicional específica de la geografía.
     * Este método puede ser sobrescrito para añadir detalles geográficos.
     *
     * @return Una cadena con información geográfica adicional.
     */
    public String getInformacionGeografica() {
        return "Esta es una pista relacionada con la geografía de Uruguay.";
    }

    @Override
    public String toString() {
        return String.format("PistaGeografia{id=%d, texto='%s', esCorrecta=%b, infoGeografica='%s'}",
                             getId(), getTexto(), esCorrecta(), getInformacionGeografica());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PistaGeografia)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
