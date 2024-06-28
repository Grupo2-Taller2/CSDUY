//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/tts/TTSManager.java
package com.ejemplo.carmenuy.tts;

import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.concurrent.CompletableFuture;

public class TTSManager implements ITTSManager {
    private static final Logger logger = Logger.getLogger(TTSManager.class.getName());
    private Synthesizer synthesizer;
    private final Locale locale;
    private final String voiceName;

    public TTSManager() {
        this(new Locale("es", "UY"), "mbrola_es1");
    }

    public TTSManager(Locale locale, String voiceName) {
        this.locale = locale;
        this.voiceName = voiceName;
        initializeSynthesizer();
    }

    private void initializeSynthesizer() {
        try {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(locale));
            synthesizer.allocate();
            synthesizer.resume();

            // Configurar la voz específica aquí si es necesario
            // synthesizer.getSynthesizerProperties().setVoiceName(voiceName);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al inicializar el sintetizador", e);
            throw new RuntimeException("No se pudo inicializar el sintetizador de voz", e);
        }
    }

    @Override
    public void speak(String text) {
        try {
            synthesizer.speakPlainText(text, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error al sintetizar el texto", e);
        }
    }

    @Override
    public CompletableFuture<Void> speakAsync(String text) {
        return CompletableFuture.runAsync(() -> speak(text));
    }

    @Override
    public void setVolume(float volume) {
        // La implementación depende de la biblioteca TTS específica
        // Ejemplo: synthesizer.getSynthesizerProperties().setVolume(volume);
    }

    @Override
    public void setSpeed(float speed) {
        // La implementación depende de la biblioteca TTS específica
        // Ejemplo: synthesizer.getSynthesizerProperties().setSpeakingRate(speed);
    }

    @Override
    public void shutdown() {
        try {
            if (synthesizer != null) {
                synthesizer.deallocate();
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error al cerrar el sintetizador", e);
        }
    }
}

interface ITTSManager {
    void speak(String text);
    CompletableFuture<Void> speakAsync(String text);
    void setVolume(float volume);
    void setSpeed(float speed);
    void shutdown();
}
