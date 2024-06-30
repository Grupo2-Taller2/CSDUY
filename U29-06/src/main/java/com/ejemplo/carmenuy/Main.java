package com.ejemplo.carmenuy;

import com.ejemplo.carmenuy.controlador.Controlador;
import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        
        // Configurar la apariencia de la interfaz gráfica
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        Controlador controlador = Controlador.obtenerInstancia();
          
        
        
        // JPA: Java Persistence API (Hibertante)
        // Proporciona las operaciones básicas sobre una tabla
        // CRUD: Create, Read, Update, Delete
        
        // Derby DB
        // H2
        
        
//        // Iniciar la ventana de login
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                VentanaLogin ventanaLogin = new VentanaLogin();
//                ventanaLogin.setVisible(true);
//            }
//        });

        // Inicializar el servicio del juego
//        JuegoService juegoService = new JuegoService();
//        juegoService.iniciarJuego();
    }
}
