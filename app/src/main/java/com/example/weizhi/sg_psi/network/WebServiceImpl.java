package com.example.weizhi.sg_psi.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.weizhi.sg_psi.network.response.PsiJson;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import timber.log.Timber;

/**
 * @author Lin Weizhi (ecc.weizhi@gmail.com)
 */

public class WebServiceImpl implements WebService {
    public static final int API_VERSION = 1;
    public static final String API_KEY = "X8Z3mrlg3HAbp9Vk5dglhHPeUfhIah1J";
    public static final String BASE_URL = "https://api.data.gov.sg/";
    private final RetrofitWs webService;
    private final ObjectMapper mapper;

    public WebServiceImpl(String baseUrl){
        mapper = new ObjectMapper();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        webService = retrofit.create(RetrofitWs.class);
    }

    @NonNull
    @Override
    public NetworkResponse<PsiJson> getPsi(@Nullable String dateTime, @Nullable String date) {
        Call<PsiJson> call = webService.getPsi(API_VERSION, API_KEY, dateTime, date);

        PsiJson payload = null;
        Integer httpStatusCode = null;
        Throwable error = null;
        try {
            retrofit2.Response<PsiJson> response = call.execute();
            if(response.isSuccessful()){
                payload = response.body();
            }
            httpStatusCode = response.code();
        } catch (IOException e) {
            Timber.e(e, "IOE while executing getPsi");
            error = e;
        }

        return new NetworkResponse<>(payload, httpStatusCode, error);
    }

    private interface RetrofitWs{
        @GET("v{apiVersion}/environment/psi")
        Call<PsiJson> getPsi(@Path("apiVersion") int apiVersion,
                             @Header("api-key") String apiKey,
                             @Query("date_time") String date_time,
                             @Query("date") String date);
    }
}
