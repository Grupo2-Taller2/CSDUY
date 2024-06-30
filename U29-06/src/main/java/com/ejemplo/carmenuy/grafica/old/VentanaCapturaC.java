package com.ejemplo.carmenuy.grafica.old;

//package com.ejemplo.carmenuy.ui;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class VentanaCaptura extends JFrame {
//
//    private JTextArea textArea;
//    private JButton botonCerrar;
//
//    public VentanaCaptura(String mensaje) {
//
//        setTitle("Captura del Secuaz");
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
//    public static void mostrarCaptura(String mensaje) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                VentanaCaptura ventana = new VentanaCaptura(mensaje);
//                ventana.setVisible(true);
//            }
//        });
//    }
//
//    public static void main(String[] args) {
//        mostrarCaptura("¡Has capturado al secuaz!");
//    }
//}
