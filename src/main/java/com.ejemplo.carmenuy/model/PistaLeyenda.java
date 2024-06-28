//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/model/PistaLeyenda.java
package com.ejemplo.carmenuy.model;

/**
 * Representa una pista sobre leyendas en el juego Carmen Sandiego Uruguay.
 * Extiende la clase Pista con características específicas de leyendas.
 */
public class PistaLeyenda extends Pista {

    /**
     * Constructor para crear una instancia de PistaLeyenda.
     *
     * @param id El identificador único de la pista.
     * @param texto El texto de la pista sobre leyendas.
     * @param esCorrecta Indica si la pista es correcta o no.
     * @throws IllegalArgumentException si el texto es nulo o vacío.
     */
    public PistaLeyenda(int id, String texto, boolean esCorrecta) {
        super(id, texto, esCorrecta);
    }

    /**
     * Proporciona información adicional específica de la leyenda.
     * Este método puede ser sobrescrito para añadir detalles sobre la leyenda.
     *
     * @return Una cadena con información adicional sobre la leyenda.
     */
    public String getInformacionLeyenda() {
        return "Esta es una pista relacionada con una leyenda de Uruguay.";
    }

    @Override
    public String toString() {
        return String.format("PistaLeyenda{id=%d, texto='%s', esCorrecta=%b, infoLeyenda='%s'}",
                             getId(), getTexto(), esCorrecta(), getInformacionLeyenda());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PistaLeyenda)) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
