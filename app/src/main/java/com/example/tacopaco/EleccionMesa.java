package com.example.tacopaco;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EleccionMesa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.eleccion_mesa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.eleccionmesa), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button boton_entrar = findViewById(R.id.volver);
        Button boton_mesa1 = findViewById(R.id.mesa1);
        Button boton_mesa2 = findViewById(R.id.mesa2);
        Button boton_mesa3 = findViewById(R.id.mesa3);
        Button boton_mesa4 = findViewById(R.id.mesa4);
        Button boton_mesa5 = findViewById(R.id.mesa5);

        boton_entrar.setOnClickListener(v -> {
            Intent intent = new Intent(EleccionMesa.this, MainActivity.class);
            startActivity(intent);
        });

        boton_mesa1.setOnClickListener(v -> {
            Intent intent = new Intent(EleccionMesa.this, Carta.class);
            startActivity(intent);
        });
        boton_mesa2.setOnClickListener(v -> {
            Intent intent = new Intent(EleccionMesa.this, Carta.class);
            startActivity(intent);
        });
        boton_mesa3.setOnClickListener(v -> {
            Intent intent = new Intent(EleccionMesa.this, Carta.class);
            startActivity(intent);
        });
        boton_mesa4.setOnClickListener(v -> {
            Intent intent = new Intent(EleccionMesa.this, Carta.class);
            startActivity(intent);
        });
        boton_mesa5.setOnClickListener(v -> {
            Intent intent = new Intent(EleccionMesa.this, Carta.class);
            startActivity(intent);
        });


    }
}