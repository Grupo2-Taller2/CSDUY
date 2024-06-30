package com.ejemplo.carmenuy.model.dto;

/**
 *
 * @author diego
 */

// DTO = Data Transfer Object: almacenan los datos de las tablas de la base de datos
// Ejemplo: si tenemos una tabla usuario, entonces tenemos un objeto usuarioDTO con los mismos campos
public class LocalidadDTO {

    private int id;
    private String nombre;
    private String descripcion;

    public LocalidadDTO() {
    }

    public LocalidadDTO(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
