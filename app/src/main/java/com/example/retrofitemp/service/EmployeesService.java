package com.example.retrofitemp.service;

import com.example.retrofitemp.model.Employees;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeesService {

    @GET("employees")
    Call<List<Employees>> getEmployees();

    @POST("employees")
    Call<Employees> addEmployee(@Body Employees employees);

    @PUT("employees")
    Call<Employees> updateEmployee(@Body Employees employees);

    @DELETE("employees/{id}")
    Call<Employees> deleteEmployee(@Path("id") int id);
}
