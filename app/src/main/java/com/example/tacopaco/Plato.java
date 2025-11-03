package com.example.tacopaco;

public class Plato {
    public String nombre;
    public int contador;
    public Plato(){
        this.contador = 0;
    }

    public void pedir(){
        this.contador +=1;
    }
    public void restarPedido(){
        this.contador -= 1;
    }

    public void cancelarPedido(){
        this.contador = 0;
    }
}
