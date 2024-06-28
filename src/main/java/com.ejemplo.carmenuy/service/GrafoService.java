//CarmenSandiegoUruguay/src/main/java/com/ejemplo/carmenuy/service/GrafoService.java
package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.model.Grafo;
import com.ejemplo.carmenuy.model.Localidad;
import com.ejemplo.carmenuy.dao.LocalidadDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GrafoService {
    private static final Logger logger = LoggerFactory.getLogger(GrafoService.java);
    private static final int MIN_CONEXIONES = 2;
    private static final int MAX_CONEXIONES = 4;
    private static final int MAX_DISTANCIA = 10;

    private final Grafo grafo;
    private final LocalidadDAO localidadDAO;
    private final Random random;

    public GrafoService(LocalidadDAO localidadDAO) {
        this.localidadDAO = localidadDAO;
        this.grafo = new Grafo();
        this.random = new Random();
        inicializarGrafo();
    }

    private void inicializarGrafo() {
        try {
            List<Localidad> localidades = localidadDAO.obtenerTodasLasLocalidades();
            localidades.forEach(localidad -> grafo.agregarNodo(localidad.getNombre()));
            conectarNodos();
            logger.info("Grafo inicializado con éxito. Nodos: {}", grafo.obtenerNodos().size());
        } catch (SQLException e) {
            logger.error("Error al inicializar el grafo", e);
            throw new RuntimeException("Error al inicializar el grafo", e);
        }
    }

    private void conectarNodos() {
        List<String> nodos = grafo.obtenerNodos();
        nodos.forEach(nodo -> {
            int conexiones = random.nextInt(MAX_CONEXIONES - MIN_CONEXIONES + 1) + MIN_CONEXIONES;
            for (int i = 0; i < conexiones; i++) {
                String nodoDestino = obtenerNodoDestinoAleatorio(nodo, nodos);
                if (nodoDestino != null && !grafo.estanConectados(nodo, nodoDestino)) {
                    int distancia = random.nextInt(MAX_DISTANCIA) + 1;
                    grafo.conectarNodos(nodo, nodoDestino, distancia);
                    logger.debug("Conectado {} con {} (distancia: {})", nodo, nodoDestino, distancia);
                }
            }
        });
    }

    private String obtenerNodoDestinoAleatorio(String nodoOrigen, List<String> nodos) {
        List<String> nodosDisponibles = nodos.stream()
                .filter(nodo -> !nodo.equals(nodoOrigen) && !grafo.estanConectados(nodoOrigen, nodo))
                .collect(Collectors.toList());
        
        return nodosDisponibles.isEmpty() ? null : nodosDisponibles.get(random.nextInt(nodosDisponibles.size()));
    }

    public List<String> obtenerConexiones(String localidad) {
        return grafo.obtenerConexiones(localidad);
    }

    public String obtenerLocalidadAleatoria() {
        List<String> nodos = grafo.obtenerNodos();
        return nodos.get(random.nextInt(nodos.size()));
    }

    public int obtenerDistancia(String localidad1, String localidad2) {
        return grafo.obtenerDistancia(localidad1, localidad2);
    }

    public List<String> obtenerRutaAleatoria(String inicio, int longitud) {
        return grafo.obtenerRutaAleatoria(inicio, longitud);
    }

    public void generarConexiones() {
        conectarNodos();
    }

    public void distribuirPistas() {
        // Implementar la lógica para distribuir pistas en localidades
        logger.info("Distribución de pistas no implementada aún");
    }
}
