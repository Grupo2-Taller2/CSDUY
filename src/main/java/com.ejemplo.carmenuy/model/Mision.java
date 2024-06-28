//CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/model/Mision.java
package com.ejemplo.carmenuy.model;

public class Mision {
    private int id;
    private String titulo;
    private String descripcion;
    private String objetivo;
    private boolean completada;

    public Mision(int id, String titulo, String descripcion, String objetivo) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.completada = false;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }
    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) { this.completada = completada; }
}
