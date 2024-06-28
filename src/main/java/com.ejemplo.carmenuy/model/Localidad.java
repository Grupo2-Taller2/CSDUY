//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/model/Localidad.java
package com.ejemplo.carmenuy.model;

import java.util.List;

public class Localidad {
    private String id; // Usando String para IDs como A, B, C, AA, AB, etc.
    private String nombre;
    private int poblacion;
    private String narrativa;
    private List<String> conexiones; // IDs de las localidades conectadas

    // Constructor
    public Localidad(String id, String nombre, int poblacion, String narrativa, List<String> conexiones) {
        this.id = id;
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.narrativa = narrativa;
        this.conexiones = conexiones;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public String getNarrativa() {
        return narrativa;
    }

    public void setNarrativa(String narrativa) {
        this.narrativa = narrativa;
    }

    public List<String> getConexiones() {
        return conexiones;
    }

    public void setConexiones(List<String> conexiones) {
        this.conexiones = conexiones;
    }

    // Método para agregar una conexión
    public void agregarConexion(String idLocalidad) {
        if (!conexiones.contains(idLocalidad)) {
            conexiones.add(idLocalidad);
        }
    }

    // Método para eliminar una conexión
    public void eliminarConexion(String idLocalidad) {
        conexiones.remove(idLocalidad);
    }

    @Override
    public String toString() {
        return "Localidad{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", poblacion=" + poblacion +
                ", conexiones=" + conexiones +
                '}';
    }
}
