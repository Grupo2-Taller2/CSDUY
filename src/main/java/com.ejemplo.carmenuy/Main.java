//CarmenSandiegoUruguay/src/main/java/com/ejemplo/carmenuy/Main.java
package com.ejemplo.carmenuy;

import com.ejemplo.carmenuy.service.JuegoService;
import com.ejemplo.carmenuy.service.AccesibilidadManager;
import com.ejemplo.carmenuy.ui.InputManager;
import com.ejemplo.carmenuy.tts.TTSManager;
import com.ejemplo.carmenuy.service.DatabaseInitializationService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            // Inicializar la base de datos
            DatabaseInitializationService dbInit = new DatabaseInitializationService();
            dbInit.initializeDatabase();

            // Establecer conexión con la base de datos
            Connection connection = DriverManager.getConnection("jdbc:sqlite:carmenuy.db");
            
            // Inicializar servicios
            TTSManager ttsManager = new TTSManager();
            AccesibilidadManager accesibilidadManager = new AccesibilidadManager(ttsManager);
            JuegoService juegoService = new JuegoService(connection, accesibilidadManager);

            // Iniciar el juego
            iniciarJuego(juegoService, accesibilidadManager);

        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Error crítico al inicializar el juego: " + e.getMessage());
        }
    }

    private static void iniciarJuego(JuegoService juegoService, AccesibilidadManager accesibilidadManager) {
        accesibilidadManager.preguntarPreferenciaAudio();

        String nombreUsuario = InputManager.obtenerNombreUsuario();
        String contrasena = InputManager.obtenerContrasena();

        if (juegoService.registrarOIniciarSesion(nombreUsuario, contrasena)) {
            juegoService.iniciarNuevaPartida(nombreUsuario);

            while (!juegoService.juegoTerminado()) {
                juegoService.mostrarEstadoActual();
                char opcion = InputManager.obtenerEntradaUsuario();
                juegoService.procesarEntrada(opcion);
            }

            juegoService.mostrarResultadoFinal();
        } else {
            System.out.println("No se pudo iniciar sesión. El juego se cerrará.");
        }
    }
}
