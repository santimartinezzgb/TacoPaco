import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String URL_BASE = "http://10.0.2.2:3000/";
    private final Api api;

    private RetrofitClient() {
        OkHttpClient cliente = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(cliente)
                .build();

        api = retrofit.create(Api.class);
    }

}
