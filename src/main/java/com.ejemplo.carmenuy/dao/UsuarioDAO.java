//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/dao/UsuarioDAO.java
package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class UsuarioDAO {
    private static final Logger logger = Logger.getLogger(UsuarioDAO.class.getName());
    private static final String TABLE_NAME = "usuarios";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_APELLIDO = "apellido";
    private static final String COLUMN_CONTRASENA = "contrasena";

    private final Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
        try {
            crearTabla();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al crear la tabla de usuarios", e);
        }
    }

    private void crearTabla() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                     COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     COLUMN_NOMBRE + " TEXT NOT NULL, " +
                     COLUMN_APELLIDO + " TEXT NOT NULL, " +
                     COLUMN_CONTRASENA + " TEXT NOT NULL)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_NOMBRE + ", " + COLUMN_APELLIDO + ", " + COLUMN_CONTRASENA + ") VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellido());
            pstmt.setString(3, usuario.getContrasena()); // Considerar usar hash+salt aquí
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al insertar usuario", e);
        }
    }

    public Usuario obtenerUsuarioPorId(int id) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(rs.getInt(COLUMN_ID), rs.getString(COLUMN_NOMBRE), rs.getString(COLUMN_APELLIDO), rs.getString(COLUMN_CONTRASENA));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener usuario por ID", e);
        }
        return null;
    }

    public Usuario obtenerUsuarioPorNombreYContrasena(String nombre, String contrasena) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NOMBRE + " = ? AND " + COLUMN_CONTRASENA + " = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, contrasena); // Considerar usar hash+salt aquí
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(rs.getInt(COLUMN_ID), rs.getString(COLUMN_NOMBRE), rs.getString(COLUMN_APELLIDO), rs.getString(COLUMN_CONTRASENA));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener usuario por nombre y contraseña", e);
        }
        return null;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt(COLUMN_ID), rs.getString(COLUMN_NOMBRE), rs.getString(COLUMN_APELLIDO), rs.getString(COLUMN_CONTRASENA));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener todos los usuarios", e);
        }
        return usuarios;
    }

    public void actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE " + TABLE_NAME + " SET " + COLUMN_NOMBRE + " = ?, " + COLUMN_APELLIDO + " = ?, " + COLUMN_CONTRASENA + " = ? WHERE " + COLUMN_ID + " = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellido());
            pstmt.setString(3, usuario.getContrasena()); // Considerar usar hash+salt aquí
            pstmt.setInt(4, usuario.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar usuario", e);
        }
    }

    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al eliminar usuario", e);
        }
    }
}
