package com.ejemplo.carmenuy.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PistaTurismo extends Pista {
    
    public PistaTurismo(String texto, String correcta) {
        super(texto, correcta);
    }

    @Override
    public void mostrarPista() {
        super.mostrarPista();
        System.out.println("Lugar: " + correcta);
    }

    @Override
    public List<String> generarOpciones() {
        
        List<String> opciones = new ArrayList<>();
        opciones.add(correcta);
        
        opciones.add("Lugar Incorrecto 1");
        opciones.add("Lugar Incorrecto 2");
        opciones.add("Lugar Incorrecto 3");
        opciones.add("Lugar Incorrecto 4");
        Collections.shuffle(opciones);
        
        return opciones.subList(0, 3); // Devolver tres opciones aleatorias
    }
}
