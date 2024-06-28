//CarmenSandiegoUruguay/src/main/java/com/ejemplo/carmenuy/service/DatabaseInitializationService.java

package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.dao.LocalidadDAO;
import com.ejemplo.carmenuy.dao.PistaDAO;
import com.ejemplo.carmenuy.dao.SecuazDAO;
import com.ejemplo.carmenuy.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;
import java.util.function.Predicate;

public class DatabaseInitializationService {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitializationService.class);
    private final Properties config;

    public DatabaseInitializationService() {
        this.config = loadConfig();
    }

    private Properties loadConfig() {
        Properties config = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Unable to find config.properties");
            }
            config.load(input);
        } catch (IOException e) {
            logger.error("Error al cargar el archivo de configuración", e);
            throw new RuntimeException("No se pudo cargar la configuración", e);
        }
        return config;
    }

    public void initializeDatabase() {
        createTables();
        loadInitialData();
    }

    private void createTables() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            conn.setAutoCommit(false);
            String sql = readSqlFile("/init_database.sql");
            for (String statement : sql.split(";")) {
                if (!statement.trim().isEmpty()) {
                    stmt.execute(statement);
                }
            }
            conn.commit();
            logger.info("Tablas creadas exitosamente.");
        } catch (SQLException | IOException e) {
            logger.error("Error al crear las tablas", e);
            throw new RuntimeException("No se pudieron crear las tablas", e);
        }
    }

    private String readSqlFile(String resourcePath) throws IOException {
        try (InputStream in = getClass().getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        }
    }

    private void loadInitialData() {
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            try {
                loadLocalidades(conn);
                loadPistas(conn);
                loadSecuaces(conn);
                conn.commit();
                logger.info("Datos iniciales cargados exitosamente.");
            } catch (SQLException e) {
                conn.rollback();
                logger.error("Error al cargar datos iniciales. Se ha realizado rollback.", e);
                throw new RuntimeException("Error al cargar datos iniciales", e);
            }
        } catch (SQLException e) {
            logger.error("Error al establecer conexión con la base de datos", e);
            throw new RuntimeException("No se pudo inicializar la base de datos", e);
        }
    }

    private void loadLocalidades(Connection conn) throws SQLException {
        List<Localidad> localidades = loadDataFromCsv("localidades.data.path", this::parseLocalidad, this::validarLocalidad);
        new LocalidadDAO(conn).insertarLocalidades(localidades);
        logger.info("Localidades cargadas exitosamente.");
    }

    private void loadPistas(Connection conn) throws SQLException {
        List<Pista> pistas = loadDataFromCsv("pistas.data.path", this::parsePista, this::validarPista);
        new PistaDAO(conn).insertarPistas(pistas);
        logger.info("Pistas cargadas exitosamente.");
    }

    private void loadSecuaces(Connection conn) throws SQLException {
        List<Secuaz> secuaces = loadDataFromCsv("secuaces.data.path", this::parseSecuaz, this::validarSecuaz);
        new SecuazDAO(conn).insertarSecuaces(secuaces);
        logger.info("Secuaces cargados exitosamente.");
    }

    private <T> List<T> loadDataFromCsv(String propertyKey, Function<String[], T> parser, Predicate<T> validator) {
        String csvFile = config.getProperty(propertyKey);
        List<T> items = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(csvFile), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                T item = parser.apply(data);
                if (validator.test(item)) {
                    items.add(item);
                }
            }
        } catch (IOException e) {
            logger.error("Error al leer el archivo CSV: " + csvFile, e);
            throw new RuntimeException("Error al cargar datos desde CSV", e);
        }
        return items;
    }

    private Localidad parseLocalidad(String[] data) {
        if (data.length != 4) {
            logger.warn("Línea de datos inválida para localidad");
            return null;
        }
        return new Localidad(data[0], data[1], Double.parseDouble(data[2]), Double.parseDouble(data[3]));
    }

    private Pista parsePista(String[] data) {
        if (data.length != 5) {
            logger.warn("Línea de datos inválida para pista");
            return null;
        }
        return crearPista(data);
    }

    private Secuaz parseSecuaz(String[] data) {
        if (data.length != 3) {
            logger.warn("Línea de datos inválida para secuaz");
            return null;
        }
        return new Secuaz(data[0], data[1], data[2]);
    }

    private Pista crearPista(String[] data) {
        return switch (data[0]) {
            case "GASTRONOMIA" -> new PistaGastronomia(data[1], data[2], data[3], data[4]);
            case "GEOGRAFIA" -> new PistaGeografia(data[1], data[2], data[3], data[4]);
            case "HISTORIA" -> new PistaHistoria(data[1], data[2], data[3], data[4]);
            case "LEYENDA" -> new PistaLeyenda(data[1], data[2], data[3], data[4]);
            case "TURISMO" -> new PistaTurismo(data[1], data[2], data[3], data[4]);
            default -> {
                logger.warn("Tipo de pista desconocido: " + data[0]);
                yield null;
            }
        };
    }

    private boolean validarLocalidad(Localidad localidad) {
        if (localidad == null || localidad.getNombre() == null || localidad.getNombre().isEmpty()) {
            logger.warn("Nombre de localidad inválido");
            return false;
        }
        if (localidad.getLatitud() < -90 || localidad.getLatitud() > 90) {
            logger.warn("Latitud inválida para la localidad: " + localidad.getNombre());
            return false;
        }
        if (localidad.getLongitud() < -180 || localidad.getLongitud() > 180) {
            logger.warn("Longitud inválida para la localidad: " + localidad.getNombre());
            return false;
        }
        return true;
    }

    private boolean validarPista(Pista pista) {
        if (pista == null || pista.getDescripcion() == null || pista.getDescripcion().isEmpty()) {
            logger.warn("Pista inválida");
            return false;
        }
        return true;
    }

    private boolean validarSecuaz(Secuaz secuaz) {
        if (secuaz == null || secuaz.getNombre() == null || secuaz.getNombre().isEmpty()) {
            logger.warn("Nombre de secuaz inválido");
            return false;
        }
        return true;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            config.getProperty("db.url"),
            config.getProperty("db.user"),
            config.getProperty("db.password")
        );
    }
}
