package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.model.dao.LocalidadDAO;
import com.ejemplo.carmenuy.model.dto.LocalidadDTO;
import java.sql.SQLException;
import java.util.List;

public class LocalidadService {

    private LocalidadDAO localidadDAO;

    public LocalidadService(LocalidadDAO localidadDAO) {
        this.localidadDAO = localidadDAO;
    }

    public void crearTabla() {
        try {
            localidadDAO.crearTabla();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarLocalidad(LocalidadDTO localidad) {
        try {
            localidadDAO.insertarLocalidad(localidad);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LocalidadDTO obtenerLocalidadPorId(int id) {
        try {
            return localidadDAO.obtenerLocalidadPorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LocalidadDTO> obtenerTodasLasLocalidades() {
        try {
            return localidadDAO.obtenerTodasLasLocalidades();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualizarLocalidad(LocalidadDTO localidad) {
        try {
            localidadDAO.actualizarLocalidad(localidad);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarLocalidad(int id) {
        try {
            localidadDAO.eliminarLocalidad(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
