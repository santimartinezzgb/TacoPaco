package com.example.tacopaco.clases;

import java.util.concurrent.atomic.AtomicInteger;

public class Mesa {
    private String nombre;
    private boolean ocupada;
    public double pedido;


    public Mesa(String nombre, boolean ocupada, double pedido) {
        this.nombre = nombre;
        this.ocupada = ocupada;
        this.pedido = pedido;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isOcupada() {
        return ocupada;
    }

}
