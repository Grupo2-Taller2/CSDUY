package com.ejemplo.carmenuy.model;

import java.util.*;

public class Grafo {

    private Map<String, Nodo> nodos;

    public Grafo() {
        nodos = new HashMap<>();
        inicializarGrafo();
    }

    private void inicializarGrafo() {
        // Crear nodos
        for (int i = 1; i <= 40; i++) {
            String nombreLocalidad = "Localidad" + i;
            nodos.put(nombreLocalidad, new Nodo(nombreLocalidad));
        }

        // Crear conexiones (5 rutas de escape por nodo)
        Random random = new Random();
        for (Nodo nodo : nodos.values()) {
            while (nodo.getConexiones().size() < 5) {
                String nombreDestino = "Localidad" + (random.nextInt(40) + 1);
                if (!nombreDestino.equals(nodo.getNombre()) && !nodo.estaConectado(nombreDestino)) {
                    nodo.agregarConexion(nodos.get(nombreDestino));
                }
            }
        }
    }

    public Nodo getNodo(String nombre) {
        return nodos.get(nombre);
    }

    public Collection<Nodo> getNodos() {
        return nodos.values();
    }

    public static class Nodo {

        private String nombre;
        private List<Nodo> conexiones;

        public Nodo(String nombre) {
            this.nombre = nombre;
            this.conexiones = new ArrayList<>();
        }

        public String getNombre() {
            return nombre;
        }

        public List<Nodo> getConexiones() {
            return conexiones;
        }

        public void agregarConexion(Nodo nodo) {
            conexiones.add(nodo);
        }

        public boolean estaConectado(String nombre) {
            for (Nodo conexion : conexiones) {
                if (conexion.getNombre().equals(nombre)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(nombre).append(" conectado a: ");
            for (Nodo conexion : conexiones) {
                sb.append(conexion.getNombre()).append(" ");
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo();
        for (Nodo nodo : grafo.getNodos()) {
            System.out.println(nodo);
        }
    }
}
