package com.example.tacopaco.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tacopaco.R;
import com.example.tacopaco.clases.Mesa;
import com.example.tacopaco.servicios.Api;
import com.example.tacopaco.servicios.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EleccionMesa extends AppCompatActivity {
    ArrayList<Button> BOTONES = new ArrayList<>();
    Api api;

    @SuppressLint("UseCompatLoadingForDrawables")
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

        api = RetrofitClient.getInstance().getApi();

        Button boton_volver = findViewById(R.id.volver);
        Button btn_actualizar = findViewById(R.id.actualizar);

        Button boton_mesa1 = findViewById(R.id.mesa1);
        Button boton_mesa2 = findViewById(R.id.mesa2);
        Button boton_mesa3 = findViewById(R.id.mesa3);
        Button boton_mesa4 = findViewById(R.id.mesa4);
        Button boton_mesa5 = findViewById(R.id.mesa5);

        BOTONES.add(boton_mesa1);
        BOTONES.add(boton_mesa2);
        BOTONES.add(boton_mesa3);
        BOTONES.add(boton_mesa4);
        BOTONES.add(boton_mesa5);

        btn_actualizar.setOnClickListener(v -> recreate());

        boton_volver.setOnClickListener(v -> {
            Intent intent = new Intent(EleccionMesa.this, MainActivity.class);
            startActivity(intent);
        });

        // Obtener mesas del backend
        Call<List<Mesa>> call = api.getMesas();
        call.enqueue(new Callback<List<Mesa>>() {
            @Override
            public void onResponse(Call<List<Mesa>> call, Response<List<Mesa>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Mesa> mesas = response.body();

                    for (int i = 0; i < mesas.size() && i < BOTONES.size(); i++) {
                        Mesa mesa = mesas.get(i);
                        Button boton = BOTONES.get(i);
                        boton.setText(mesa.getNombre());

                        if (mesa.isOcupada()) {
                            boton.setBackground(getResources().getDrawable(R.drawable.mesa_ocupada));
                            boton.setTextColor(getResources().getColor(R.color.white));
                            boton.setEnabled(false);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Mesa>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        // Marcar mesa ocupada al pulsar
        for (Button boton : BOTONES) {
            boton.setOnClickListener(v -> {
                String nombreMesa = boton.getText().toString();
                Mesa nuevaMesa = new Mesa(nombreMesa, true, 0.0);

                api.ocuparMesa(nombreMesa, nuevaMesa).enqueue(new Callback<Mesa>() {
                    @Override
                    public void onResponse(Call<Mesa> call, Response<Mesa> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(EleccionMesa.this, Carta.class);
                            intent.putExtra("nombreMesa", nombreMesa);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Mesa> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            });
        }

    }

}
