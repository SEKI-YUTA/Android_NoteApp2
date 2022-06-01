package com.example.noteapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SettingActivity extends AppCompatActivity {
    Button btn_red, btn_blue, btn_green, btn_yellow;
    String selectedColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

       btn_red = findViewById(R.id.btn_red);
       btn_blue = findViewById(R.id.btn_blue);
       btn_green = findViewById(R.id.btn_green);
       btn_yellow = findViewById(R.id.btn_yellow);

        btn_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActionBar actionBar = getSupportActionBar();

                ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FF0000"));
                actionBar.setBackgroundDrawable(colorDrawable);
            }
        });
        btn_blue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ActionBar actionBar = getSupportActionBar();

                        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0000FF"));
                        actionBar.setBackgroundDrawable(colorDrawable);
                    }
                });
        btn_green.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ActionBar actionBar = getSupportActionBar();

                        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#00FF00"));
                        actionBar.setBackgroundDrawable(colorDrawable);
                    }
                });
        btn_yellow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ActionBar actionBar = getSupportActionBar();

                        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#FFEB3B"));
                        actionBar.setBackgroundDrawable(colorDrawable);
                    }
                });


    }
}