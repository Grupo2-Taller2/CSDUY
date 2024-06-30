package com.ejemplo.carmenuy.grafica.old;

//package com.ejemplo.carmenuy.ui;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class VentanaRegistro extends JFrame {
//
//    private JTextField campoUsuario;
//    private JPasswordField campoContrasena;
//    private JButton botonRegistrar;
//    private JButton botonCancelar;
//
//    public VentanaRegistro() {
//
//        setTitle("Registro de Usuario");
//        setSize(300, 200);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        JPanel panel = new JPanel();
//        panel.setLayout(new GridLayout(3, 2));
//
//        JLabel etiquetaUsuario = new JLabel("Usuario:");
//        campoUsuario = new JTextField();
//
//        JLabel etiquetaContrasena = new JLabel("Contraseña:");
//        campoContrasena = new JPasswordField();
//
//        botonRegistrar = new JButton("Registrar");
//        botonRegistrar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                registrarUsuario();
//            }
//        });
//
//        botonCancelar = new JButton("Cancelar");
//        botonCancelar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dispose();
//            }
//        });
//
//        panel.add(etiquetaUsuario);
//        panel.add(campoUsuario);
//        panel.add(etiquetaContrasena);
//        panel.add(campoContrasena);
//        panel.add(botonRegistrar);
//        panel.add(botonCancelar);
//
//        getContentPane().add(panel, BorderLayout.CENTER);
//    }
//
//    // MAL !!!!!!!!!!!!!
//    // acá no puede existir lógica de negocio
//    // Nunca puede haber comunicación con la base de datos
//    // en la capa de presentación
//    // para eso existe el Controller
//    private void registrarUsuario() {
//
//        String usuario = campoUsuario.getText();
//        String contrasena = new String(campoContrasena.getPassword());
//
//        // Aquí iría la lógica para registrar el usuario en la base de datos
//        JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente");
//        dispose();
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                VentanaRegistro ventana = new VentanaRegistro();
//                ventana.setVisible(true);
//            }
//        });
//    }
//}
