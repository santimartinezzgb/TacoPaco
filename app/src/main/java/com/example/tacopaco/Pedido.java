package com.example.tacopaco;

public class Pedido {
    private double precioTotal;
    private Mesa mesa;

    public Pedido() {}

    public Pedido(double precioTotal, Mesa mesa) {
        this.precioTotal = precioTotal;
        this.mesa = mesa;
    }

}
