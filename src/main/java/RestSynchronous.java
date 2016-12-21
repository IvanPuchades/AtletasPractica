import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

/**
 * Created by usu32 on 19/12/2016.
 */
public class RestSynchronous {

    private static Retrofit retrofit;

    public static void main(String[] args) {

        retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AtletaService atletaService = retrofit.create(AtletaService.class);
        Call<List<Atleta>> callAllAtleta = atletaService.getAllAtleta();

    }

}
