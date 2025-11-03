import com.example.tacopaco.Mesa;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    @PUT("mesas/{id}")
    Call<Mesa> ocuparMesa(
            @Path("nombre") String nombre,
            @Body Mesa mesa
    );
}
