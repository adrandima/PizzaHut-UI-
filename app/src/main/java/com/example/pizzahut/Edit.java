package com.example.pizzahut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    public void onButtonClick3(View v){
        Intent myIntent = new Intent(getBaseContext(), Cart.class);
        startActivity(myIntent);
    }
}
