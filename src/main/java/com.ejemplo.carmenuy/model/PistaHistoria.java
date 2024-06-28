//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/model/PistaHistoria.java
package com.ejemplo.carmenuy.model;

/**
 * Representa una pista histórica en el juego Carmen Sandiego Uruguay.
 * Extiende la clase Pista con características específicas de historia.
 */
public class PistaHistoria extends Pista {

    /**
     * Constructor para crear una instancia de PistaHistoria.
     *
     * @param id El identificador único de la pista.
     * @param texto El texto de la pista histórica.
     * @param esCorrecta Indica si la pista es correcta o no.
     * @throws IllegalArgumentException si el texto es nulo o vacío.
     */
    public PistaHistoria(int id, String texto, boolean esCorrecta) {
        super(id, texto, esCorrecta);
    }

    /**
     * Proporciona información adicional específica de la historia.
     * Este método puede ser sobrescrito para añadir detalles históricos.
     *
     * @return Una cadena con información histórica adicional.
     */
    public String getInformacionHistorica() {
        return "Esta es una pista relacionada con la historia de Uruguay.";
    }

    @Override
    public String toString() {
        return String.format("PistaHistoria{id=%d, texto='%s', esCorrecta=%b, infoHistorica='%s'}",
                             getId(), getTexto(), esCorrecta(), getInformacionHistorica());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PistaHistoria)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
