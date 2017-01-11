import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.print.AttributeException;
import java.io.IOException;
import java.util.List;

/**
 * Created by usu32 on 19/12/2016.
 */
public class RestSynchronous {

    private static Retrofit retrofit;

    public static void main(String[] args) throws IOException {

        retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AtletaService atletaService = retrofit.create(AtletaService.class);
        Call<List<Atleta>> callAllAtleta = atletaService.getAllAtleta();
        Response<List<Atleta>> response = callAllAtleta.execute();
        if(response.isSuccessful()){
            List<Atleta> atletasList = response.body();
            System.out.println("Estado del codigo: " + response.code() + System.lineSeparator() +
                    "GET all atletas: " + atletasList);
        }else{
            System.out.println("Estado del codigo: " + response.code() + "Mensaje de error: "+ response.errorBody());
        }



        Call<Atleta> callAtleta = atletaService.getAtleta(2L);
        Response<Atleta> responseAtleta = callAtleta.execute();

        if(responseAtleta.isSuccessful()){
            System.out.println("Estado del codigo: "+responseAtleta.code()+ " Atleta: "+responseAtleta.body());
        }else{
            System.out.println("Estado del codigo: "+responseAtleta.code()+ "Mensaje de error: "+responseAtleta.errorBody());
        }

    }

}
