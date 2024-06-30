package com.ejemplo.carmenuy.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PistaGeografia extends Pista {
    
    public PistaGeografia(String texto, String correcta) {
        super(texto, correcta);
    }

    @Override
    public void mostrarPista() {
        super.mostrarPista();
        System.out.println("Ubicación: " + correcta);
    }

    @Override
    public List<String> generarOpciones() {
        
        List<String> opciones = new ArrayList<>();
        opciones.add(correcta);
        
        opciones.add("Localidad Incorrecta 1");
        opciones.add("Localidad Incorrecta 2");
        opciones.add("Localidad Incorrecta 3");
        opciones.add("Localidad Incorrecta 4");
        Collections.shuffle(opciones);
        
        return opciones.subList(0, 3); // Devolver tres opciones aleatorias
    }
}
