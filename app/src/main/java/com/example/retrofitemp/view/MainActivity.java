package com.example.retrofitemp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.retrofitemp.R;
import com.example.retrofitemp.adapter.EmployeeAdapter;
import com.example.retrofitemp.model.Employees;
import com.example.retrofitemp.service.EmployeesService;
import com.example.retrofitemp.service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAddUser;
    Button btnGetUsersList;
    ListView listView;

    EmployeesService employeesService;

    List<Employees> list = new ArrayList<Employees>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Retrofit 2 CRUD Demo");

        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        btnGetUsersList = (Button) findViewById(R.id.btnGetUsersList);
        listView = (ListView) findViewById(R.id.listView);


        employeesService=   RetrofitClient.getService();


        btnGetUsersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get users listuser_id
                getUsersList();
            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EmployeeActivty.class);
                intent.putExtra("first", "");
                startActivity(intent);
            }
        });

    }

    public void getUsersList(){
        Call<List<Employees>> call =   employeesService.getEmployees();

        call.enqueue(new Callback<List<Employees>>() {
            @Override
            public void onResponse(Call<List<Employees>> call, Response<List<Employees>> response) {
                if(response.isSuccessful()){
                    list = response.body();
                    listView.setAdapter(new EmployeeAdapter(MainActivity.this, R.layout.list_emp, list));
                }
            }

            @Override
            public void onFailure(Call<List<Employees>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }
}
