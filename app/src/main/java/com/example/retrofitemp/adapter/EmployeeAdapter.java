package com.example.retrofitemp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.retrofitemp.R;
import com.example.retrofitemp.model.Employees;
import com.example.retrofitemp.view.EmployeeActivty;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<Employees> {

    private Context context;
    private List<Employees> emp;

    public EmployeeAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Employees> objects) {
        super(context, resource, objects);
        this.context = context;
        this.emp = objects;
    }

    @NonNull
    @Override
    public View getView(final int pos, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_emp, parent, false);

        TextView txtUserId = (TextView) rowView.findViewById(R.id.txtUserId);
        TextView txtUsername = (TextView) rowView.findViewById(R.id.txtUsername);

        txtUserId.setText(String.format("#ID: %d", emp.get(pos).getId()));
        txtUsername.setText(String.format("USER NAME: %s", emp.get(pos).getFirstName()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start Activity User Form
                Intent intent = new Intent(context, EmployeeActivty.class);
                intent.putExtra("user_id", String.valueOf(emp.get(pos).getId()));
                intent.putExtra("first", emp.get(pos).getFirstName());
                intent.putExtra("last", emp.get(pos).getLastName());
                intent.putExtra("email", emp.get(pos).getEmail());
                context.startActivity(intent);
            }
        });
        return rowView;
    }
}
