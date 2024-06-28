//CarmenSandiegoUruguay/src/main/java/com/ejemplo/carmenuy/service/AccesibilidadManager.java
package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.tts.TTSManager;
import com.ejemplo.carmenuy.ui.InputManager;

public class AccesibilidadManager {
    private final TTSManager ttsManager;
    private boolean audioActivo;

    public AccesibilidadManager(TTSManager ttsManager) {
        this.ttsManager = ttsManager;
        this.audioActivo = true;
    }

    public void preguntarPreferenciaAudio() {
        ttsManager.speak("¿Desea mantener el audio activo? Presione H para Sí, J para No.");
        audioActivo = InputManager.obtenerRespuestaSiNo();
    }

    public void setAudioActivo(boolean audioActivo) {
        this.audioActivo = audioActivo;
    }

    public boolean isAudioActivo() {
        return audioActivo;
    }

    public void hablar(String mensaje) {
        if (audioActivo) {
            ttsManager.speak(mensaje);
        }
    }
}
