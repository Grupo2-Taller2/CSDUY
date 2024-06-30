package com.ejemplo.carmenuy.model.dao;

import com.ejemplo.carmenuy.model.dto.LocalidadDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


// DAO = Data Access Object: son los objetos que hablan con la base de datos

public class LocalidadDAO {
    
    private Connection connection;

    public LocalidadDAO(Connection connection) {
        this.connection = connection;
    }

    public void crearTabla() throws SQLException {
        
        String sql = "CREATE TABLE IF NOT EXISTS localidades (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "nombre TEXT NOT NULL, " +
                     "descripcion TEXT NOT NULL)";
        
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

     public void insertarLocalidad(LocalidadDTO localidad) throws SQLException {
         
         String sql = "INSERT INTO localidades (nombre, descripcion) VALUES (?, ?)";
         
         try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
             pstmt.setString(1, localidad.getNombre());
             pstmt.setString(2, localidad.getDescripcion());
             pstmt.executeUpdate();
         }
     }

     public LocalidadDTO obtenerLocalidadPorId(int id) throws SQLException {
         String sql = "SELECT * FROM localidades WHERE id = ?";
         try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
             pstmt.setInt(1, id);
             ResultSet rs = pstmt.executeQuery();
             if (rs.next()) {
                 return new LocalidadDTO(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"));
             }
         }
         return null;
     }

     public List<LocalidadDTO> obtenerTodasLasLocalidades() throws SQLException {
         List<LocalidadDTO> localidades = new ArrayList<>();
         String sql = "SELECT * FROM localidades";
         try (Statement stmt = connection.createStatement();
              ResultSet rs = stmt.executeQuery(sql)) {
             while (rs.next()) {
                 LocalidadDTO localidad = new LocalidadDTO(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"));
                 localidades.add(localidad);
             }
         }
         return localidades;
     }

     public void actualizarLocalidad(LocalidadDTO localidad) throws SQLException {
         String sql = "UPDATE localidades SET nombre = ?, descripcion = ? WHERE id = ?";
         try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
             pstmt.setString(1, localidad.getNombre());
             pstmt.setString(2, localidad.getDescripcion());
             pstmt.setInt(3, localidad.getId());
             pstmt.executeUpdate();
         }
     }

    public void eliminarLocalidad(int id) throws SQLException {
        String sql = "DELETE FROM localidades WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
