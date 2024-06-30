package com.ejemplo.carmenuy.controlador;

import com.ejemplo.carmenuy.model.dto.UsuarioDTO;
import com.ejemplo.carmenuy.grafica.paneles.PanelRegistro;
import com.ejemplo.carmenuy.grafica.VInicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Controlador implements ActionListener { // para escuchar a la ventana

    private static Controlador instancia; // autoreferencia (singleton)
//    private VentanaInicio ventanaInicio; // habla con la ventana

    // nuevo login
    private VInicio vInicio;
    private PanelRegistro panelRegistro;
    
    private UsuarioDTO usuario;

    private Controlador() {
        // usuario de prueba (BORRAR)
        usuario = new UsuarioDTO(1, "Pepe", "López", "abc123", "pepel@gmail.com");
    }

    // Singleton: cuando se crea el controlador, crea una ventana
    public static Controlador obtenerInstancia() {

        if (instancia == null) {
            instancia = new Controlador();
            instancia.vInicio = VInicio.obtenerInstancia();
            instancia.panelRegistro = PanelRegistro.obtenerInstancia();
        }
        return instancia;
    }

    // Este método escucha los eventos de ventana
    @Override
    public void actionPerformed(ActionEvent e) { // detecta los eventos

        // Ventana inicio -> BOTÓN INGRESAR
        if (e.getSource() == vInicio.getBtnIngresar()) { // evento botón ingresar
            String nombre = vInicio.getTxtUsuario().getText();
            String contrasena = vInicio.getTxtContrasena().getText();
            
            if(nombre.equals(usuario.getNombre()) && contrasena.equals(usuario.getContrasena())) { // si el evento proviene del botón btnIngresar
                JOptionPane.showMessageDialog(null, "Ingreso exitoso!!!");
                // llamar a los servicios
                // 1. consultar la base de datos con los datos de la ventana
                // 2: Si las credenciales son correctas: ingresa al sistema
                // 3: Si no: mostrar mensaje de error y solicitar nuevamente
            } else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta.");
            }
        }
        
        // Ventana inicio -> BOTÓN PARA REGITRAR
        if (e.getSource() == vInicio.getBtnRegistrar()) { // evento botón registrar
            vInicio.getContentPane().removeAll();
            vInicio.getContentPane().add(panelRegistro);
            vInicio.getContentPane().revalidate();
            vInicio.getContentPane().repaint();
        }

        // Ventana regitrar -> BOTÓN REGISTRAR
        if (e.getSource() == panelRegistro.getBtnRegistrar()) { // evento botón registrar
            JOptionPane.showMessageDialog(null, "Registro exitoso!!!");
        }
        
        // Ventana regitrar -> BOTÓN VOLVER
        if (e.getSource() == panelRegistro.getBtnVolver()) { // evento botón volver
            vInicio.getContentPane().removeAll();
            vInicio.getContentPane().add(vInicio.getPanelBase());
            vInicio.getContentPane().revalidate();
            vInicio.getContentPane().repaint();
        }

    }

}
