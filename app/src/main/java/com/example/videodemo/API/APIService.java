package com.example.videodemo.API;

public class APIService {
    private static String base_url = "https://appvideoapi.000webhostapp.com/Server/";

    public static DataService getService(){
        return APIRetrofitClient.getClient(base_url).create(DataService.class);
    }
}
