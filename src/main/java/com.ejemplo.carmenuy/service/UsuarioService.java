//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/service/UsuarioService.java
package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.dao.UsuarioDAO;
import com.ejemplo.carmenuy.model.Usuario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Servicio que maneja las operaciones relacionadas con los usuarios en el juego Carmen Sandiego Uruguay.
 */
public class UsuarioService {
    private static final Logger LOGGER = Logger.getLogger(UsuarioService.class.getName());
    
    private final UsuarioDAO usuarioDAO;

    /**
     * Constructor del servicio de usuarios.
     *
     * @param connection Conexión a la base de datos.
     */
    public UsuarioService(Connection connection) {
        this.usuarioDAO = new UsuarioDAO(connection);
    }

    /**
     * Crea la tabla de usuarios en la base de datos.
     *
     * @throws SQLException Si ocurre un error al crear la tabla.
     */
    public void crearTablaUsuarios() throws SQLException {
        try {
            usuarioDAO.crearTabla();
            LOGGER.info("Tabla de usuarios creada exitosamente.");
        } catch (SQLException e) {
            LOGGER.severe("Error al crear la tabla de usuarios: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     *
     * @param usuario El usuario a insertar.
     * @throws SQLException Si ocurre un error al insertar el usuario.
     */
    public void insertarUsuario(Usuario usuario) throws SQLException {
        try {
            usuarioDAO.insertarUsuario(usuario);
            LOGGER.info("Usuario insertado: " + usuario.getNombre());
        } catch (SQLException e) {
            LOGGER.severe("Error al insertar usuario: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id El ID del usuario a obtener.
     * @return El usuario encontrado.
     * @throws SQLException Si ocurre un error al obtener el usuario.
     */
    public Usuario obtenerUsuarioPorId(int id) throws SQLException {
        try {
            return usuarioDAO.obtenerUsuarioPorId(id);
        } catch (SQLException e) {
            LOGGER.severe("Error al obtener usuario con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene un usuario por su nombre.
     *
     * @param nombre El nombre del usuario a obtener.
     * @return El usuario encontrado.
     * @throws SQLException Si ocurre un error al obtener el usuario.
     */
    public Usuario obtenerUsuarioPorNombre(String nombre) throws SQLException {
        try {
            return usuarioDAO.obtenerUsuarioPorNombre(nombre);
        } catch (SQLException e) {
            LOGGER.severe("Error al obtener usuario con nombre " + nombre + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene todos los usuarios de la base de datos.
     *
     * @return Lista de todos los usuarios.
     * @throws SQLException Si ocurre un error al obtener los usuarios.
     */
    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        try {
            return usuarioDAO.obtenerTodosLosUsuarios();
        } catch (SQLException e) {
            LOGGER.severe("Error al obtener todos los usuarios: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Actualiza un usuario existente en la base de datos.
     *
     * @param usuario El usuario a actualizar.
     * @throws SQLException Si ocurre un error al actualizar el usuario.
     */
    public void actualizarUsuario(Usuario usuario) throws SQLException {
        try {
            usuarioDAO.actualizarUsuario(usuario);
            LOGGER.info("Usuario actualizado: " + usuario.getNombre());
        } catch (SQLException e) {
            LOGGER.severe("Error al actualizar usuario: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Elimina un usuario de la base de datos por su ID.
     *
     * @param id El ID del usuario a eliminar.
     * @throws SQLException Si ocurre un error al eliminar el usuario.
     */
    public void eliminarUsuario(int id) throws SQLException {
        try {
            usuarioDAO.eliminarUsuario(id);
            LOGGER.info("Usuario eliminado con ID: " + id);
        } catch (SQLException e) {
            LOGGER.severe("Error al eliminar usuario con ID " + id + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Valida las credenciales de un usuario.
     *
     * @param nombre El nombre del usuario.
     * @param contrasena La contraseña del usuario.
     * @return true si las credenciales son válidas, false en caso contrario.
     * @throws SQLException Si ocurre un error al validar el usuario.
     */
    public boolean validarUsuario(String nombre, String contrasena) throws SQLException {
        try {
            Usuario usuario = usuarioDAO.obtenerUsuarioPorNombre(nombre);
            boolean esValido = usuario != null && usuario.getContrasena().equals(contrasena);
            LOGGER.info("Validación de usuario " + nombre + ": " + (esValido ? "exitosa" : "fallida"));
            return esValido;
        } catch (SQLException e) {
            LOGGER.severe("Error al validar usuario " + nombre + ": " + e.getMessage());
            throw e;
        }
    }
}
