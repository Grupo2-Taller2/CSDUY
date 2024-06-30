package com.ejemplo.carmenuy.grafica.old;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaJuegoC extends JFrame {
    
    private JTextArea textArea;
    private JButton botonPista;
    private JButton botonMover;
    private JButton botonCaptura;

    public VentanaJuegoC() {
        
        setTitle("Juego de Carmen Sandiego");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        botonPista = new JButton("Mostrar Pista");
        botonPista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPista();
            }
        });

        botonMover = new JButton("Mover Detective");
        botonMover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moverDetective();
            }
        });

        botonCaptura = new JButton("Capturar Secuaz");
        botonCaptura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capturarSecuaz();
            }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonPista);
        panelBotones.add(botonMover);
        panelBotones.add(botonCaptura);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);
    }

    // idem otras ventanas
    // MAL
    // nunca interactúan con la lógica
    private void mostrarPista() {
        // Lógica para mostrar una pista
        textArea.append("Aquí va una pista...\n");
    }

    private void moverDetective() {
        // Lógica para mover al detective
        textArea.append("El detective se ha movido a una nueva localidad...\n");
    }

    private void capturarSecuaz() {
        // Lógica para capturar al secuaz
        //VentanaCaptura.mostrarCaptura("¡Has capturado al secuaz!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaJuegoC ventana = new VentanaJuegoC();
                ventana.setVisible(true);
            }
        });
    }
}
