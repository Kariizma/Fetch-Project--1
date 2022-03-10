package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HiringAPI {
    @GET("hiring.json")
    Call<List<Hiring>> getHiring();

}
