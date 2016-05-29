package com.example.esra.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainPageActivity extends AppCompatActivity {
    ImageButton gotomap;
    ImageButton classrooms;
    ImageButton aboutus;
    ImageButton exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        gotomap = (ImageButton) findViewById(R.id.button);
        classrooms = (ImageButton) findViewById(R.id.button4);
        aboutus = (ImageButton) findViewById(R.id.button2);
        exit = (ImageButton) findViewById(R.id.button3);

        gotomap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        classrooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainPageActivity.this, ClassroomsMainActivity.class);
                startActivity(intent);
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainPageActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
