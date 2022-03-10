package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HiringAPI {

    //We annotate the method with @GET to tell retrofit we are doing a GET Request
    //The string within the GET Request, will retrieve it from the URL location
    //we are getting back a List of the hiring data
    @GET("hiring.json")
    Call<List<Hiring>> getHiring();

}
