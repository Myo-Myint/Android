package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText name_input,date_input,location_input,des_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.myWh)));

        name_input = findViewById(R.id.name_input);
        date_input = findViewById(R.id.date_input);
        location_input = findViewById(R.id.location_input);
        des_input = findViewById(R.id.des_input);

        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addEvent(name_input.getText().toString().trim(),
                        date_input.getText().toString().trim(),
                        location_input.getText().toString().trim(),
                        des_input.getText().toString().trim());




            }
        });



    }
}