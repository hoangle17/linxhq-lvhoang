package com.example.videodemo.API;

import com.example.videodemo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    @GET("getuser.php")
    Call<List<User>> GetAllUser();
}
