//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/model/Grafo.java
package com.ejemplo.carmenuy.model;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Representa el grafo de localidades en el juego Carmen Sandiego Uruguay.
 */
public class Grafo {
    private final Map<String, Nodo> nodos;
    private static final int NUMERO_LOCALIDADES = 40;
    private static final int CONEXIONES_POR_NODO = 5;

    /**
     * Constructor que inicializa el grafo con las localidades y sus conexiones.
     */
    public Grafo() {
        this.nodos = new HashMap<>();
        inicializarGrafo();
    }

    private void inicializarGrafo() {
        crearNodos();
        crearConexiones();
    }

    private void crearNodos() {
        for (int i = 1; i <= NUMERO_LOCALIDADES; i++) {
            String nombreLocalidad = "Localidad" + i;
            nodos.put(nombreLocalidad, new Nodo(nombreLocalidad));
        }
    }

    private void crearConexiones() {
        for (Nodo nodo : nodos.values()) {
            while (nodo.getConexiones().size() < CONEXIONES_POR_NODO) {
                String nombreDestino = "Localidad" + (ThreadLocalRandom.current().nextInt(NUMERO_LOCALIDADES) + 1);
                if (!nombreDestino.equals(nodo.getNombre()) && !nodo.estaConectado(nombreDestino)) {
                    Nodo nodoDestino = nodos.get(nombreDestino);
                    nodo.agregarConexion(nodoDestino);
                    nodoDestino.agregarConexion(nodo); // Conexión bidireccional
                }
            }
        }
    }

    /**
     * Obtiene un nodo del grafo por su nombre.
     * @param nombre El nombre del nodo a obtener.
     * @return El nodo correspondiente al nombre, o null si no existe.
     */
    public Nodo getNodo(String nombre) {
        return nodos.get(nombre);
    }

    /**
     * Obtiene todos los nodos del grafo.
     * @return Una colección inmutable de todos los nodos del grafo.
     */
    public Collection<Nodo> getNodos() {
        return Collections.unmodifiableCollection(nodos.values());
    }

    /**
     * Representa un nodo (localidad) en el grafo.
     */
    public static class Nodo {
        private final String nombre;
        private final Set<Nodo> conexiones;

        public Nodo(String nombre) {
            this.nombre = nombre;
            this.conexiones = new HashSet<>();
        }

        public String getNombre() {
            return nombre;
        }

        public Set<Nodo> getConexiones() {
            return Collections.unmodifiableSet(conexiones);
        }

        public void agregarConexion(Nodo nodo) {
            conexiones.add(nodo);
        }

        public boolean estaConectado(String nombre) {
            return conexiones.stream().anyMatch(nodo -> nodo.getNombre().equals(nombre));
        }

        @Override
        public String toString() {
            return String.format("%s conectado a: %s", nombre, 
                conexiones.stream().map(Nodo::getNombre).reduce((a, b) -> a + ", " + b).orElse(""));
        }
    }

    /**
     * Método main para pruebas.
     */
    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        grafo.getNodos().forEach(System.out::println);
    }
}
