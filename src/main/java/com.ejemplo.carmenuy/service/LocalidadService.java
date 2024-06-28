//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/service/LocalidadService.java
package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.dao.LocalidadDAO;
import com.ejemplo.carmenuy.model.Localidad;
import com.ejemplo.carmenuy.exception.LocalidadNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Servicio que maneja las operaciones relacionadas con las localidades en el juego Carmen Sandiego Uruguay.
 */
public class LocalidadService {
    private static final Logger LOGGER = Logger.getLogger(LocalidadService.class.getName());
    
    private final LocalidadDAO localidadDAO;

    /**
     * Constructor del servicio de localidades.
     *
     * @param connection Conexión a la base de datos.
     */
    public LocalidadService(Connection connection) {
        this.localidadDAO = new LocalidadDAO(connection);
    }

    /**
     * Crea la tabla de localidades en la base de datos.
     *
     * @throws SQLException Si ocurre un error al crear la tabla.
     */
    public void crearTablaLocalidades() throws SQLException {
        try {
            localidadDAO.crearTabla();
            LOGGER.info("Tabla de localidades creada exitosamente.");
        } catch (SQLException e) {
            LOGGER.severe("Error al crear la tabla de localidades: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Inserta una nueva localidad en la base de datos.
     *
     * @param localidad La localidad a insertar.
     * @throws SQLException Si ocurre un error al insertar la localidad.
     */
    public void insertarLocalidad(Localidad localidad) throws SQLException {
        try {
            localidadDAO.insertarLocalidad(localidad);
            LOGGER.info("Localidad insertada: " + localidad.getNombre());
        } catch (SQLException e) {
            LOGGER.severe("Error al insertar localidad: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene una localidad por su ID.
     *
     * @param id El ID de la localidad a obtener.
     * @return La localidad encontrada.
     * @throws SQLException Si ocurre un error al obtener la localidad.
     */
    public Localidad obtenerLocalidadPorId(int id) throws SQLException {
        try {
            return localidadDAO.obtenerLocalidadPorId(id);
        } catch (SQLException e) {
            LOGGER.severe("Error al obtener localidad con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene todas las localidades de la base de datos.
     *
     * @return Lista de todas las localidades.
     * @throws SQLException Si ocurre un error al obtener las localidades.
     */
    public List<Localidad> obtenerTodasLasLocalidades() throws SQLException {
        try {
            return localidadDAO.obtenerTodasLasLocalidades();
        } catch (SQLException e) {
            LOGGER.severe("Error al obtener todas las localidades: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Actualiza una localidad existente en la base de datos.
     *
     * @param localidad La localidad a actualizar.
     * @throws SQLException Si ocurre un error al actualizar la localidad.
     */
    public void actualizarLocalidad(Localidad localidad) throws SQLException {
        try {
            localidadDAO.actualizarLocalidad(localidad);
            LOGGER.info("Localidad actualizada: " + localidad.getNombre());
        } catch (SQLException e) {
            LOGGER.severe("Error al actualizar localidad: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Elimina una localidad de la base de datos por su ID.
     *
     * @param id El ID de la localidad a eliminar.
     * @throws SQLException Si ocurre un error al eliminar la localidad.
     */
    public void eliminarLocalidad(int id) throws SQLException {
        try {
            localidadDAO.eliminarLocalidad(id);
            LOGGER.info("Localidad eliminada con ID: " + id);
        } catch (SQLException e) {
            LOGGER.severe("Error al eliminar localidad con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Inserta las localidades iniciales en la base de datos.
     *
     * @throws SQLException Si ocurre un error al insertar las localidades iniciales.
     */
    public void insertarLocalidadesIniciales() throws SQLException {
        try {
            localidadDAO.insertarLocalidadesIniciales();
            LOGGER.info("Localidades iniciales insertadas exitosamente.");
        } catch (SQLException e) {
            LOGGER.severe("Error al insertar localidades iniciales: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene la narrativa de una localidad por su ID.
     *
     * @param idLocalidad El ID de la localidad.
     * @return La narrativa procesada de la localidad.
     * @throws LocalidadNotFoundException Si no se encuentra la localidad.
     */
    public String obtenerNarrativaLocalidad(int idLocalidad) {
        Localidad localidad = null;
        try {
            localidad = localidadDAO.obtenerLocalidadPorId(idLocalidad);
        } catch (SQLException e) {
            LOGGER.severe("Error al obtener localidad con ID " + idLocalidad + ": " + e.getMessage());
            throw new RuntimeException("Error al obtener la localidad", e);
        }
        
        if (localidad == null) {
            throw new LocalidadNotFoundException("No se encontró la localidad con ID: " + idLocalidad);
        }
        
        String narrativa = localidad.getNarrativa();
        return procesarNarrativa(narrativa, localidad);
    }

    private String procesarNarrativa(String narrativa, Localidad localidad) {
        // Reemplazar placeholders con información dinámica
        narrativa = narrativa.replace("{NOMBRE}", localidad.getNombre());
        narrativa = narrativa.replace("{POBLACION}", String.valueOf(localidad.getPoblacion()));
        // Añadir más reemplazos según sea necesario
        
        return narrativa;
    }
}
