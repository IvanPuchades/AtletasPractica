import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.time.LocalDate;
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

        //Creamos un atleta nuevo y lo mostramos
        Atleta atleta = new Atleta("Oskar", "Rodriguez", "española", LocalDate.of(1995, 12, 5));
        Response<Atleta> createAtleta = atletaService.createAtleta(atleta).execute();
        Atleta responseCreateAtleta = null;
        if (createAtleta.isSuccessful()){
            //hacemos el update del atleta creado
            responseCreateAtleta = createAtleta.body();
            responseCreateAtleta.setNombre("Nombre cambiado");
            System.out.println("Estado del codigo: "+createAtleta.code()+" Atleta: "+createAtleta.body());
            Response<Atleta> updateAtleta = atletaService.updateAtleta(responseCreateAtleta).execute();
            if (updateAtleta.isSuccessful()){
                System.out.println("Estado del codigo: "+updateAtleta.body());
                System.out.println("Atleta "+updateAtleta.body()+" Modificado con exito ");
                Response<Atleta> deleteAtleta = atletaService.deleteAtleta(updateAtleta).execute();
            }



        }else {
            System.out.println("Estado del codigo: "+createAtleta.code()+ "Mensaje de error: "+createAtleta.errorBody());
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
