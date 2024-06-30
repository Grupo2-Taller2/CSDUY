package com.ejemplo.carmenuy.grafica.old;

//package com.ejemplo.carmenuy.ui;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class VentanaFinal extends JFrame {
//    
//    private JTextArea textArea;
//    private JButton botonCerrar;
//
//    public VentanaFinal(String mensaje) {
//        
//        setTitle("Fin del Juego");
//        setSize(400, 300);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        textArea = new JTextArea(mensaje);
//        textArea.setWrapStyleWord(true);
//        textArea.setLineWrap(true);
//        textArea.setEditable(false);
//        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
//
//        JScrollPane scrollPane = new JScrollPane(textArea);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//
//        botonCerrar = new JButton("Cerrar");
//        botonCerrar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dispose();
//            }
//        });
//
//        JPanel panelBoton = new JPanel();
//        panelBoton.add(botonCerrar);
//
//        getContentPane().add(scrollPane, BorderLayout.CENTER);
//        getContentPane().add(panelBoton, BorderLayout.SOUTH);
//    }
//
//    public static void mostrarVentanaFinal(String mensaje) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                VentanaFinal ventana = new VentanaFinal(mensaje);
//                ventana.setVisible(true);
//            }
//        });
//    }
//
//    public static void main(String[] args) {
//        mostrarVentanaFinal("¡Felicidades! Has completado el juego.");
//    }
//}
