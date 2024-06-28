//CarmenSandiegoUruguay/src/main/java/com/ejemplo/carmenuy/model/Partida.java
package com.ejemplo.carmenuy.model;

import java.util.List;
import java.util.Random;

public class Partida {
    private Detective detective;
    private Jugador jugador;
    private List<Secuaz> secuaces;
    private Secuaz objetivoActual;
    private Nodo nodoDetective;
    private Nodo nodoObjetivo;
    private int distanciaAlObjetivo;
    private Grafo grafo;

    public Partida(Detective detective, List<Secuaz> secuaces, Grafo grafo) {
        this.detective = detective;
        this.secuaces = secuaces;
        this.grafo = grafo;
        this.nodoDetective = grafo.getNodoAleatorio();
        inicializarObjetivo();
    }

    private void inicializarObjetivo() {
        Random rand = new Random();
        this.objetivoActual = secuaces.get(rand.nextInt(secuaces.size()));
        this.nodoObjetivo = grafo.getNodoAleatorio();
        actualizarDistanciaAlObjetivo();
    }

    public void moverDetective(Nodo nuevoNodo) {
        if (grafo.sonNodosAdyacentes(nodoDetective, nuevoNodo)) {
            this.nodoDetective = nuevoNodo;
            actualizarDistanciaAlObjetivo();
        }
    }

    public void moverObjetivo() {
        List<Nodo> nodosAdyacentes = grafo.getNodosAdyacentes(nodoObjetivo);
        Random rand = new Random();
        this.nodoObjetivo = nodosAdyacentes.get(rand.nextInt(nodosAdyacentes.size()));
        actualizarDistanciaAlObjetivo();
    }

    private void actualizarDistanciaAlObjetivo() {
        this.distanciaAlObjetivo = grafo.calcularDistancia(nodoDetective, nodoObjetivo);
    }

    // Getters y setters
    public Detective getDetective() { return detective; }
    public Secuaz getObjetivoActual() { return objetivoActual; }
    public Nodo getNodoDetective() { return nodoDetective; }
    public Nodo getNodoObjetivo() { return nodoObjetivo; }
    public int getDistanciaAlObjetivo() { return distanciaAlObjetivo; }
}
