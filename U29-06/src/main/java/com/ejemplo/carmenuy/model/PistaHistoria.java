package com.ejemplo.carmenuy.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PistaHistoria extends Pista {
    
    public PistaHistoria(String texto, String correcta) {
        super(texto, correcta);
    }

    @Override
    public void mostrarPista() {
        super.mostrarPista();
        System.out.println("Época: " + correcta);
    }

    @Override
    public List<String> generarOpciones() {
        
        List<String> opciones = new ArrayList<>();
        opciones.add(correcta);
        
        opciones.add("Época Incorrecta 1");
        opciones.add("Época Incorrecta 2");
        opciones.add("Época Incorrecta 3");
        opciones.add("Época Incorrecta 4");
        Collections.shuffle(opciones);
        
        return opciones.subList(0, 3); // Devolver tres opciones aleatorias
    }
}
