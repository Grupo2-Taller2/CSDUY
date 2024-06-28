//CarmenSandiegoUruguay/src/main/java/com/ejemplo/carmenuy/ui/InputManager.java
package com.ejemplo.carmenuy.ui;

import java.util.Scanner;

public class InputManager {
    private static final Scanner scanner = new Scanner(System.in);

    public static char obtenerEntradaUsuario() {
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.length() == 1 && (input.charAt(0) == 'H' || input.charAt(0) == 'J' || input.charAt(0) == 'K')) {
                return input.charAt(0);
            } else {
                System.out.println("Por favor, ingrese H, J o K.");
            }
        }
    }

    public static boolean obtenerRespuestaSiNo() {
        while (true) {
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (respuesta.equals("si") || respuesta.equals("sí")) {
                return true;
            } else if (respuesta.equals("no")) {
                return false;
            }
            System.out.println("Por favor, responda 'Sí' o 'No'.");
        }
    }

    public static String obtenerNombreUsuario() {
        System.out.println("Ingrese su nombre de usuario:");
        return scanner.nextLine().trim();
    }

    public static String obtenerContrasena() {
        System.out.println("Ingrese su contraseña:");
        return scanner.nextLine().trim();
    }
}
