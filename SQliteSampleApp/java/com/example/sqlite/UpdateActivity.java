package com.example.sqlite;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input,date_input,location_input,des_input;
    Button update_button, delete_buton;

    String id, name, date, location, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.myWh)));

        name_input = findViewById(R.id.name_input2);
        date_input = findViewById(R.id.date_input2);
        location_input = findViewById(R.id.location_input2);
        des_input = findViewById(R.id.des_input2);

        update_button = findViewById(R.id.update_button);
        delete_buton  = findViewById(R.id.delete_button);

        getAndSetIntentData();


        ActionBar ab = getSupportActionBar();
        if(ab != null){
            ab.setTitle(name);
        }


        update_button.setOnClickListener((view) -> {


                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                name = name_input.getText().toString().trim();
                date = date_input.getText().toString().trim();
                location = location_input.getText().toString().trim();
                description = des_input.getText().toString().trim();
                myDB.updateData(id,name,date,location,description);


        });

        delete_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmDialog();

            }
        });




    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("date") && getIntent().hasExtra("location") && getIntent().hasExtra("description")){

            // GET
            id = getIntent().getStringExtra("id");
            name= getIntent().getStringExtra("name");
            date = getIntent().getStringExtra("date");
            location = getIntent().getStringExtra("location");
            description = getIntent().getStringExtra("description");

            // SET
            name_input.setText(name);
            date_input.setText(date);
            location_input.setText(location);
            des_input.setText(description);

        } else{
            Toast.makeText(this,"No data.",Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are U sure to Delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);

                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

}
