package com.ejemplo.carmenuy.grafica.old;

//package com.ejemplo.carmenuy.ui;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class VentanaAyudaVisual extends JFrame {
//    
//    private JTextArea textArea;
//    private JButton botonCerrar;
//
//    public VentanaAyudaVisual(String mensaje) {
//        
//        setTitle("Ayuda Visual");
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
//        botonCerrar.addActionListener(e -> dispose());
//
//        JPanel panelBoton = new JPanel();
//        panelBoton.add(botonCerrar);
//
//        getContentPane().add(scrollPane, BorderLayout.CENTER);
//        getContentPane().add(panelBoton, BorderLayout.SOUTH);
//    }
//
//    public static void mostrarAyudaVisual(String mensaje) {
//        
//        SwingUtilities.invokeLater(() -> {
//            VentanaAyudaVisual ventana = new VentanaAyudaVisual(mensaje);
//            ventana.setVisible(true);
//        });
//    }
//
//    public static void main(String[] args) {
//        mostrarAyudaVisual("Este es un mensaje de ayuda visual.");
//    }
//}
