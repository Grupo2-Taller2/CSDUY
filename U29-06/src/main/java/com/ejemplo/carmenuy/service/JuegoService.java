package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.model.dao.PistaDAO;
import com.ejemplo.carmenuy.model.dao.UsuarioDAO;
import com.ejemplo.carmenuy.model.dao.LocalidadDAO;
import com.ejemplo.carmenuy.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JuegoService {
    
    private Grafo grafo;
    private Detective detective;
    private Grafo.Nodo nodoActual;
    private Random random;
    private boolean ttsEnabled;
    private Connection connection;
    private UsuarioDAO usuarioDAO;
    private PistaDAO pistaDAO;
    private LocalidadDAO localidadDAO;

    public JuegoService() {
        
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            usuarioDAO = new UsuarioDAO(connection);
            pistaDAO = new PistaDAO(connection);
            localidadDAO = new LocalidadDAO(connection);

            usuarioDAO.crearTabla();
            pistaDAO.crearTabla();
            localidadDAO.crearTabla();

            grafo = new Grafo();
            detective = new Detective("Juan", "Perez", Rango.DETECTIVE_JUNIOR);
            nodoActual = grafo.getNodo("Localidad1"); // Iniciar en una localidad aleatoria
            random = new Random();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void iniciarJuego() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Necesitas ayuda visual para jugar? Presiona la tecla H, si respondes No, presiona la tecla K");
        String respuesta = scanner.nextLine().toUpperCase();
        ttsEnabled = respuesta.equals("H");

        if (ttsEnabled) {
            System.out.println("TTS activado.");
            // Aquí se activaría el TTS para leer los textos
        } else {
            System.out.println("TTS desactivado.");
        }

        System.out.println("Bienvenido, " + detective.getNombre() + " " + detective.getApellido());
        mostrarPistas();
    }

    public void mostrarPistas() {
        
        Pista pista = generarPistaAleatoria();
        System.out.println("Mensaje ACME, prioridad Alfa 6: " + pista.getTexto());

        List<String> opciones = pista.generarOpciones();
        System.out.println("Opciones:");
        System.out.println("H: " + opciones.get(0));
        System.out.println("J: " + opciones.get(1));
        System.out.println("K: " + opciones.get(2));

        Scanner scanner = new Scanner(System.in);
        String respuesta = scanner.nextLine().toUpperCase();

        switch (respuesta) {
            case "H":
                evaluarRespuesta(opciones.get(0), pista.getCorrecta());
                break;
            case "J":
                evaluarRespuesta(opciones.get(1), pista.getCorrecta());
                break;
            case "K":
                evaluarRespuesta(opciones.get(2), pista.getCorrecta());
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    private Pista generarPistaAleatoria() {
        
        int tipoPista = random.nextInt(5);
        
        switch (tipoPista) {
            case 0:
                return new PistaGeografia("PISTA1", "Localidad Correcta");
            case 1:
                return new PistaHistoria("PISTA2", "Época Correcta");
            case 2:
                return new PistaLeyenda("PISTA3", "Personaje Correcto");
            case 3:
                return new PistaGastronomia("PISTA4", "Plato Correcto");
            case 4:
                return new PistaTurismo("PISTA5", "Lugar Correcto");
            default:
                return new PistaGeografia("PISTA1", "Localidad Correcta");
        }
    }

    private void evaluarRespuesta(String seleccionada, String correcta) {
        
        if (seleccionada.equals(correcta)) {
            System.out.println("¡Correcto! Moviéndote a la siguiente localidad...");
            moverDetective();
        } else {
            System.out.println("Incorrecto. El secuaz se aleja.");
            // Lógica para alejar al detective del secuaz
        }
        mostrarPistas();
    }

    private void moverDetective() {
        
        // Lógica para mover el detective a la siguiente localidad
        // Por ejemplo, simplemente cambiar a un nodo conectado
        List<Grafo.Nodo> conexiones = nodoActual.getConexiones();
        
        if (!conexiones.isEmpty()) {
            nodoActual = conexiones.get(0); // Simplemente tomar la primera conexión
            System.out.println("Te has movido a " + nodoActual.getNombre());
        }
    }

    // POR QUÉ EL SERVICIO TIENE UN MAIN?????? (MALLLL)
    public static void main(String[] args) {
        
        JuegoService juegoService = new JuegoService();
        juegoService.iniciarJuego();
        
    }
}
