package com.ejemplo.carmenuy.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PistaLeyenda extends Pista {
    
    public PistaLeyenda(String texto, String correcta) {
        super(texto, correcta);
    }

    @Override
    public void mostrarPista() {
        super.mostrarPista();
        System.out.println("Personaje: " + correcta);
    }

    @Override
    public List<String> generarOpciones() {
        
        List<String> opciones = new ArrayList<>();
        opciones.add(correcta);
        
        opciones.add("Personaje Incorrecto 1");
        opciones.add("Personaje Incorrecto 2");
        opciones.add("Personaje Incorrecto 3");
        opciones.add("Personaje Incorrecto 4");
        Collections.shuffle(opciones);
        
        return opciones.subList(0, 3); // Devolver tres opciones aleatorias
    }
}
