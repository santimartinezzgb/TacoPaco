package com.example.tacopaco;

public class Mesa {
    private String nombre;
    private boolean ocupada;

    public Mesa(String nombre, boolean ocupada) {
        this.nombre = nombre;
        this.ocupada = ocupada;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
}
