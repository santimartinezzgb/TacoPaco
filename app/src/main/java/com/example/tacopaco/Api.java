package com.example.tacopaco;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    @GET("mesas")
    Call<List<Mesa>> getMesas();
    @Headers("Content-Type: application/json")
    @PUT("mesas/{nombre}")
    Call<Mesa> ocuparMesa(
            @Path("nombre") String nombre,
            @Body Mesa mesa
    );

    @Headers("Content-Type: application/json")
    @POST("pedidos")
    Call<Pedido> guardarPedido(@Body Pedido pedido);
}
