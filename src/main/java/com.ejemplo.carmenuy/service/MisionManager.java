//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/service/MisionManager.java
package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.model.*;
import com.ejemplo.carmenuy.dao.MisionDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class MisionManager {
    private final MisionDAO misionDAO;
    private Mision misionActual;

    public MisionManager(Connection connection) {
        this.misionDAO = new MisionDAO(connection);
    }

    public void iniciarNuevaMision() throws SQLException {
        List<Mision> misiones = misionDAO.obtenerTodasLasMisiones();
        if (!misiones.isEmpty()) {
            Random random = new Random();
            misionActual = misiones.get(random.nextInt(misiones.size()));
        } else {
            throw new IllegalStateException("No hay misiones disponibles");
        }
    }

    public Mision getMisionActual() {
        return misionActual;
    }

    public boolean verificarObjetivoMision(Secuaz secuazCapturado) {
        return misionActual != null && misionActual.getObjetivo().equals(secuazCapturado.getNombre());
    }

    public void completarMision() {
        if (misionActual != null) {
            misionActual.setCompletada(true);
            // Aquí podrías agregar lógica para guardar el progreso en la base de datos
        }
    }

    public String obtenerDescripcionMision() {
        return misionActual != null ? misionActual.getDescripcion() : "No hay misión activa";
    }
}
