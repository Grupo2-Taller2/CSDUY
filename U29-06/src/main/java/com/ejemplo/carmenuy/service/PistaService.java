package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.model.dao.PistaDAO;
import com.ejemplo.carmenuy.model.Pista;
import com.ejemplo.carmenuy.model.Pista.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class PistaService {

    private PistaDAO pistaDAO;
    private Random random;

    public PistaService(PistaDAO pistaDAO) {
        this.pistaDAO = pistaDAO;
        this.random = new Random();
    }

    public void crearTabla() {
        try {
            pistaDAO.crearTabla();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarPista(Pista pista) {
        try {
            pistaDAO.insertarPista(pista);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pista obtenerPistaPorId(int id) {
        try {
            return pistaDAO.obtenerPistaPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Pista> obtenerTodasLasPistas() {
        try {
            return pistaDAO.obtenerTodasLasPistas();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualizarPista(Pista pista) {
        try {
            pistaDAO.actualizarPista(pista);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarPista(int id) {
        try {
            pistaDAO.eliminarPista(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pista generarPistaAleatoria() {

        int tipoPista = random.nextInt(5);

        switch (tipoPista) {
            case 0:
                return new PistaGeografia("PISTA1", "Localidad Correcta");
            case 1:
                return new PistaHistoria("PISTA2", "Época Correcta");
            case 2:
                return new PistaLeyenda("PISTA3", "Personaje Correcto");
            case 3:
                return new PistaGastronomia("PISTA4", "Plato Correcto");
            case 4:
                return new PistaTurismo("PISTA5", "Lugar Correcto");
            default:
                return new PistaGeografia("PISTA1", "Localidad Correcta");
        }
    }
}
