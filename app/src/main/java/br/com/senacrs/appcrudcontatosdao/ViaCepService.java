package br.com.senacrs.appcrudcontatosdao;

import java.util.List;

import br.com.senacrs.appcrudcontatosdao.model.Address;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ViaCepService {

    @GET("ws/{cep}/json/")
    Call<Address> detailCep(@Path("cep") String cep);

//    @GET("ws/{state}/{city}/{street}/json/")
//    Call<List<Address>> searchInStreets(
//            @Path("state") String state,
//            @Path("city") String city,
//            @Path("street") String street);

}
