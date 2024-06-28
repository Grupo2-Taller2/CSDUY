//CarmenSandiegoUruguay/src/main/java/com/ejemplo/carmenuy/service/NarrativaManager.java
package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.model.Localidad;
import com.ejemplo.carmenuy.model.Grafo;
import com.ejemplo.carmenuy.model.Nodo;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public class NarrativaManager {
    private final Map<String, String> narrativas;
    private final Map<String, String> letrasPorLocalidad;
    private final Grafo grafo;

    public NarrativaManager(Grafo grafo) {
        this.grafo = grafo;
        narrativas = new HashMap<>();
        letrasPorLocalidad = new HashMap<>();
        inicializarNarrativas();
        inicializarLetrasPorLocalidad();
    }

    private void inicializarNarrativas() {
        narrativas.put("A", "Te encuentras en Montevideo, la vibrante capital de Uruguay. El aroma a café recién hecho y el sonido de los tambores de candombe llenan el aire. Busca pistas entre 1.1, 1.2, 1.3, 1.4 y 1.5.");
        narrativas.put("B", "Has llegado a Punta del Este, el lujoso balneario conocido como la 'Saint-Tropez de Sudamérica'. Las playas doradas y los yates lujosos dominan el paisaje. Investiga las pistas 2.1, 2.2, 2.3, 2.4 y 2.5.");
        narrativas.put("C", "Estás en Colonia del Sacramento, una ciudad histórica con encanto colonial. Las calles empedradas y los farolitos te transportan a otra época. Examina las pistas 3.1, 3.2, 3.3, 3.4 y 3.5.");
        narrativas.put("D", "Te adentras en Cabo Polonio, un paraíso natural sin electricidad. Las dunas, el faro y los leones marinos crean un escenario único. Busca entre las pistas 4.1, 4.2, 4.3, 4.4 y 4.5.");
        narrativas.put("E", "Llegas a Salto, famosa por sus termas y naranjales. El río Uruguay fluye majestuosamente a tu lado. Investiga las pistas 5.1, 5.2, 5.3, 5.4 y 5.5.");
    }

    private void inicializarLetrasPorLocalidad() {
        letrasPorLocalidad.put("Montevideo", "A");
        letrasPorLocalidad.put("Punta del Este", "B");
        letrasPorLocalidad.put("Colonia del Sacramento", "C");
        letrasPorLocalidad.put("Cabo Polonio", "D");
        letrasPorLocalidad.put("Salto", "E");
    }

    public String generarNarrativa(Localidad localidad) {
        String letra = letrasPorLocalidad.getOrDefault(localidad.getNombre(), "X");
        String narrativaBase = narrativas.getOrDefault(letra, 
            "Te encuentras en " + localidad.getNombre() + " (Localidad " + letra + "). [Narrativa por defecto]");
        
        Nodo nodoActual = grafo.getNodo(localidad.getNombre());
        List<String> conexiones = nodoActual.getConexiones().stream()
                                    .map(Nodo::getNombre)
                                    .collect(Collectors.toList());
        
        String infoConexiones = "Desde aquí puedes viajar a: " + String.join(", ", conexiones) + ".";
        
        return narrativaBase + "\n" + infoConexiones;
    }

    public String obtenerLetraLocalidad(String nombreLocalidad) {
        return letrasPorLocalidad.getOrDefault(nombreLocalidad, "X");
    }

    public String generarPista(Localidad localidad) {
        String letra = obtenerLetraLocalidad(localidad.getNombre());
        return "Pista para la localidad " + letra + ": [Inserta aquí la pista específica]";
    }
}
