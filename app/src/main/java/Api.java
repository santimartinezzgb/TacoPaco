import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @GET("menu")
    Call<List<Menu>> obtenerMenu();

    @POST("order/{mesa}")
    Call<Pedido> crearOActualizarPedido(@Path("mesa") int mesa, @Body SolicitarPedido solicitud);

    @GET("order/{mesa}")
    Call<Pedido> obtenerPedido(@Path("mesa") int mesa);

    @POST("pay/{mesa}")
    Call<Map<String, Object>> pagarPedido(@Path("mesa") int mesa);
}
