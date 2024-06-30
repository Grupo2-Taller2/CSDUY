package com.ejemplo.carmenuy.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Pista {

    // PistaDAO obtiene el atributo ID pero este no existe
    // faltan los setters (ver cuales son necesarios)
    protected String texto;
    protected String correcta;

    public Pista(String texto, String correcta) {
        this.texto = texto;
        this.correcta = correcta;
    }

    public void mostrarPista() {
        System.out.println("Pista: " + texto);
    }

    public abstract List<String> generarOpciones();

    // Subclase PistaGeografia
    public static class PistaGeografia extends Pista {

        public PistaGeografia(String texto, String correcta) {
            super(texto, correcta);
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

    // Subclase PistaHistoria
    public static class PistaHistoria extends Pista {

        public PistaHistoria(String texto, String correcta) {
            super(texto, correcta);
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

    // Subclase PistaLeyenda
    public static class PistaLeyenda extends Pista {

        public PistaLeyenda(String texto, String correcta) {
            super(texto, correcta);
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

    // Subclase PistaGastronomia
    public static class PistaGastronomia extends Pista {

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

    // Subclase PistaTurismo
    public static class PistaTurismo extends Pista {

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

    public String getTexto() {
        return texto;
    }

    public String getCorrecta() {
        return correcta;
    }
}
