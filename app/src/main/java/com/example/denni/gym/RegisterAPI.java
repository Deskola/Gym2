package com.example.denni.gym;


import retrofit.Callback;
import retrofit.http.FormUrlEncoded;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.POST;

public interface RegisterAPI {
    @FormUrlEncoded
    @POST("/Register.php")
    public void insertUser(
            @Field("full_name") String full_name,
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password,
            retrofit.Callback<Response> callback);

}
