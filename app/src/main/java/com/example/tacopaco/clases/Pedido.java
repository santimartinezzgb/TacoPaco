package com.example.tacopaco.clases;

public class Pedido {

    private String _id;
    private double precioTotal;
    private String fecha;
    private Mesa mesa;


    public Pedido(double precioTotal, Mesa mesa) {
        this.precioTotal = precioTotal;
        this.mesa = mesa;
    }

}
