//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/dao/PistaDAO.java
package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Pista;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class PistaDAO {
    private static final Logger logger = Logger.getLogger(PistaDAO.class.getName());
    private static final String TABLE_NAME = "Pistas";

    private final Connection db;
    private final LocalidadDAO localidadDAO;

    public PistaDAO(Connection connection, LocalidadDAO localidadDAO) {
        this.db = connection;
        this.localidadDAO = localidadDAO;
        try {
            crearTabla();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al inicializar PistaDAO", e);
            throw new RuntimeException("No se pudo inicializar PistaDAO", e);
        }
    }

    public void crearTabla() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "Localidad TEXT NOT NULL, "
                + "Numero TEXT NOT NULL, "
                + "Descripcion TEXT NOT NULL, "
                + "esCorrecta BOOLEAN NOT NULL);";
        try (Statement stmt = db.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void insertarPistasIniciales() throws SQLException {
        if (!existenPistas()) {
            List<Pista> pistasIniciales = generarPistasIniciales();
            for (Pista pista : pistasIniciales) {
                insertarPista(pista);
            }
        }
    }

    private List<Pista> generarPistasIniciales() throws SQLException {
        List<Pista> pistas = new ArrayList<>();
        List<String> localidades = localidadDAO.obtenerTodasLasLocalidades();
        int idPista = 1;

        for (String localidad : localidades) {
            for (int i = 1; i <= 5; i++) {
                String numeroPista = localidad + "." + i;
                boolean esCorrecta = i == 5;
                String descripcion = "[Descripción de la pista " + numeroPista + " para " + localidad + "]";
                Pista pista = new Pista(idPista++, localidad, numeroPista, descripcion, esCorrecta);
                pistas.add(pista);
            }
        }
        return pistas;
    }

    public void insertarPista(Pista pista) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " (Localidad, Numero, Descripcion, esCorrecta) VALUES (?, ?, ?, ?);";
        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            pstmt.setString(1, pista.getLocalidad());
            pstmt.setString(2, pista.getNumero());
            pstmt.setString(3, pista.getDescripcion());
            pstmt.setBoolean(4, pista.isEsCorrecta());
            pstmt.executeUpdate();
        }
    }

    public List<Pista> obtenerPistasParaLocalidad(String localidad) {
        List<Pista> pistas = new ArrayList<>();
        String sql = "SELECT ID, Localidad, Numero, Descripcion, esCorrecta FROM " + TABLE_NAME + " WHERE Localidad = ?;";
        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            pstmt.setString(1, localidad);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    pistas.add(new Pista(
                        rs.getInt("ID"), 
                        rs.getString("Localidad"), 
                        rs.getString("Numero"), 
                        rs.getString("Descripcion"),
                        rs.getBoolean("esCorrecta")
                    ));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener pistas para localidad", e);
            throw new RuntimeException("Error al obtener pistas para localidad", e);
        }
        return pistas;
    }

    private boolean existenPistas() throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + TABLE_NAME;
        try (PreparedStatement pstmt = db.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}
