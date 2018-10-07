package br.com.senacrs.appcrudcontatosdao;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ControlLifeCycle extends Application {

    public static ViaCepService service;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ViaCepService.class);
    }
}
