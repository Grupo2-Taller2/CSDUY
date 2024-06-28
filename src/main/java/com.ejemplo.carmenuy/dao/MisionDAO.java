//CarmenSandiegoUruguay/src/main/java/com/ejemplo/carmenuy/dao/MisionDAO.java
package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Mision;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MisionDAO {
    private final Connection connection;

    public MisionDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Mision> obtenerTodasLasMisiones() throws SQLException {
        List<Mision> misiones = new ArrayList<>();
        String sql = "SELECT * FROM Misiones";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Mision mision = new Mision(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getString("objetivo")
                );
                mision.setCompletada(rs.getBoolean("completada"));
                misiones.add(mision);
            }
        }
        return misiones;
    }

    public void insertarMision(Mision mision) throws SQLException {
        String sql = "INSERT INTO Misiones (titulo, descripcion, objetivo, completada) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, mision.getTitulo());
            pstmt.setString(2, mision.getDescripcion());
            pstmt.setString(3, mision.getObjetivo());
            pstmt.setBoolean(4, mision.isCompletada());
            pstmt.executeUpdate();
        }
    }

    public void actualizarMision(Mision mision) throws SQLException {
        String sql = "UPDATE Misiones SET titulo = ?, descripcion = ?, objetivo = ?, completada = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, mision.getTitulo());
            pstmt.setString(2, mision.getDescripcion());
            pstmt.setString(3, mision.getObjetivo());
            pstmt.setBoolean(4, mision.isCompletada());
            pstmt.setInt(5, mision.getId());
            pstmt.executeUpdate();
        }
    }

    public void eliminarMision(int id) throws SQLException {
        String sql = "DELETE FROM Misiones WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public Mision obtenerMisionPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Misiones WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Mision mision = new Mision(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("objetivo")
                    );
                    mision.setCompletada(rs.getBoolean("completada"));
                    return mision;
                }
            }
        }
        return null;
    }
}
