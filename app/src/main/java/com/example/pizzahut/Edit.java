package com.example.pizzahut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Edit extends AppCompatActivity {
    private TextView hedding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        hedding = findViewById(R.id.hedding);
        hedding.setText("CART");
    }

    public void onButtonClick3(View v){
        Intent myIntent = new Intent(getBaseContext(), Cart.class);
        startActivity(myIntent);
    }
}
