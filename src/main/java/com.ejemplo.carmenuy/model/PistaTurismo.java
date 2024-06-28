//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/model/PistaTurismo.java
package com.ejemplo.carmenuy.model;

/**
 * Representa una pista turística en el juego Carmen Sandiego Uruguay.
 * Extiende la clase Pista con características específicas de turismo.
 */
public class PistaTurismo extends Pista {

    /**
     * Constructor para crear una instancia de PistaTurismo.
     *
     * @param id El identificador único de la pista.
     * @param texto El texto de la pista turística.
     * @param esCorrecta Indica si la pista es correcta o no.
     * @throws IllegalArgumentException si el texto es nulo o vacío.
     */
    public PistaTurismo(int id, String texto, boolean esCorrecta) {
        super(id, texto, esCorrecta);
    }

    /**
     * Proporciona información adicional específica del turismo.
     * Este método puede ser sobrescrito para añadir detalles turísticos.
     *
     * @return Una cadena con información turística adicional.
     */
    public String getInformacionTuristica() {
        return "Esta es una pista relacionada con atracciones turísticas de Uruguay.";
    }

    @Override
    public String toString() {
        return String.format("PistaTurismo{id=%d, texto='%s', esCorrecta=%b, infoTuristica='%s'}",
                             getId(), getTexto(), esCorrecta(), getInformacionTuristica());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PistaTurismo)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
