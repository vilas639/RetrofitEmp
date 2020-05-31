package com.example.retrofitemp.service;

import com.example.retrofitemp.model.Employees;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;
   // private static String BASE_URL="http://ec2-13-126-241-226.ap-south-1.compute.amazonaws.com:8080/api/";
    private static String BASE_URL="http://ec2-13-233-126-6.ap-south-1.compute.amazonaws.com:8080/curddemo/api/";

    public static EmployeesService getService(){


        if(retrofit==null){

            retrofit=new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit.create(EmployeesService.class);

    }


}
