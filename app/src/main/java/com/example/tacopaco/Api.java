package com.example.tacopaco;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    @PUT("mesas/{nombre}")
    Call<Mesa> ocuparMesa(
            @Path("nombre") String nombre,
            @Body Mesa mesa);

}
