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

import com.google.android.gms.common.api.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        Button boton_volver = findViewById(R.id.volver);
        Button boton_mesa1 = findViewById(R.id.mesa1);
        Button boton_mesa2 = findViewById(R.id.mesa2);
        Button boton_mesa3 = findViewById(R.id.mesa3);
        Button boton_mesa4 = findViewById(R.id.mesa4);
        Button boton_mesa5 = findViewById(R.id.mesa5);

        Mesa mesa1 = new Mesa("mesa1",false);
        Mesa mesa2 = new Mesa("mesa2",false);
        Mesa mesa3 = new Mesa("mesa3",false);
        Mesa mesa4 = new Mesa("mesa4",false);
        Mesa mesa5 = new Mesa("mesa5",false);

        Api api = RetrofitClient.getInstance().getApi();

        boton_volver.setOnClickListener(v -> {
            Intent intent = new Intent(EleccionMesa.this, MainActivity.class);
            startActivity(intent);
        });

        boton_mesa1.setOnClickListener(v -> {

            Call<Mesa> call = api.ocuparMesa();

            call.enqueue(new Callback<Mesa>() {
                @Override
                public void onResponse(Call<Mesa> call, Response<Mesa> response) {
                    if (response.isSuccessful()) {
                        System.out.println("Mesa ocupada correctamente");
                    } else {
                        System.out.println("Error al ocupar la mesa");
                    }
                }

                @Override
                public void onFailure(Call<Mesa> call, Throwable throwable) {
                    throwable.printStackTrace();
                }
            });

            Intent intent = new Intent(EleccionMesa.this, Carta.class);
            startActivity(intent);
        });
        boton_mesa2.setOnClickListener(v -> {
            mesa2.ocupada = true;
            Intent intent = new Intent(EleccionMesa.this, Carta.class);
            startActivity(intent);
        });
        boton_mesa3.setOnClickListener(v -> {
            mesa3.ocupada = true;
            Intent intent = new Intent(EleccionMesa.this, Carta.class);
            startActivity(intent);
        });
        boton_mesa4.setOnClickListener(v -> {
            mesa4.ocupada = true;
            Intent intent = new Intent(EleccionMesa.this, Carta.class);
            startActivity(intent);
        });
        boton_mesa5.setOnClickListener(v -> {
            mesa5.ocupada = true;
            Intent intent = new Intent(EleccionMesa.this, Carta.class);
            startActivity(intent);
        });





    }
}