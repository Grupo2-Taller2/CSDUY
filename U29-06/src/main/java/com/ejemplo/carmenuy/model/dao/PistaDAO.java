package com.ejemplo.carmenuy.model.dao;

import com.ejemplo.carmenuy.model.Pista;
import com.ejemplo.carmenuy.model.Pista.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PistaDAO {

    private Connection connection;

    public PistaDAO(Connection connection) {
        this.connection = connection;
    }

    public void crearTabla() throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS pistas ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "tipo TEXT NOT NULL, "
                + "texto TEXT NOT NULL, "
                + "correcta TEXT NOT NULL)";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void insertarPista(Pista pista) throws SQLException {

        String sql = "INSERT INTO pistas (tipo, texto, correcta) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, pista.getClass().getSimpleName());
            pstmt.setString(2, pista.getTexto());
            pstmt.setString(3, pista.getCorrecta());
            pstmt.executeUpdate();
        }
    }

    public Pista obtenerPistaPorId(int id) throws SQLException {

        String sql = "SELECT * FROM pistas WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return crearPistaDesdeResultSet(rs);
            }
        }
        return null;
    }

    public List<Pista> obtenerTodasLasPistas() throws SQLException {

        List<Pista> pistas = new ArrayList<>();
        String sql = "SELECT * FROM pistas";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pista pista = crearPistaDesdeResultSet(rs);
                pistas.add(pista);
            }
        }
        return pistas;
    }

    public void actualizarPista(Pista pista) throws SQLException {

        String sql = "UPDATE pistas SET tipo = ?, texto = ?, correcta = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, pista.getClass().getSimpleName());
            pstmt.setString(2, pista.getTexto());
            pstmt.setString(3, pista.getCorrecta());
            //pstmt.setInt(4, pista.getId()); // la clase Pista no tiene ID
            pstmt.executeUpdate();
        }
    }

    public void eliminarPista(int id) throws SQLException {

        String sql = "DELETE FROM pistas WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    private Pista crearPistaDesdeResultSet(ResultSet rs) throws SQLException {

        String tipo = rs.getString("tipo");
        String texto = rs.getString("texto");
        String correcta = rs.getString("correcta");

        switch (tipo) {
            case "PistaGeografia":
                return new PistaGeografia(texto, correcta);
            case "PistaHistoria":
                return new PistaHistoria(texto, correcta);
            case "PistaLeyenda":
                return new PistaLeyenda(texto, correcta);
            case "PistaGastronomia":
                return new PistaGastronomia(texto, correcta);
            case "PistaTurismo":
                return new PistaTurismo(texto, correcta);
            default:
                throw new SQLException("Tipo de pista desconocido: " + tipo);
        }
    }
}
