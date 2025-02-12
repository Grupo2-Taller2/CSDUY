  //CarmenSandiegoUruguay/src/test/java/com/ejemplo/carmenuy/dao/UsuarioDAOTest.java
package com.ejemplo.carmenuy.dao;

import com.ejemplo.carmenuy.model.Usuario;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class UsuarioDAOTest {
    private UsuarioDAO usuarioDAO;
    private Connection connection;

    @Before
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:test_database.db");
        usuarioDAO = new UsuarioDAO(connection);
        usuarioDAO.crearTabla();
    }

    @After
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testInsertarUsuario() throws SQLException {
        Usuario usuario = new Usuario("John", "Doe", "password");
        usuarioDAO.insertarUsuario(usuario);
        Usuario usuarioObtenido = usuarioDAO.obtenerUsuarioPorNombreYContrasena("John", "password");
        assertNotNull("El usuario no debería ser null", usuarioObtenido);
        assertEquals("El nombre del usuario debería ser John", "John", usuarioObtenido.getNombre());
        assertEquals("El apellido del usuario debería ser Doe", "Doe", usuarioObtenido.getApellido());
    }

    @Test
    public void testObtenerUsuarioInexistente() throws SQLException {
        Usuario usuarioObtenido = usuarioDAO.obtenerUsuarioPorNombreYContrasena("NoExiste", "password");
        assertNull("El usuario debería ser null para un nombre inexistente", usuarioObtenido);
    }

    @Test
    public void testActualizarUsuario() throws SQLException {
        Usuario usuario = new Usuario("Jane", "Doe", "password");
        usuarioDAO.insertarUsuario(usuario);
        usuario.setApellido("Smith");
        usuarioDAO.actualizarUsuario(usuario);
        Usuario usuarioActualizado = usuarioDAO.obtenerUsuarioPorNombreYContrasena("Jane", "password");
        assertNotNull("El usuario actualizado no debería ser null", usuarioActualizado);
        assertEquals("El apellido actualizado debería ser Smith", "Smith", usuarioActualizado.getApellido());
    }

    @Test
    public void testEliminarUsuario() throws SQLException {
        Usuario usuario = new Usuario("DeleteMe", "Test", "password");
        usuarioDAO.insertarUsuario(usuario);
        usuarioDAO.eliminarUsuario(usuario.getId());
        Usuario usuarioEliminado = usuarioDAO.obtenerUsuarioPorNombreYContrasena("DeleteMe", "password");
        assertNull("El usuario eliminado debería ser null", usuarioEliminado);
    }

    @Test(expected = SQLException.class)
    public void testInsertarUsuarioDuplicado() throws SQLException {
        Usuario usuario1 = new Usuario("Duplicate", "User", "password");
        Usuario usuario2 = new Usuario("Duplicate", "User", "differentPassword");
        usuarioDAO.insertarUsuario(usuario1);
        usuarioDAO.insertarUsuario(usuario2); // Esto debería lanzar una SQLException
    }
}
