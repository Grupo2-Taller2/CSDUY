//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/service/AudioManager.java
package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.tts.TTSManager;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class AudioManager {
    private static final Logger LOGGER = Logger.getLogger(AudioManager.class.getName());
    private final TTSManager ttsManager;
    private final Map<String, Clip> efectos;
    private Clip musicaFondo;
    private float volumenGeneral;

    public AudioManager() {
        this.ttsManager = new TTSManager();
        this.efectos = new HashMap<>();
        this.volumenGeneral = 1.0f;
        cargarEfectos();
    }

    private void cargarEfectos() {
        cargarEfecto("victoria", "sonidos/victoria.wav");
        cargarEfecto("fracaso", "sonidos/fracaso.wav");
        cargarEfecto("latido", "sonidos/latido.wav");
        cargarEfecto("hjk", "sonidos/hjk.wav");
        // Agregar más efectos según sea necesario
    }

    private void cargarEfecto(String nombre, String ruta) {
        try {
            File archivoAudio = new File(ruta);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivoAudio);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            efectos.put(nombre, clip);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            LOGGER.severe("Error al cargar el efecto de sonido " + nombre + ": " + e.getMessage());
        }
    }

    public void reproducirEfecto(String nombreEfecto) {
        Clip clip = efectos.get(nombreEfecto);
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            ajustarVolumen(clip);
            clip.start();
        } else {
            LOGGER.warning("Efecto de sonido no encontrado: " + nombreEfecto);
        }
    }

    public void reproducirVictoria() {
        reproducirEfecto("victoria");
    }

    public void reproducirFracaso() {
        reproducirEfecto("fracaso");
    }

    public void reproducirLatido() {
        reproducirEfecto("latido");
    }

    public void reproducirEfectoHJK() {
        reproducirEfecto("hjk");
    }

    public void reproducirMusica(String nombrePista) {
        try {
            if (musicaFondo != null && musicaFondo.isRunning()) {
                musicaFondo.stop();
            }
            File archivoMusica = new File("sonidos/" + nombrePista + ".wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(archivoMusica);
            musicaFondo = AudioSystem.getClip();
            musicaFondo.open(audioInputStream);
            ajustarVolumen(musicaFondo);
            musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            LOGGER.severe("Error al reproducir la música de fondo: " + e.getMessage());
        }
    }

    public void hablar(String texto) {
        ttsManager.speak(texto);
    }

    public void setVolumenGeneral(float volumen) {
        this.volumenGeneral = Math.max(0.0f, Math.min(1.0f, volumen));
        ajustarVolumenTodosLosClips();
    }

    private void ajustarVolumenTodosLosClips() {
        for (Clip clip : efectos.values()) {
            ajustarVolumen(clip);
        }
        if (musicaFondo != null) {
            ajustarVolumen(musicaFondo);
        }
    }

    private void ajustarVolumen(Clip clip) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * volumenGeneral) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }
}
