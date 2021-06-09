package com.dicoding.nettoietonvisage.retrofit;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IUploadAPI {

    @Multipart
    @POST("/mob/detectionv2")
    Call<String> UploadFile (@Part MultipartBody.Part file);
}
