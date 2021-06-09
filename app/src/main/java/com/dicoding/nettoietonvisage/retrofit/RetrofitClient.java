package com.dicoding.nettoietonvisage.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofitClient = null;
    public static Retrofit getClient(){
        if (retrofitClient == null){
            retrofitClient = new Retrofit.Builder()
                    .baseUrl("http://34.67.93.220:80")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofitClient;
    }
}
