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

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button sumarTacos = findViewById(R.id.sumarTacos);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button restarTacos = findViewById(id.restarTacos);
        @SuppressLint({"WrongViewCast", "MissingInflatedId", "LocalSuppress"})
        TextView cantidadTacos = findViewById(R.id.cantidadTacos);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button pagar = findViewById(R.id.pagar);

        ArrayList cuentaTotal = new ArrayList<>();

        // AtomicInteger para manejar la cantidad de tacos en una sola variable
        AtomicInteger cantidad = new AtomicInteger(Integer.parseInt(cantidadTacos.getText().toString()));
        if(cantidad==0){
           pagar.getVisibility() ;
        }

        sumarTacos.setOnClickListener(v -> {;
            cantidad.getAndIncrement();
            cantidadTacos.setText(String.valueOf(cantidad.get()));
        });
        restarTacos.setOnClickListener(v -> {
            if (cantidad.get() > 0) {
                cantidad.getAndDecrement();
                cantidadTacos.setText(String.valueOf(cantidad.get()));
            }
        });

        pagar.setOnClickListener(v -> {
            double cantidadTotalTacos = Integer.parseInt(String.valueOf(cantidadTacos)) * 7.99;
            cuentaTotal.add(cantidadTotalTacos);

            Intent intent = new Intent(Carta.this, EleccionMesa.class);
            startActivity(intent);
        });


    }
}