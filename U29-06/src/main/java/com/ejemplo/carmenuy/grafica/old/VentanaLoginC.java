package com.ejemplo.carmenuy.grafica.old;

//package com.ejemplo.carmenuy.ui;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class VentanaLogin extends JFrame {
//
//    private JTextField campoUsuario;
//    private JPasswordField campoContrasena;
//    private JButton botonLogin;
//    private JButton botonRegistro;
//
//    public VentanaLogin() {
//
//        setTitle("Login");
//        setSize(300, 150);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
//        botonLogin = new JButton("Login");
//        botonLogin.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                realizarLogin();
//            }
//        });
//
//        botonRegistro = new JButton("Registro");
//        botonRegistro.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                realizarRegistro();
//            }
//        });
//
//        panel.add(etiquetaUsuario);
//        panel.add(campoUsuario);
//        panel.add(etiquetaContrasena);
//        panel.add(campoContrasena);
//        panel.add(botonLogin);
//        panel.add(botonRegistro);
//
//        getContentPane().add(panel, BorderLayout.CENTER);
//    }
//
//    private void realizarLogin() {
//
//        String usuario = campoUsuario.getText();
//        String contrasena = new String(campoContrasena.getPassword());
//
//        // Aquí iría la lógica para validar el usuario y la contraseña
//        // MAL !!!!!!
//        // idem ventanaRegistro (NO LÓGICA ACÁ!!!)
//        if (usuario.equals("admin") && contrasena.equals("admin")) {
//            JOptionPane.showMessageDialog(this, "Login exitoso");
//            // Aquí se podría abrir la ventana principal del juego
//        } else {
//            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
//        }
//    }
//
//    private void realizarRegistro() {
//
//        // Aquí iría la lógica para registrar un nuevo usuario
//        JOptionPane.showMessageDialog(this, "Funcionalidad de registro no implementada");
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                VentanaLogin ventana = new VentanaLogin();
//                ventana.setVisible(true);
//            }
//        });
//    }
//}
