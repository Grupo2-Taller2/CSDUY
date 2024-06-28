//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/service/LocalidadService.java
package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.dao.LocalidadDAO;
import com.ejemplo.carmenuy.model.Localidad;
import com.ejemplo.carmenuy.exception.LocalidadNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

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
     */
    public void crearTablaLocalidades() {
        try {
            localidadDAO.crearTabla();
            LOGGER.info("Tabla de localidades creada exitosamente.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al crear la tabla de localidades", e);
            throw new RuntimeException("Error al crear la tabla de localidades", e);
        }
    }

    /**
     * Inserta una nueva localidad en la base de datos.
     *
     * @param localidad La localidad a insertar.
     */
    public void insertarLocalidad(Localidad localidad) {
        try {
            localidadDAO.insertarLocalidad(localidad);
            LOGGER.info("Localidad insertada: " + localidad.getNombre());
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al insertar localidad", e);
            throw new RuntimeException("Error al insertar localidad", e);
        }
    }

    /**
     * Obtiene una localidad por su nombre.
     *
     * @param nombre El nombre de la localidad a obtener.
     * @return La localidad encontrada.
     * @throws LocalidadNotFoundException Si no se encuentra la localidad.
     */
    public Localidad obtenerLocalidadPorNombre(String nombre) {
        Localidad localidad = localidadDAO.obtenerLocalidadPorNombre(nombre);
        if (localidad == null) {
            throw new LocalidadNotFoundException("No se encontró la localidad con nombre: " + nombre);
        }
        return localidad;
    }

    /**
     * Obtiene todas las localidades de la base de datos.
     *
     * @return Lista de todas las localidades.
     */
    public List<String> obtenerTodasLasLocalidades() {
        return localidadDAO.obtenerTodasLasLocalidades();
    }

    /**
     * Inserta las localidades iniciales en la base de datos.
     */
    public void insertarLocalidadesIniciales() {
        try {
            localidadDAO.insertarLocalidadesIniciales();
            LOGGER.info("Localidades iniciales insertadas exitosamente.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al insertar localidades iniciales", e);
            throw new RuntimeException("Error al insertar localidades iniciales", e);
        }
    }

    /**
     * Obtiene la narrativa de una localidad por su nombre.
     *
     * @param nombreLocalidad El nombre de la localidad.
     * @return La narrativa procesada de la localidad.
     * @throws LocalidadNotFoundException Si no se encuentra la localidad.
     */
    public String obtenerNarrativaLocalidad(String nombreLocalidad) {
        Localidad localidad = obtenerLocalidadPorNombre(nombreLocalidad);
        return procesarNarrativa(localidad.getDescripcion(), localidad);
    }

    private String procesarNarrativa(String narrativa, Localidad localidad) {
        // Reemplazar placeholders con información dinámica
        narrativa = narrativa.replace("{NOMBRE}", localidad.getNombre());
        narrativa = narrativa.replace("{NUMERO}", localidad.getNumero());
        // Añadir más reemplazos según sea necesario
        
        return narrativa;
    }
}
