package com.ejemplo.carmenuy.model;

public class Secuaz {
    
    private String nombre;
    private String habilidad;
    private String descripcion;
    private int peligrosidad;

    public Secuaz(String nombre, String habilidad, String descripcion, int peligrosidad) {
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.descripcion = descripcion;
        this.peligrosidad = peligrosidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPeligrosidad() {
        return peligrosidad;
    }

    public void setPeligrosidad(int peligrosidad) {
        this.peligrosidad = peligrosidad;
    }

    @Override
    public String toString() {
        
        return "Secuaz{" +
                "nombre='" + nombre + '\'' +
                ", habilidad='" + habilidad + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", peligrosidad=" + peligrosidad +
                '}';
    }

    // Creación de los secuaces según el documento proporcionado
    public static Secuaz crearMoonabomber() {
        return new Secuaz("MOONabomber", "Experto en explosivos", "Especialista en fabricar y detonar explosivos con precisión.", 4);
    }

    public static Secuaz crearEllaBella() {
        return new Secuaz("EllaBella", "Experta en estafas", "Maestra del engaño y la manipulación, capaz de ejecutar las estafas más complejas.", 3);
    }

    public static Secuaz crearMindyAnaSon() {
        return new Secuaz("Mindy Ana Son", "Experta en piedras preciosas y arte", "Conocedora de las gemas más raras y las obras de arte más valiosas.", 5);
    }

    public static Secuaz crearBetosecreto() {
        return new Secuaz("Betosecreto", "Entrenado en artes Ninjas", "Peligroso en combate cuerpo a cuerpo y experto en tácticas de sigilo.", 6);
    }
}
