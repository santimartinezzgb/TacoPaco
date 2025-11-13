// language: java
package com.example.tacopaco.activities;

import static com.example.tacopaco.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    private static final String TAG = "Carta";

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

        int mesaId = getIntent().getIntExtra("mesaId", -1);

        // Precios unitarios de cada producto
        double precioTacos = 7.99;
        double precioNachos = 6.99;
        double precioQuesadillas = 6.50;
        double precioTamales = 7.50;
        double precioBurritos = 9.99;

        TextView precio = findViewById(R.id.precio);

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

        // Inicializar cantidades a 0
        cantidadTacos.setText("0");
        cantidadNachos.setText("0");
        cantidadQuesadillas.setText("0");
        cantidadTamal.setText("0");
        cantidadBurrito.setText("0");

        // Cantidades
        AtomicInteger totalTacos = new AtomicInteger();
        AtomicInteger totalNachos = new AtomicInteger();
        AtomicInteger totalQuesadillas = new AtomicInteger();
        AtomicInteger totalTamales = new AtomicInteger();
        AtomicInteger totalBurritos = new AtomicInteger();

        // Función para actualizar el precio total
        AtomicReference<Double> totalActual = new AtomicReference<>(0.0);
        Runnable actualizarTotal = () -> {
            double total = (totalTacos.get() * precioTacos) +
                    (totalNachos.get() * precioNachos) +
                    (totalQuesadillas.get() * precioQuesadillas) +
                    (totalTamales.get() * precioTamales) +
                    (totalBurritos.get() * precioBurritos);
            totalActual.set(total);
            precio.setText(String.format("Precio: " + total +"€"));
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

        // Actualiza el total al iniciar la actividad
        actualizarTotal.run();

        // Envía el total a la APP-Escritorio al pulsar
        Api api = RetrofitClient.getInstance().getApi();
        pagar.setOnClickListener(v -> {

            // Obtener el total actual
            double totalPedido = totalActual.get();

            if(totalPedido > 0) {

                // Crear un nuevo pedido con el total
                Pedido nuevoPedido = new Pedido(totalPedido, null);
                // Enviar el pedido a la API
                Call<Pedido> call = api.guardarPedido(nuevoPedido);

                // Manejar la respuesta de la API
                call.enqueue(new Callback<Pedido>() {
                    @Override
                    public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                        if (response.isSuccessful()) {
                            System.out.println("Pedido guardado en APP-Escritorio");
                        } else {
                            System.out.println("Error al guardar pedido: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<Pedido> call, Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

                Toast.makeText(getApplicationContext(), "Pedido realizado por " + totalPedido + " €", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Carta.this, EleccionMesa.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Pedido descartado", Toast.LENGTH_SHORT).show();
            }

        });

        cancelar.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Pedido cancelado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Carta.this, EleccionMesa.class);
            startActivity(intent);
        });
    }
}
