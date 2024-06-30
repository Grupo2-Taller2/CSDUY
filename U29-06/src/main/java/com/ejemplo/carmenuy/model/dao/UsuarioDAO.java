package com.ejemplo.carmenuy.model.dao;

import com.ejemplo.carmenuy.model.dto.UsuarioDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


// si se usa la convención DAO para interactuar con la base de datos
// entonces se debe usar la convención DTO para las entidades
// por ejemplo, si tenemos una tabla 'usuario' en la base
// la representamos con una clase UsuarioDTO en Java
public class UsuarioDAO {
    
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    // ERROR!!! las tablas no se crean con código
    // la base de datos se debe crear por separado (separar roles)
    public void crearTabla() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "nombre TEXT NOT NULL, " +
                     "apellido TEXT NOT NULL, " +
                     "contrasena TEXT NOT NULL)";
        
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

     public void insertarUsuario(UsuarioDTO usuario) throws SQLException {
         
         String sql = "INSERT INTO usuarios (nombre, apellido, contrasena) VALUES (?, ?, ?)";
         
         try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
             pstmt.setString(1, usuario.getNombre());
             pstmt.setString(2, usuario.getApellido());
             pstmt.setString(3, usuario.getContrasena());
             pstmt.executeUpdate();
         }
     }

//     public UsuarioDTO obtenerUsuarioPorId(int id) throws SQLException {
//         
//         String sql = "SELECT * FROM usuarios WHERE id = ?";
//         
//         try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//             pstmt.setInt(1, id);
//             ResultSet rs = pstmt.executeQuery();
//             if (rs.next()) {
//                 return new UsuarioDTO(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("contrasena"));
//             }
//         }
//         return null;
//     }

//     public UsuarioDTO obtenerUsuarioPorNombreYContrasena(String nombre, String contrasena) throws SQLException {
//         
//         String sql = "SELECT * FROM usuarios WHERE nombre = ? AND contrasena = ?";
//         
//         try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
//             pstmt.setString(1, nombre);
//             pstmt.setString(2, contrasena);
//             ResultSet rs = pstmt.executeQuery();
//             if (rs.next()) {
//                 return new UsuarioDTO(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("contrasena"));
//             }
//         }
//         return null;
//     }

//     public List<UsuarioDTO> obtenerTodosLosUsuarios() throws SQLException {
//         
//         List<UsuarioDTO> usuarios = new ArrayList<>();
//         
//         String sql = "SELECT * FROM usuarios";
//         try (Statement stmt = connection.createStatement();
//              ResultSet rs = stmt.executeQuery(sql)) {
//             while (rs.next()) {
//                 UsuarioDTO usuario = new UsuarioDTO(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("contrasena"));
//                 usuarios.add(usuario);
//             }
//         }
//         return usuarios;
//     }

     public void actualizarUsuario(UsuarioDTO usuario) throws SQLException {
         
         String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, contrasena = ? WHERE id = ?";
         
         try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
             pstmt.setString(1, usuario.getNombre());
             pstmt.setString(2, usuario.getApellido());
             pstmt.setString(3, usuario.getContrasena());
             pstmt.setInt(4, usuario.getId());
             pstmt.executeUpdate();
         }
     }

    public void eliminarUsuario(int id) throws SQLException {
        
        String sql = "DELETE FROM usuarios WHERE id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
