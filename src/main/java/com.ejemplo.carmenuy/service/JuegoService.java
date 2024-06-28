//CarmenSandiegoUruguay/src/main/java/com/ejemplo/carmenuy/service/JuegoService.java
package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.model.*;
import com.ejemplo.carmenuy.dao.*;
import com.ejemplo.carmenuy.tts.TTSManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

public class JuegoService {
    private static final Logger LOGGER = Logger.getLogger(JuegoService.class.getName());
    private static final Random RANDOM = new Random();

    private Detective detective;
    private final Grafo grafo;
    private final PistaDAO pistaDAO;
    private final LocalidadDAO localidadDAO;
    private final PartidaDAO partidaDAO;
    private List<Secuaz> secuaces;
    private Nodo nodoActual;
    private Localidad localidadActual;
    private final NarrativaManager narrativaManager;
    private final TTSManager ttsManager;
    private final AccesibilidadManager accesibilidadManager;
    private final MisionManager misionManager;
    private Secuaz carmenSandiego;

    public JuegoService(Connection connection) throws SQLException {
        this.grafo = new Grafo();
        this.localidadDAO = new LocalidadDAO(connection);
        this.pistaDAO = new PistaDAO(connection, this.localidadDAO);
        this.partidaDAO = new PartidaDAO(connection);
        this.narrativaManager = new NarrativaManager(this.grafo);
        this.ttsManager = new TTSManager();
        this.accesibilidadManager = new AccesibilidadManager(ttsManager);
        this.misionManager = new MisionManager(connection);
        inicializarJuego();
    }

    private void inicializarJuego() throws SQLException {
        accesibilidadManager.preguntarPreferenciaAudio();
        crearTablas();
        if (localidadDAO.obtenerTodasLasLocalidades().isEmpty()) {
            insertarDatosIniciales();
        }
        inicializarPersonajes();
    }

    private void crearTablas() throws SQLException {
        pistaDAO.crearTabla();
        localidadDAO.crearTabla();
        partidaDAO.crearTabla();
    }

    private void insertarDatosIniciales() throws SQLException {
        localidadDAO.insertarLocalidadesIniciales();
        pistaDAO.insertarPistasIniciales();
    }

    private void inicializarPersonajes() {
        this.detective = new Detective("Detective", "Apellido", Rango.DETECTIVE_JUNIOR);
        this.secuaces = new ArrayList<>();
        inicializarSecuaces();
        inicializarLocalidadAleatoria();
    }

    private void inicializarSecuaces() {
        secuaces.add(new Secuaz("Betosecreto", "Escalador, experto en artes ninja y espionaje", 5));
        secuaces.add(new Secuaz("Ellabella", "Experta en estafas", 4));
        secuaces.add(new Secuaz("Mindy Ana Son", "Arqueóloga experta en gemas", 3));
        secuaces.add(new Secuaz("MOONabomber", "Experto en explosivos", 5));
        for (Secuaz secuaz : secuaces) {
            secuaz.setNodo(grafo.getNodoAleatorio());
        }
        carmenSandiego = new Secuaz("Carmen Sandiego", "Líder criminal", 10);
        carmenSandiego.setNodo(grafo.getNodoAleatorio());
    }

    private void inicializarLocalidadAleatoria() {
        List<String> todasLasLocalidades = localidadDAO.obtenerTodasLasLocalidades();
        String localidadInicial = todasLasLocalidades.get(RANDOM.nextInt(todasLasLocalidades.size()));
        this.nodoActual = grafo.getNodo(localidadInicial);
        this.localidadActual = new Localidad(localidadInicial, "Descripción de " + localidadInicial);
    }

    public String obtenerNarrativaLocalidad() {
        return narrativaManager.generarNarrativa(localidadActual);
    }

    public String obtenerPistaActual() {
        return narrativaManager.generarPista(localidadActual);
    }

    public void moverDetective(String nombreLocalidad) {
        Nodo nuevoNodo = grafo.getNodo(nombreLocalidad);
        if (nuevoNodo != null && nodoActual.getConexiones().contains(nuevoNodo)) {
            nodoActual = nuevoNodo;
            localidadActual = new Localidad(nombreLocalidad, "Descripción de " + nombreLocalidad);
            detective.decrementarMovimientos(1);
            verificarCaptura();
        } else {
            LOGGER.warning("Movimiento inválido a: " + nombreLocalidad);
        }
    }

    private void verificarCaptura() {
        for (Secuaz secuaz : secuaces) {
            if (nodoActual.equals(secuaz.getNodo())) {
                capturarSecuaz(secuaz);
                return;
            }
        }
        if (nodoActual.equals(carmenSandiego.getNodo()) && detective.getRango() == Rango.INSPECTOR) {
            capturarCarmenSandiego();
        }
    }

    private void capturarSecuaz(Secuaz secuaz) {
        String mensaje = "Felicitaciones " + detective.getRango() + " " + detective.getNombre() + 
                         " has capturado al peligroso " + secuaz.getNombre();
        LOGGER.info(mensaje);
        accesibilidadManager.hablar(mensaje);
        secuaces.remove(secuaz);
        detective.incrementarCapturas();
        verificarAscenso();
    }

    private void capturarCarmenSandiego() {
        String mensaje = "¡Increíble! " + detective.getRango() + " " + detective.getNombre() + 
                         " has capturado a Carmen Sandiego.";
        LOGGER.info(mensaje);
        accesibilidadManager.hablar(mensaje);
        // Aquí puedes agregar lógica adicional para manejar la captura de Carmen Sandiego
    }

    private void verificarAscenso() {
        if (detective.getCapturas() >= 3 && detective.getRango() == Rango.DETECTIVE_JUNIOR) {
            detective.setRango(Rango.DETECTIVE_SENIOR);
        } else if (detective.getCapturas() >= 6 && detective.getRango() == Rango.DETECTIVE_SENIOR) {
            detective.setRango(Rango.INSPECTOR);
        }
    }

    public List<Pista> obtenerPistasParaLocalidad() {
        List<Pista> todasLasPistas = pistaDAO.obtenerPistasParaLocalidad(localidadActual.getNombre());
        List<Pista> pistasSeleccionadas = new ArrayList<>();

        // Asegurarse de que siempre haya una pista correcta
        Pista pistaCorrecta = todasLasPistas.stream()
                .filter(Pista::isEsCorrecta)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró pista correcta para " + localidadActual.getNombre()));
        pistasSeleccionadas.add(pistaCorrecta);

        // Seleccionar pistas incorrectas aleatoriamente
        List<Pista> pistasIncorrectas = new ArrayList<>(todasLasPistas);
        pistasIncorrectas.remove(pistaCorrecta);
        while (pistasSeleccionadas.size() < 3 && !pistasIncorrectas.isEmpty()) {
            int index = RANDOM.nextInt(pistasIncorrectas.size());
            pistasSeleccionadas.add(pistasIncorrectas.remove(index));
        }

        // Mezclar las pistas seleccionadas
        java.util.Collections.shuffle(pistasSeleccionadas);

        return pistasSeleccionadas;
    }

    public boolean verificarRespuesta(Pista pistaSeleccionada) {
        return pistaSeleccionada.isEsCorrecta();
    }

    public void moverDetectiveALocalidadAleatoria() {
        List<String> todasLasLocalidades = localidadDAO.obtenerTodasLasLocalidades();
        String nuevaLocalidad = todasLasLocalidades.get(RANDOM.nextInt(todasLasLocalidades.size()));
        moverDetective(nuevaLocalidad);
    }

    public void iniciarNuevaPartida(String nombreDetective) throws SQLException {
        this.detective = new Detective(nombreDetective, "", Rango.DETECTIVE_JUNIOR);
        inicializarSecuaces();
        inicializarLocalidadAleatoria();
        misionManager.iniciarNuevaMision();
    }

    public String obtenerInformacionMision() {
        return misionManager.obtenerDescripcionMision();
    }

    public void guardarEstadoJuego() {
        try {
            Partida partida = new Partida(
                detective,
                secuaces.isEmpty() ? null : secuaces.get(0),
                nodoActual.getNombre(),
                secuaces.isEmpty() ? "" : secuaces.get(0).getNodo().getNombre(),
                localidadActual,
                misionManager.getMisionActual()
            );
            partidaDAO.guardarPartida(partida);
            LOGGER.info("Estado del juego guardado exitosamente.");
        } catch (SQLException e) {
            LOGGER.severe("Error al guardar el estado del juego: " + e.getMessage());
        }
    }

    public boolean cargarEstadoJuego() {
        try {
            Partida partida = partidaDAO.cargarUltimaPartida();
            if (partida != null) {
                this.detective = partida.getDetective();
                this.secuaces = new ArrayList<>();
                if (partida.getSecuaz() != null) {
                    this.secuaces.add(partida.getSecuaz());
                }
                this.nodoActual = grafo.getNodo(partida.getNodoActualNombre());
                this.localidadActual = partida.getLocalidadActual();
                this.misionManager.setMisionActual(partida.getMision());
                LOGGER.info("Estado del juego cargado exitosamente.");
                return true;
            } else {
                LOGGER.info("No se encontró un estado de juego guardado.");
                return false;
            }
        } catch (SQLException e) {
            LOGGER.severe("Error al cargar el estado del juego: " + e.getMessage());
            return false;
        }
    }

    public boolean juegoTerminado() {
        return carmenSandiego == null || detective.getMovimientos() <= 0;
    }

    public void mostrarResultadoFinal() {
        String mensaje = (carmenSandiego == null) ?
            "¡Felicidades! Has capturado a Carmen Sandiego y completado el juego." :
            "Se han agotado tus movimientos. El juego ha terminado.";
        LOGGER.info(mensaje);
        accesibilidadManager.hablar(mensaje);
    }
}