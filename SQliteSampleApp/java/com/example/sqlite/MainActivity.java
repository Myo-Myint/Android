package com.example.sqlite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton plus_button;

    MyDatabaseHelper myDB;
    ArrayList<String> event_id,event_name,event_date,event_location,event_des;
    CustomAdapter customAdapter ;

    ImageView empty_img;
    TextView no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.myWh)));



        recyclerView = findViewById(R.id.recyclerview);
        plus_button = findViewById(R.id.plus_button);

        empty_img = findViewById(R.id.empty_img);
        no_data = findViewById(R.id.textView);

        plus_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        event_id = new ArrayList<>();
        event_name = new ArrayList<>();
        event_date = new ArrayList<>();
        event_location = new ArrayList<>();
        event_des = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, this,event_id,event_name,event_date,event_location,event_des);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_img.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }
        else{
            while(cursor.moveToNext()){
                event_id.add(cursor.getString(0));
                event_name.add(cursor.getString(1));
                event_date.add(cursor.getString(2));
                event_location.add(cursor.getString(3));
                event_des.add(cursor.getString(4));
            }
            empty_img.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);

        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){


            confirmDialog();


        }

        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete all ?");
        builder.setMessage("Are U sure to Delete All Data ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);

                myDB.deleteAllData();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
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