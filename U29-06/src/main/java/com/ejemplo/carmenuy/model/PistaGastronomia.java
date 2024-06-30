package com.ejemplo.carmenuy.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PistaGastronomia extends Pista {
    
    public PistaGastronomia(String texto, String correcta) {
        super(texto, correcta);
    }

    @Override
    public void mostrarPista() {
        super.mostrarPista();
        System.out.println("Plato: " + correcta);
    }

    @Override
    public List<String> generarOpciones() {
        
        List<String> opciones = new ArrayList<>();
        opciones.add(correcta);
        
        opciones.add("Plato Incorrecto 1");
        opciones.add("Plato Incorrecto 2");
        opciones.add("Plato Incorrecto 3");
        opciones.add("Plato Incorrecto 4");
        Collections.shuffle(opciones);
        
        return opciones.subList(0, 3); // Devolver tres opciones aleatorias
    }
}
