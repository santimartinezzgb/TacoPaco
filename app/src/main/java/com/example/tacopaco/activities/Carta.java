package com.example.tacopaco.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tacopaco.R;
import com.example.tacopaco.clases.Mesa;
import com.example.tacopaco.clases.Pedido;
import com.example.tacopaco.servicios.Api;
import com.example.tacopaco.servicios.RetrofitClient;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        Api api = RetrofitClient.getInstance().getApi();
        String nombreMesa = getIntent().getStringExtra("nombreMesa");

        Button pagar = findViewById(R.id.pagar);
        Button cancelar = findViewById(R.id.cancelar);

        TextView precio = findViewById(R.id.precio);

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

        Button restarTacos = findViewById(R.id.restarTacos);
        Button restarNachos = findViewById(R.id.restarNachos);
        Button restarQuesadillas = findViewById(R.id.restarQuesadillas);
        Button restarTamal = findViewById(R.id.restarTamal);
        Button restarBurrito = findViewById(R.id.restarBurrito);

        // Cantidad productos
        TextView cantidadTacos = findViewById(R.id.cantidadTacos);
        TextView cantidadNachos = findViewById(R.id.cantidadNachos);
        TextView cantidadQuesadillas = findViewById(R.id.cantidadQuesadillas);
        TextView cantidadTamal = findViewById(R.id.cantidadTamal);
        TextView cantidadBurrito = findViewById(R.id.cantidadBurrito);

        // Inicializar cantidades a 0
        cantidadTacos.setText("0");
        cantidadNachos.setText("0");
        cantidadQuesadillas.setText("0");
        cantidadTamal.setText("0");
        cantidadBurrito.setText("0");

        AtomicInteger totalTacos = new AtomicInteger();
        AtomicInteger totalNachos = new AtomicInteger();
        AtomicInteger totalQuesadillas = new AtomicInteger();
        AtomicInteger totalTamales = new AtomicInteger();
        AtomicInteger totalBurritos = new AtomicInteger();
        AtomicReference<Double> totalActual = new AtomicReference<>(0.0);

        Runnable actualizarTotal = () -> {
            double total = (totalTacos.get() * precioTacos)
                    + (totalNachos.get() * precioNachos)
                    + (totalQuesadillas.get() * precioQuesadillas)
                    + (totalTamales.get() * precioTamales)
                    + (totalBurritos.get() * precioBurritos);
            totalActual.set(total);
            precio.setText(String.format("Precio: %.2f €", total));
        };

        // Tacos
        sumarTacos.setOnClickListener(v -> {
            if (totalTacos.get() < 10) {
                totalTacos.getAndIncrement();
                cantidadTacos.setText(String.valueOf(totalTacos.get()));
                actualizarTotal.run();
            } else {
                Toast.makeText(getApplicationContext(), "Cantidad máxima de tacos alcanzada", Toast.LENGTH_SHORT).show();
            }
        });
        restarTacos.setOnClickListener(v -> {
            if (totalTacos.get() > 0) {
                totalTacos.getAndDecrement();
                cantidadTacos.setText(String.valueOf(totalTacos.get()));
                actualizarTotal.run();
            }
        });

        // Nachos
        sumarNachos.setOnClickListener(v -> {
            if (totalNachos.get() < 10) {
                totalNachos.getAndIncrement();
                cantidadNachos.setText(String.valueOf(totalNachos.get()));
                actualizarTotal.run();
            } else {
                Toast.makeText(getApplicationContext(), "Cantidad máxima de nachos alcanzada", Toast.LENGTH_SHORT).show();
            }
        });
        restarNachos.setOnClickListener(v -> {
            if (totalNachos.get() > 0) {
                totalNachos.getAndDecrement();
                cantidadNachos.setText(String.valueOf(totalNachos.get()));
                actualizarTotal.run();
            }
        });

        // Quesadillas
        sumarQuesadillas.setOnClickListener(v -> {
            if (totalQuesadillas.get() < 10) {
                totalQuesadillas.getAndIncrement();
                cantidadQuesadillas.setText(String.valueOf(totalQuesadillas.get()));
                actualizarTotal.run();
            } else {
                Toast.makeText(getApplicationContext(), "Cantidad máxima de quesadillas alcanzada", Toast.LENGTH_SHORT).show();
            }
        });
        restarQuesadillas.setOnClickListener(v -> {
            if (totalQuesadillas.get() > 0) {
                totalQuesadillas.getAndDecrement();
                cantidadQuesadillas.setText(String.valueOf(totalQuesadillas.get()));
                actualizarTotal.run();
            }
        });

        // Tamales
        sumarTamal.setOnClickListener(v -> {
            if (totalTamales.get() < 10) {
                totalTamales.getAndIncrement();
                cantidadTamal.setText(String.valueOf(totalTamales.get()));
                actualizarTotal.run();
            } else {
                Toast.makeText(getApplicationContext(), "Cantidad máxima de tamales alcanzada", Toast.LENGTH_SHORT).show();
            }
        });
        restarTamal.setOnClickListener(v -> {
            if (totalTamales.get() > 0) {
                totalTamales.getAndDecrement();
                cantidadTamal.setText(String.valueOf(totalTamales.get()));
                actualizarTotal.run();
            }
        });

        // Burritos
        sumarBurrito.setOnClickListener(v -> {
            if (totalBurritos.get() < 10) {
                totalBurritos.getAndIncrement();
                cantidadBurrito.setText(String.valueOf(totalBurritos.get()));
                actualizarTotal.run();
            } else {
                Toast.makeText(getApplicationContext(), "Cantidad máxima de burritos alcanzada", Toast.LENGTH_SHORT).show();
            }
        });
        restarBurrito.setOnClickListener(v -> {
            if (totalBurritos.get() > 0) {
                totalBurritos.getAndDecrement();
                cantidadBurrito.setText(String.valueOf(totalBurritos.get()));
                actualizarTotal.run();
            }
        });


        actualizarTotal.run();
        // PAGAR
        pagar.setOnClickListener(v -> {

            double totalPedido = totalActual.get();

            if (totalPedido > 0) {
                Mesa mesaActual = new Mesa(nombreMesa, true);
                Pedido nuevoPedido = new Pedido(totalPedido, mesaActual);

                api.guardarPedido(nuevoPedido).enqueue(new Callback<Pedido>() {
                    @Override
                    public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Pedido realizado, valor: " + totalPedido + " €", Toast.LENGTH_SHORT).show();

                        }
                        Intent intent = new Intent(Carta.this, EleccionMesa.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Pedido> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Pedido vacío", Toast.LENGTH_SHORT).show();
            }
        });

        cancelar.setOnClickListener(v -> {

            if (nombreMesa != null) {
                Mesa mesaLibre = new Mesa(nombreMesa, false);
                api.ocuparMesa(nombreMesa, mesaLibre).enqueue(new Callback<Mesa>() {
                    @Override
                    public void onResponse(Call<Mesa> call, Response<Mesa> response) {
                    }

                    @Override
                    public void onFailure(Call<Mesa> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Fallo de conexión", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
            }

            Toast.makeText(getApplicationContext(), "Pedido cancelado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Carta.this, EleccionMesa.class);
            startActivity(intent);
        });

    }
}
