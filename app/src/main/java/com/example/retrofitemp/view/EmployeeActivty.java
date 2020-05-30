package com.example.retrofitemp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitemp.R;
import com.example.retrofitemp.model.Employees;
import com.example.retrofitemp.service.EmployeesService;
import com.example.retrofitemp.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeActivty extends AppCompatActivity {


    EmployeesService  employeesService;
    EditText edtUId;
    EditText editTextfirstName;
    EditText editTextlastName;
    EditText editTextemail;
    Button btnSave;
    Button btnDel;
    TextView txtUId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_activty);
        setTitle("Employees");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        edtUId = (EditText) findViewById(R.id.editTextid);
        editTextfirstName = (EditText) findViewById(R.id.editTextfirstName);
        editTextlastName = (EditText) findViewById(R.id.editTextlastName);
        editTextemail = (EditText) findViewById(R.id.editTextemail);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDel = (Button) findViewById(R.id.btnDel);

        employeesService=   RetrofitClient.getService();

        Bundle extras = getIntent().getExtras();
        final String userId = extras.getString("user_id");
        String first = extras.getString("first");
        String last = extras.getString("last");
        String email = extras.getString("email");

        edtUId.setText(userId);
        editTextfirstName.setText(first);
        editTextlastName.setText(last);
        editTextemail.setText(email);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employees u = new Employees();
                u.setId(Integer.parseInt(userId));
                u.setFirstName(editTextfirstName.getText().toString());
                u.setLastName(editTextlastName.getText().toString());
                u.setEmail(editTextemail.getText().toString());
                if(userId != null && userId.trim().length() > 0){
                    //update user
                    updateUser(Integer.parseInt(userId), u);
                } else {
                    //add user
                    addUser(u);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(Integer.parseInt(userId));

                Intent intent = new Intent(EmployeeActivty.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }



    public void addUser(Employees u){

        Call<Employees> call =   employeesService.addEmployee(u);

        call.enqueue(new Callback<Employees>() {
            @Override
            public void onResponse(Call<Employees> call, Response<Employees> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EmployeeActivty.this, "User created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Employees> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void updateUser(int id, Employees u){

        Call<Employees> call =    employeesService.updateEmployee(u);

        call.enqueue(new Callback<Employees>() {
            @Override
            public void onResponse(Call<Employees> call, Response<Employees> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EmployeeActivty.this, "User updated successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Employees> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }

    public void deleteUser(int id){
        Call<Employees> call =  employeesService.deleteEmployee(id);

        call.enqueue(new Callback<Employees>() {
            @Override
            public void onResponse(Call<Employees> call, Response<Employees> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EmployeeActivty.this, "User deleted successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Employees> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());

            }
        });
    }

}
