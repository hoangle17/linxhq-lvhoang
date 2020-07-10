package com.example.videodemo.API;

import com.example.videodemo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("getuser.php")
    Call<List<User>> GetAllUser();

    @FormUrlEncoded
    @POST("insertuser.php")
    Call<String> InsertUser(@Field("name") String username, @Field("pass")String password);
}
