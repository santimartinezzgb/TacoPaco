package com.example.tacopaco;

import static android.view.View.GONE;
import static com.example.tacopaco.R.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Carta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.carta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.carta), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button tacos = findViewById(R.id.volver);
        tacos.setOnClickListener(v -> {
            Intent intent = new Intent(Carta.this, EleccionMesa.class);
            startActivity(intent);
        });

        Button pagar = findViewById(R.id.pagar);
        Button cancelar = findViewById(id.cancelar);

        // Precios unitarios de cada producto
        double precioTacos = 7.99;
        double precioNachos = 6.99;
        double precioQuesadillas = 6.50;
        double precioTamales = 7.50;
        double precioBurritos = 9.99;

        // Botones de sumar y restar productos
        Button sumarTacos = findViewById(R.id.sumarTacos);
        Button sumarNachos = findViewById(R.id.sumarNachos);
        Button sumarQuesadillas = findViewById(R.id.sumarQuesadillas);
        Button sumarTamal = findViewById(R.id.sumarTamal);
        Button sumarBurrito = findViewById(R.id.sumarBurrito);

        Button restarTacos = findViewById(id.restarTacos);
        Button restarNachos = findViewById(id.restarNachos);
        Button restarQuesadillas = findViewById(id.restarQuesadillas);
        Button restarTamal = findViewById(id.restarTamal);
        Button restarBurrito = findViewById(id.restarBurrito);

        // Cantidad productos
        TextView cantidadTacos = findViewById(R.id.cantidadTacos);
        TextView cantidadNachos = findViewById(R.id.cantidadNachos);
        TextView cantidadQuesadillas = findViewById(R.id.cantidadQuesadillas);
        TextView cantidadTamal = findViewById(R.id.cantidadTamal);
        TextView cantidadBurrito = findViewById(R.id.cantidadBurrito);

        // Cantidades
        AtomicInteger totalTacos = new AtomicInteger(Integer.parseInt(cantidadTacos.getText().toString()));
        AtomicInteger totalNachos = new AtomicInteger(Integer.parseInt(cantidadNachos.getText().toString()));
        AtomicInteger totalQuesadillas = new AtomicInteger(Integer.parseInt(cantidadQuesadillas.getText().toString()));
        AtomicInteger totalTamales = new AtomicInteger(Integer.parseInt(cantidadTamal.getText().toString()));
        AtomicInteger totalBurritos = new AtomicInteger(Integer.parseInt(cantidadBurrito.getText().toString()));

        // Eventos sumar/restar productos
        sumarTacos.setOnClickListener(v -> {
            totalTacos.getAndIncrement();
            cantidadTacos.setText(String.valueOf(totalTacos.get()));
        });
        restarTacos.setOnClickListener(v -> {
            if (totalTacos.get() > 0) {
                totalTacos.getAndDecrement();
                cantidadTacos.setText(String.valueOf(totalTacos.get()));
            }
        });

        sumarNachos.setOnClickListener(v -> {
            totalNachos.getAndIncrement();
            cantidadNachos.setText(String.valueOf(totalNachos.get()));
        });
        restarNachos.setOnClickListener(v -> {
            if (totalNachos.get() > 0) {
                totalNachos.getAndDecrement();
                cantidadNachos.setText(String.valueOf(totalNachos.get()));
            }
        });

        sumarQuesadillas.setOnClickListener(v -> {
            totalQuesadillas.getAndIncrement();
            cantidadQuesadillas.setText(String.valueOf(totalQuesadillas.get()));
        });
        restarQuesadillas.setOnClickListener(v -> {
            if (totalQuesadillas.get() > 0) {
                totalQuesadillas.getAndDecrement();
                cantidadQuesadillas.setText(String.valueOf(totalQuesadillas.get()));
            }
        });

        sumarTamal.setOnClickListener(v -> {
            totalTamales.getAndIncrement();
            cantidadTamal.setText(String.valueOf(totalTamales.get()));
        });
        restarTamal.setOnClickListener(v -> {
            if (totalTamales.get() > 0) {
                totalTamales.getAndDecrement();
                cantidadTamal.setText(String.valueOf(totalTamales.get()));
            }
        });

        sumarBurrito.setOnClickListener(v -> {
            totalBurritos.getAndIncrement();
            cantidadBurrito.setText(String.valueOf(totalBurritos.get()));
        });
        restarBurrito.setOnClickListener(v -> {
            if (totalBurritos.get() > 0) {
                totalBurritos.getAndDecrement();
                cantidadBurrito.setText(String.valueOf(totalBurritos.get()));
            }
        });

        // Calcular total a pagar
        pagar.setOnClickListener(v -> {
            double total = (totalTacos.get() * precioTacos) +
                    (totalNachos.get() * precioNachos) +
                    (totalQuesadillas.get() * precioQuesadillas) +
                    (totalTamales.get() * precioTamales) +
                    (totalBurritos.get() * precioBurritos);

            Intent intent = new Intent(Carta.this, EleccionMesa.class);
            startActivity(intent);
        });

        cancelar.setOnClickListener(v -> {

            Intent cancelar_y_volver = new Intent(Carta.this, EleccionMesa.class);
            startActivity(cancelar_y_volver);
        });
    }
}
