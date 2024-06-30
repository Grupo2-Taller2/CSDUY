package com.ejemplo.carmenuy.service;

import com.ejemplo.carmenuy.model.dao.UsuarioDAO;
import com.ejemplo.carmenuy.model.dto.UsuarioDTO;
import java.sql.SQLException;
import java.util.List;

// otra vez faltan las entidades (represenciones de las tablas)
public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void crearTabla() {
        try {
            usuarioDAO.crearTabla();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Se debe almacenar el DTO no el DAO (el DAO hace operaciones)
    // public void registrarUsuario(UsuarioDAO usuario) {
    public void registrarUsuario(UsuarioDTO usuario) {
        try {
            usuarioDAO.insertarUsuario(usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public UsuarioDTO obtenerUsuarioPorId(int id) {
//        try {
//            return usuarioDAO.obtenerUsuarioPorId(id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public UsuarioDTO obtenerUsuarioPorNombreYContrasena(String nombre, String contrasena) {
//        try {
//            return usuarioDAO.obtenerUsuarioPorNombreYContrasena(nombre, contrasena);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
//        try {
//            return usuarioDAO.obtenerTodosLosUsuarios();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public void actualizarUsuario(UsuarioDTO usuario) {
        try {
            usuarioDAO.actualizarUsuario(usuario);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(int id) {
        try {
            usuarioDAO.eliminarUsuario(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
