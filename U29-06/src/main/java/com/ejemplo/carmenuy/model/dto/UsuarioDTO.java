package com.ejemplo.carmenuy.model.dto;


public class UsuarioDTO {
    
    
    // se supone que estos son los campos de la tabla 'usuario'
    private int id;
    private String nombre;
    private String apellido;
    private String contrasena;
    private String email;

    public UsuarioDTO() {
    }

    public UsuarioDTO(int id, String nombre, String apellido, String conrasena, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = conrasena;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
}
